package Twenty;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

import fall2018.csc2017.CoreClasses.GestureDetectGridView;
import fall2018.csc2017.CoreClasses.MovementController;

import android.view.View;

public class TwentyGestureDetectGridView extends GestureDetectGridView implements GestureDetector.OnGestureListener {
    /**
     * Minimum swipe distance that must be reached for a swipe to be registered and processed.
     */
    private static final int SWIPE_MIN_DISTANCE = 100;

    /**
     * GestureDetector object that is used for classifying events that involve touching the screen.
     */
    private final GestureDetector gDetector = new GestureDetector(this);

    /**
     * The text for the toast when the game is over.
     */
    private String toastMessage = "Game Over! No more moves possible!";


    /**
     * Calls the constructor to create a CheckersGestureDetectGridView when it is called in with context.
     * @param context
     */
    public TwentyGestureDetectGridView(Context context) {
        super(context);
    }

    /**
     * Calls the constructor to create a CheckersGestureDetectGridView when an attribute of it is set.
     * @param context
     * @param attrs
     */
    public TwentyGestureDetectGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Calls the constructor to create a CheckersGestureDetectGridView when an attribute and
     * style attribute is set.
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public TwentyGestureDetectGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    /**
     * Calls the movement controller to process an upwards swipe. 0 being the int that refers to up.
     */
    public void onSwipeUp() {
        mController.processMovement(this.getContext(),  toastMessage, 0);
    }

    /**
     * Calls the movement controller to process a downwards swipe. 1 being the int that refers to down.
     */
    public void onSwipeDown() {
        mController.processMovement(this.getContext(),  toastMessage, 1);
    }

    /**
     * Calls the movement controller to process a leftward swipe. 2 being the int that refers to left.
     */
    public void onSwipeLeft() {
        mController.processMovement(this.getContext(),  toastMessage, 2);
    }

    /**
     * Calls the movement controller to process a rightward swipe. 3 being the int that refers to right.
     */
    public void onSwipeRight() {
        mController.processMovement(this.getContext(),  toastMessage, 3);
    }

    /**
     * Calls the gesture detector to evaluate what kind of touch event has taken place. Needs override for the interface.
     * @param event
     * @return boolean that tells if that specific touch event is dealt by the code.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    /**
     * Similar to onTouchEvent, but this dispatches the touch event to the relevant touch event method. Utilised because
     * we are using a gridView and this works better for it. Needs override for the interface.
     * @param e
     * @return boolean that tells if that specific touch event is dealt by the code.
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent e) {
        super.dispatchTouchEvent(e);
        return gDetector.onTouchEvent(e);
    }

    /**
     * Method returns if a pressing on a screen should be dealt with by the code. Should always be true if
     * you want to process touch movements as a Down event always occurs first. Needs override for the interface.
     * @param e
     * @return boolean to see if touching the screen should result in the code continuing.
     */
    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    /**
     * Calculates the direction of the swipe and if it is above the min distance threshold. If so,
     * it calls the specific swipe method. Needs override for the interface.
     * @param e1
     * @param e2
     * @param velocityX
     * @param velocityY
     * @return true if the swipe is handled by the code. False otherwise.
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float distanceX = e2.getX() - e1.getX();
        float distanceY = e2.getY() - e1.getY();
        if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_MIN_DISTANCE) {
            if (distanceX > 0) {
                onSwipeRight();
            } else {
                onSwipeLeft();
            }
            return true;

        } else if (Math.abs(distanceY) > Math.abs(distanceX) && Math.abs(distanceY) > SWIPE_MIN_DISTANCE) {
            if (distanceY > 0) {
                onSwipeDown();
            } else {
                onSwipeUp();
            }
            return true;
        }

        return false;
    }

    /**
     * We don't need or want to deal with scrolling in the game so this returns false. Needs override for the interface.
     * @param e1
     * @param e2
     * @param distanceX
     * @param distanceY
     * @return false
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    /**
     * We aren't using single taps in this game so this returns false. Needs override for the interface.
     * @param e
     * @return false
     */
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    /**
     * Method not relevant to our game so it does nothing. Needs override for the interface.
     * @param e
     */
    @Override
    public void onShowPress(MotionEvent e) {
    }

    /**
     * Method not relevant to our game so it does nothing. Needs override for the interface.
     * @param e
     */
    @Override
    public void onLongPress(MotionEvent e) {
    }

    /**
     * Sets the current twentyBoardManager as the boardmanager in the movement controller.
     * @param twentyBoardManager
     */
    public void setBoardManager(TwentyBoardManager twentyBoardManager) {
        mController.setBoardManager(twentyBoardManager);
    }
}