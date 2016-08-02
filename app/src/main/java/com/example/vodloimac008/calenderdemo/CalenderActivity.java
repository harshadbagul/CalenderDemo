package com.example.vodloimac008.calenderdemo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class CalenderActivity  extends AppCompatActivity implements OnClickListener{

		Dialog dialog1;
		private TextView currentMonth;
	    private ImageView prevMonth;
	    private ImageView nextMonth;
	    private GridView calendarView;
	    private GridCellAdapter adapter;
	    private Calendar _calendar;
	    private int month, year;
	    private static final String dateTemplate = "MMMM yyyy";
	    String flag ="abc";
	    String date_month_year;
		GridView gview;
		View previousView = null;
		View mPreviousView = null;
		Spinner spin1,spin4;
		TextView spin3;
		LinearLayout spin2;
		TextView tvDate;
		private Calendar cal;
		 private int day;
		 private int month1;
		 private int year1;
		 Dialog dialog;
		 public static int count = 0;
		 ArrayList<String> arrDay = new ArrayList<String>();
		 ArrayList<String> arrMonth = new ArrayList<String>();
		 ArrayList<String> arrYear = new ArrayList<String>();
		 int mPrevMonth,mPrevYear;
		 int mNextMonth,mNextYear;
		 boolean mFlag = false;
		 int mValue ,mtrailingSpaces;
		 ViewPager viewPager;
		 ViewPagerAdapter pagerAdapter;
		 public static String myDate = "Select Date";
		 ActionBar actionBar;
		 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calender);

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

		cal = Calendar.getInstance();
		day = cal.get(Calendar.DAY_OF_MONTH);
		month1 = cal.get(Calendar.MONTH);
		year1 = cal.get(Calendar.YEAR);

		viewPager = (ViewPager) findViewById(R.id.pager);
		_calendar = Calendar.getInstance(Locale.getDefault());
        month = _calendar.get(Calendar.MONTH) + 1;
        year = _calendar.get(Calendar.YEAR);
        gview = (GridView)findViewById(R.id.calendar);

        prevMonth = (ImageView) this.findViewById(R.id.prevMonth);
        prevMonth.setOnClickListener(this);

        currentMonth = (TextView) this.findViewById(R.id.currentMonth);
        currentMonth.setText(DateFormat.format(dateTemplate, _calendar.getTime()));


        nextMonth = (ImageView) this.findViewById(R.id.nextMonth);
        nextMonth.setOnClickListener(this);

        calendarView = (GridView) this.findViewById(R.id.calendar);

        // Initialised
        adapter = new GridCellAdapter(getApplicationContext(), R.id.calendar_day_gridcell, month, year);
        adapter.notifyDataSetChanged();
        calendarView.setAdapter(adapter);



		
	}
	

	public void createEventView(int days, int currentmonth){
		
		ArrayList<String> arrDate = new ArrayList<String>();
		
		for (int i = 1; i <= days; i++) {
			arrDate.add(i+"");
		}
		
		/*pagerAdapter = new ViewPagerAdapter(CalenderActivity.this, arrDate,currentmonth);
		viewPager.setAdapter(pagerAdapter);*/
	}

	 @Override
	 @Deprecated
	 protected Dialog onCreateDialog(int id) {
		 
		 switch (id) {
		case 0:
			
			return new DatePickerDialog(this, datePickerListener, year1, month1, day);
		}
		 return null;
	 }
	 private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		  public void onDateSet(DatePicker view, int selectedYear,
		    int selectedMonth, int selectedDay) {
		   /*et.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
		     + selectedYear);*/
			  String  date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
		   
		  	  myDate = date;
		   
		  }
		 };
	 private void setGridCellAdapterToDate(int month, int year, String next){
         adapter = new GridCellAdapter(getApplicationContext(), R.id.calendar_day_gridcell, month, year);
         _calendar.set(year, month - 1, _calendar.get(Calendar.DAY_OF_MONTH));
         currentMonth.setText(DateFormat.format(dateTemplate, _calendar.getTime()));
         adapter.notifyDataSetChanged();
         calendarView.setAdapter(adapter);
         Animation animation;
         if (next.equals("prev")){
             animation = AnimationUtils.loadAnimation(CalenderActivity.this, R.anim.left_in);

         }else{
             animation = AnimationUtils.loadAnimation(CalenderActivity.this, R.anim.right_in);
         }

         currentMonth.setAnimation(animation);
         calendarView.setAnimation(animation);
     }

	 @Override
     public void onClick(View v){
             if (v == prevMonth){
                 if (month <= 1){
                     month = 12;
                     year--;
                 }
                 else
                     month--;
                 setGridCellAdapterToDate(month, year,"prev");
             }
             if (v == nextMonth){
                 if (month > 11){
                     month = 1;
                     year++;
                 }
                 else
                     month++;
                 setGridCellAdapterToDate(month, year,"next");
             }
             
     }


	 
	 
     public class GridCellAdapter extends BaseAdapter
         {
             private final Context _context;
             String[] day_color;

             private final List<String> list;
             private static final int DAY_OFFSET = 1;
             private final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
             private final int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
             private int daysInMonth;
             private int currentDayOfMonth;
             private int currentWeekDay;
             private Button gridcell;
             private TextView tvEvent1,tvEvent2,tvEvent3,tvEvent4;
             private final HashMap<String, String> eventsPerMonthMap;
             private int selectedMonth;
             private int selectedYear;



             // Days in Current Month
             public GridCellAdapter(Context context, int textViewResourceId, int month, int year){
                     super();
                     this._context = context;
                     this.list = new ArrayList<String>();
                     this.selectedMonth = month;
                     this.selectedYear = year;
                     Calendar calendar = Calendar.getInstance();
                     setCurrentDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH));
                     setCurrentWeekDay(calendar.get(Calendar.DAY_OF_WEEK));

                     // Print Month
                     printMonth(month, year);

                     // Find Number of Events
                     eventsPerMonthMap = findNumberOfEventsPerMonth(year, month);
                     
                 }
             private String getMonthAsString(int i){
                     return months[i];
                 }

             private int getNumberOfDaysOfMonth(int i){
                     return daysOfMonth[i];
                 }

             public String getItem(int position){
                     return list.get(position);
                 }

             @Override
             public int getCount(){
                     return list.size();
                 }

             private void printMonth(int mm, int yy){
            	 
            	 arrDay = new ArrayList<String>();
            	 arrMonth = new ArrayList<String>();
            	 arrYear = new ArrayList<String>();
            	 
                     int trailingSpaces = 0;
                     int daysInPrevMonth = 0;
                     int prevMonth = 0;
                     int prevYear = 0;
                     int nextMonth = 0;
                     int nextYear = 0;

                     int currentMonth = mm - 1;
                     daysInMonth = getNumberOfDaysOfMonth(currentMonth);

                     createEventView(daysInMonth ,currentMonth);
                     
                     // Gregorian Calendar : MINUS 1, set to FIRST OF MONTH
                     GregorianCalendar cal = new GregorianCalendar(yy, currentMonth, 1);

                     if (currentMonth == 11){
                             prevMonth = currentMonth - 1;
                             daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
                             nextMonth = 0;
                             prevYear = yy;
                             nextYear = yy + 1;
                         }
                     else if (currentMonth == 0){
                             prevMonth = 11;
                             prevYear = yy - 1;
                             nextYear = yy;
                             daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
                             nextMonth = 1;
                         }
                     else{
                             prevMonth = currentMonth - 1;
                             nextMonth = currentMonth + 1;
                             nextYear = yy;
                             prevYear = yy;
                             daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
                         }

                     int currentWeekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
                     trailingSpaces = currentWeekDay;

                     mtrailingSpaces = trailingSpaces;
                     
                     if (cal.isLeapYear(cal.get(Calendar.YEAR)) && mm == 1){
                             ++daysInMonth;
                         }

                     // Trailing Month days
                     for (int i = 0; i < trailingSpaces; i++){
                    	 
                    	 	 arrDay.add(String.valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET) + i));
                    	 	 
                    	 	 
                    	 	 mPrevMonth = prevMonth + 1 ;
                    	 	 mPrevYear = prevYear;
                    	 	 
                    	 	 
                             list.add(String.valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET) + i) + "-GREY" + "-" + getMonthAsString(prevMonth) + "-" + prevYear);
                         }

                 System.out.println(" Trailing Month days arrDay "+arrDay);
                     
                     // Current Month Days
                     for (int i = 1; i <= daysInMonth; i++){
                             if (i == getCurrentDayOfMonth())
                                     list.add(String.valueOf(i) + "-BLUE" + "-" + getMonthAsString(currentMonth) + "-" + yy);
                             else
                                     list.add(String.valueOf(i) + "-WHITE" + "-" + getMonthAsString(currentMonth) + "-" + yy);
                         }

                     // Leading Month days
                     for (int i = 0; i < list.size() % 7; i++){
                    	 
                    	 	 arrDay.add(String.valueOf(i + 1) );
                    	 	 
                    	 	 mNextMonth = nextMonth + 1 ; 
                    	 	 mNextYear = nextYear ;
                    	 	 	
                             list.add(String.valueOf(i + 1) + "-GREY" + "-" + getMonthAsString(nextMonth) + "-" + nextYear);
                         }


                 System.out.println("All list ##### "+list);
                 }

             private HashMap<String, String> findNumberOfEventsPerMonth(int year, int month){
                     HashMap<String, String> map = new HashMap<String, String>();
                     map.put("10","2");
                     map.put("2","1");
                     map.put("18","4");
                     map.put("25","1");
                     map.put("11","3");
                     map.put("28","2");
                     return map;
                 }

             @Override
             public long getItemId(int position){
                     return position;
                 }

             @Override
             public View getView(final int position, View convertView, ViewGroup parent){
                     View row = convertView;
                     if (row == null){
                             LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                             row = inflater.inflate(R.layout.calender_gridcell, parent, false);
                         }

                     // Get a reference to the Day gridcell
                     gridcell = (Button) row.findViewById(R.id.calendar_day_gridcell);
                     tvEvent1 = (TextView) row.findViewById(R.id.tvEvent1);
                     tvEvent2 = (TextView) row.findViewById(R.id.tvEvent2);
                     tvEvent3 = (TextView) row.findViewById(R.id.tvEvent3);
                     tvEvent4 = (TextView) row.findViewById(R.id.tvEvent4);

                     // ACCOUNT FOR SPACING
                     day_color = list.get(position).split("-");
                     String theday = day_color[0];
                     String colorStatus = day_color[1];
                     String themonth = day_color[2];
                     String theyear = day_color[3];

                     // Set the Day GridCell
                     if (colorStatus.equals("GREY")){
                         gridcell.setText(theday);  // these are empty days
                         gridcell.setTextColor(Color.LTGRAY);
                     }else if(colorStatus.equals("BLUE")){
                         gridcell.setText(theday); // this is today day
                         ///// Checking wherather selected year and month are belongs to particular day ex. 12 - 4 - 2016 then 12 should not be selected for the all month or years
                         gridcell.setTextColor(Color.GRAY);
                         if ((month1+1) == selectedMonth && year1 == selectedYear){
                             gridcell.setBackgroundResource(R.drawable.circle_green);
                         }
                     }else if(colorStatus.equals("WHITE")){
                         gridcell.setText(theday); // these are current day in month
                         gridcell.setTextColor(Color.GRAY);
                     }

                    gridcell.setTag(theday + "-" + themonth + "-" + theyear);


                     if ((!eventsPerMonthMap.isEmpty()) && (eventsPerMonthMap != null)){

                             int color = getCellColor(gridcell);

                             if (color ==  Color.LTGRAY){
                                 System.out.println("background color is LTGRAY");
                             }else{

                                 if (eventsPerMonthMap.containsKey(theday)){
                                     String value = eventsPerMonthMap.get(theday);
                                     if (value.equals("1")){
                                         tvEvent1.setVisibility(View.VISIBLE);
                                     }else if(value.equals("2")){
                                         tvEvent1.setVisibility(View.VISIBLE);
                                         tvEvent2.setVisibility(View.VISIBLE);
                                     }else if(value.equals("3")){
                                         tvEvent1.setVisibility(View.VISIBLE);
                                         tvEvent2.setVisibility(View.VISIBLE);
                                         tvEvent3.setVisibility(View.VISIBLE);
                                     }else if(value.equals("4")){
                                         tvEvent1.setVisibility(View.VISIBLE);
                                         tvEvent2.setVisibility(View.VISIBLE);
                                         tvEvent3.setVisibility(View.VISIBLE);
                                         tvEvent4.setVisibility(View.VISIBLE);
                                     }
                                 }

                             }


                         }



                    /* if (day_color[1].equals("GREY"))
                             gridcell.setTextColor(Color.LTGRAY);

                     if (day_color[1].equals("WHITE"))
                             gridcell.setTextColor(Color.GRAY);
                     			//gridcell.setFocusable(false);


                    if (day_color[1].equals("BLUE"))
                             gridcell.setTextColor(Color.GRAY);*/
                    
                    int k =  (mtrailingSpaces + mValue)-1;
                    
                    if (mFlag == true  &&  position == k) {
					
                    	//gridcell.setBackgroundResource(R.drawable.backgroudcolor);
                    	mPreviousView = (View)gridcell;
                    	mFlag = false;
                    }
                    
                    
                    gridcell.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub

                            int color = getCellColor(arg0);

                            if (color ==  Color.LTGRAY){
                                System.out.println("background color is LTGRAY");
                            }else{

                                if (previousView != null ) {
                                    previousView.setBackgroundColor(Color.WHITE);
                                }
                                previousView = arg0;
                                arg0.setBackgroundResource(R.drawable.circle_grey);

                                if (mPreviousView != null) {
                                    mPreviousView.setBackgroundColor(Color.WHITE);
                                }

                            }



			         		
			         	/*	Button button = (Button)arg0.findViewById(R.id.calendar_day_gridcell);
			         		String text = button.getText().toString();
			         		
			         		int i = Integer.parseInt(text);
			         		
			         		//viewPager.setCurrentItem(i-1, true);
			         		
			         		//horizontalScrollView.smoothScrollTo(x-(k+100), 0);
			         		
			         		ColorStateList mList = button.getTextColors();
			         		int color = mList.getDefaultColor();
			         		
			         		switch(color)
			         		{
				         		case Color.LTGRAY:
				         			System.out.println("yoyoyo  ");
				         			System.out.println("click val is :  "+i);
				         			ArrayList<String> mArr = new ArrayList<String>();
				         			mArr.add("1");
				         			mArr.add("2");
				         			mArr.add("3");
				         			mArr.add("4");
				         			mArr.add("5");
				         			mArr.add("6");
				         			mArr.add("7");
 				         			
				         			mValue = i;
				         			mFlag = true;
				         			
				         			if (mArr.contains(""+i)) {
										
				         				System.out.println("Go to next month");
				         				
				         				setGridCellAdapterToDate(mNextMonth, mNextYear);
				         				
									}else {
										 System.out.println("Go to previous  month");
										 setGridCellAdapterToDate(mPrevMonth, mPrevYear);
									}
				         			
				         		viewPager.setCurrentItem(i-1, true);
				         			
				         		break;
			         		}*/
			         		
						}
					});
                    
                    
                    
                     return row;
                 }

             private int getCellColor(View arg0) {
                 Button button = (Button)arg0.findViewById(R.id.calendar_day_gridcell);
                 ColorStateList mList = button.getTextColors();
                 return mList.getDefaultColor();
             }


             public int getCurrentDayOfMonth(){
                     return currentDayOfMonth;
                 }

             private void setCurrentDayOfMonth(int currentDayOfMonth){
                     this.currentDayOfMonth = currentDayOfMonth;
                 }
             public void setCurrentWeekDay(int currentWeekDay){
                     this.currentWeekDay = currentWeekDay;
                 }
             public int getCurrentWeekDay(){
                     return currentWeekDay;
                 }
         }

	
	 
	
	
	
}
