package com.example.test.viewgroup.touch;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
/**
 * 分析touch事件分发机制
 * @author xiawenquan
 * activity ---touch----->> MyLinearLayout---touch----->> MyTextView---touch
 * 
 <<<<<<-------------------------MyTextView控件的事件分析-------------------------------------------------------->>>>
 
 * 1.MyTextView---onTouchEvent--->> return false;
 * 1.1 单击 MyTextView
 *   MainActivity ---> dispatchTouchEvent action:ACTION_DOWN //第1步
 *   						|
 	 MyLinearLayout ---> dispatchTouchEvent action:ACTION_DOWN //第2步
 	 						|
	 MyLinearLayout ---> onInterceptTouchEvent action:ACTION_DOWN //第3步
	  	 					|
	 MyTextView ---> dispatchTouchEvent action:ACTION_DOWN //第4步
 *   						|
	 MyTextView ---> onTouchEvent action:ACTION_DOWN //第5步
 *   						|
	 MyLinearLayout ---> onTouchEvent action:ACTION_DOWN //第6步
 *   						|
	 MainActivity ---> onTouchEvent action:ACTION_DOWN //第7步
 *   						|
	 MainActivity ---> dispatchTouchEvent action:ACTION_UP //第8步
 *   						|
	 MainActivity ---> onTouchEvent action:ACTION_UP //第9步
 * 	
 * 1.1 单击 MyTextView并且有滑动手势
 * 
 *  MainActivity ---> dispatchTouchEvent action:ACTION_DOWN //第1步
 *   						|
 	MyLinearLayout ---> dispatchTouchEvent action:ACTION_DOWN //第2步
 *   						|
 	MyLinearLayout ---> onInterceptTouchEvent action:ACTION_DOWN //第3步
 *   						|
 	MyTextView ---> dispatchTouchEvent action:ACTION_DOWN //第4步
 *   						|
 	MyTextView ---> onTouchEvent action:ACTION_DOWN //第5步
 *   						|
 	MyLinearLayout ---> onTouchEvent action:ACTION_DOWN //第6步
 *   						|
 	MainActivity ---> onTouchEvent action:ACTION_DOWN //第7步
 *   						|
 	MainActivity ---> dispatchTouchEvent action:ACTION_MOVE  //第8步
 *   						|
 	MainActivity ---> onTouchEvent action:ACTION_MOVE //第9步
 *   						|
 	MainActivity ---> dispatchTouchEvent action:ACTION_MOVE //重复滑动
 *   						|
	MainActivity ---> onTouchEvent action:ACTION_MOVE
 *   						|
 	MainActivity ---> dispatchTouchEvent action:ACTION_MOVE
 *   						|
 	MainActivity ---> onTouchEvent action:ACTION_MOVE
 *   						|
 	MainActivity ---> dispatchTouchEvent action:ACTION_MOVE
 *   						|
	MainActivity ---> onTouchEvent action:ACTION_MOVE
 *   						|
 	MainActivity ---> dispatchTouchEvent action:ACTION_UP  //第10步
 *   						|
 	MainActivity ---> onTouchEvent action:ACTION_UP //第11步
 *
 *
 *2.MyTextView---onTouchEvent--->> return true;
 *2.1 单击 MyTextView
 *  MainActivity ---> dispatchTouchEvent action:ACTION_DOWN //第1步
 *   						|
 	MyLinearLayout ---> dispatchTouchEvent action:ACTION_DOWN //第2步
 *   						|
 	MyLinearLayout ---> onInterceptTouchEvent action:ACTION_DOWN //第3步
 *   						|
 	MyTextView ---> dispatchTouchEvent action:ACTION_DOWN //第4步
 *   						|
 	MyTextView ---> onTouchEvent action:ACTION_DOWN //第5步
 *   						|
 	MainActivity ---> dispatchTouchEvent action:ACTION_UP //第6步
 *   						|
 	MyLinearLayout ---> dispatchTouchEvent action:ACTION_UP //第7步
 *   						|
 	MyLinearLayout ---> onInterceptTouchEvent action:ACTION_UP //第8步
 *   						|
 	MyTextView ---> dispatchTouchEvent action:ACTION_UP //第9步
 *   						|
 	MyTextView ---> onTouchEvent action:ACTION_UP //第10步
 	
 *2.2 单击 MyTextView并且有滑动手势
 	MainActivity ---> dispatchTouchEvent action:ACTION_DOWN //第1步
 *   						|
 	MyLinearLayout ---> dispatchTouchEvent action:ACTION_DOWN //第2步
 *   						|
 	MyLinearLayout ---> onInterceptTouchEvent action:ACTION_DOWN //第3步
 *   						|
 	MyTextView ---> dispatchTouchEvent action:ACTION_DOWN //第4步
 *   						|
 	MyTextView ---> onTouchEvent action:ACTION_DOWN //第5步
 *   						|
 	MainActivity ---> dispatchTouchEvent action:ACTION_MOVE //第6步
 *   						|
 	MyLinearLayout ---> dispatchTouchEvent action:ACTION_MOVE //第7步
 *   						|
 	MyLinearLayout ---> onInterceptTouchEvent action:ACTION_MOVE //第8步
 *   						|
 	MyTextView ---> dispatchTouchEvent action:ACTION_MOVE //第9步
 *   						|
 	MyTextView ---> onTouchEvent action:ACTION_MOVE //第10步
 *   						|
 	MainActivity ---> dispatchTouchEvent action:ACTION_MOVE //重复滑动
 *   						|
 	MyLinearLayout ---> dispatchTouchEvent action:ACTION_MOVE
 *   						|
 	MyLinearLayout ---> onInterceptTouchEvent action:ACTION_MOVE
 *   						|
 	MyTextView ---> dispatchTouchEvent action:ACTION_MOVE
 *   						|
 	MyTextView ---> onTouchEvent action:ACTION_MOVE
 *   						|
 	MainActivity ---> dispatchTouchEvent action:ACTION_UP //第11步
 *   						|
 	MyLinearLayout ---> dispatchTouchEvent action:ACTION_UP //第12步
 *   						|
 	MyLinearLayout ---> onInterceptTouchEvent action:ACTION_UP //第13步
 *   						|
 	MyTextView ---> dispatchTouchEvent action:ACTION_UP //第14步
 *   						|
 	MyTextView ---> onTouchEvent action:ACTION_UP //第15步
 	
 *3.MyTextView---dispatchTouchEvent--->> return true; 
 
 *   						|
 	MainActivity ---> dispatchTouchEvent action:ACTION_DOWN //第1步
 *   						|
    MyLinearLayout ---> dispatchTouchEvent action:ACTION_DOWN //第2步
 *   						|
 	MyLinearLayout ---> onInterceptTouchEvent action:ACTION_DOWN //第3步
 *   						|
	MyTextView ---> dispatchTouchEvent action:ACTION_DOWN //第4步
 *   						|
	MainActivity ---> dispatchTouchEvent action:ACTION_UP
 *   						|
	MyLinearLayout ---> dispatchTouchEvent action:ACTION_UP
 *   						|
	MyLinearLayout ---> onInterceptTouchEvent action:ACTION_UP
 *   						|
	MyTextView ---> dispatchTouchEvent action:ACTION_UP

 	
 <<<<<<-------------------------MyLinearLayout控件事件情况分析-------------------------------------------------------->>>>
 
 *1.MyLinearLayout---onTouchEvent----->> return false && MyTextView --- onTouchEvent--->>  return false
 	1.1 走MyTextView的1和2和3
 	
 	
 *2.MyLinearLayout---onTouchEvent----->> return true && MyTextView --- onTouchEvent--->>  return false
2.1 单击MyTextView
 	
 	MainActivity ---> dispatchTouchEvent action:ACTION_DOWN //第1步
 *   						|
	 MyLinearLayout ---> dispatchTouchEvent action:ACTION_DOWN //第2步
 *   						|
	 MyLinearLayout ---> onInterceptTouchEvent action:ACTION_DOWN //第3步
 *   						|
	 MyTextView ---> dispatchTouchEvent action:ACTION_DOWN //第4步
 *   						|
	 MyTextView ---> onTouchEvent action:ACTION_DOWN //第5步 
 *   						|
	 MyLinearLayout ---> onTouchEvent action:ACTION_DOWN//第6步 
 *   						|
	 MainActivity ---> dispatchTouchEvent action:ACTION_UP
 *   						|
	 MyLinearLayout ---> dispatchTouchEvent action:ACTION_UP
 *   						|
	 MyLinearLayout ---> onTouchEvent action:ACTION_UP
 
2.1 单击MyTextView并且滑动
 	
	 MainActivity ---> dispatchTouchEvent action:ACTION_DOWN //第1步
 *   						|
	 MyLinearLayout ---> dispatchTouchEvent action:ACTION_DOWN //第2步
 *   						|
	 MyLinearLayout ---> onInterceptTouchEvent action:ACTION_DOWN //第3步
 *   						|
	 MyTextView ---> dispatchTouchEvent action:ACTION_DOWN //第4步
 *   						|
	 MyTextView ---> onTouchEvent action:ACTION_DOWN //第5步
 *   						|
	 MyLinearLayout ---> onTouchEvent action:ACTION_DOWN //第6步
 *   						|
	 MainActivity ---> dispatchTouchEvent action:ACTION_MOVE //滑动
 *   						|
	 MyLinearLayout ---> dispatchTouchEvent action:ACTION_MOVE
 *   						|
	 MyLinearLayout ---> onTouchEvent action:ACTION_MOVE
 *   						|
	 MainActivity ---> dispatchTouchEvent action:ACTION_MOVE//重复滑动
 *   						|
	 MyLinearLayout ---> dispatchTouchEvent action:ACTION_MOVE
 *   						|
	 MyLinearLayout ---> onTouchEvent action:ACTION_MOVE
 *   						|
	 MainActivity ---> dispatchTouchEvent action:ACTION_UP // 手离开屏幕
 *   						|
	 MyLinearLayout ---> dispatchTouchEvent action:ACTION_UP
 *   						|
	 MyLinearLayout ---> onTouchEvent action:ACTION_UP
 	
3.MyLinearLayout---onTouchEvent----->> return true && MyTextView --- onTouchEvent--->>  return true
 	
	 MainActivity ---> dispatchTouchEvent action:ACTION_DOWN //第1步
 *   						|
	 MyLinearLayout ---> dispatchTouchEvent action:ACTION_DOWN //第2步
 *   						|
	 MyLinearLayout ---> onInterceptTouchEvent action:ACTION_DOWN //第3步
 *   						|
	 MyTextView ---> dispatchTouchEvent action:ACTION_DOWN //第4步
 *   						|
	 MyTextView ---> onTouchEvent action:ACTION_DOWN //第5步
 *   						|
	 MainActivity ---> dispatchTouchEvent action:ACTION_UP
 *   						|
	 MyLinearLayout ---> dispatchTouchEvent action:ACTION_UP
 *   						|
	 MyLinearLayout ---> onInterceptTouchEvent action:ACTION_UP
 *   						|
	 MyTextView ---> dispatchTouchEvent action:ACTION_UP
 *   						|
	 MyTextView ---> onTouchEvent action:ACTION_UP

4.MyLinearLayout---onInterceptTouchEvent----->> return true 把事件拦截之后，只是交给它的onTouchEvent处理，事件到此终止
	
	 MainActivity ---> dispatchTouchEvent action:ACTION_DOWN //第1步
 *   						|
 	 MyLinearLayout ---> dispatchTouchEvent action:ACTION_DOWN //第2步
 *   						|
     MyLinearLayout ---> onInterceptTouchEvent action:ACTION_DOWN //第3步
 *   						|
 	 MyLinearLayout ---> onTouchEvent action:ACTION_DOWN //第4步，事件到此终止
 *   						|
	 MainActivity ---> dispatchTouchEvent action:ACTION_MOVE
 *   						|
	 MyLinearLayout ---> dispatchTouchEvent action:ACTION_MOVE
 *   						|
	 MyLinearLayout ---> onTouchEvent action:ACTION_MOVE
 *   						|
	 MainActivity ---> dispatchTouchEvent action:ACTION_UP
 *   						|
	 MyLinearLayout ---> dispatchTouchEvent action:ACTION_UP
 *   						|
	 MyLinearLayout ---> onTouchEvent action:ACTION_UP
	
5.MyLinearLayout---dispatchTouchEvent----->> return true 

	 MainActivity ---> dispatchTouchEvent action:ACTION_DOWN
 *   						|
	 MyLinearLayout ---> dispatchTouchEvent action:ACTION_DOWN
 *   						|
	 MainActivity ---> dispatchTouchEvent action:ACTION_MOVE
 *   						|
	 MyLinearLayout ---> dispatchTouchEvent action:ACTION_MOVE
 *   						|
	 MainActivity ---> dispatchTouchEvent action:ACTION_UP
 *   						|
	 MyLinearLayout ---> dispatchTouchEvent action:ACTION_UP
	
	
 <<<<<<-------------------------------------------------------------------->>>>>>>>>>>>>>
 	
 */
public class MainActivity extends Activity {

	private final String TAG = "MainActivity";
	// private MyTextView myTextView;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// myTextView = (MyTextView) findViewById(R.id.testview);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		switch(ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.d(TAG, TAG + " ---> dispatchTouchEvent action:ACTION_DOWN");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.d(TAG, TAG + " ---> dispatchTouchEvent action:ACTION_MOVE");
			break;
		case MotionEvent.ACTION_UP:
			Log.d(TAG, TAG + " ---> dispatchTouchEvent action:ACTION_UP");
			break;
		}
		return super.dispatchTouchEvent(ev);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.d(TAG, TAG + " ---> onTouchEvent action:ACTION_DOWN");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.d(TAG, TAG + " ---> onTouchEvent action:ACTION_MOVE");
			break;
		case MotionEvent.ACTION_UP:
			Log.d(TAG, TAG + " ---> onTouchEvent action:ACTION_UP");
			break;
		}
		return super.onTouchEvent(event);
	}
	
}