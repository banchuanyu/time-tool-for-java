package com.pianlin.time.util;

import java.util.Calendar;

import junit.framework.Assert;
import junit.framework.TestCase;

public class DateCompareUtilTest extends TestCase {
	
	public void testGetDiffInYearDateDate() {

		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		
		// 完全相等
		cal1.set(2003, 1, 28, 1, 1, 1);
		cal2.setTime(cal1.getTime());
		Assert.assertEquals(0, DateCompareUtil.getDiffInYear(cal1.getTime(), cal2.getTime()));
		
		// 年份相等
		cal1.set(2003, 2, 28, 1, 1, 1);
		cal2.set(2003, 1, 28, 1, 1, 1);
		Assert.assertEquals(0, DateCompareUtil.getDiffInYear(cal1.getTime(), cal2.getTime()));
		
		// 负数
		cal1.set(2003, 1, 28, 1, 1, 1);
		cal2.set(2002, 0, 28, 1, 1, 1);
		Assert.assertEquals(-1, DateCompareUtil.getDiffInYear(cal1.getTime(), cal2.getTime()));
		
		// 较小但仍为0
		cal1.set(2003, 1, 28, 1, 1, 1);
		cal2.set(2002, 3, 28, 1, 1, 1);
		Assert.assertEquals(0, DateCompareUtil.getDiffInYear(cal1.getTime(), cal2.getTime()));
		
		// 闰年
		cal1.set(2003, 1, 29, 1, 1, 1);
		cal2.set(2004, 1, 29, 1, 1, 1);
		Assert.assertEquals(0, DateCompareUtil.getDiffInYear(cal1.getTime(), cal2.getTime()));
	}

	public void testGetDiffInYearCalendarCalendar() {

		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		// 完全相等
		cal1.set(2003, 1, 28, 1, 1, 1);
		cal2.setTime(cal1.getTime());
		Assert.assertEquals(0, DateCompareUtil.getDiffInYear(cal1, cal2));
		
		// 年份相等
		cal1.set(2003, 2, 28, 1, 1, 1);
		cal2.set(2003, 1, 28, 1, 1, 1);
		Assert.assertEquals(0, DateCompareUtil.getDiffInYear(cal1, cal2));
		
		// 负数
		cal1.set(2003, 1, 28, 1, 1, 1);
		cal2.set(2002, 0, 28, 1, 1, 1);
		Assert.assertEquals(-1, DateCompareUtil.getDiffInYear(cal1, cal2));
		
		// 较小但仍为0
		cal1.set(2003, 1, 28, 1, 1, 1);
		cal2.set(2002, 3, 28, 1, 1, 1);
		Assert.assertEquals(0, DateCompareUtil.getDiffInYear(cal1, cal2));
		
		// 闰年
		cal1.set(2003, 1, 29, 1, 1, 1);
		cal2.set(2004, 1, 29, 1, 1, 1);
		Assert.assertEquals(0, DateCompareUtil.getDiffInYear(cal1, cal2));
	}

}
