
package com.yzy.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
	
	
	/**
	 * java.util.Date转String
	 */
	public static String convertDateToStr(java.util.Date date) {
		SimpleDateFormat myformatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String mycurrentdate = myformatter.format(date);
		return mycurrentdate;
	}
	
	
	 /**
	  *@author ly
	  *时间样式字符串转换成时间
	  */
	 public static Timestamp dataFormatToTimestamp(String arg0, String arg1) {
			DateFormat dateFormat = new SimpleDateFormat(arg1, Locale.ENGLISH);// 设定格式
			dateFormat.setLenient(false);
			Date timeDate = null;
			try {
				timeDate = dateFormat.parse(arg0);
			} catch (ParseException e) {
			}
			return new Timestamp(timeDate.getTime());
		}
	 
	 public static String getForDate(String arg0){
			String formatData="";
			try{
				SimpleDateFormat sdf=new SimpleDateFormat(arg0);
				Calendar rightNow = Calendar.getInstance();
				formatData=sdf.format(rightNow.getTime());
			}catch(Exception ex){
			}
			return formatData;
		}
	 
	 
	 
	 public static String IntegerToDate(Integer integer){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 Long time = ((long)(integer))*1000;
			return df.format(new Date(time));
		}
	 
	 
	 /**转换为时间戳*/
		public static String getStrTimeStamp(String time)throws ParseException{
			String strTime = "";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date=simpleDateFormat .parse(time);
			Long timeStemp = date.getTime();
			strTime = timeStemp.toString();
			strTime = strTime.substring(0, strTime.length()-3);
			return strTime;
		}

		/**获取一周前的时间*/
		public static String getStrLastWeek(){
			SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dateNow = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateNow);
//			cl.add(Calendar.DAY_OF_YEAR, -1);	//上一天
//			calendar.set(Calendar. HOUR , Calendar. HOUR -1 ); //上一个小时
			calendar.add(Calendar.WEEK_OF_YEAR, -1);	//上一周
//			cl.add(Calendar.MONTH, -1);			//上一个月
			Date dateFrom = calendar.getTime();
			return sdf.format(dateFrom);
		}
		
		/**获取24小时前的时间*/
		public static String getStrLastHour(){
			SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dateNow = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateNow);
			calendar.add(Calendar.DATE, -1);//注意：这里add和set有区别
			Date dateFrom = calendar.getTime();
			return sdf.format(dateFrom);
		}
		
		/**比较2个日期时间,返回最近的日期*/
		public static String compareDate(String DATE1, String DATE2) {
			String str = "";
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			try {
				Date dt1 = df.parse(DATE1);
				Date dt2 = df.parse(DATE2);
				if (dt1.getTime() > dt2.getTime()) {
					str = DATE1;
				} else if (dt1.getTime() < dt2.getTime()) {
					str = DATE2;
				} else {
					str = DATE1;
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
			return str;
		}
		
		
		
		public static String getForDay(){
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String createTime = sdf.format(date);
			return createTime;
		}
		
		public String createTime(){
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createTime = sdf.format(date);
			return createTime;
		}
		/**
		 * 
		 * 获取当前时间
		 */
		public static String getCurrentTime(){
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(date);
			return time;
		}
		
		/**获取 传入时间的几个月前、后的时间
		 * 传入参数：dateNow 传入时间，
		 * 		nMonth（月份数 获取几个月前的时间值为负，获取几个月后的时间值为正）
		 * */
		public static Date getTimeMonthsAOrL(Date dateNow, Integer nMonth){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateNow);
			calendar.add(Calendar.MONTH, nMonth);	
			Date dateFrom = calendar.getTime();
			return dateFrom;
		}

}
