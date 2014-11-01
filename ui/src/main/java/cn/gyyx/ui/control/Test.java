package cn.gyyx.ui.control;

import java.text.SimpleDateFormat;
import java.util.Date;

/*-------------------------------------------------------------------------
 * 版权所有：北京光宇在线科技有限责任公司
 * 作者：mawenbin
 * 联系方式：mawenbin@gyyx.cn
 * 创建时间： 2014年10月28日
 * 版本号：v1.0
 * 本类主要用途描述：
 * 
-------------------------------------------------------------------------*/
public class Test {
	public static void main(String[] args){
		/*UserLogin userLogin = new UserLogin();
		userLogin.setUsername("mawenbin");
		userLogin.setPassword("123");
		userLogin = UserDaoFactory.getUserInfoDao().getUserLogin(userLogin);
		System.out.println(userLogin);*/
		Date date = new Date();
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
	}
}
