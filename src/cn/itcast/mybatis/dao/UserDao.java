package cn.itcast.mybatis.dao;
//dao借口：用户管理

import java.util.List;

import cn.itcast.mybatis.po.User;

public interface UserDao {
	//根据id查询用户信息
	public User findUserById(int id) throws Exception;
	//添加用户信息
	//根据用户名称查询用户列表
	public List<User> findUserByName(String name) throws Exception;
	public void insertUser(User user) throws Exception;
	//删除用户信息
	public void deleteUser(int id) throws Exception;
}
