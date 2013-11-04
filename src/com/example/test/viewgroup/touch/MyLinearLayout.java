package com.example.test.viewgroup.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyLinearLayout extends LinearLayout {

	private String TAB = "MyLinearLayout";

	public MyLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyLinearLayout(Context context) {
		super(context);
	}

//	@Override
//	protected void onLayout(boolean changed, int l, int t, int r, int b) {
//		int childCount = getChildCount();
//		for(int i = 0;i < childCount;i++) {
//			View child = getChildAt(i);
//			child.layout(l, t, r, b);
//		}
//	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch(ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.d(TAB, TAB + " ---> dispatchTouchEvent action:ACTION_DOWN");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.d(TAB, TAB + " ---> dispatchTouchEvent action:ACTION_MOVE");
			break;
		case MotionEvent.ACTION_UP:
			Log.d(TAB, TAB + " ---> dispatchTouchEvent action:ACTION_UP");
			break;
		}
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		//true表示拦截后交给自己的onTouchEvent处理，false表示传递给子View
		switch(ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.d(TAB, TAB + " ---> onInterceptTouchEvent action:ACTION_DOWN");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.d(TAB, TAB + " ---> onInterceptTouchEvent action:ACTION_MOVE");
			break;
		case MotionEvent.ACTION_UP:
			Log.d(TAB, TAB + " ---> onInterceptTouchEvent action:ACTION_UP");
			break;
		}
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.d(TAB, TAB + " ---> onTouchEvent action:ACTION_DOWN");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.d(TAB, TAB + " ---> onTouchEvent action:ACTION_MOVE");
			break;
		case MotionEvent.ACTION_UP:
			Log.d(TAB, TAB + " ---> onTouchEvent action:ACTION_UP");
			break;
		}
		return false;
	}
}
