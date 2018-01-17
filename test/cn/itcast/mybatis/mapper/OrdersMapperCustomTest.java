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

import cn.itcast.mybatis.po.Orders;
import cn.itcast.mybatis.po.OrdersCustom;
import cn.itcast.mybatis.po.User;

public class OrdersMapperCustomTest {
	private SqlSessionFactory sqlSessionFactory;
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
	public void testFindOrdersUser() throws Exception {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
		//调用mapper的方法
		List<OrdersCustom> list = ordersMapperCustom.findOrdersUser();
		System.out.println(list);
		sqlSession.close();
	}
	@Test
	public void testFindOrdersUserResultMap() throws Exception {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
		//调用mapper的方法
		List<Orders> list = ordersMapperCustom.findOrdersUserResultMap();
		System.out.println(list);
		sqlSession.close();
	}
	
	@Test
	public void testFindOrdersAndOrderDetailUserResultMap() throws Exception {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
		//调用mapper的方法
		List<Orders> list = ordersMapperCustom.findOrdersAndOrderDetailUserResultMap();
		System.out.println(list);
		sqlSession.close();
	}
	@Test
	public void testFindUserAndItemsUserResultMap() throws Exception {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
		List<User> list = ordersMapperCustom.findUserAndItemsUserResultMap();
		System.out.println(list);
		sqlSession.close();
	}
	//查询订单关联查询用户，用户信息使用延迟加载
	@Test
	public void testFindOrdersUserLazyLoading() throws Exception {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
		List<Orders> list = ordersMapperCustom.findOrdersUserLazyLoading();
		//遍历上边的订单列表
		for(Orders orders:list) {
			//执行getUser()取查询用户信息，这里实现延迟加载
			
			
			User user = orders.getUser();
			System.out.println(user);
		}
		sqlSession.close();
	}
	@Test
	//一级缓存测试
	public void testCache1() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		//下边查询使用一个Sqlsession
		//第一次发起请求，查询id为1的用户
		User user1 = userMapper.findUserById(1);
		System.out.println(user1);
		//如果执行commit  增删改会清空缓存    脏读
		//更新user1的信息，去清空缓存
		user1.setUsername("ceshi");
//		userMapper.updateUser(user1);
//		//清空缓存
//		sqlSession.commit();
		//第二次发起请求，查询id为1的用户
		User user2 = userMapper.findUserById(1);
		System.out.println(user2);
		sqlSession.close();

	}
	@Test
	//二级缓存测试
	public void testCache2() throws Exception {
		SqlSession sqlSession1 = sqlSessionFactory.openSession();
		SqlSession sqlSession2 = sqlSessionFactory.openSession();
		SqlSession sqlSession3 = sqlSessionFactory.openSession();

		UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
		//第一次发起请求，查询id为1的用户
		User user1 = userMapper1.findUserById(1);
		System.out.println(user1);
		//这里执行关闭操作，将sqlSession中的数据写到二级缓存区域
		sqlSession1.close();
		UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);

		
//		//使用sqlSession3执行commit操作
//		UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);
//		User user = userMapper3.findUserById(1);
//		user.setUsername("hi");
//		userMapper3.updateUser(user);
//		//执行提交，清空UserMapper下的二级缓存
//		sqlSession3.commit();
//		sqlSession3.close();
		//第二次发起请求，查询id为1的用户
		User user2 = userMapper2.findUserById(1);
		System.out.println(user2);
		sqlSession2.close();
	}
	
}
