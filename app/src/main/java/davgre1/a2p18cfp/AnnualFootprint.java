package davgre1.a2p18cfp;

public class AnnualFootprint {

    public Integer ID, Year_ID;
    public String Email;
    public float jan, feb, mar, apr, may, jun, jul , aug, sep, oct, nov, dec;

    public static final String KEY_ID = "ID";
    public static final String KEY_YEAR_ID = "YEAR_ID";
    public static final String KEY_EMAIL = "EMAIL";
    public static final String KEY_JAN = "JANUARY";
    public static final String KEY_FEB = "FEBRUARY";
    public static final String KEY_MAR = "MARCH";
    public static final String KEY_APR = "APRIL";
    public static final String KEY_MAY = "MAY";
    public static final String KEY_JUN = "JUNE";
    public static final String KEY_JUL = "JULY";
    public static final String KEY_AUG = "AUGUST";
    public static final String KEY_SEP = "SEPTEMBER";
    public static final String KEY_OCT = "OCTOBER";
    public static final String KEY_NOV = "NOVEMBER";
    public static final String KEY_DEC = "DECEMBER";

    public AnnualFootprint() {
    }

    public AnnualFootprint(Integer ID, Integer year_ID, String email, float jan, float feb, float mar, float apr, float may, float jun, float jul, float aug, float sep, float oct, float nov, float dec) {
        this.ID = ID;
        Year_ID = year_ID;
        Email = email;
        this.jan = jan;
        this.feb = feb;
        this.mar = mar;
        this.apr = apr;
        this.may = may;
        this.jun = jun;
        this.jul = jul;
        this.aug = aug;
        this.sep = sep;
        this.oct = oct;
        this.nov = nov;
        this.dec = dec;
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

    public float getJan() {
        return jan;
    }

    public void setJan(float jan) {
        this.jan = jan;
    }

    public float getFeb() {
        return feb;
    }

    public void setFeb(float feb) {
        this.feb = feb;
    }

    public float getMar() {
        return mar;
    }

    public void setMar(float mar) {
        this.mar = mar;
    }

    public float getApr() {
        return apr;
    }

    public void setApr(float apr) {
        this.apr = apr;
    }

    public float getMay() {
        return may;
    }

    public void setMay(float may) {
        this.may = may;
    }

    public float getJun() {
        return jun;
    }

    public void setJun(float jun) {
        this.jun = jun;
    }

    public float getJul() {
        return jul;
    }

    public void setJul(float jul) {
        this.jul = jul;
    }

    public float getAug() {
        return aug;
    }

    public void setAug(float aug) {
        this.aug = aug;
    }

    public float getSep() {
        return sep;
    }

    public void setSep(float sep) {
        this.sep = sep;
    }

    public float getOct() {
        return oct;
    }

    public void setOct(float oct) {
        this.oct = oct;
    }

    public float getNov() {
        return nov;
    }

    public void setNov(float nov) {
        this.nov = nov;
    }

    public float getDec() {
        return dec;
    }

    public void setDec(float dec) {
        this.dec = dec;
    }
}
