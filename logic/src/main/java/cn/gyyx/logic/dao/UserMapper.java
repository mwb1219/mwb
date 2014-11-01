package cn.gyyx.logic.dao;

import java.util.List;

import cn.gyyx.logic.beans.UserInfo;
import cn.gyyx.logic.beans.UserLog;
import cn.gyyx.logic.beans.UserLogin;
/*-------------------------------------------------------------------------
* 版权所有：北京光宇在线科技有限责任公司
* 作者：mawenbin
* 联系方式：mawenbin@gyyx.cn
* 创建时间： 2014年10月28日
* 版本号：v1.0
* 本类主要用途描述：
* 用户数据库操作接口
-------------------------------------------------------------------------*/
public interface UserMapper {
	/*
	 * 根据主键查找用户
	 * @param code 用户主键
	 * @return 用户实体
	 * */
	UserInfo selectUserByCode(int code);
	/*
	 * 插入用户信息
	 * @param userinfo 用户实体
	 * */
	void insertUser(UserInfo userinfo);
	/*
	 * 查询所有的用户信息
	 * @return 用户实体集
	 * */
	List<UserInfo> selectUser(); 
	/*
	 * 插入用户登录信息
	 * @param userLogin 用户登录实体
	 * */
	void insertUserLogin(UserLogin userLogin);
	/*
	 * 根据用户名密码查询用户
	 * @param username 用户名
	 * @param password 密码
	 * */
	UserLogin selectLogin(UserLogin userLogin);
	/*
	 * 根据用户登录code查询出用户完整信息
	 * @param code 用户登录code
	 * @return 用户信息实体
	 * */
	UserInfo selectUserByLoginCode(int code);
	/*
	 * 插入用户操作日志
	 * @param userLog 用户日志实体对象
	 * */
	void insertLog(UserLog userLog);
}
