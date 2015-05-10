/**
 * 
 */
package com.donglihan.CollegeLifeManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;

/**
 * @author donglihan 西北工业大学软件与微电子学院 RJ010802
 *@version 1
 */
public class score_activity extends Activity {
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.score);
		setTitle("评个分吧O(∩_∩)O~");

	RatingBar ratingBar = (RatingBar) findViewById(R.id.rating_bar);
	ratingBar.setOnRatingBarChangeListener(
			new RatingBar.OnRatingBarChangeListener() {  
	        @Override  
	        public void onRatingChanged(RatingBar ratingBar,  
	                float rating, boolean fromUser) {  
	            // TODO Auto-generated method stub  
	            ratingBar.setRating(rating);  
	            Toast.makeText(score_activity.this,  
	                    "谢谢评分！", Toast.LENGTH_SHORT).show();
	            finish();
	        	}         
			}
			);  
	}
}
