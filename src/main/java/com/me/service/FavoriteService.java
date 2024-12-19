package com.me.service;

import com.me.entity.Favorite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.*;

/**
 * 收藏表(Favorite)表服务接口
 *
 * @author yushi
 * @since 2024-12-19 21:01:04
 */
public interface FavoriteService {

    //查询全部
    List<Favorite> queryList();

    //查询特定条件
    List<Favorite> queryListByLimit(Favorite favorite);

    //查询特定条件单调数据
    Favorite queryOne(Favorite favorite);


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Favorite queryById(Integer id);

    /**
     * 分页查询
     *
     * @param favorite 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    //Page<Favorite> queryByPage(Favorite favorite, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param favorite 实例对象
     * @return 实例对象
     */
    Favorite insert(Favorite favorite);

    /**
     * 修改数据
     *
     * @param favorite 实例对象
     * @return 实例对象
     */
    Favorite update(Favorite favorite);

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
    List<Favorite> getDimList(Favorite favorite);
}
