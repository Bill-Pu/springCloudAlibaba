package learning.order.config;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * 全局公共时间类常量
 * @author zhangliang
 * 2022年4月15日 下午3:32:29
 */
public abstract class CommonDateConstants {
	public static final String startHHmmss = " 00:00:00";

	public static final String endtHHmmss = " 23:59:59";

	public static final String timeGmt = "GMT+8" ;
	
	public static final String timeStr = "Asia/Shanghai" ;
	
	public static final ZoneId zoneId = ZoneId.of(timeStr) ;
	
	public static final TimeZone timeZone = TimeZone.getTimeZone(zoneId) ;
	
	public static final String patternyyyyMMddHHmmss = "yyyyMMddHHmmss";
	
	public static final String pattern = "yyyy-MM-dd HH:mm:ss" ;
	
	public static final String patternYYYYBMMBDD = "yyyy-MM-dd" ;

	public static final String patternYYYYMM = "yyyyMM" ;

	public static final String patternYYYYMMDD = "yyyyMMdd" ;
	public static final String patternYYYYMMDD2 = "yyyy.MM.dd" ;

	public static final String pattern2 = "yyyy年MM月dd日 HH:mm:ss";

	public static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern(patternyyyyMMddHHmmss).withZone(zoneId) ;
	
	public static final DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern(pattern).withZone(zoneId) ;
	
	public static final DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern(patternYYYYMMDD).withZone(zoneId);
	
	public static final DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern(patternYYYYBMMBDD).withZone(zoneId) ;

	public static final DateTimeFormatter dtf5 = DateTimeFormatter.ofPattern(patternYYYYMMDD2).withZone(zoneId);


	public static final DateTimeFormatter dtf6 = DateTimeFormatter.ofPattern(patternYYYYMM).withZone(zoneId);

	public static final DateTimeFormatter dateTimeFormatterYYYYMM = DateTimeFormatter.ofPattern(patternYYYYMM).withZone(zoneId);



	public static final String patternHHMMSS = "HH:mm:ss" ;
	
	public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern).withZone(zoneId) ;
	
	public static final DateTimeFormatter dateTimeFormatterYYYYMMDD = DateTimeFormatter.ofPattern(patternYYYYBMMBDD).withZone(zoneId) ;
	
	public static final DateTimeFormatter dateTimeFormatterHHMMSS = DateTimeFormatter.ofPattern(patternHHMMSS).withZone(zoneId) ;
	
	public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern) ;

	public static final SimpleDateFormat sdf = new SimpleDateFormat(patternyyyyMMddHHmmss);
}
