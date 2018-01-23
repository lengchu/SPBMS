package cn.lenchu.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.junit.Test;

public class EncodingTest {
	
	@Test
	public void testUrlEncode() {
		String data = "%E5%86%B7%E6%A5%9A";
		String s = "";
		try {
			s = URLDecoder.decode(data,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(s);
	}

}
