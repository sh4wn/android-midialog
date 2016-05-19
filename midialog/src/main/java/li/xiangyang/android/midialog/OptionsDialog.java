package li.xiangyang.android.midialog;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.Arrays;
import java.util.List;

import li.xiangyang.android.adapter.CommonListAdapter;

/**
 * Created by bac on 16/5/9.
 */
public class OptionsDialog extends BaseDialog implements CommonListAdapter.ViewFormater<String> {

    private ListView listView;
    private TextView titleView;

    private List<String> items;
    private int selected;

    private int selectedColor;
    private int defaultColor;

    private IListener listener;

    public OptionsDialog(Context context, IListener listener, String title, String... options) {
        this(context, listener, title, -1, options);
    }

    public OptionsDialog(Context context, final IListener listener, String title, int selected, String... options) {
        super(context, R.layout.midialog_options);

        this.listener = listener;
        this.selected = selected;
        listView = (ListView) findViewById(R.id.listView);
        titleView = (TextView) findViewById(R.id.txtTitle);
        items = Arrays.asList(options);
        listView.setAdapter(new CommonListAdapter<String>(context, items, null, R.layout.midialog_item_option, 0, this));
        setTitle(title);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (listener != null) {
                    listener.onSelected(i, items.get(i));
                }
                dismiss();
            }
        });

        selectedColor = Color.parseColor("#FF6A00");
        defaultColor = Color.parseColor("#333333");
    }

    private void setTitle(String title) {
        if (title != null) {
            titleView.setText(title);
        }
    }

    @Override
    protected void onCancel() {
        if (listener != null) {
            listener.onCancel();
        }
    }

    @Override
    public void formatItemView(CommonListAdapter<String> adapter,String item, View view, int index, boolean group) {
        TextView txt = (TextView) view.findViewById(R.id.txtOption);
        txt.setText(item);
        view.findViewById(R.id.imgSelected).setVisibility(index == selected ? View.VISIBLE : View.INVISIBLE);
        txt.setTextColor(index == selected ? selectedColor : defaultColor);
    }


    public static interface IListener {
        void onCancel();

        void onSelected(int index, String option);
    }

}
