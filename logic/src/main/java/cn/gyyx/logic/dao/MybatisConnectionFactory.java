package cn.gyyx.logic.dao;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * MyBatis连接工厂类
 * */
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
