package davgre1.a2p18cfp;

/**
 * Created by david f greene on 12/25/2017.
 */

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpener extends SQLiteAssetHelper{

    private static final String DATABASE_NAME = "footprint2.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseOpener(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

}
