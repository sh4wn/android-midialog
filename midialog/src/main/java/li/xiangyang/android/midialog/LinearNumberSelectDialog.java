package li.xiangyang.android.midialog;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bac on 16/5/11.
 */
public class LinearNumberSelectDialog extends SelectDialog {

    private IListener listener;
    private List<String> items = new ArrayList<>();

    public LinearNumberSelectDialog(Context context, IListener lis, String title, String uint, int start, int end, int step, int selection) {
        super(context, null, title, uint);
        this.listener = lis;
        super.setListener(new SelectDialog.IListener() {
            @Override
            public void onCancel() {
                if (LinearNumberSelectDialog.this.listener != null) {
                    LinearNumberSelectDialog.this.listener.onCancel();
                }
            }

            @Override
            public void onDone(int selection) {
                if (listener != null) {
                    listener.onDone(Integer.parseInt(items.get(selection)));
                }
            }
        });
        for (int i = start; i < end; i += step) {
            items.add(i + "");
        }
        super.setItems(items, selection);
    }

    public static interface IListener {
        void onCancel();

        void onDone(int number);
    }
}
