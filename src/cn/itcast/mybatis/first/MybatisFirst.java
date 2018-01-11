package cn.itcast.mybatis.first;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.itcast.mybatis.po.User;

//入门程序
public class MybatisFirst {

	//根据id查询用户信息，得到一条记录信息
	@Test
	public void findUserByIdTest() throws IOException {
		//mybatis配置文件
		String resource = "SqlMapConfig.xml";
		//得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//创建会话工厂,向build传入mybatis的配置文件信息
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		//通过工厂得到SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//通过SqlSession操作数据库
		//第一个参数：映射文件中statement的id，等于命名空间.statement的id
		//第二个参数：指定和映射文件中所匹配的parameterType类型的参数。
		//sqlSession.selectOne结果与映射文件中所匹配的resultType类型的对象
		//查询出一条数据
		User user = sqlSession.selectOne("test.findUserById", 1);
		System.out.println(user);
		
		//释放资源
		sqlSession.close();
	}
	//根据用户名称模糊查询用户列表
	@Test
	public void findUserByNameTest() throws IOException {
		//mybatis配置文件
		String resource = "SqlMapConfig.xml";
		//得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//创建会话工厂,向build传入mybatis的配置文件信息
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		//通过工厂得到SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//list中的user和映射文件中resultType所指定的类型一致
		List<User> list = sqlSession.selectList("findUserByName", "小明");
		System.out.println(list);
		//释放资源
		sqlSession.close();

	}
}