package davgre1.a2p18cfp;

/**
 * Created by david f greene on 12/24/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private SignInButton sIgnInButton; // Signing button
    private GoogleSignInOptions googleSignInOptions; // Signing Options
    private GoogleApiClient mGoogleApiClient; // google api client

    private static String _email = "";
    private Integer[] _year = {2016, 2017, 2018};
    private float totalMean, totalq1Mean, totalq2Mean, totalq3Mean = 0.0f;
    public float individual_footprint = 0.0f;

    private TableLayout field_table;
    private TextView title, field1, field2, field3, field4, field5, field6, field7, field8, field9, field10, field11, field12;

    private MenuItem signOut;
    private PieChart chart;

    private ArrayList <Integer> year = new ArrayList <Integer>(3);
    private ArrayList <Float> mean = new ArrayList <Float>(3);
    private ArrayList <Float> max = new ArrayList <Float>(3);
    private ArrayList <Float> min = new ArrayList <Float>(3);
    private ArrayList <Float> q1Mean = new ArrayList <Float>(3);
    private ArrayList <Float> q2Mean = new ArrayList <Float>(3);
    private ArrayList <Float> q3Mean = new ArrayList <Float>(3);
    private ArrayList <Float> q1 = new ArrayList <Float>(3);


    @Override // Setting up the display
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //************************************//

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        title = (TextView) findViewById(R.id.textView_Title);
        field1 = (TextView) findViewById(R.id.textView_1);
        field2 = (TextView) findViewById(R.id.textView_2);
        field3 = (TextView) findViewById(R.id.textView_3);
        field4 = (TextView) findViewById(R.id.textView_4);
        field5 = (TextView) findViewById(R.id.textView_5);
        field6 = (TextView) findViewById(R.id.textView_6);
        field7 = (TextView) findViewById(R.id.textView_7);
        field8 = (TextView) findViewById(R.id.textView_8);
        field9 = (TextView) findViewById(R.id.textView_9);
        field10 = (TextView) findViewById(R.id.textView_10);
        field11 = (TextView) findViewById(R.id.textView_11);
        field12 = (TextView) findViewById(R.id.textView_12);
        field_table = (TableLayout) findViewById(R.id.field_table);
        chart = (PieChart) findViewById(R.id.idChart);

        //************************************//

        // Set VISIBLITY
        field_table.setVisibility(View.GONE);
        chart.setVisibility(View.GONE);

        //************************************//

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Initializing signinbutton
        sIgnInButton = (SignInButton) findViewById(R.id.sign_in_button);
        sIgnInButton.setSize(SignInButton.SIZE_WIDE);
        sIgnInButton.setScopes(googleSignInOptions.getScopeArray());
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApiIfAvailable(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
        // Setting onclick listener to signing button
        sIgnInButton.setOnClickListener(this);

    }

    public void emailValidation(String email) {

        DatabaseAccess repo = new DatabaseAccess(this);

        String[] quotes = repo.getQuotes(); // Takes list of emails from DatabaseAccess()

        List<String> list = Arrays.asList(quotes); // Populates List with emails

        if(list.contains(email)) {
            sIgnInButton.setVisibility(View.GONE); // Sign in button is now invisible
            signOut.setVisible(true); // Sign out button is now visible
            title.setVisibility(View.GONE);

            int index = email.indexOf('@');
            String display_email = email.substring(0,index);

            setTitle("Welcome, " + display_email); // Sets actionbar to email name!

            Log.i("EMAIL? ", "YES");
            Toast.makeText(this, "Email found in our database", Toast.LENGTH_LONG).show();

            _email = ("'" + email + "'").toString(); // Formatting email

            getPerson_Stats(_email); // Sends formatted email to method
        } else {
            Log.i("EMAIL? ", "NO");
            Toast.makeText(this, "No Email found in our database", Toast.LENGTH_LONG).show();
        }

    }

    // Display a quick summary of the users footprint statistics
    public void getPerson_Stats(String email) {

        DatabaseAccess repo = new DatabaseAccess(this);
        UserProfile userProfile = new UserProfile();

        for(int i = 0; i < 3; i++){
            userProfile = repo.getByUserProfile(email, _year[i]);

            year.add(userProfile.getYear_ID());
            mean.add((float) userProfile.getMean());
            max.add((float) userProfile.getMax());
            min.add((float) userProfile.getMin());
            q1Mean.add((float) userProfile.getQ1mean());
            q2Mean.add((float) userProfile.getQ2mean());
            q3Mean.add((float) userProfile.getQ3mean());

            Float sum = new Float(0);
            for (Float ii : mean) { sum = sum + ii; }
            totalMean = sum / mean.size();

            Float sum1 = new Float(0);
            for (Float iii : q1Mean) { sum1 = sum1 + iii; }
            totalq1Mean = sum1 / q1Mean.size();

            Float sum2 = new Float(0);
            for (Float aa : q2Mean) { sum2 = sum2 + aa; }
            totalq2Mean = sum2 / q3Mean.size();

            Float sum3 = new Float(0);
            for (Float bb : q3Mean) { sum3 = sum3 + bb; }
            totalq3Mean = sum3 / q3Mean.size();

        }

        // Set VISIBILITY
        title.setVisibility(View.VISIBLE);
        field_table.setVisibility(View.VISIBLE);
        chart.setVisibility(View.GONE);

        title.setText("Personal Stats");
        field1.setText("Person ID: " + userProfile.getID());
        field2.setText("State: " + userProfile.getState());
        field3.setText("Mean: " + totalMean);
        field4.setText("Max: " + Collections.max((max)));
        field5.setText("Min: " + Collections.min((min)));
        field6.setText("Q1Mean: " + totalq1Mean);
        field7.setText("Q2Mean: " + totalq2Mean);
        field8.setText("Q3Mean: " + totalq3Mean);

    }

    // Displaying users footprint and options to reduce
    public void getFootprint(float ifp) {

        ifp = 0.9712f;

        // Set VISIBILITY
        title.setVisibility(View.VISIBLE);
        field_table.setVisibility(View.VISIBLE);
        chart.setVisibility(View.GONE);

        title.setText("Inidividual Footprint");
        field3.setText("Footprint: " + ifp);

    }

    // Display users annual stats over a 3 year period
    public void getAnnual_Stats(String email) {

        DatabaseAccess repo = new DatabaseAccess(this);
        AnnualFootprint annualFootprint = new AnnualFootprint();

        annualFootprint = repo.getByUserID(email, 2018);

        q1.add((float) annualFootprint.getJan());
        q1.add((float) annualFootprint.getFeb());
        q1.add((float) annualFootprint.getMar());

//        for(int i = 0; i < 3; i++) {
//            annualFootprint = repo.getByUserID(email, _year[i]);
//
//            q1.add((float) annualFootprint.getJan());
//            q1.add((float) annualFootprint.getFeb());
//            q1.add((float) annualFootprint.getMar());
//        }

        // Set VISIBILITY
        title.setVisibility(View.VISIBLE);
        field_table.setVisibility(View.VISIBLE);
        chart.setVisibility(View.GONE);

        title.setText("Annual Foot Print " + annualFootprint.getID() + annualFootprint.getYear_ID() );
        field1.setText("January: " + annualFootprint.getJan());
        field2.setText("February: " + annualFootprint.getFeb() );
        field3.setText("March: " + annualFootprint.getMar() );
        field4.setText("April: " + annualFootprint.getApr() );
        field5.setText("May: " + annualFootprint.getMay() );
        field6.setText("June: " + annualFootprint.getJun() );
        field7.setText("July: " + annualFootprint.getJul() );
        field8.setText("August: " + annualFootprint.getAug() );
        field9.setText("September: " + annualFootprint.getSep() );
        field10.setText("October: " + annualFootprint.getOct() );
        field11.setText("November: " + annualFootprint.getNov() );
        field12.setText("December: " + annualFootprint.getDec() );

    }

    // Display users foot forecast for the next 5 years
    public void getForecast() {

        // Set VISIBILITY
        title.setVisibility(View.VISIBLE);
        field_table.setVisibility(View.VISIBLE);
        chart.setVisibility(View.GONE);

        title.setText("Forecasting your Footprint ");

    }

    // Generates statistic data
    public void getGraph(ArrayList a, ArrayList b, String s) {

        Description desc = chart.getDescription();
        desc.setText("");

        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(Float.parseFloat(a.get(0).toString()), b.get(0).toString()));
        entries.add(new PieEntry(Float.parseFloat(a.get(1).toString()), b.get(1).toString()));
        entries.add(new PieEntry(Float.parseFloat(a.get(2).toString()), b.get(2).toString()));

        PieDataSet dataSet = new PieDataSet(entries, "Results");

        // Set Colors
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.MATERIAL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.MATERIAL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.MATERIAL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        title.setText("Stats");
        PieData data = new PieData(dataSet);

        // Set VISIBILITY
        title.setVisibility(View.VISIBLE);
        field_table.setVisibility(View.GONE);
        chart.setVisibility(View.VISIBLE);

        chart.setData(data);
        chart.invalidate(); // Refresh

    }

    // Clearing ALL data entries
    public void clearData() {

        field1.setText("");
        field2.setText("");
        field3.setText("");
        field4.setText("");
        field5.setText("");
        field6.setText("");
        field7.setText("");
        field8.setText("");
        field9.setText("");
        field10.setText("");
        field11.setText("");
        field12.setText("");
//        mean.removeAll(mean);

    }

    // Navigation View, Tab selects
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {

                case R.id.navigation_dashboard:
                    //TODO
                    clearData();
                    getPerson_Stats(_email);
                    return true;
                case R.id.navigation_foot:
                    //TODO
                    clearData();
                    getFootprint(individual_footprint);
                    return true;
                case R.id.navigation_annual:
                    //TODO
                    clearData();
                    getAnnual_Stats(_email);
                    return true;
                case R.id.navigation_forecast:
                    //TODO
                    clearData();
                    getGraph(q1, year, "Quarter 1");
                    return true;
                case R.id.navigation_graphs:
                    //TODO
                    clearData();
                    getGraph(mean, year, "Mean of...");
                    return true;
            }
            return false;

        }
    };


    @Override // Authentication of Sign In from Google API
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    // After the signing we are calling this function, SECOND
    private void handleSignInResult(GoogleSignInResult result) {
        // If the login succeed
        if (result.isSuccess()) {
            // Getting google account information
            GoogleSignInAccount acct = result.getSignInAccount();
            // Set email
            String email  = new String(acct.getEmail());
            emailValidation(email);
        } else {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override// Signing In, FIRST
    public void onClick(View v) {
        if (v == sIgnInButton) {
            Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
            startActivityForResult(signInIntent, 100);
        }
    }

    @Override // If connection to API fails, show results
    public void onConnectionFailed(ConnectionResult connectionResult) {}

    // Creates action bar for 'Sign Out' button
    public boolean onCreateOptionsMenu(Menu ab) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, ab);
        signOut = ab.findItem(R.id.action_signout);
        return super.onCreateOptionsMenu(ab);
    }

    @Override // If 'Sign Out' is selected, return to Sign In
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            //TODO
            case R.id.action_signout:
                if(mGoogleApiClient.isConnected()) {
                    Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                    mGoogleApiClient.disconnect();
                    mGoogleApiClient.connect();}

                sIgnInButton.setVisibility(View.VISIBLE); // Sign in button is now invisible
                signOut.setVisible(false); // Sign out button is now visible
                title.setVisibility(View.VISIBLE);

                clearData(); // Cleans any data that might have leaked

                break;
        }

        return super.onOptionsItemSelected(item);
    }


}
