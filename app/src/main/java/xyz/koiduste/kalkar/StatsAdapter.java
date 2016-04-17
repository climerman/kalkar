package xyz.koiduste.kalkar;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by marko on 4/16/16.
 */
public class StatsAdapter extends CursorAdapter{
    private final LayoutInflater layoutInflater;
    private UOW uow;
    private ViewGroup parentViewGroup;

    public StatsAdapter(Context context, Cursor c, UOW uow) {
        super(context, c, 0);
        layoutInflater = LayoutInflater.from(context);
        this.uow = uow;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        final View view=layoutInflater.inflate(R.layout.operation_stats, parent, false);
        parentViewGroup = parent;
        return view;
    }


    // this can be called several times by the system!!!
    // first pass - initial draw, get measurements
    // second pass - final draw
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewName =(TextView) view.findViewById(R.id.statsViewHeader);

        Stats Stats = uow.statsRepo.cursorToEntity(cursor);
        textViewName.setText(Stats.toString());
        //displayOperations(view, context, Stats);
    }


    private void displayOperations(View view, Context context, Stats operand) {
        ListView listView = (ListView) view.findViewById(R.id.operationViewList);
        OperationAdapter adapter = new OperationAdapter(context, uow.operationRepo.getByOperandId(operand.getOperandId()), uow);
        listView.setAdapter(adapter);

    }
}
