package com.me.service;

import com.me.entity.Shopapp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.*;

/**
 * 店铺申请表(Shopapp)表服务接口
 *
 * @author yushi
 * @since 2024-12-20 12:02:01
 */
public interface ShopappService {

    //查询全部
    List<Shopapp> queryList();

    //查询特定条件
    List<Shopapp> queryListByLimit(Shopapp shopapp);

    //查询特定条件单调数据
    Shopapp queryOne(Shopapp shopapp);


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Shopapp queryById(Integer id);

    /**
     * 分页查询
     *
     * @param shopapp 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    //Page<Shopapp> queryByPage(Shopapp shopapp, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param shopapp 实例对象
     * @return 实例对象
     */
    Shopapp insert(Shopapp shopapp);

    /**
     * 修改数据
     *
     * @param shopapp 实例对象
     * @return 实例对象
     */
    Shopapp update(Shopapp shopapp);

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
    List<Shopapp> getDimList(Shopapp shopapp);
}
