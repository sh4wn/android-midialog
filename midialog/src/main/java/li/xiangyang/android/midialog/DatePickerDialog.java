package li.xiangyang.android.midialog;

import android.content.Context;

import java.text.DateFormatSymbols;
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
        this(context,listener,title,year,month,day,Calendar.getInstance().get(Calendar.YEAR));
    }

    public DatePickerDialog(Context context, final IListener listener, String title, int year, final int month, int day,int maxYear) {
        this(context,listener,title,R.color.midialog_select_color,R.drawable.midialog_select_box,year,month,day,maxYear);
    }

    public DatePickerDialog(Context context, final IListener listener, String title,int itemTextColor,int bgImage, int year, final int month, int day,int maxYear) {
        super(context, null, title,itemTextColor,bgImage);
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
                    initDays();
                } else if (index == 2) {
                    currentMonth = selection + 1;
                    initDays();

                } else if (index == 3) {
                    currentDay = Integer.parseInt(days.get(selection));
                }
            }

            @Override
            public void onDone(int first, int second, int third) {
                if (listener != null) {
                    listener.onDone(Integer.parseInt(years.get(first)), second + 1, Integer.parseInt(days.get(third)));
                }
            }
        });

        for (int i = maxYear - 100; i <= maxYear; i++) {
            years.add(i + "");
        }

        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] monthSymbols = dfs.getShortMonths();

        for (int i = 0; i < 12; i++) {
            months.add(formatChineseMonth(monthSymbols[i]));
        }

        setItems(years, years.indexOf(digitalString(currentYear)));
        setItems2(months, currentMonth - 1);
        initDays();

        setUints(context.getString(R.string.midialog_uint_year), context.getString(R.string.midialog_uint_month), context.getString(R.string.midialog_uint_day));
    }

    private String formatChineseMonth(String month) {
        if (month.endsWith("月")) {
            month = month.replace("月", "");
            if (month.length() < 2) {
                month = "0" + month;
            }
        }
        return month;
    }

    private String digitalString(int digital) {
        if (digital < 10) {
            return "0" + digital;
        } else {
            return "" + digital;
        }
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
            days.add(digitalString(i));
        }
        int selection = days.indexOf(digitalString(currentDay));
        selection = selection >= 0 ? selection : days.size() - 1;
        setItems3(days, selection);
        currentDay = Integer.parseInt(days.get(selection));
    }

    public static interface IListener {
        void onCancel();

        void onDone(int year, int month, int day);
    }
}
