package fall2018.csc2017.CoreClasses;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.GridView;

import Checkers.CheckersGestureDetectGridView;
import Checkers.CheckersMovementController;

public class GestureDetectGridView extends GridView {


    private String gameName;

    /**
     * Swipe distance in gesture detect gridvew.
     */
    public static final int SWIPE_MIN_DISTANCE = 100;


    /**
     * Controls detection of gestures on gridview.
     */
    protected GestureDetector gDetector;

    protected boolean mFlingConfirmed = false;

    /**
     * x coordinate of touch movement on gridview.
     */
    protected float mTouchX;

    /**
     * y coordinate of touch movement on gridview.
     */
    protected float mTouchY;

    /**
     * Controls movements of buttons and tiles in gridview.
     */
    protected MovementController mController;

    public GestureDetectGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GestureDetectGridView(Context context) {
        super(context);
        init(context);
    }

    public GestureDetectGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setBoardManager(BoardManager boardManager) {
        mController.setBoardManager(boardManager);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) // API 21

    private void init(final Context context) {
        gameName = AccountManager.activeAccount.getActiveGameName();
        if(gameName.equals(Game.CHECKERS_NAME)){
            mController = new CheckersMovementController();
        }else{
            mController = new MovementController();
        }
        gDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapConfirmed(MotionEvent event) {
                int position = GestureDetectGridView.this.pointToPosition
                        (Math.round(event.getX()), Math.round(event.getY()));
                mController.processMovement(context, "A player wins!", position);
                return true;
            }

            @Override
            public boolean onDown(MotionEvent event) {
                return true;
            }

        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getActionMasked();
        gDetector.onTouchEvent(ev);

        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mFlingConfirmed = false;
        } else if (action == MotionEvent.ACTION_DOWN) {
            mTouchX = ev.getX();
            mTouchY = ev.getY();
        } else {

            if (mFlingConfirmed) {
                return true;
            }
            float dX = (Math.abs(ev.getX() - mTouchX));
            float dY = (Math.abs(ev.getY() - mTouchY));
            if ((dX > SWIPE_MIN_DISTANCE) || (dY > SWIPE_MIN_DISTANCE)) {
                mFlingConfirmed = true;
                return true;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * Return true if movement event occurs.
     *
     * @param ev motion event.
     * @return true or false.
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return gDetector.onTouchEvent(ev);
    }

}
