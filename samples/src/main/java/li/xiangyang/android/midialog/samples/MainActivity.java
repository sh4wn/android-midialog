package li.xiangyang.android.midialog.samples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import li.xiangyang.android.midialog.AlertDialog;
import li.xiangyang.android.midialog.ConfirmDialog;
import li.xiangyang.android.midialog.DatePickerDialog;
import li.xiangyang.android.midialog.InputDialog;
import li.xiangyang.android.midialog.LinearNumberSelectDialog;
import li.xiangyang.android.midialog.OptionsDialog;
import li.xiangyang.android.midialog.ProgressDialog;

public class MainActivity extends AppCompatActivity {

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
                ProgressDialog dialog=new ProgressDialog(MainActivity.this);
                dialog.setCancelable(true);
                dialog.show();
            }
        });
        findViewById(R.id.btnInput).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputDialog dialog=new InputDialog(MainActivity.this);

                dialog.setText("bac");
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
                new ConfirmDialog(MainActivity.this, null, getString(R.string.confirm), getString(R.string.areyousure)).show();
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
                }, getString(R.string.birthday), year, month, day).show();
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
                dialog.show();
            }
        });
    }
}
