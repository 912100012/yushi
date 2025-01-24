package com.me.service;

import com.me.entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.*;

/**
 * 购物车表(Cart)表服务接口
 *
 * @author yushi
 * @since 2025-01-24 20:03:20
 */
public interface CartService {

    //查询全部
    List<Cart> queryList();

    //查询特定条件
    List<Cart> queryListByLimit(Cart cart);

    //查询特定条件单调数据
    Cart queryOne(Cart cart);


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Cart queryById(Integer id);

    /**
     * 分页查询
     *
     * @param cart 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    //Page<Cart> queryByPage(Cart cart, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param cart 实例对象
     * @return 实例对象
     */
    Cart insert(Cart cart);

    /**
     * 修改数据
     *
     * @param cart 实例对象
     * @return 实例对象
     */
    Cart update(Cart cart);

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
    List<Cart> getDimList(Cart cart);
}
