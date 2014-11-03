package cn.gyyx.logic.dao;


import java.io.IOException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClient;

/*-------------------------------------------------------------------------
 * 版权所有：北京光宇在线科技有限责任公司
 * 作者：mawenbin
 * 联系方式：mawenbin@gyyx.cn
 * 创建时间： 2014年10月28日
 * 版本号：v1.0
 * 本类主要用途描述：
 * MemcachedClient工厂
-------------------------------------------------------------------------*/
public class MemcachedClientFactory {
	private static MemcachedClient memC;
	private static String ip;
	private static int port;
	static{
		ip = "192.168.6.195";
		port = 20000;
		try {
			memC = new XMemcachedClient(ip,port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static MemcachedClient getMemcachedClient(){
		return memC;
	}
}
