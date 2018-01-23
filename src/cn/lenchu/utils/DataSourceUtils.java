package cn.lenchu.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtils {
	
	private static DataSource ds;
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	static {
		ds = new ComboPooledDataSource();
	}

	public static DataSource getDataSource() {
		return ds;
	}
	
	public static Connection getConnection() {
		Connection con = null;
		con = tl.get();
		if (con == null) {
			try {
				con = ds.getConnection();
				tl.set(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return con;
	}
	
	public static void remove() {
		tl.remove();
	}

}
