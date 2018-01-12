package cn.itcast.mybatis.dao;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.itcast.mybatis.po.User;

public class UserDaoImplTest {
	private SqlSessionFactory sqlSessionFactory;
	//此方法是在testFindUserById方法执行之前执行
	@Before
	public void setUp() throws Exception {
		//创建sqlSessionFactory
		//mybatis配置文件
		String resource = "SqlMapConfig.xml";
		//得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//创建会话工厂,向build传入mybatis的配置文件信息
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	@Test
	public void testFindUserById() throws Exception {
		//创建UserDao对象
		UserDao userDao = new UserDaoImpl(sqlSessionFactory);
		
		//调用UserDao的方法
		User user = userDao.findUserById(1);
		System.out.println(user);
	}
	@Test
	public void testInsertUser() throws Exception {
		//创建UserDao对象
		UserDao userDao = new UserDaoImpl(sqlSessionFactory);
		User user = new User();
		user.setUsername("陈彪");
		user.setBirthday(new Date());
		user.setAddress("山推");
		user.setSex("1");
		//调用UserDao的方法
		userDao.insertUser(user);
	}
	@Test
	public void testDeleteUserById() throws Exception {
		//创建UserDao对象
		UserDao userDao = new UserDaoImpl(sqlSessionFactory);
		
		//调用UserDao的方法
		userDao.deleteUser(31);
	}
}
