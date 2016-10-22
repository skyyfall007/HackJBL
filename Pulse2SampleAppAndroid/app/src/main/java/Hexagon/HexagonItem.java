package Hexagon;

import android.graphics.Path;
import android.graphics.Point;
import android.view.View;

public class HexagonItem {

    private View mView;
    private Path mPath;
    private int mPaint;

    private Point mPos;
    private int mLen;

    private int mRow;
    private int mIndex;

    public HexagonItem(View view, int row, int idx, int paint) {
        mView = view;
        mRow = row;
        mIndex = idx;
        mPaint = paint;
    }

    public void setGeometry(Point pos, int len, Path p) {
        mPos = pos;
        mLen = len;
        mPath = p;
    }

    public Point getPos(){return mPos;}
    public View getView() {
        return mView;
    }
    public Path getPath() {
        return mPath;
    }
    public int getPaint(){return mPaint;}
    public int getRow(){return mRow;}
    public int getIdx(){return mIndex;}
}
