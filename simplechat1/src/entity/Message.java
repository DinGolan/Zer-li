package entity;

import java.io.Serializable;

@SuppressWarnings("serial")

/**
 * class to store a message
 * 1- the option by a string- what the query is
 * 2- the object that we need for the query
 */
public class Message implements Serializable{
	
	Object msg;
	String option;

	/**
	 * constructor
	 * @param msg- object
	 * @param option- String
	 */
	public Message(Object msg,String option) {
		
		this.msg = msg;
		this.option = option;
	}

	/**
	 * getter for the object
	 */
	public Object getMsg() {
		return msg;
	}

	/**
	 * setter for the object msg
	 * @param msg
	 */
	public void setMsg(Object msg) {
		this.msg = msg;
	}

	/**
	 * getter for the option
	 * @return String- the option
	 */
	public String getOption() {
		return option;
	}

	/**
	 * setter for the option
	 * @param option- String
	 */
	public void setOption(String option) {
		this.option = option;
	}
}
