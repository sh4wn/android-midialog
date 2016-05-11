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

    private int currentYear;
    private int currentMonth = 1;
    private int currentDay = 1;

    public DatePickerDialog(Context context, final IListener listener, String title, int year, final int month, int day) {
        super(context, null, title);
        currentYear = year;
        currentMonth = month;
        currentDay = day;
        setListener(new Select3Dialog.IListener() {
            @Override
            public void onCancel() {
                if (listener != null) {
                    listener.onCancel();
                }
            }

            @Override
            public void onChange(int index, int selection) {
                if (index == 1) {
                    currentYear = Integer.parseInt(years.get(selection));
                } else if (index == 2) {
                    currentMonth = Integer.parseInt(months.get(selection));
                    initDays();

                } else if (index == 3) {
                    currentDay = Integer.parseInt(days.get(selection));
                }
            }

            @Override
            public void onDone(int first, int second, int third) {
                if (listener != null) {
                    listener.onDone(Integer.parseInt(years.get(first)), Integer.parseInt(months.get(second)), Integer.parseInt(days.get(third)));
                }
            }
        });

        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = thisYear - 80; i <= thisYear; i++) {
            years.add(i + "");
        }

        for (int i = 1; i <= 12; i++) {
            months.add(i + "");
        }

        for (int i = 1; i <= 31; i++) {
            days.add(i + "");
        }

        setItems(years, years.indexOf("" + currentYear));
        setItems2(months, months.indexOf("" + currentMonth));
        initDays();

        setUints("年", "月", "日");
    }

    private void initDays() {

        int totalDay;
        switch (currentMonth) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                totalDay = 31;
                break;
            case 2:
                if (currentYear % 4 == 0 && currentYear % 100 != 0 || currentYear % 400 == 0) {
                    totalDay = 29;
                } else {
                    totalDay = 28;
                }
                break;
            default:
                totalDay = 30;
                break;
        }

        days.clear();
        for (int i = 1; i <= totalDay; i++) {
            days.add(i + "");
        }
        int selection = days.indexOf("" + currentDay);
        setItems3(days, selection > 0 ? selection : 0);
    }

    public static interface IListener {
        void onCancel();

        void onDone(int year, int month, int day);
    }
}
