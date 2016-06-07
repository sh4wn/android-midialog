package li.xiangyang.android.midialog;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

/**
 * Created by bac on 16/5/11.
 */
public class Select2Dialog extends Select3Dialog {
    private IListener mListener;

    public Select2Dialog(Context context, IListener listener, String title, String unitLeft, List<String> itemsLeft, String unitRight, List<String> itemsRight) {
        super(context, null, title);
        super.setListener(new Select3Dialog.IListener() {
            @Override
            public void onCancel() {
                if (mListener != null) {
                    mListener.onCancel();
                }
            }

            @Override
            public void onChange(int index, int selection) {
                if (mListener != null) {
                    mListener.onChange(index, selection);
                }
            }

            @Override
            public void onDone(int first, int second, int third) {
                if (mListener != null) {
                    mListener.onDone(first, second);
                }
            }
        });
        this.setUints(unitLeft, unitRight, null);
        mListener = listener;
        setItems3(null,0);
    }

    public Select2Dialog(Context context, IListener o, String title, String unitLeft, String unitRight) {
        this(context, o, title, unitLeft, null, unitRight, null);
    }

    public void setListener(IListener lis) {
        this.mListener = lis;
    }

    public static interface IListener {
        void onCancel();

        void onChange(int index, int selection);

        void onDone(int selection, int selectionRight);
    }
}
