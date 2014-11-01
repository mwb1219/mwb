package cn.gyyx.logic.beans;

import java.io.Serializable;

/*-------------------------------------------------------------------------
* 版权所有：北京光宇在线科技有限责任公司
* 作者：mawenbin
* 联系方式：mawenbin@gyyx.cn
* 创建时间： 2014年10月28日
* 版本号：v1.0
* 本类主要用途描述：
* 用户登录类
-------------------------------------------------------------------------*/
public class UserLogin implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//主键
	private int code;	
	//用户名
	private String username;	
	
	private String password;		
	//密码
	private int userid;
	
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
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
	public String toString(){
		return code+","+username;
	}
}
