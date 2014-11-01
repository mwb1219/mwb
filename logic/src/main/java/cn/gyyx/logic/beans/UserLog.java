package cn.gyyx.logic.beans;

import java.io.Serializable;

/*-------------------------------------------------------------------------
 * 版权所有：北京光宇在线科技有限责任公司
 * 作者：mawenbin
 * 联系方式：mawenbin@gyyx.cn
 * 创建时间： 2014年10月28日
 * 版本号：v1.0
 * 本类主要用途描述：
 * 用户操作日志类
-------------------------------------------------------------------------*/
public class UserLog implements Serializable{

	private static final long serialVersionUID = 1L;
	//主键
	private int code;
	//用户名
	private String username;
	//用户IP
	private String ip;
	//操作时间
	private String createTime;
	//用户操作内容
	private String work;
	//getter and setter method
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	
}
