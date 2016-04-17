package xyz.koiduste.kalkar;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private TextView textView;
    private UOW uow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = (ListView) findViewById(R.id.list);
        textView = (TextView) findViewById(R.id.text);
        uow = new UOW(getApplicationContext());
        displayOperationView();
    }


    private void displayOperandView() {
        OperandAdapter adapter = new OperandAdapter(this, uow.operandRepo.getCursorAll(), uow);
        //((ListView) findViewById(R.id.operandViewList)).setAdapter(adapter);
        listView.setAdapter(adapter);
        textView.setText(getString(R.string.operand_header));
    }

    private void displayStatsView() {
        StatsAdapter adapter = new StatsAdapter(this, uow.statsRepo.getCursorAll(), uow);
        //((ListView) findViewById(R.id.statsViewList)).setAdapter(adapter);
        listView.setAdapter(adapter);
        textView.setText(getString(R.string.stats_header));
    }

    private void displayOperationView() {
        OperationAdapter adapter = new OperationAdapter(this, uow.operationRepo.getCursorAll(), uow);
        //((ListView) findViewById(R.id.operationViewList)).setAdapter(adapter);
        listView.setAdapter(adapter);
        textView.setText(getString(R.string.operation_header));
    }

    public void refreshClicked() {
        refreshActivity();
    }

    private void refreshActivity() {

    }

    public void deleteClicked() {
        new AlertDialog.Builder(this)
                .setTitle("Title")
                .setMessage("Kustutan kõik andmed?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        uow.DropCreateDatabase();
                        Toast.makeText(MainActivity.this, "Tehtud", Toast.LENGTH_SHORT).show();
                        refreshActivity();
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_delete) {
            deleteClicked();
        } else if (id == R.id.action_refresh) {
            refreshClicked();
        } else if (id == R.id.action_show_history) {
            displayOperationView();
        } else if (id == R.id.action_show_daysstats) {
            displayStatsView();
        } else if (id == R.id.action_show_operandstats) {
            displayOperandView();
        }
        return super.onOptionsItemSelected(item);
    }
}
