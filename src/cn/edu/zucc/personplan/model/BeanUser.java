package cn.edu.zucc.personplan.model;

import java.sql.Timestamp;
import java.util.Date;

public class BeanUser {
	public static BeanUser currentLoginUser = null;
	Date register_time;
	String user_id;

	public Date getRegister_time() {
		return register_time;
	}

	public void setRegister_time(Date register_time) {
		this.register_time = register_time;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

}
