package cn.gyyx.logic.dao;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*-------------------------------------------------------------------------
 * 版权所有：北京光宇在线科技有限责任公司
 * 作者：mawenbin
 * 联系方式：mawenbin@gyyx.cn
 * 创建时间： 2014年10月28日
 * 版本号：v1.0
 * 本类主要用途描述：
 * MybaisConnectio工厂
-------------------------------------------------------------------------*/
public class MybatisConnectionFactory {
	private static SqlSessionFactory sqlSessionFactory;
	static{
		String resource = "myBatis/myBatis-config.xml";
		try {
			Reader reader = Resources.getResourceAsReader(resource);		//加载配置文件
			if(sqlSessionFactory == null){
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader,"user");		//得到sqlSessionFactory
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static SqlSessionFactory getSessionFactory(){
		return sqlSessionFactory;
	}
	
}
