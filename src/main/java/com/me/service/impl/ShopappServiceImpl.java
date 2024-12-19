package com.me.service.impl;

import com.me.entity.Shopapp;
import com.me.dao.ShopappDao;
import com.me.service.ShopappService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.*;

import cn.hutool.core.util.*;

/**
 * 店铺申请表(Shopapp)表服务实现类
 *
 * @author yushi
 * @since 2024-12-19 21:01:05
 */
@Service("shopappService")
public class ShopappServiceImpl implements ShopappService {
    @Resource
    private ShopappDao shopappDao;

    @Override
    //查询全部
    public List<Shopapp> queryList() {
        return this.shopappDao.queryList();
    }

    //查询特定条件
    @Override
    public List<Shopapp> queryListByLimit(Shopapp shopapp) {
        List<Shopapp> shopapps = this.shopappDao.queryListByLimit(shopapp);
        if (ObjectUtil.isEmpty(shopapps))
            return null;
        return shopapps;
    }

    //查询特定条件单条数据
    @Override
    public Shopapp queryOne(Shopapp shopapp) {
        if (ObjectUtil.isEmpty(shopappDao.queryListByLimit(shopapp)))
            return null;
        return this.shopappDao.queryListByLimit(shopapp).get(0);
    }


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Shopapp queryById(Integer id) {
        return this.shopappDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param shopapp 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    /**@Override public Page<Shopapp> queryByPage(Shopapp shopapp, PageRequest pageRequest) {
    long total = this.shopappDao.count(shopapp);
    return new PageImpl<>(this.shopappDao.queryAllByLimit(shopapp, pageRequest), pageRequest, total);
    }*/

    /**
     * 新增数据
     *
     * @param shopapp 实例对象
     * @return 实例对象
     */
    @Override
    public Shopapp insert(Shopapp shopapp) {
        this.shopappDao.insert(shopapp);
        return shopapp;
    }

    /**
     * 修改数据
     *
     * @param shopapp 实例对象
     * @return 实例对象
     */
    @Override
    public Shopapp update(Shopapp shopapp) {
        this.shopappDao.update(shopapp);
        return this.queryById(shopapp.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.shopappDao.deleteById(id) > 0;
    }

    /**
     * 通过关键字模糊搜索
     */
    public List<Shopapp> getDimList(Shopapp shopapp) {
        return this.shopappDao.getDimList(shopapp);
    }
}
