package Hexagon;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class HexagonButtonLayout extends FrameLayout {

    private static final int MAX_LEVEL = 20;
    private int defaultEdgeLen = 50;
    private static final int DEFAULT_INTERVAL_LEN = 5;
    
    private OnItemClickListener mItemClickListener = null;
    private List<List<HexagonItem>> mItemList;
    private Context mContext;
    private boolean mIsLayoutItems = false;

    private int navigationBarHeight = 0;

    public void setNavigationBarHeight(int height){
        navigationBarHeight = height;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        double sqrt3= Math.sqrt(3);
        int width = this.getWidth();
        int height = this.getHeight()*3/4;
        int cntWidth = 0;
        int cntHeight = mItemList.size();
        for (List<HexagonItem> list : mItemList) {
            if(cntWidth < list.size()){
                cntWidth = list.size();
            }
        }

        int lenWidth = (width - DEFAULT_INTERVAL_LEN*(cntWidth+3))/cntWidth/2;
        int lenHeight = (int)((height - navigationBarHeight - DEFAULT_INTERVAL_LEN*(cntHeight+3))*sqrt3/cntHeight/2);
        if (lenWidth > lenHeight){
            defaultEdgeLen = lenHeight;
        }else{
            defaultEdgeLen = lenWidth;
        }
        super.onLayout(changed, left, top, right, bottom);
    }

    public HexagonButtonLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public HexagonButtonLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HexagonButtonLayout(final Context context) {
        super(context);
        init(context);
        mContext = context;
    }
    
    public void setOnItemClickListener(OnItemClickListener listener){
    	mItemClickListener = listener;
    }

    private void init(Context ctx) {
        mItemList = new ArrayList<List<HexagonItem>>();
        Resources res = ctx.getResources();
        setWillNotDraw(false);
        setDrawingCacheEnabled(false);
    }

    public void addItem(HexagonItem item) {
        int row = item.getRow();
        if (row >= MAX_LEVEL) {
            row = MAX_LEVEL - 1;
        }
        while (mItemList.size() <= row) {
            mItemList.add(new ArrayList<HexagonItem>());
        }
        mItemList.get(row).add(item);
    }

    private Path makePath(Point pos, int edgeLen) {
        Path path = new Path();
        double sqrt3 = Math.sqrt(3);

        path.rMoveTo(pos.x - edgeLen, pos.y);
        path.lineTo(pos.x - edgeLen/2, (float)(pos.y-sqrt3*edgeLen/2));
        path.lineTo(pos.x + edgeLen/2, (float)(pos.y-sqrt3*edgeLen/2));
        path.lineTo(pos.x + edgeLen, pos.y);
        path.lineTo(pos.x + edgeLen/2, (float)(pos.y+sqrt3*edgeLen/2));
        path.lineTo(pos.x - edgeLen/2, (float)(pos.y+sqrt3*edgeLen/2));
        path.lineTo(pos.x - edgeLen, pos.y);
        path.close();
        return path;
    }

    private void layoutItems() {

        double sqrt3= Math.sqrt(3);

        WindowManager wm = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();

        int itemStartYPos = defaultEdgeLen + DEFAULT_INTERVAL_LEN;//height/2 - defaultEdgeLen*mItemList.size() - DEFAULT_INTERVAL_LEN*(mItemList.size()-1)/2;

        for (List<HexagonItem> list : mItemList) {
            int itemStartXPos = width/2 - defaultEdgeLen*(list.size()-1) - DEFAULT_INTERVAL_LEN*(list.size()-1)/2;

             for (HexagonItem item : list) {
                View view = item.getView();
                view.measure(view.getLayoutParams().width, view.getLayoutParams().height);
                int w = view.getMeasuredWidth();
                int h = view.getMeasuredHeight();

                int x = itemStartXPos - defaultEdgeLen;
                int y = (int)(itemStartYPos - defaultEdgeLen * sqrt3/2);
                view.layout(x, y, x + w, (int)(y + h * sqrt3/2));

                Point pos = new Point();
                pos.x = itemStartXPos;
                pos.y = itemStartYPos;

                Path path = makePath(pos, defaultEdgeLen);
                item.setGeometry(pos, defaultEdgeLen, path);

                itemStartXPos += defaultEdgeLen*2 + DEFAULT_INTERVAL_LEN;
            }

            itemStartYPos += defaultEdgeLen*sqrt3 + DEFAULT_INTERVAL_LEN;
        }
    }

    private HexagonItem findItem(float x, float y) {

        double sqrt3= Math.sqrt(3);
        for (List<HexagonItem> list : mItemList) {
            for (HexagonItem item : list) {
                Point pt = item.getPos();
                float deltaX = Math.abs(pt.x - x);
                float deltaY = Math.abs(pt.y - y);
                if (deltaX<=defaultEdgeLen && deltaY<=defaultEdgeLen) {
                    return item;
                }
            }
        }
        return null;
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent evt) {
        int x = (int) evt.getX();
        int y = (int) evt.getY();
        int action = evt.getActionMasked();
        if (MotionEvent.ACTION_DOWN == action) {
            HexagonItem lastTouchItem = findItem(x, y);
            if (lastTouchItem != null) {
                Log.d("touch", "item row: "+ lastTouchItem.getRow() + " idx :" + lastTouchItem.getIdx());
                mItemClickListener.onItemClick(lastTouchItem);
                invalidate();
            } else {
                Log.d("touch", "item == null");
            }
            return true;
        } else if (MotionEvent.ACTION_UP == action) {
            Log.d("touch", "up");
        } else if (MotionEvent.ACTION_CANCEL == action) {
        } else if (MotionEvent.ACTION_MOVE == action) {
        }
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(!mIsLayoutItems){
            layoutItems();
            mIsLayoutItems = true;
        }
        int state;
        Resources res = mContext.getResources();
        for (List<HexagonItem> list : mItemList) {
            for (HexagonItem item : list) {
                Paint p = new Paint();
                p.setColor(res.getColor(item.getPaint()));
                p.setAntiAlias(true);
                state = canvas.save();
                drawPath(canvas, item.getPath(), p);
                canvas.restoreToCount(state);
                drawItem(canvas, item);
            }
        }
    }

    @SuppressLint("NewApi") 
    private void drawItem(Canvas canvas, HexagonItem item) {
        View view = item.getView();
        int state = canvas.save();
        canvas.translate(view.getX(), view.getY());
        view.draw(canvas);
        canvas.restoreToCount(state);
        state = canvas.save();
        canvas.restoreToCount(state);
    }

    private void drawPath(Canvas canvas, Path path, Paint paint) {
        canvas.drawPath(path, paint);
    }

    public HexagonItem makeItemByColor(int color, int row, int idx) {
        ImageView view = new ImageView(mContext);
//        view.setMinimumWidth(defaultEdgeLen);
//        view.setMinimumHeight(defaultEdgeLen);
        view.setScaleType(ImageView.ScaleType.CENTER);
        LayoutParams lp = new LayoutParams(defaultEdgeLen, defaultEdgeLen);
        view.setLayoutParams(lp);
        return new HexagonItem(view, row, idx, color);
    }
    
    public static interface OnItemClickListener{
    	public void onItemClick(HexagonItem item);
    }
}
