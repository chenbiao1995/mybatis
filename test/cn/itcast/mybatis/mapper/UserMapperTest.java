package cn.itcast.mybatis.mapper;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.itcast.mybatis.po.User;

public class UserMapperTest {
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
		SqlSession sqlSession = sqlSessionFactory.openSession();
	//创建UserMapper的对象，mybatis自动生成mapper的代理对象
		UserMapper userMapper =sqlSession.getMapper(UserMapper.class);
	//调用userMapper的方法
		User user = userMapper.findUserById(1);
		System.out.println(user);
		
	}

	@Test
	public void testFindUserByName() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
	//创建UserMapper的对象，mybatis自动生成mapper的代理对象
		UserMapper userMapper =sqlSession.getMapper(UserMapper.class);
	//调用userMapper的方法
		List<User> list = userMapper.findUserByName("小明");
		System.out.println(list);
		
	}
}
