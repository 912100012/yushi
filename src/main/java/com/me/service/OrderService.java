package com.me.service;

import com.me.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.*;

/**
 * 历史购买订单表(Order)表服务接口
 *
 * @author yushi
 * @since 2024-12-20 12:02:00
 */
public interface OrderService {

    //查询全部
    List<Order> queryList();

    //查询特定条件
    List<Order> queryListByLimit(Order order);

    //查询特定条件单调数据
    Order queryOne(Order order);


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Order queryById(Integer id);

    /**
     * 分页查询
     *
     * @param order 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    //Page<Order> queryByPage(Order order, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    Order insert(Order order);

    /**
     * 修改数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    Order update(Order order);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过关键字模糊搜索
     */
    List<Order> getDimList(Order order);
}
