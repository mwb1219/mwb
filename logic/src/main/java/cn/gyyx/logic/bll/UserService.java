package cn.gyyx.logic.bll;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import cn.gyyx.logic.beans.UserInfo;
import cn.gyyx.logic.beans.UserLog;
import cn.gyyx.logic.beans.UserLogin;
import cn.gyyx.logic.dao.UserDaoFactory;
import cn.gyyx.logic.dao.UserInfoDao;
import cn.gyyx.logic.factory.MemcachedClientFactory;
/*-------------------------------------------------------------------------
* 版权所有：北京光宇在线科技有限责任公司
* 作者：mawenbin
* 联系方式：mawenbin@gyyx.cn
* 创建时间： 2014年10月29日
* 版本号：v1.0
* 本类主要用途描述：
* 用户功能的业务类
-------------------------------------------------------------------------*/
public class UserService {
	private static UserInfoDao userDao = UserDaoFactory.getUserInfoDao();
	private static MemcachedClient memC = MemcachedClientFactory.getMemcachedClient();
	/**
	 * 用户注册业务
	 * @param userInfo 用户信息实体
	 * 		  userLogin 用户登录实体
	 * */
	public static void regesiter(UserInfo userInfo, UserLogin userLogin){
		//将用户信息保存到数据库里
		userDao.saveUserInfo(userInfo);
		userLogin.setUserid(userInfo.getCode());
		userDao.saveUserLogin(userLogin);
		//将用户信息保存在memCached
		try {
			memC.add("user"+userLogin.getCode(), 12*80, userLogin, 1000*36);
			memC.add("user"+userInfo.getCode(), 12*80, userInfo, 1000*36);
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 用户登录业务(通过数据库中查询)
	 * @param username 用户名
	 * 		  password 密码
	 * */
	public static UserLogin loginByDB(String username,String password){
		//System.out.println(username+", "+password);
		UserLogin userLogin = new UserLogin();
		userLogin.setUsername(username);
		userLogin.setPassword(password);
		userLogin = userDao.getUserLogin(userLogin);
		//判断用户是否存在
		if(userLogin != null){
			//将用户信息保存在memCached中
			try {
				memC.add("user"+userLogin.getCode(), 12*80, userLogin, 1000*36);
				UserInfo userInfo = userDao.getUserInfoByLoginCode(userLogin.getCode());
				memC.add("user"+userInfo.getCode(), 12*80, userInfo, 1000*36);
			} catch (TimeoutException | InterruptedException | MemcachedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return userLogin;
	}
	/**
	 * 用户登录业务(通过在memcached中查询)
	 * @param username 用户名
	 * 		  password 密码
	 * */
	public static boolean loginByMemCached(String username, String password,int code){
		UserLogin userLogin = new UserLogin();
		userLogin.setUsername(username);
		userLogin.setPassword(password);
		userLogin = userDao.getUserLogin(userLogin);
		boolean result = false;
		try {
			userLogin = memC.get("user"+code);
			if(userLogin != null){
				result = true;
			}
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 记录日志
	 * @param username 用户名
	 * 		  ip 客户端ip
	 * 		  work 用户的操作
	 * */
	public static void writeLog(String username,String ip,String work){
		UserLog uLog = new UserLog();
		uLog.setUsername(username);
		uLog.setIp(ip);
		uLog.setWork(work);
		Date date = new Date();
		uLog.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		userDao.saveUserLog(uLog);
	}
}
