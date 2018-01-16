package cn.itcast.mybatis.mapper;

import java.util.List;

import cn.itcast.mybatis.po.Orders;
import cn.itcast.mybatis.po.OrdersCustom;
import cn.itcast.mybatis.po.User;

//订单mapper

public interface OrdersMapperCustom {
	//查询订单关联查询用户信息
	public List<OrdersCustom> findOrdersUser() throws Exception; 
	//查询订单关联查询用户信息，使用resultMap
	public List<Orders> findOrdersUserResultMap() throws Exception; 
	//查询订单（关联用户）及订单信息
	public List<Orders> findOrdersAndOrderDetailUserResultMap() throws Exception;
	//查询用户购买的商品信息
	public List<User> findUserAndItemsUserResultMap() throws Exception;

}
