package Checkers;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

import fall2018.csc2017.CoreClasses.GestureDetectGridView;

public class CheckersGestureDetectGridView extends GestureDetectGridView {

    /**
     * Calls the constructor to create a CheckersGestureDetectGridView when an attribute of it is set.
     * @param context
     * @param attrs
     */
    public CheckersGestureDetectGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Calls the constructor to create a CheckersGestureDetectGridView when it is called in with context.
     * @param context
     */
    public CheckersGestureDetectGridView(Context context) {
        super(context);
    }

    /**
     * Calls the constructor to create a CheckersGestureDetectGridView when an attribute and
     * style attribute is set.
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public CheckersGestureDetectGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
