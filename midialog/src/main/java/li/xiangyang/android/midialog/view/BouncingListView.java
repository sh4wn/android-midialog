package li.xiangyang.android.midialog.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by bac on 16/5/10.
 */
public class BouncingListView extends ListView {

    private float itemHeight;

    private OnChangeListener listener;

    public BouncingListView(Context context) {
        super(context);
    }

    public BouncingListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private boolean scrolling=false;

    public BouncingListView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.setOnScrollListener(new OnScrollListener() {
            /**
             * 记录第一个显示的Item
             */
            private int mFirstVisibleItem;

            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (i == OnScrollListener.SCROLL_STATE_IDLE) {
                    int top = getChildAt(0).getTop();
                    int selection;
                    if (Math.abs(top) > itemHeight / 2) {
                        selection = mFirstVisibleItem + 1;
                    } else {
                        selection = mFirstVisibleItem;
                    }
                    if (listener != null) {
                        listener.onChange(BouncingListView.this, selection);
                    }
                    setSelection(selection);

                    scrolling=false;
                }else if (i == OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    scrolling=true;
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visible_count, int total) {

                mFirstVisibleItem = firstVisibleItem;
            }
        });
    }

    public void setItemHeight(float height) {
        itemHeight = height;
    }

    public void setOnChangeListener(OnChangeListener listener) {
        this.listener = listener;
    }

    public static interface OnChangeListener {
        void onChange(BouncingListView listView, int selection);
    }

    public boolean isScrolling() {
        return scrolling;
    }
}
