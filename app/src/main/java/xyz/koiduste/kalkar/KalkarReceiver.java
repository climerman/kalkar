package xyz.koiduste.kalkar;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by marko on 4/17/16.
 */
public class KalkarReceiver extends BroadcastReceiver {

    private static final String OPERAND = "OPERAND";
    private static final String RESULT = "RESULT";
    private static final String OPERATOR = "OPERATOR";

    private String result = "0";
    private String operand = "0";
    private String operator = "+";

    private KalkarEngine engine = new KalkarEngineImpl();

    public KalkarReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (isOrderedBroadcast()) {
            // TODO: This method is called when the BroadcastReceiver is receiving
            // an Intent broadcast.
            Bundle bundle = intent.getExtras();
            result = bundle.getString(RESULT);
            operand = bundle.getString(OPERAND);
            operator = bundle.getString(OPERATOR);

            setResultCode(Activity.RESULT_OK);
            result = String.valueOf(engine.getResultFromStringInput(result, operand, operator));
            if (!result.isEmpty()) {
                setResultData(result);
            } else {
                setResultData("1337");
            }
        }
    }
}
