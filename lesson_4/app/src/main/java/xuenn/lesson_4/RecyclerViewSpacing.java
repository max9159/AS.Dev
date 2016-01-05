package xuenn.lesson_4;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by SsuChi on 7/23/2015.
 */
public class RecyclerViewSpacing extends RecyclerView.ItemDecoration {
    private int top;
    private int bottom;
    private int l;
    private int t;
    private int r;
    private int b;

    public RecyclerViewSpacing(int marginLeft, int marginTop, int marginRight, int marginBottom) {
        this.l = marginLeft;
        this.t = marginTop;
        this.r = marginRight;
        this.b = marginBottom;
    }

    public RecyclerViewSpacing setTopSpacing(int top){
        this.top=top;
        return this;
    }

    public RecyclerViewSpacing setBottomSpacing(int bottom){
        this.bottom=bottom;
        return this;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = l;
        outRect.top = t;
        outRect.right = r;
        outRect.bottom = b;
        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildPosition(view) == 0) {
            outRect.top = top;
        }
//        Log.i("position",parent.getAdapter().getItemCount()-1+"   "+parent.getChildPosition(view)+"");
        if(parent.getChildPosition(view)==parent.getAdapter().getItemCount()-1){
            outRect.bottom = bottom;
        }

    }

}
