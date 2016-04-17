package xyz.koiduste.kalkar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by marko on 4/16/16.
 */
public class OperandRepo extends Repo<Operand> {

    public OperandRepo(SQLiteDatabase db, String tableName, String[] allColumns) {
        super(db, tableName, allColumns);
    }

    @Override
    public Operand cursorToEntity(Cursor cursor) {
        Operand op = new Operand();
        op.setId(cursor.getInt(0));
        op.setOperand(cursor.getString(1));
        op.setLifetimeCounter(cursor.getInt(2));
        return op;
    }

    @Override
    public ContentValues entityToContentValues(Operand op) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteHelper.COLUMN_OPERANDS_OPERAND, op.getOperand());
        contentValues.put(SQLiteHelper.COLUMN_OPERANDS_LIFETIMECOUNTER, op.getLifetimeCounter());
        return contentValues;
    }

    public Operand getByOperandName(String op) {
        Operand newOp;
        Cursor cursor = getDb().query(getTableName(), getAllColumns(), SQLiteHelper.COLUMN_OPERANDS_OPERAND + " = '" + op + "';", null, null, null, null);

        if (cursor == null || cursor.getCount()<=0) {
            Operand tmp = new Operand();
            tmp.setOperand(op);
            tmp.setLifetimeCounter(0);
            newOp = add(tmp);
        } else {
            cursor.moveToFirst();
            newOp = cursorToEntity(cursor);
        }

        return newOp;
    }
}
