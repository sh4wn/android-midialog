package li.xiangyang.android.midialog.samples;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.WindowManager;
import android.widget.NumberPicker;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import li.xiangyang.android.midialog.AlertDialog;
import li.xiangyang.android.midialog.ConfirmDialog;
import li.xiangyang.android.midialog.DatePickerDialog;
import li.xiangyang.android.midialog.InputDialog;
import li.xiangyang.android.midialog.LinearNumberSelect2Dialog;
import li.xiangyang.android.midialog.LinearNumberSelectDialog;
import li.xiangyang.android.midialog.OptionsDialog;
import li.xiangyang.android.midialog.ProgressDialog;
import li.xiangyang.android.midialog.Select2Dialog;

public class MainActivity extends Activity {

    int year = 1991;
    int month = 2;
    int day = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(li.xiangyang.android.midialog.samples.R.layout.activity_main);

        findViewById(R.id.btnProgress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog dialog = new ProgressDialog(MainActivity.this);
                dialog.setCancelable(true);
                dialog.show();
            }
        });
        findViewById(R.id.btnInput).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputDialog dialog = new InputDialog(MainActivity.this);
                dialog.setText("bac");
                dialog.setInputFilter(new InputFilter[]{new HanzLengthInputFilter(10)});
                dialog.show();
            }
        });
        findViewById(R.id.btnGender).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new OptionsDialog(MainActivity.this, new OptionsDialog.IListener() {
                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onSelected(int index, String option) {

                    }
                }, getString(R.string.gender), 0, getString(R.string.male), getString(R.string.female)).show();
            }
        });

        findViewById(R.id.btnConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfirmDialog dialog = new ConfirmDialog(MainActivity.this, null, getString(R.string.confirm), getString(R.string.areyousure));
                dialog.setButtonText("Not Sure", "Sure");
                dialog.show();
            }
        });

        findViewById(R.id.btnSelect3).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                new DatePickerDialog(MainActivity.this, new DatePickerDialog.IListener() {
                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onDone(int year, int month, int day) {
                        MainActivity.this.year = year;
                        MainActivity.this.month = month;
                        MainActivity.this.day = day;

                        Toast.makeText(MainActivity.this, "" + year + ":" + month + ":" + day, Toast.LENGTH_SHORT).show();
                    }
                }, getString(R.string.birthday), year, month, day, Calendar.getInstance().get(Calendar.YEAR)-6).show();
            }
        });

        findViewById(R.id.btnSelect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LinearNumberSelectDialog dialog = new LinearNumberSelectDialog(MainActivity.this, new LinearNumberSelectDialog.IListener() {
                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onDone(Number number) {
                        Toast.makeText(MainActivity.this, "" + number, Toast.LENGTH_SHORT).show();
                    }
                }, getString(R.string.weight), getString(R.string.kg), 130, 230, 1, 0);
                dialog.show();
            }
        });

        findViewById(R.id.btnAlert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = new AlertDialog(MainActivity.this, new AlertDialog.IListener() {
                    @Override
                    public void onDone() {

                    }
                }, getString(R.string.midialog_alert), getString(R.string.thisisaalert));
                dialog.setButtonText("OK");
                // 系统应用才有的权限，会crash
                dialog.setWindowType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                dialog.show();
            }
        });

        findViewById(R.id.btnSelect2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Select2Dialog dialog=new Select2Dialog(MainActivity.this,null,"2","","");
                List<String> left=new ArrayList<String>();
                left.add("福建");

                List<String> right=new ArrayList<String>();
                right.add("福州");
                right.add("厦门");

                dialog.setItems(left,0);
                dialog.setItems2(right,0);
                dialog.show();
            }
        });

    }
}
