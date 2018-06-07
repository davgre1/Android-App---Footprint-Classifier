package davgre1.a2p18cfp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by david f greene on 12/25/2017.
 */

public class DatabaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    public DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpener(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public String[] getQuotes() {
        SQLiteDatabase database = openHelper.getReadableDatabase();
        String selectQuery =  "SELECT  EMAIL" + " FROM user_profile";

        ArrayList<String> emailList = new ArrayList<String>();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                emailList.add(cursor.getString(cursor.getColumnIndex("EMAIL")));
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
        return emailList.toArray(new String[emailList.size()]);
    }


    public UserProfile getByUserProfile(String email, Integer year){
        SQLiteDatabase database = openHelper.getReadableDatabase();
        String selectQuery =  "SELECT  *" + " FROM user_profile"
                + " WHERE " + UserProfile.KEY_EMAIL + "=" + email + " AND " + UserProfile.KEY_YEAR_ID + "=" + year;

        UserProfile userProfile = new UserProfile();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                userProfile.ID = cursor.getInt(cursor.getColumnIndex(UserProfile.KEY_ID));
                userProfile.Year_ID = cursor.getInt(cursor.getColumnIndex(UserProfile.KEY_YEAR_ID));
                userProfile.Email = cursor.getString(cursor.getColumnIndex(UserProfile.KEY_EMAIL));
                userProfile.State = cursor.getString(cursor.getColumnIndex(UserProfile.KEY_STATE));
                userProfile.mean = cursor.getFloat(cursor.getColumnIndex(UserProfile.KEY_MEAN));
                userProfile.max = cursor.getFloat(cursor.getColumnIndex(UserProfile.KEY_MAX));
                userProfile.min = cursor.getFloat(cursor.getColumnIndex(UserProfile.KEY_MIN));
                userProfile.q1mean = cursor.getFloat(cursor.getColumnIndex(UserProfile.KEY_Q1MEAN));
                userProfile.q2mean = cursor.getFloat(cursor.getColumnIndex(UserProfile.KEY_Q2MEAN));
                userProfile.q3mean = cursor.getFloat(cursor.getColumnIndex(UserProfile.KEY_Q3MEAN));
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
        return userProfile;
    }


    public AnnualFootprint getByUserID(String email, Integer year){
        SQLiteDatabase database = openHelper.getReadableDatabase();
        String selectQuery =  "SELECT  *" + " FROM annual_footprint"
                + " INNER JOIN user_profile "
                + " ON annual_footprint.ID = user_profile.ID AND annual_footprint.YEAR_ID = user_profile.YEAR_ID"
                + " WHERE user_profile.EMAIL " + "=" + email + " AND annual_footprint.YEAR_ID " + "=" + year;

        AnnualFootprint annualFootprint = new AnnualFootprint();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                annualFootprint.ID = cursor.getInt(cursor.getColumnIndex(AnnualFootprint.KEY_ID));
                annualFootprint.Year_ID = cursor.getInt(cursor.getColumnIndex(AnnualFootprint.KEY_YEAR_ID));
                annualFootprint.Email = cursor.getString(cursor.getColumnIndex(AnnualFootprint.KEY_EMAIL));
                annualFootprint.jan = cursor.getFloat(cursor.getColumnIndex(AnnualFootprint.KEY_JAN));
                annualFootprint.feb = cursor.getFloat(cursor.getColumnIndex(AnnualFootprint.KEY_FEB));
                annualFootprint.mar = cursor.getFloat(cursor.getColumnIndex(AnnualFootprint.KEY_MAR));
                annualFootprint.apr = cursor.getFloat(cursor.getColumnIndex(AnnualFootprint.KEY_APR));
                annualFootprint.may = cursor.getFloat(cursor.getColumnIndex(AnnualFootprint.KEY_MAY));
                annualFootprint.jun = cursor.getFloat(cursor.getColumnIndex(AnnualFootprint.KEY_JUN));
                annualFootprint.jul = cursor.getFloat(cursor.getColumnIndex(AnnualFootprint.KEY_JUL));
                annualFootprint.aug = cursor.getFloat(cursor.getColumnIndex(AnnualFootprint.KEY_AUG));
                annualFootprint.sep = cursor.getFloat(cursor.getColumnIndex(AnnualFootprint.KEY_SEP));
                annualFootprint.oct = cursor.getFloat(cursor.getColumnIndex(AnnualFootprint.KEY_OCT));
                annualFootprint.nov = cursor.getFloat(cursor.getColumnIndex(AnnualFootprint.KEY_NOV));
                annualFootprint.dec = cursor.getFloat(cursor.getColumnIndex(AnnualFootprint.KEY_DEC));
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
        return annualFootprint;
    }

}
