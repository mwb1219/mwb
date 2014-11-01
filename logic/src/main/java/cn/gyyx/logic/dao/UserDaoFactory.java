package cn.gyyx.logic.dao;

public class UserDaoFactory {
	public static UserInfoDao getUserInfoDao(){
		return new UserInfoDao();
	}
}
