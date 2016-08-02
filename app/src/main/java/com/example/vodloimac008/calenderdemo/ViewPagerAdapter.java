package com.example.vodloimac008.calenderdemo;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;



public class ViewPagerAdapter extends PagerAdapter {
	// Declare Variables
	Context context;
	ArrayList<String> arrDate = new ArrayList<String>();
	int currentMonth;
	Dialog dialog1,dialog;
	LayoutInflater inflater;
	private Calendar cal;
	 private int day;
	 private int month1;
	 private int year1;
	int screenWidth, screenHeight;



	public ViewPagerAdapter(Context context, ArrayList<String> arrDate, int month) {
		this.context = context;
		this.arrDate = arrDate;
		this.currentMonth = month;
		cal = Calendar.getInstance();
		day = cal.get(Calendar.DAY_OF_MONTH);
		month1 = cal.get(Calendar.MONTH);
		year1 = cal.get(Calendar.YEAR);

		screenWidth = ((Activity) (context)).getWindowManager().getDefaultDisplay().getWidth();
		screenHeight = ((Activity) (context)).getWindowManager().getDefaultDisplay().getHeight();

	}

	@Override
	public int getCount() {
		return arrDate.size();
	}
	
	/*@Override
	public float getPageWidth(int position) {
		// TODO Auto-generated method stub
		 return(0.96f);
	}*/

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == ((LinearLayout) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, final int position) {

		// Declare Variables
		TextView txtrank;
		TextView txtcountry;
		TextView txtpopulation;
		ImageView imgflag;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.row_calender, container,
				false);

		// Locate the TextViews in viewpager_item.xml
		txtrank = (TextView) itemView.findViewById(R.id.rank);
		txtrank.setText(arrDate.get(position));

		//Button btnEdit = (Button)itemView.findViewById(R.id.btnEdit);
		((ViewPager) container).addView(itemView);


//		btnEdit.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//
//			}
//		});


		return itemView;
	}

	

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// Remove viewpager_item.xml from ViewPager
		((ViewPager) container).removeView((LinearLayout) object);

	}






}
