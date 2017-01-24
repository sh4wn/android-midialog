package li.xiangyang.android.midialog;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

/**
 * Created by bac on 16/5/11.
 */
public class SelectDialog extends Select3Dialog {
    private IListener mListener;

    public SelectDialog(Context context, Select3Dialog.IListener listener, String title, String uint, IListener mListener) {
        super(context, listener, title);
        this.mListener = mListener;
        this.setUint(uint);
    }
    public SelectDialog(Context context, IListener listener, String title, String uint, String... items) {
        this(context,listener,title,R.color.midialog_select_color,R.drawable.midialog_select_box,uint,items);
    }
    public SelectDialog(Context context, IListener listener, String title,int itemTextColor,int bgImage, String uint, String... items) {
        super(context, null, title,itemTextColor,bgImage, Arrays.asList(items), null, null);
        super.setListener(new Select3Dialog.IListener() {
            @Override
            public void onCancel() {
                if (mListener != null) {
                    mListener.onCancel();
                }
            }

            @Override
            public void onChange(int index, int selection) {

            }

            @Override
            public void onDone(int first, int second, int third) {
                if (mListener != null) {
                    mListener.onDone(first);
                }
            }
        });
        this.setUint(uint);
        mListener = listener;
    }

    public void setListener(IListener lis) {
        this.mListener = lis;
    }

    public void setItems(List<String> items, int selection) {
        super.setItems(items, selection);
    }

    public void setUint(String uint) {
        setUints(uint, null, null);
    }

    public static interface IListener {
        void onCancel();

        void onDone(int selection);
    }
}
