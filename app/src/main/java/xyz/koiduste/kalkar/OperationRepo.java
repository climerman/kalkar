package xyz.koiduste.kalkar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marko on 4/16/16.
 */
public class OperationRepo extends Repo<Operation> {

    private OperandRepo operandRepo;

    public OperationRepo(SQLiteDatabase db, String tableName, String[] allColumns, OperandRepo operandRepo) {
        this(db, tableName, allColumns);
        this.operandRepo = operandRepo;
    }

    public OperationRepo(SQLiteDatabase db, String tableName, String[] allColumns) {
        super(db, tableName, allColumns);
    }

    @Override
    public Operation cursorToEntity(Cursor cursor) {
        Operation op = new Operation();
        op.setId(cursor.getInt(0));
        op.setOperandId(cursor.getInt(1));
        op.setNum1(cursor.getFloat(2));
        op.setNum2(cursor.getFloat(3));
        op.setResult(cursor.getFloat(4));
        op.setTimestamp(cursor.getInt(5));
        return op;
    }

    @Override
    public ContentValues entityToContentValues(Operation op) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteHelper.COLUMN_OPERATIONS_OPERAND_ID, op.getOperandId());
        contentValues.put(SQLiteHelper.COLUMN_OPERATIONS_NUM1, op.getNum1());
        contentValues.put(SQLiteHelper.COLUMN_OPERATIONS_NUM2, op.getNum2());
        contentValues.put(SQLiteHelper.COLUMN_OPERATIONS_RESULT, op.getResult());
        contentValues.put(SQLiteHelper.COLUMN_OPERATIONS_TIMESTAMP, op.getTimestamp());
        return contentValues;
    }

    public Cursor getByOperandId(int id) {
        List<Stats> stats = new ArrayList<Stats>();
        Cursor cursor = getDb().query(getTableName(), getAllColumns(), SQLiteHelper.COLUMN_OPERATIONS_OPERAND_ID + " = " + id, null, null, null, null);
        cursor.moveToFirst();
        return cursor;
    }

}
