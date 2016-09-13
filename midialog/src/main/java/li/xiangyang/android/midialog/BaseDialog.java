package li.xiangyang.android.midialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import java.lang.ref.WeakReference;

/**
 * Created by bac on 16/5/9.
 */
abstract class BaseDialog {

    protected WeakReference<Context> context;
    protected Dialog dialog;

    private View contentView;
    private boolean cancelable = true;

    protected BaseDialog(Context context, int layoutId) {
        this.context = new WeakReference<>(context);

        dialog = new Dialog(context, R.style.DialogTheme);
        contentView = dialog.getLayoutInflater().inflate(layoutId, null);
        dialog.setContentView(contentView);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        initEvents();
    }

    protected View findViewById(int id) {
        return dialog.findViewById(id);
    }

    protected abstract void onCancel();

    public void show() {
        dialog.show();
    }

    public void dismiss() {
        try {
            dialog.dismiss();
        } catch (Exception ex) {

        }
    }

    public boolean isCancelable() {
        return cancelable;
    }

    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    public void setWindowType(int type) {
        dialog.getWindow().setType(type);
    }

    private void initEvents() {
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cancelable) {
                    onCancel();
                    dismiss();
                }
            }
        });

        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                    if (cancelable) {
                        onCancel();
                        dialog.dismiss();
                    }
                    return true;
                }
                return false;
            }
        });

    }

    private float displayDensity;

    protected float dp2px(float dp) {
        if (displayDensity == 0) {
            if (context.get() != null) {
                displayDensity = context.get().getResources().getDisplayMetrics().density;
            }
        }
        return (dp * displayDensity + 0.1f);
    }

    protected Context getContext() {
        return context.get();
    }

}
