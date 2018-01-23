package cn.lenchu.test;

import org.junit.Test;
import cn.lenchu.utils.ConnectionFactory;
import cn.lenchu.utils.DataSourceUtils;

public class ConnectionTest {

	@Test
	public void testConn() {
		System.out.println(ConnectionFactory.getConnection());
	}

	@Test
	public void testConn2() {
		System.out.println(DataSourceUtils.getConnection());
	}
}
