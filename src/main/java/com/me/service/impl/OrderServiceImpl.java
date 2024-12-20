package com.me.service.impl;

import com.me.entity.Order;
import com.me.dao.OrderDao;
import com.me.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.*;

import cn.hutool.core.util.*;

/**
 * 历史购买订单表(Order)表服务实现类
 *
 * @author yushi
 * @since 2024-12-20 12:02:00
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;

    @Override
    //查询全部
    public List<Order> queryList() {
        return this.orderDao.queryList();
    }

    //查询特定条件
    @Override
    public List<Order> queryListByLimit(Order order) {
        List<Order> orders = this.orderDao.queryListByLimit(order);
        if (ObjectUtil.isEmpty(orders))
            return null;
        return orders;
    }

    //查询特定条件单条数据
    @Override
    public Order queryOne(Order order) {
        if (ObjectUtil.isEmpty(orderDao.queryListByLimit(order)))
            return null;
        return this.orderDao.queryListByLimit(order).get(0);
    }


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Order queryById(Integer id) {
        return this.orderDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param order 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    /**@Override public Page<Order> queryByPage(Order order, PageRequest pageRequest) {
    long total = this.orderDao.count(order);
    return new PageImpl<>(this.orderDao.queryAllByLimit(order, pageRequest), pageRequest, total);
    }*/

    /**
     * 新增数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    @Override
    public Order insert(Order order) {
        this.orderDao.insert(order);
        return order;
    }

    /**
     * 修改数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    @Override
    public Order update(Order order) {
        this.orderDao.update(order);
        return this.queryById(order.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.orderDao.deleteById(id) > 0;
    }

    /**
     * 通过关键字模糊搜索
     */
    public List<Order> getDimList(Order order) {
        return this.orderDao.getDimList(order);
    }
}
