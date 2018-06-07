package davgre1.a2p18cfp;

public class UserProfile {

    public Integer ID, Year_ID;
    public String Email, State;
    public float mean, max, min, q1mean, q2mean, q3mean;

    public static final String KEY_ID = "ID";
    public static final String KEY_YEAR_ID = "YEAR_ID";
    public static final String KEY_EMAIL = "EMAIL";
    public static final String KEY_STATE = "STATE";
    public static final String KEY_MEAN = "MEAN";
    public static final String KEY_MAX = "MAX";
    public static final String KEY_MIN = "MIN";
    public static final String KEY_Q1MEAN = "q1MEAN";
    public static final String KEY_Q2MEAN = "q2MEAN";
    public static final String KEY_Q3MEAN = "q3MEAN";

    public UserProfile() {
    }

    public UserProfile(Integer ID, Integer year_ID, String email, String state, float mean, float max, float min, float q1mean, float q2mean, float q3mean) {
        this.ID = ID;
        Year_ID = year_ID;
        Email = email;
        State = state;
        this.mean = mean;
        this.max = max;
        this.min = min;
        this.q1mean = q1mean;
        this.q2mean = q2mean;
        this.q3mean = q3mean;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getYear_ID() {
        return Year_ID;
    }

    public void setYear_ID(Integer year_ID) {
        Year_ID = year_ID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(float mean) {
        this.mean = mean;
    }

    public double getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public double getQ1mean() {
        return q1mean;
    }

    public void setQ1mean(float q1mean) {
        this.q1mean = q1mean;
    }

    public double getQ2mean() {
        return q2mean;
    }

    public void setQ2mean(float q2mean) {
        this.q2mean = q2mean;
    }

    public double getQ3mean() {
        return q3mean;
    }

    public void setQ3mean(float q3mean) {
        this.q3mean = q3mean;
    }
}
