package xyz.koiduste.kalkar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by marko on 4/16/16.
 */
public class StatsRepo extends Repo<Stats>{
    private OperandRepo op;

    public StatsRepo(SQLiteDatabase db, String tableName, String[] allColumns, OperandRepo op) {
        this(db, tableName, allColumns);
        this.op = op;
    }

    public StatsRepo(SQLiteDatabase db, String tableName, String[] allColumns) {
        super(db, tableName, allColumns);
    }

    @Override
    public Stats cursorToEntity(Cursor cursor) {
        Stats stats = new Stats();
        stats.setId(cursor.getInt(0));
        stats.setDaystamp(cursor.getInt(1));
        stats.setOperandId(cursor.getInt(2));
        stats.setDayCounter(cursor.getInt(3));
        return stats;
    }

    @Override
    public ContentValues entityToContentValues(Stats stats) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteHelper.COLUMN_STATS_ID, stats.getId());
        contentValues.put(SQLiteHelper.COLUMN_STATS_DAYSTAMP, stats.getDaystamp());
        contentValues.put(SQLiteHelper.COLUMN_STATS_OPERAND_ID, stats.getOperandId());
        contentValues.put(SQLiteHelper.COLUMN_STATS_DAYCOUNTER, stats.getDayCounter());
        return contentValues;
    }

    public Cursor getCursorByOperandId(int id) {
        Cursor cursor = getDb().query(getTableName(), getAllColumns(), SQLiteHelper.COLUMN_STATS_OPERAND_ID + " = " + id, null, null, null, null);
        cursor.moveToFirst();
        return cursor;
    }
}