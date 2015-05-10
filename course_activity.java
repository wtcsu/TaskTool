/**
 * 
 */
package com.donglihan.CollegeLifeManager;

import java.sql.Date;
import java.util.Calendar;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 *@author donglihan ������ҵ��ѧ�����΢����ѧԺ RJ010802
 *@version 1
 */
public class course_activity extends ListActivity {
	
	//�˵�ѡ��
	public static final int NEW = Menu.FIRST;
	public static final int SET = Menu.FIRST + 1;
	public static final int DELETE = Menu.FIRST + 2;
	public static final int HELP = Menu.FIRST + 3;
	
	//��ֵȷ�ϵĹؼ���
	private static final int REQUEST_SET = 0;
	private static final int REQUEST_NEW = 1;
	
	//Ĭ�ϵĵ�һ�ܿ�ʼʱ��
	int first_year = 2010;
	int first_month = 9;
	int first_day = 1;	
	@SuppressWarnings("deprecation")
	Date start_date = new Date(first_year,first_month,first_day);
	
	//�½��Ŀγ���Ϣ
	String course_name = "";
	String week_start  = "";
	String week_end  = "";
	String course_index1  = "";
	String course_place  = "";
	String week_index  = "";
	
	//��ǰ����
	Calendar c = Calendar.getInstance();
	int now_year = c.get(Calendar.YEAR);
	int now_month = c.get(Calendar.MONTH);
	int now_day = c.get(Calendar.DAY_OF_MONTH);
	Date now_date = new Date(now_year,now_month,now_day);
	
	//�����ǵڼ���
	int interval_weeks = 1;
	
	//���ݿ����
	private DbAdapter mDbHelper;
	private Cursor mCourseCursor;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("���ܿγ���Ϣ");
		setContentView(R.layout.course_activity);
		
		mDbHelper = new DbAdapter(this);
		updateCourseView();
	}
	/**
	 * ����listactivity������
	 */
	private void updateCourseView() {
		// TODO Auto-generated method stub
		Log.e("done", "getcourse");
		mDbHelper.open();
		mCourseCursor = mDbHelper.getAllCourses(interval_weeks);
        Toast.makeText(course_activity.this,  
                "��ǰ�ǵ�"+interval_weeks +"��,���menu����", Toast.LENGTH_SHORT).show();
		Log.e("weeks"," " +interval_weeks);
		Log.e("done", "donegetcourse");
		setTitle("��"+interval_weeks +"�� "+"�γ���Ϣ");
		startManagingCursor(mCourseCursor);
		
		String[] from = new String[] { DbAdapter.KEY_NAME, DbAdapter.KEY_PLACE, DbAdapter.KEY_INDEX, DbAdapter.KEY_WEEK_INDEX};
		int[] to = new int[] { R.id.item_name, R.id.item_place, R.id.item_index, R.id.item_week_index };
		SimpleCursorAdapter courses = new SimpleCursorAdapter(this,
				R.layout.course_list_item, mCourseCursor, from, to);
		setListAdapter(courses);
		mDbHelper.closeclose();
	}
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		 super.onCreateOptionsMenu(menu);
		 menu.add(0, NEW, 0, "�½�").setIcon(R.drawable.new_course);
		menu.add(0, SET, 0, "����").setIcon(R.drawable.setting);
		menu.add(0, DELETE, 0, "ɾ��").setIcon(R.drawable.delete);
		menu.add(0, HELP, 0, "����").setIcon(R.drawable.helps);
		return true;
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case HELP: 
			 Intent help_intent = new Intent();
			 help_intent.setClass(course_activity.this, help_activity.class);
			 startActivity(help_intent);
			 return true;
		case NEW:
			 Intent new_course_intent = new Intent();
			 new_course_intent.setClass(course_activity.this, course_new_activity.class);
			 startActivityForResult(new_course_intent, REQUEST_NEW);
			 return true;
		case SET: 
			Intent set_intent = new Intent();
			set_intent.setClass(course_activity.this, course_set_activity.class);
			startActivityForResult(set_intent, REQUEST_SET);
			 return true;
		case DELETE:
			mDbHelper.open();
			mDbHelper.deleteCourse(getListView().getSelectedItemId());
			mDbHelper.closeclose();
			updateCourseView();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	//�õ������ǵڼ���
	private int get_interval_weeks(Date ds, Date de)
	{
		   long total = (de.getTime()-ds.getTime())/(24*60*60*1000);
			Log.e("total"," " +total);
		   return ((int)(total/7.0) + 1);
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		//���õ�һ�ܵĻظ�
		if(requestCode == REQUEST_SET)
		{
			if(resultCode == RESULT_OK)
			{
				Bundle extras = data.getExtras();
				if(extras != null)
				{
					first_year = extras.getInt("year");
					first_month = extras.getInt("month");
					first_day = extras.getInt("day");
					start_date = new Date(first_year,first_month,first_day);
					Log.e("now_month"," " +now_month);
					Log.e("now_day"," " +now_day);
					interval_weeks = get_interval_weeks(start_date, now_date);
			        Toast.makeText(course_activity.this,  
			                "��ǰ�ǵ�"+interval_weeks +"��", Toast.LENGTH_LONG).show();
					setTitle("��"+interval_weeks +"�� "+"�γ���Ϣ");
					Log.e("weeks"," " +interval_weeks);
				}
				
			}
		}
		
		//����¿γ���Ϣ�Ļظ�
		else if(requestCode == REQUEST_NEW)
		{
			if(resultCode == RESULT_OK)
			{
				updateCourseView();
			}
		}
	}
}





