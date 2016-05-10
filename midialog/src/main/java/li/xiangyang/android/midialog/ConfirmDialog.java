package li.xiangyang.android.midialog;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by bac on 16/5/9.
 */
public class ConfirmDialog extends BaseDialog {

    private TextView txtMessage;
    private TextView txtTitle;

    private IListener listener;

    public ConfirmDialog(Context context) {
        this(context, null, null, null);
    }

    public ConfirmDialog(Context context, IListener listener, String title, String message) {
        super(context, R.layout.dialog_confirm);
        this.listener = listener;

        txtMessage = (TextView) findViewById(R.id.txtMessage);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        setTitle(title);
        setMessage(message);

        initEvents();
    }


    private void setTitle(String title) {
        if (title != null) {
            txtTitle.setText(title);
        }
    }

    public void setMessage(String txt) {
        if (txt != null) {
            txtMessage.setText(txt);
        }
    }

    public void setListener(IListener listener) {
        this.listener = listener;
    }

    private void initEvents() {

        findViewById(R.id.box).setClickable(true);
        final View.OnClickListener lis = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btnLeft) {
                    onCancel();
                } else {
                    if (listener != null) {
                        listener.onSure();
                    }
                }
                dismiss();
            }
        };
        findViewById(R.id.btnLeft).setOnClickListener(lis);
        findViewById(R.id.btnRight).setOnClickListener(lis);
    }

    @Override
    protected void onCancel() {
        if (listener != null) {
            listener.onCancel();
        }
    }

    public static interface IListener {
        void onCancel();

        void onSure();
    }
}
