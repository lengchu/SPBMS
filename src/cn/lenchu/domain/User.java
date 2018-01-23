package cn.lenchu.domain;

public class User {
	
	private String username;
	private String password;
	private String email;
	private String createtime;

	public User() {
	}

	public User(String username, String password, String email, String createtime) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.createtime = createtime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", email=" + email + ", createtime="
				+ createtime + "]";
	}
	
}
