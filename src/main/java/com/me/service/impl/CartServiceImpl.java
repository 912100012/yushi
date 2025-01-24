package com.me.service.impl;

import com.me.entity.Cart;
import com.me.dao.CartDao;
import com.me.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.*;

import cn.hutool.core.util.*;

/**
 * 购物车表(Cart)表服务实现类
 *
 * @author yushi
 * @since 2025-01-24 20:03:20
 */
@Service("cartService")
public class CartServiceImpl implements CartService {
    @Resource
    private CartDao cartDao;

    @Override
    //查询全部
    public List<Cart> queryList() {
        return this.cartDao.queryList();
    }

    //查询特定条件
    @Override
    public List<Cart> queryListByLimit(Cart cart) {
        List<Cart> carts = this.cartDao.queryListByLimit(cart);
        if (ObjectUtil.isEmpty(carts))
            return null;
        return carts;
    }

    //查询特定条件单条数据
    @Override
    public Cart queryOne(Cart cart) {
        if (ObjectUtil.isEmpty(cartDao.queryListByLimit(cart)))
            return null;
        return this.cartDao.queryListByLimit(cart).get(0);
    }


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Cart queryById(Integer id) {
        return this.cartDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param cart 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    /**@Override public Page<Cart> queryByPage(Cart cart, PageRequest pageRequest) {
    long total = this.cartDao.count(cart);
    return new PageImpl<>(this.cartDao.queryAllByLimit(cart, pageRequest), pageRequest, total);
    }*/

    /**
     * 新增数据
     *
     * @param cart 实例对象
     * @return 实例对象
     */
    @Override
    public Cart insert(Cart cart) {
        this.cartDao.insert(cart);
        return cart;
    }

    /**
     * 修改数据
     *
     * @param cart 实例对象
     * @return 实例对象
     */
    @Override
    public Cart update(Cart cart) {
        this.cartDao.update(cart);
        return this.queryById(cart.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.cartDao.deleteById(id) > 0;
    }

    /**
     * 通过关键字模糊搜索
     */
    public List<Cart> getDimList(Cart cart) {
        return this.cartDao.getDimList(cart);
    }
}
