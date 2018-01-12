package cn.itcast.mybatis.mapper;
//mapper借口，相当于dao接口：用户管理

import java.util.List;

import cn.itcast.mybatis.po.User;

public interface UserMapper {
//	根据id查询用户信息
	public User findUserById(int id) throws Exception;
	//插入用户
	public void insertUser(User user) throws Exception;
	//删除用户
	public void deleteUser(int id) throws Exception;
	//根据用户名称查询用户列表
	public List<User> findUserByName(String name) throws Exception;

}
