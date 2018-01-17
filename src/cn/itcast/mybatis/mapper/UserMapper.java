package cn.itcast.mybatis.mapper;
//mapper借口，相当于dao接口：用户管理

import java.util.List;

import cn.itcast.mybatis.po.User;
import cn.itcast.mybatis.po.UserCustom;
import cn.itcast.mybatis.po.UserQueryVo;

public interface UserMapper {
	
	//用户信息的综合查询
	public List<UserCustom> findUserList(UserQueryVo userQueryVo) throws Exception;
	
	//用户信息的综合查询总数
	public int findUserCount(UserQueryVo userQueryVo) throws Exception;
	//	根据id查询用户信息,使用resultMap输出
	public User findUserByIdResultMap(int id) throws Exception;

	//	根据id查询用户信息
	public User findUserById(int id) throws Exception;
	//插入用户
	public void insertUser(User user) throws Exception;
	//删除用户
	public void deleteUser(int id) throws Exception;
	//更新用户
	public void updateUser(User user) throws Exception;
	//根据用户名称查询用户列表
	public List<User> findUserByName(String name) throws Exception;

}
