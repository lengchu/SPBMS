package cn.lenchu.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SpbmsUtils {
	
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String date() {
		return sdf.format(new Date());
	}
}
