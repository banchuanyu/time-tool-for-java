package com.pianlin.time.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 日期比较
 * 
 * @author banchuanyu
 * 
 */
public class DateCompareUtil {

	/**
	 * 返回second - first的年差 若相差不到1年 则按0计算 若相差1年以上 2年以下 则按1记 依此类推
	 * 
	 * @param first
	 * @param second
	 * @return diffInYear
	 */
	public static int getDiffInYear(Date first, Date second){
		// calendar first
    	Calendar calendarFirst = Calendar.getInstance();
    	calendarFirst.setTime(first);
    	
    	// calendar second
    	Calendar calendarSecond = Calendar.getInstance();
    	calendarSecond.setTime(second);
    	
    	return getDiffInYear(calendarFirst, calendarSecond);
    }
	
	/**
	 * 返回calendarSecond - calendarFirst的年差 若相差不到1年 则按0计算 若相差1年以上 2年以下 则按1记 依此类推
	 * 
	 * @param calendarFirst
	 * @param calendarSecond
	 * @return diffInYear
	 */
	public static int getDiffInYear(Calendar calendarFirst, Calendar calendarSecond){
    	
    	int diffInYear = calendarSecond.get(Calendar.YEAR) - calendarFirst.get(Calendar.YEAR);
    	
    	// 使calendar first 与 calendar second同年
    	calendarFirst.add(Calendar.YEAR, diffInYear);
    	
    	if (diffInYear > 0) {
    		// 若yearSecond > yearFirst 并且同年后的calendarFirst > calendarSecond
			if (calendarFirst.after(calendarSecond)) {
				diffInYear--;
			}
		} else {
			// 若yearSecond < yearFirst 并且同年后的calendarFirst < calendarSecond
			if (calendarFirst.before(calendarSecond)) {
				diffInYear++;
			}
		}
    	
    	return diffInYear;
    }


}
