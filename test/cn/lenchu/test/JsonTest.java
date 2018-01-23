package cn.lenchu.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.Test;
import com.google.gson.Gson;
import cn.lenchu.domain.User;

public class JsonTest {
	
	@Test
	public void testGson() {
		Gson gson = new Gson();
		User u = new User("lengchu", "1234", null, null);
//		System.out.println(gson.toJson(u));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", u.getUsername());
		map.put("result", "1");
		map.put("msg", "注册成功");
		System.out.println(gson.toJson(map));
		
		User u2 = new User("lenchu","xxxx", null, null);
		Set<User> list = new HashSet<User>();
		list.add(u);
		list.add(u2);
		list.add(u2);
		System.out.println(gson.toJson(list));
	}

}
