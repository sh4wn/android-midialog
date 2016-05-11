package li.xiangyang.android.midialog;

import android.content.Context;
import android.widget.TextView;


/**
 * Created by bac on 16/5/9.
 */
public class ProgressDialog extends BaseDialog {

    private TextView txtProgress;

    public ProgressDialog(Context context){
        this(context,null);
    }
    public ProgressDialog(Context context, String progressStr) {
        super(context, R.layout.dialog_progress);
        txtProgress = (TextView) dialog.findViewById(R.id.txtProgress);
        findViewById(R.id.box).setClickable(true);
        setProgressString(progressStr);
        setCancelable(false);
    }

    public void setProgressString(String txt) {
        if (txt != null) {
            txtProgress.setText(txt);
        }
    }


    @Override
    protected void onCancel() {

    }
}
