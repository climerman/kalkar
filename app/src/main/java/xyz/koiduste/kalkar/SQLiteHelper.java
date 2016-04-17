package xyz.koiduste.kalkar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by marko on 4/15/16.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHelper.class.getName();
    private final Context context;

    public static final String TABLE_OPERANDS = "Operands";
    public static final String COLUMN_OPERANDS_ID = "_id";
    public static final String COLUMN_OPERANDS_OPERAND = "operand";
    public static final String COLUMN_OPERANDS_LIFETIMECOUNTER = "lifetimeCounter";
    public static final String[] ALLCOLUMNS_OPERANDS = {COLUMN_OPERANDS_ID, COLUMN_OPERANDS_OPERAND, COLUMN_OPERANDS_LIFETIMECOUNTER};

    public static final String TABLE_STATS = "Statistics";
    public static final String COLUMN_STATS_ID = "_id";
    public static final String COLUMN_STATS_DAYSTAMP = "timestamp";
    public static final String COLUMN_STATS_OPERAND_ID = "operandId";
    public static final String COLUMN_STATS_DAYCOUNTER = "dayCounter";
    public static final String[] ALLCOLUMNS_STATS = {COLUMN_STATS_ID, COLUMN_STATS_DAYSTAMP, COLUMN_STATS_OPERAND_ID, COLUMN_STATS_DAYCOUNTER};

    public static final String TABLE_OPERATIONS = "Operations";
    public static final String COLUMN_OPERATIONS_ID = "_id";
    public static final String COLUMN_OPERATIONS_OPERAND_ID = "operandId";
    public static final String COLUMN_OPERATIONS_NUM1 = "num1";
    public static final String COLUMN_OPERATIONS_NUM2 = "num2";
    public static final String COLUMN_OPERATIONS_RESULT = "result";
    public static final String COLUMN_OPERATIONS_TIMESTAMP = "timestamp";
    public static final String[] ALLCOLUMNS_OPERATIONS = {COLUMN_OPERATIONS_ID, COLUMN_OPERATIONS_OPERAND_ID, COLUMN_OPERATIONS_NUM1, COLUMN_OPERATIONS_NUM2, COLUMN_OPERATIONS_RESULT, COLUMN_OPERATIONS_RESULT, COLUMN_OPERATIONS_TIMESTAMP};

    //Creation SQL statements

    private static final String DB_CREATE_OPERANDS = "create table"
            + TABLE_OPERANDS + "("
            + COLUMN_OPERANDS_ID + "integer primary key autoincrement, "
            + COLUMN_OPERANDS_OPERAND + "text not null, "
            + COLUMN_OPERANDS_LIFETIMECOUNTER + "integer not null);";


    private static final String DB_CREATE_STATS = "create table"
            + TABLE_STATS + "("
            + COLUMN_STATS_ID + "integer primary key autoincrement, "
            + COLUMN_STATS_DAYSTAMP + "integer not null, "
            + COLUMN_STATS_OPERAND_ID + "integer not null, "
            + COLUMN_STATS_DAYCOUNTER + "integer not null);";

    private static final String DB_CREATE_OPERATIONS = "create table"
            + TABLE_OPERATIONS + "("
            + COLUMN_OPERATIONS_ID + "integer primary key autoincrement, "
            + COLUMN_OPERATIONS_OPERAND_ID + "integer not null, "
            + COLUMN_OPERATIONS_NUM1 + "float not null, "
            + COLUMN_OPERATIONS_NUM2 + "float not null, "
            + COLUMN_OPERATIONS_RESULT + "float not null, "
            + COLUMN_OPERATIONS_TIMESTAMP + "integer not null);";
    //End of creation

    private static final String DB_NAME = "kalkar.db";
    private static final int DB_VERSION = 1;

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    public void dropCreateDatabase(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPERANDS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPERATIONS);

        db.execSQL(DB_CREATE_OPERANDS);
        db.execSQL(DB_CREATE_STATS);
        db.execSQL(DB_CREATE_OPERATIONS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE_OPERANDS);
        db.execSQL(DB_CREATE_STATS);
        db.execSQL(DB_CREATE_OPERATIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG,
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPERANDS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPERATIONS);
        onCreate(db);
    }
}
