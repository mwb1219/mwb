package cn.gyyx.logic.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import cn.gyyx.logic.beans.UserInfo;
import cn.gyyx.logic.beans.UserLog;
import cn.gyyx.logic.beans.UserLogin;
import cn.gyyx.logic.factory.MybatisConnectionFactory;

/*-------------------------------------------------------------------------
* 版权所有：北京光宇在线科技有限责任公司
* 作者：mawenbin
* 联系方式：mawenbin@gyyx.cn
* 创建时间： 2014年10月28日
* 版本号：v1.0
* 本类主要用途描述：
* 实现用户的数据库操作
-------------------------------------------------------------------------*/
public class UserInfoDao {
	private SqlSessionFactory sqlSessionFactory = MybatisConnectionFactory.getSessionFactory();
	private SqlSession session = null;
	/*
	 * 根据Id获得用户实体
	 * @param code 用户主键
	 * @return 用户实体
	 * */
	public UserInfo getUserInfo(int code){
		session = sqlSessionFactory.openSession();
		try{
			UserMapper userMapper = session.getMapper(UserMapper.class);
			return userMapper.selectUserByCode(code);
		}finally{
			session.close();
		}
	}
	/*
	 * 根据用户登录code查询出用户完整信息
	 * @param code 用户登录code
	 * @return 用户信息实体
	 * */
	public UserInfo getUserInfoByLoginCode(int code){
		session = sqlSessionFactory.openSession();
		try{
			UserMapper userMapper = session.getMapper(UserMapper.class);
			return userMapper.selectUserByLoginCode(code);
		}finally{
			session.close();
		}
	}
	/*
	 * 插入用户信息
	 * @param userinfo 用户实体
	 * */
	public void saveUserInfo(UserInfo userinfo){
		session = sqlSessionFactory.openSession();
		try{
			UserMapper userMapper = session.getMapper(UserMapper.class);
			userMapper.insertUser(userinfo);
			session.commit();
		}finally{
			session.close();
		}
	}
	/*
	 * 查询所有的用户信息
	 * @return 用户实体集
	 * */
	public List<UserInfo> getUserInfos(){
		session = sqlSessionFactory.openSession();
		try{
			UserMapper userMapper = session.getMapper(UserMapper.class);
			return userMapper.selectUser();
		}finally{
			session.close();
		}
	}
	/*
	 * 插入用户登录信息
	 * @param userLogin 用户登录实体
	 * */
	public void saveUserLogin(UserLogin userLogin){
		session = sqlSessionFactory.openSession();
		try{
			UserMapper userMapper = session.getMapper(UserMapper.class);
			userMapper.insertUserLogin(userLogin);
			session.commit();
		}finally{
			session.close();
		}
	}
	/*
	 * 根据用户名密码查询用户
	 * @param username 用户名
	 * @param password 密码
	 * */
	public UserLogin getUserLogin(UserLogin userLogin){
		session = sqlSessionFactory.openSession();
		try{
			UserMapper userMapper = session.getMapper(UserMapper.class);
			return userMapper.selectLogin(userLogin);
		}finally{
			session.close();
		}
	}
	/*
	 * 插入用户操作日志
	 * @param userLog 用户日志实体对象
	 * */
	public void saveUserLog(UserLog userLog){
		session = sqlSessionFactory.openSession();
		try{
			UserMapper userMapper = session.getMapper(UserMapper.class);
			userMapper.insertLog(userLog);
			session.commit();
		}finally{
			session.close();
		}
	}
}
