package li.xiangyang.android.midialog;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import li.xiangyang.android.adapter.CommonListAdapter;
import li.xiangyang.android.midialog.view.BouncingListView;

/**
 * Created by bac on 16/5/9.
 */
public class Select3Dialog extends BaseDialog implements CommonListAdapter.ViewFormater<String> {

    private TextView txtTitle;

    private BouncingListView listFirst;
    private BouncingListView listSecond;
    private BouncingListView listThird;

    private IListener listener;

    private List<String> itemsFirst;
    private List<String> itemsSecond;
    private List<String> itemsThird;
    private CommonListAdapter<String> adapterFirst;
    private CommonListAdapter<String> adapterSecond;
    private CommonListAdapter<String> adapterThird;


    private int firstSelection;
    private int secondSelectin;
    private int thirdSelelction;

    private int itemTextColor;

    public Select3Dialog(Context context, IListener listener, String title) {
        this(context, listener, title, R.color.midialog_select_color,R.drawable.midialog_select_box);

    }

    public Select3Dialog(Context context, IListener listener, String title, int itemTextColor,int bgImage) {
        super(context, R.layout.midialog_select3);
        this.itemTextColor = itemTextColor;

        this.listener = listener;

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        listFirst = (BouncingListView) findViewById(R.id.listFirst);
        listSecond = (BouncingListView) findViewById(R.id.listSecond);
        listThird = (BouncingListView) findViewById(R.id.listThird);

        ((ImageView)findViewById(R.id.imgBG)).setImageResource(bgImage);

        final BouncingListView.OnChangeListener lis = new BouncingListView.OnChangeListener() {
            @Override
            public void onChange(BouncingListView listView, int selection) {
                int index = 0;
                if (listView == listFirst) {
                    index = 1;
                    firstSelection = selection;
                    adapterFirst.notifyDataSetChanged();
                } else if (listView == listSecond) {
                    index = 2;
                    secondSelectin = selection;
                    adapterSecond.notifyDataSetChanged();
                } else if (listView == listThird) {
                    index = 3;
                    thirdSelelction = selection;
                    adapterThird.notifyDataSetChanged();
                }
                if (Select3Dialog.this.listener != null) {
                    Select3Dialog.this.listener.onChange(index, selection);
                }
            }
        };
        listFirst.setOnChangeListener(lis);
        listSecond.setOnChangeListener(lis);
        listThird.setOnChangeListener(lis);

        setTitle(title);

        initEvents();
    }

    public Select3Dialog(Context context, IListener listener, String title, List<String> itemsFirst, List<String> itemsSecond, List<String> itemsThird) {
        this(context, listener, title, R.color.midialog_select_color,R.drawable.midialog_select_box, itemsFirst, itemsSecond, itemsThird);
    }

    public Select3Dialog(Context context, IListener listener, String title, int itemTextColor,int bgImage, List<String> itemsFirst, List<String> itemsSecond, List<String> itemsThird) {
        this(context, listener, title, itemTextColor,bgImage);

        setItems(itemsFirst, 0);
        setItems2(itemsSecond, 0);
        setItems3(itemsThird, 0);

    }

    private void setTitle(String title) {
        if (title != null) {
            txtTitle.setText(title);
        }
    }

    public void setUints(String uintFirst, String uintSecond, String uintThird) {

        TextView txt;
        if (uintFirst != null) {
            txt = ((TextView) findViewById(R.id.txtUintFirst));
            txt.setText(uintFirst);
            txt.setTextColor(getContext().getResources().getColorStateList(itemTextColor).getColorForState(new int[]{android.R.attr.state_selected}, Color.parseColor("#FF0000")));
        }
        if (uintSecond != null) {
            txt = ((TextView) findViewById(R.id.txtUintSecond));
            txt.setText(uintSecond);
            txt.setTextColor(getContext().getResources().getColorStateList(itemTextColor).getColorForState(new int[]{android.R.attr.state_selected}, Color.parseColor("#FF0000")));
        }
        if (uintThird != null) {
            txt = ((TextView) findViewById(R.id.txtUintThird));
            txt.setText(uintThird);
            txt.setTextColor(getContext().getResources().getColorStateList(itemTextColor).getColorForState(new int[]{android.R.attr.state_selected}, Color.parseColor("#FF0000")));
        }

    }

    public void setItems(List<String> sources, int selection) {
        if (sources == null || sources.size() == 0) {
            listFirst.setVisibility(View.GONE);
            return;
        } else {
            listFirst.setVisibility(View.VISIBLE);
        }
        if (selection < 0) {
            selection = 0;
        } else if (selection >= sources.size()) {
            selection = sources.size() - 1;
        }

        List<String> items = new ArrayList<>(sources.size() + 4);
        items.addAll(sources);

        itemsFirst = items;
        firstSelection = selection;
        items.add(0, "");
        items.add(0, "");
        items.add("");
        items.add("");
        adapterFirst = new CommonListAdapter<String>(getContext(), itemsFirst, null, R.layout.midialog_item_select, 0, this);
        listFirst.setVisibility(View.VISIBLE);
        listFirst.setItemHeight(dp2px(40));
        listFirst.setAdapter(adapterFirst);
        listFirst.setSelection(firstSelection);
    }

