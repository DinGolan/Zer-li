package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable{
	
	Object msg;
	String option;
	
	public Message(Object msg,String option) {
		
		this.msg = msg;
		this.option = option;
	}

	public Object getMsg() {
		return msg;
	}

	public void setMsg(Object msg) {
		this.msg = msg;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}
}
