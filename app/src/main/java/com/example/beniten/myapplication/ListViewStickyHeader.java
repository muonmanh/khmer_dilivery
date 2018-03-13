package com.example.beniten.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by Beniten on 3/13/2018.
 */

public class ListViewStickyHeader extends ListView {

    public ListViewStickyHeader(Context context) {
        super(context);
        init();
    }

    public ListViewStickyHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ListViewStickyHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    int mScrollState = OnScrollListener.SCROLL_STATE_IDLE;
    private void init(){
        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                mScrollState = i;
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(mScrollState==OnScrollListener.SCROLL_STATE_IDLE){
                    return;
                }
                View listItem = absListView.getChildAt(0);
                if (listItem == null)
                    return;

                LinearLayout header = (LinearLayout) listItem.findViewById(R.id.header);
                int moveY = 0;
                int rowsTop = listItem.getTop();
                int contentHeight = listItem.getHeight();
                int headerHeight = header.getHeight();
                if (rowsTop < 0){ // current row is partially out of the screen..
                    // we are moving the header inside the view and we need to make sure
                    // we have space left
                    int remainingSpace = rowsTop + contentHeight;
                    if(headerHeight<remainingSpace){ // we have place to move
                        moveY = -rowsTop;
                    } else { // we reached to the limits of current view. So we need to move the header out
                        moveY = contentHeight - headerHeight;
                    }
                } else {
                    // our current list row has not reached to top yet..
                }
                // update the Y position of header
                header.setTranslationY(moveY);

            }
        });
    }



}
