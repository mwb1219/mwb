package cn.gyyx.logic.dao;
/*-------------------------------------------------------------------------
 * 版权所有：北京光宇在线科技有限责任公司
 * 作者：mawenbin
 * 联系方式：mawenbin@gyyx.cn
 * 创建时间： 2014年10月28日
 * 版本号：v1.0
 * 本类主要用途描述：
 * UserDao工厂
-------------------------------------------------------------------------*/
public class UserDaoFactory {
	public static UserInfoDao getUserInfoDao(){
		return new UserInfoDao();
	}
}
