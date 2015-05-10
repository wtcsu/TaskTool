/**
 * 
 */
package com.donglihan.CollegeLifeManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;

/**
 * @author donglihan 西北工业大学软件与微电1231321141子学院 RJ010802
 * @version 1
 */
public class course_set_activity extends Activity
{

	Button btn_course_set_confirm;
	DatePicker course_first_week;
	int year = 0;
	int month = 0;
	int day = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.course_set);

		btn_course_set_confirm = (Button) findViewById(R.id.btn_course_set_confirm);
		btn_course_set_confirm.setOnClickListener(btn_confirm_listener);

		course_first_week = (DatePicker) findViewById(R.id.course_first_week);
		day = course_first_week.getDayOfMonth();
		month = course_first_week.getMonth();
		year = course_first_week.getYear();
		Log.e("y1", " " + year);
	}

	// 按钮监听函数
	private Button.OnClickListener btn_confirm_listener = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{

			day = course_first_week.getDayOfMonth();
			month = course_first_week.getMonth();
			year = course_first_week.getYear();
			// Log.e("y", " " +year);
			// TODO Auto-generated method stub
			Bundle bundle = new Bundle();
			bundle.putInt("year", year);
			bundle.putInt("month", month);
			bundle.putInt("day", day);

			Intent intent = new Intent();
			intent.putExtras(bundle);
			setResult(RESULT_OK, intent);
			Log.e("y2", " " + year);
			finish();
		}
	};

}
