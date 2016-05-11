package li.xiangyang.android.midialog;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by bac on 16/5/10.
 */
public class DatePickerDialog extends Select3Dialog {

    private List<String> years = new ArrayList<>(80);
    private List<String> months = new ArrayList<>(12);
    private List<String> days = new ArrayList<>(31);


    public DatePickerDialog(Context context, final IListener listener, String title) {
        super(context, null, title);

        setListener(new Select3Dialog.IListener() {
            @Override
            public void onCancel() {
                if (listener != null) {
                    listener.onCancel();
                }
            }

            @Override
            public void onDone(int first, int second, int third) {
                if (listener != null) {
                    listener.onDone(Integer.parseInt(years.get(first)), Integer.parseInt(months.get(second)), Integer.parseInt(days.get(third)));
                }
            }
        });

        int year = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = year - 80; i <= year; i++) {
            years.add(i + "");
        }

        for (int i = 1; i <= 12; i++) {
            months.add(i + "");
        }

        for (int i = 1; i <= 31; i++) {
            days.add(i + "");
        }

        setItems(years, 60);
        setItems2(months, 0);
        setItems3(days, 0);

        setUints("年", "月", "日");

    }

    private void set() {

    }

    public static interface IListener {
        void onCancel();

        void onDone(int year, int month, int day);
    }
}
