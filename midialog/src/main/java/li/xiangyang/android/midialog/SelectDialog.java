package li.xiangyang.android.midialog;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

/**
 * Created by bac on 16/5/11.
 */
public class SelectDialog extends Select3Dialog {
    private IListener mListener;

    public SelectDialog(Context context, IListener listener, String title, String... items) {
        super(context, null, title, Arrays.asList(items), null, null);
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

        mListener = listener;
    }

    public void setUint(String uint) {
        setUints(uint, null, null);
    }

    public static interface IListener {
        void onCancel();

        void onDone(int selection);
    }
}
