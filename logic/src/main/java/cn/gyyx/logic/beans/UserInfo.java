package cn.gyyx.logic.beans;

import java.io.Serializable;

/*-------------------------------------------------------------------------
* 版权所有：北京光宇在线科技有限责任公司
* 作者：mawenbin
* 联系方式：mawenbin@gyyx.cn
* 创建时间： 2014年10月28日
* 版本号：v1.0
* 本类主要用途描述：
* 用户信息类
-------------------------------------------------------------------------*/
public class UserInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//主键
	private int code;
	//姓名
	private String name;
	//性别
	private String sex;	
	//年龄
	private String age;		
	//国家
	private String country;		
	//省份
	private String province;	
	//城市
	private String city;		
	public int getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCode(int code) {
		this.code = code;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String toString(){
		return code+","+name+","+age+","+sex+","+country+","+province+","+city;
	}
}
