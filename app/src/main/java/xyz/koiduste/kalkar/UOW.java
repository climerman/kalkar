package xyz.koiduste.kalkar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by marko on 4/16/16.
 */
public class UOW {

    // Database fields
    private SQLiteDatabase db;
    private SQLiteHelper helper;

    private final Context context;

    public OperandRepo operandRepo;
    public StatsRepo statsRepo;
    public OperationRepo operationRepo;

    public UOW(Context context){

        this.context = context;

        helper = new SQLiteHelper(context);
        db = helper.getWritableDatabase();
        operandRepo = new OperandRepo(db, helper.TABLE_OPERANDS, helper.ALLCOLUMNS_OPERANDS);
        statsRepo = new StatsRepo(db, helper.TABLE_STATS, helper.ALLCOLUMNS_STATS, operandRepo);
        operationRepo = new OperationRepo(db, helper.TABLE_OPERATIONS, helper.ALLCOLUMNS_OPERATIONS, operandRepo);
    }

    public void DropCreateDatabase(){
        helper.dropCreateDatabase(db);
    }


    public void newStatsRow(String op, Float num1, Float num2, Float result) {
        Operand operandObj = operandRepo.getByOperandName(op);
        operandObj.setLifetimeCounter(operandObj.getLifetimeCounter()+1);
        operandRepo.update(operandObj);

        //statsRepo.addOneToDayCounter(operandObj.getId());

        Operation operation = new Operation();
        operation.setOperandId(operandObj.getId());
        operation.setNum1(num1);
        operation.setNum2(num2);
        operation.setResult(result);
        operation.setTimestamp(System.currentTimeMillis());
        operationRepo.add(operation);
    }
}
