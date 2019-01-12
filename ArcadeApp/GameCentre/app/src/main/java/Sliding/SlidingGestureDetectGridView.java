package Sliding;

/*
Adapted from:
https://github.com/DaveNOTDavid/sample-puzzle/blob/master/app/src/main/java/com/davenotdavid/samplepuzzle/GestureDetectGridView.java

This extension of GridView contains built in logic for handling swipes between buttons
 */

import android.content.Context;
import android.util.AttributeSet;

import fall2018.csc2017.CoreClasses.GestureDetectGridView;

public class SlidingGestureDetectGridView extends GestureDetectGridView {

    /**
     * Calls the constructor to create a CheckersGestureDetectGridView when it is called in with context.
     * @param context
     */
    public SlidingGestureDetectGridView(Context context) {
        super(context);
    }

    /**
     * Calls the constructor to create a CheckersGestureDetectGridView when an attribute of it is set.
     * @param context
     * @param attrs
     */
    public SlidingGestureDetectGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Calls the constructor to create a CheckersGestureDetectGridView when an attribute and
     * style attribute is set.
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public SlidingGestureDetectGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
