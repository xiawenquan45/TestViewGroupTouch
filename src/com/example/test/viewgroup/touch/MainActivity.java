package com.example.test.viewgroup.touch;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
/**
 * ����touch�¼��ַ�����
 * @author xiawenquan
 * activity ---touch----->> MyLinearLayout---touch----->> MyTextView---touch
 * 
 <<<<<<-------------------------MyTextView�ؼ����¼�����-------------------------------------------------------->>>>
 
 * 1.MyTextView---onTouchEvent--->> return false;
 * 1.1 ���� MyTextView
 *   MainActivity ---> dispatchTouchEvent action:ACTION_DOWN //��1��
 *   						|
 	 MyLinearLayout ---> dispatchTouchEvent action:ACTION_DOWN //��2��
 	 						|
	 MyLinearLayout ---> onInterceptTouchEvent action:ACTION_DOWN //��3��
	  	 					|
	 MyTextView ---> dispatchTouchEvent action:ACTION_DOWN //��4��
 *   						|
	 MyTextView ---> onTouchEvent action:ACTION_DOWN //��5��
 *   						|
	 MyLinearLayout ---> onTouchEvent action:ACTION_DOWN //��6��
 *   						|
	 MainActivity ---> onTouchEvent action:ACTION_DOWN //��7��
 *   						|
	 MainActivity ---> dispatchTouchEvent action:ACTION_UP //��8��
 *   						|
	 MainActivity ---> onTouchEvent action:ACTION_UP //��9��
 * 	
 * 1.1 ���� MyTextView�����л�������
 * 
 *  MainActivity ---> dispatchTouchEvent action:ACTION_DOWN //��1��
 *   						|
 	MyLinearLayout ---> dispatchTouchEvent action:ACTION_DOWN //��2��
 *   						|
 	MyLinearLayout ---> onInterceptTouchEvent action:ACTION_DOWN //��3��
 *   						|
 	MyTextView ---> dispatchTouchEvent action:ACTION_DOWN //��4��
 *   						|
 	MyTextView ---> onTouchEvent action:ACTION_DOWN //��5��
 *   						|
 	MyLinearLayout ---> onTouchEvent action:ACTION_DOWN //��6��
 *   						|
 	MainActivity ---> onTouchEvent action:ACTION_DOWN //��7��
 *   						|
 	MainActivity ---> dispatchTouchEvent action:ACTION_MOVE  //��8��
 *   						|
 	MainActivity ---> onTouchEvent action:ACTION_MOVE //��9��
 *   						|
 	MainActivity ---> dispatchTouchEvent action:ACTION_MOVE //�ظ�����
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
 	MainActivity ---> dispatchTouchEvent action:ACTION_UP  //��10��
 *   						|
 	MainActivity ---> onTouchEvent action:ACTION_UP //��11��
 *
 *
 *2.MyTextView---onTouchEvent--->> return true;
 *2.1 ���� MyTextView
 *  MainActivity ---> dispatchTouchEvent action:ACTION_DOWN //��1��
 *   						|
 	MyLinearLayout ---> dispatchTouchEvent action:ACTION_DOWN //��2��
 *   						|
 	MyLinearLayout ---> onInterceptTouchEvent action:ACTION_DOWN //��3��
 *   						|
 	MyTextView ---> dispatchTouchEvent action:ACTION_DOWN //��4��
 *   						|
 	MyTextView ---> onTouchEvent action:ACTION_DOWN //��5��
 *   						|
 	MainActivity ---> dispatchTouchEvent action:ACTION_UP //��6��
 *   						|
 	MyLinearLayout ---> dispatchTouchEvent action:ACTION_UP //��7��
 *   						|
 	MyLinearLayout ---> onInterceptTouchEvent action:ACTION_UP //��8��
 *   						|
 	MyTextView ---> dispatchTouchEvent action:ACTION_UP //��9��
 *   						|
 	MyTextView ---> onTouchEvent action:ACTION_UP //��10��
 	
 *2.2 ���� MyTextView�����л�������
 	MainActivity ---> dispatchTouchEvent action:ACTION_DOWN //��1��
 *   						|
 	MyLinearLayout ---> dispatchTouchEvent action:ACTION_DOWN //��2��
 *   						|
 	MyLinearLayout ---> onInterceptTouchEvent action:ACTION_DOWN //��3��
 *   						|
 	MyTextView ---> dispatchTouchEvent action:ACTION_DOWN //��4��
 *   						|
 	MyTextView ---> onTouchEvent action:ACTION_DOWN //��5��
 *   						|
 	MainActivity ---> dispatchTouchEvent action:ACTION_MOVE //��6��
 *   						|
 	MyLinearLayout ---> dispatchTouchEvent action:ACTION_MOVE //��7��
 *   						|
 	MyLinearLayout ---> onInterceptTouchEvent action:ACTION_MOVE //��8��
 *   						|
 	MyTextView ---> dispatchTouchEvent action:ACTION_MOVE //��9��
 *   						|
 	MyTextView ---> onTouchEvent action:ACTION_MOVE //��10��
 *   						|
 	MainActivity ---> dispatchTouchEvent action:ACTION_MOVE //�ظ�����
 *   						|
 	MyLinearLayout ---> dispatchTouchEvent action:ACTION_MOVE
 *   						|
 	MyLinearLayout ---> onInterceptTouchEvent action:ACTION_MOVE
 *   						|
 	MyTextView ---> dispatchTouchEvent action:ACTION_MOVE
 *   						|
 	MyTextView ---> onTouchEvent action:ACTION_MOVE
 *   						|
 	MainActivity ---> dispatchTouchEvent action:ACTION_UP //��11��
 *   						|
 	MyLinearLayout ---> dispatchTouchEvent action:ACTION_UP //��12��
 *   						|
 	MyLinearLayout ---> onInterceptTouchEvent action:ACTION_UP //��13��
 *   						|
 	MyTextView ---> dispatchTouchEvent action:ACTION_UP //��14��
 *   						|
 	MyTextView ---> onTouchEvent action:ACTION_UP //��15��
 	
 *3.MyTextView---dispatchTouchEvent--->> return true; 
 
 *   						|
 	MainActivity ---> dispatchTouchEvent action:ACTION_DOWN //��1��
 *   						|
    MyLinearLayout ---> dispatchTouchEvent action:ACTION_DOWN //��2��
 *   						|
 	MyLinearLayout ---> onInterceptTouchEvent action:ACTION_DOWN //��3��
 *   						|
	MyTextView ---> dispatchTouchEvent action:ACTION_DOWN //��4��
 *   						|
	MainActivity ---> dispatchTouchEvent action:ACTION_UP
 *   						|
	MyLinearLayout ---> dispatchTouchEvent action:ACTION_UP
 *   						|
	MyLinearLayout ---> onInterceptTouchEvent action:ACTION_UP
 *   						|
	MyTextView ---> dispatchTouchEvent action:ACTION_UP

 	
 <<<<<<-------------------------MyLinearLayout�ؼ��¼��������-------------------------------------------------------->>>>
 
 *1.MyLinearLayout---onTouchEvent----->> return false && MyTextView --- onTouchEvent--->>  return false
 	1.1 ��MyTextView��1��2��3
 	
 	
 *2.MyLinearLayout---onTouchEvent----->> return true && MyTextView --- onTouchEvent--->>  return false
2.1 ����MyTextView
 	
 	MainActivity ---> dispatchTouchEvent action:ACTION_DOWN //��1��
 *   						|
	 MyLinearLayout ---> dispatchTouchEvent action:ACTION_DOWN //��2��
 *   						|
	 MyLinearLayout ---> onInterceptTouchEvent action:ACTION_DOWN //��3��
 *   						|
	 MyTextView ---> dispatchTouchEvent action:ACTION_DOWN //��4��
 *   						|
	 MyTextView ---> onTouchEvent action:ACTION_DOWN //��5�� 
 *   						|
	 MyLinearLayout ---> onTouchEvent action:ACTION_DOWN//��6�� 
 *   						|
	 MainActivity ---> dispatchTouchEvent action:ACTION_UP
 *   						|
	 MyLinearLayout ---> dispatchTouchEvent action:ACTION_UP
 *   						|
	 MyLinearLayout ---> onTouchEvent action:ACTION_UP
 
2.1 ����MyTextView���һ���
 	
	 MainActivity ---> dispatchTouchEvent action:ACTION_DOWN //��1��
 *   						|
	 MyLinearLayout ---> dispatchTouchEvent action:ACTION_DOWN //��2��
 *   						|
	 MyLinearLayout ---> onInterceptTouchEvent action:ACTION_DOWN //��3��
 *   						|
	 MyTextView ---> dispatchTouchEvent action:ACTION_DOWN //��4��
 *   						|
	 MyTextView ---> onTouchEvent action:ACTION_DOWN //��5��
 *   						|
	 MyLinearLayout ---> onTouchEvent action:ACTION_DOWN //��6��
 *   						|
	 MainActivity ---> dispatchTouchEvent action:ACTION_MOVE //����
 *   						|
	 MyLinearLayout ---> dispatchTouchEvent action:ACTION_MOVE
 *   						|
	 MyLinearLayout ---> onTouchEvent action:ACTION_MOVE
 *   						|
	 MainActivity ---> dispatchTouchEvent action:ACTION_MOVE//�ظ�����
 *   						|
	 MyLinearLayout ---> dispatchTouchEvent action:ACTION_MOVE
 *   						|
	 MyLinearLayout ---> onTouchEvent action:ACTION_MOVE
 *   						|
	 MainActivity ---> dispatchTouchEvent action:ACTION_UP // ���뿪��Ļ
 *   						|
	 MyLinearLayout ---> dispatchTouchEvent action:ACTION_UP
 *   						|
	 MyLinearLayout ---> onTouchEvent action:ACTION_UP
 	
3.MyLinearLayout---onTouchEvent----->> return true && MyTextView --- onTouchEvent--->>  return true
 	
	 MainActivity ---> dispatchTouchEvent action:ACTION_DOWN //��1��
 *   						|
	 MyLinearLayout ---> dispatchTouchEvent action:ACTION_DOWN //��2��
 *   						|
	 MyLinearLayout ---> onInterceptTouchEvent action:ACTION_DOWN //��3��
 *   						|
	 MyTextView ---> dispatchTouchEvent action:ACTION_DOWN //��4��
 *   						|
	 MyTextView ---> onTouchEvent action:ACTION_DOWN //��5��
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

4.MyLinearLayout---onInterceptTouchEvent----->> return true ���¼�����֮��ֻ�ǽ�������onTouchEvent�����¼�������ֹ
	
	 MainActivity ---> dispatchTouchEvent action:ACTION_DOWN //��1��
 *   						|
 	 MyLinearLayout ---> dispatchTouchEvent action:ACTION_DOWN //��2��
 *   						|
     MyLinearLayout ---> onInterceptTouchEvent action:ACTION_DOWN //��3��
 *   						|
 	 MyLinearLayout ---> onTouchEvent action:ACTION_DOWN //��4�����¼�������ֹ
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