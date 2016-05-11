package li.xiangyang.android.midialog.samples;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import li.xiangyang.android.midialog.ConfirmDialog;
import li.xiangyang.android.midialog.DatePickerDialog;
import li.xiangyang.android.midialog.InputDialog;
import li.xiangyang.android.midialog.OptionsDialog;
import li.xiangyang.android.midialog.ProgressDialog;
import li.xiangyang.android.midialog.Select3Dialog;
import li.xiangyang.android.midialog.SelectDialog;
import li.xiangyang.android.midialog.samples.*;

public class MainActivity extends AppCompatActivity {

    int year=1991;
    int month=2;
    int day=23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(li.xiangyang.android.midialog.samples.R.layout.activity_main);

        findViewById(R.id.btnProgress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ProgressDialog(MainActivity.this).show();
            }
        });
        findViewById(R.id.btnInput).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new InputDialog(MainActivity.this).show();
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
                }, "性别", 0, "男", "女").show();
            }
        });

        findViewById(R.id.btnConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ConfirmDialog(MainActivity.this,null,"确认","确认吗").show();
            }
        });

        findViewById(R.id.btnSelect3).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                new DatePickerDialog(MainActivity.this, new DatePickerDialog.IListener() {
                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onDone(int year, int month, int day) {
                        MainActivity.this.year=year;
                        MainActivity.this.month=month;
                        MainActivity.this.day=day;

                        Toast.makeText(MainActivity.this,""+year+":"+month+":"+day,Toast.LENGTH_SHORT).show();
                    }
                },"出生日期",year,month,day).show();
            }
        });

        findViewById(R.id.btnSelect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<String> heights=new ArrayList<String>(100);
                for (int i=130;i<230;i++){
                    heights.add(i+"");
                }
                SelectDialog dialog=new SelectDialog(MainActivity.this, new SelectDialog.IListener() {
                    @Override
                    public void onCancel() {

                    }
                    @Override
                    public void onDone(int selection) {

                        Toast.makeText(MainActivity.this,""+selection,Toast.LENGTH_SHORT).show();
                    }
                },"体重",heights.toArray(new String[heights.size()]));
                dialog.setUint("cm");
                dialog.show();
            }
        });
    }
}