    public void setItems2(List<String> sources, int selection) {

        if (sources == null || sources.size() == 0) {
            listSecond.setVisibility(View.GONE);
            findViewById(R.id.lineSecond).setVisibility(View.GONE);
            findViewById(R.id.rlSecond).setVisibility(View.GONE);
            return;
        } else {

            listSecond.setVisibility(View.VISIBLE);
            findViewById(R.id.lineSecond).setVisibility(View.VISIBLE);
            findViewById(R.id.rlSecond).setVisibility(View.VISIBLE);
        }
        if (selection < 0) {
            selection = 0;
        } else if (selection >= sources.size()) {
            selection = sources.size() - 1;
        }
        List<String> items = new ArrayList<>(sources.size() + 4);
        items.addAll(sources);

        itemsSecond = items;
        secondSelectin = selection;
        items.add(0, "");
        items.add(0, "");
        items.add("");
        items.add("");
        adapterSecond = new CommonListAdapter<String>(getContext(), itemsSecond, null, R.layout.midialog_item_select, 0, this);
        listSecond.setVisibility(View.VISIBLE);
        listSecond.setItemHeight(dp2px(40));
        listSecond.setAdapter(adapterSecond);
        listSecond.setSelection(secondSelectin);
    }

    public void setItems3(List<String> sources, int selection) {

        if (sources == null || sources.size() == 0) {
            listThird.setVisibility(View.GONE);
            findViewById(R.id.lineThird).setVisibility(View.GONE);
            findViewById(R.id.rlThird).setVisibility(View.GONE);
            return;
        } else {
            listThird.setVisibility(View.VISIBLE);
            findViewById(R.id.lineThird).setVisibility(View.VISIBLE);
            findViewById(R.id.rlThird).setVisibility(View.VISIBLE);
        }

        if (selection < 0) {
            selection = 0;
        } else if (selection >= sources.size()) {
            selection = sources.size() - 1;
        }

        List<String> items = new ArrayList<>(sources.size() + 4);
        items.addAll(sources);

        itemsThird = items;
        thirdSelelction = selection;

        items.add(0, "");
        items.add(0, "");
        items.add("");
        items.add("");
        adapterThird = new CommonListAdapter<String>(getContext(), itemsThird, null, R.layout.midialog_item_select, 0, this);
        listThird.setVisibility(View.VISIBLE);
        listThird.setItemHeight(dp2px(40));
        listThird.setAdapter(adapterThird);
        listThird.setSelection(thirdSelelction);
    }


    public void setListener(IListener listener) {
        this.listener = listener;
    }

    private void initEvents() {

        findViewById(R.id.box).setClickable(true);
        final View.OnClickListener lis = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!listFirst.isScrolling() && !listSecond.isScrolling() && !listThird.isScrolling()) {
                    if (view.getId() == R.id.btnLeft) {
                        onCancel();
                    } else {
                        if (listener != null) {
                            listener.onDone(firstSelection, secondSelectin, thirdSelelction);
                        }
                    }
                    dismiss();
                }
            }
        };
        findViewById(R.id.btnLeft).setOnClickListener(lis);
        findViewById(R.id.btnRight).setOnClickListener(lis);

    }

    @Override
    protected void onCancel() {
        if (listener != null) {
            listener.onCancel();
        }
    }

    @Override
    public void formatItemView(CommonListAdapter<String> adapter, String item, View view, int index, boolean group) {

        int select = 0;
        if (adapter == adapterFirst) {
            select = firstSelection;
        } else if (adapter == adapterSecond) {
            select = secondSelectin;
        } else if (adapter == adapterThird) {
            select = thirdSelelction;
        }

        ViewHolder vh = (ViewHolder) view.getTag();
        if (vh == null) {
            TextView txtItem = (TextView) view.findViewById(R.id.txtItem);
            vh = new ViewHolder(txtItem);
            txtItem.setTextColor(getContext().getResources().getColorStateList(itemTextColor));
            view.setTag(vh);
        }
        vh.txt.setText(item);

        if (index == select + 2) {
            vh.txt.setTextSize(dp2px(7));
            vh.txt.setSelected(true);
        } else {
            vh.txt.setSelected(false);
            vh.txt.setTextSize(dp2px(5));
        }
    }

    private class ViewHolder {
        TextView txt;

        public ViewHolder(TextView txt) {
            this.txt = txt;
        }
    }

    public static interface IListener {
        void onCancel();

        void onChange(int index, int selection);

        void onDone(int first, int second, int third);
    }
}
