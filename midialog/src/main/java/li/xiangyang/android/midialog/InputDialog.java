package li.xiangyang.android.midialog;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by bac on 16/5/9.
 */
public class InputDialog extends BaseDialog {

    private EditText txtInput;
    private TextView txtTitle;

    private IListener listener;

    public InputDialog(Context context) {
        this(context, null, null, null);
    }

    public InputDialog(Context context, String title, String defaultText, String hint) {
        super(context, R.layout.dialog_input_one);
        txtInput = (EditText) findViewById(R.id.txtInput);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        setTitle(title);
        setText(defaultText);
        setHint(hint);

        initEvents();
    }

    private void setTitle(String title) {
        if (title!=null){
            txtTitle.setText(title);
        }
    }

    public void setText(String txt) {
        if (txt != null) {
            txtInput.setText(txt);
        }
    }

    public void setHint(String txt) {
        if (txt != null) {
            txtInput.setHint(txt);
        }
    }

    public String getText() {
        return txtInput.getText().toString();
    }

    public void setListener(IListener listener) {
        this.listener = listener;
    }

    private void initEvents() {

        findViewById(R.id.box).setClickable(true);
        final View.OnClickListener lis = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btnClear) {
                    txtInput.setText("");
                } else {
                    if (view.getId() == R.id.btnLeft) {
                        onCancel();
                    } else {
                        if (listener != null) {
                            listener.onDone(getText());
                        }
                    }
                    dismiss();
                }

            }
        };
        findViewById(R.id.btnClear).setOnClickListener(lis);
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

        void onDone(String text);
    }
}
