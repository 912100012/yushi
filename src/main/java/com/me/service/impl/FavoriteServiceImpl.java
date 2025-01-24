package com.me.service.impl;

import com.me.entity.Favorite;
import com.me.dao.FavoriteDao;
import com.me.service.FavoriteService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.*;

import cn.hutool.core.util.*;

/**
 * 收藏表(Favorite)表服务实现类
 *
 * @author yushi
 * @since 2025-01-24 20:03:21
 */
@Service("favoriteService")
public class FavoriteServiceImpl implements FavoriteService {
    @Resource
    private FavoriteDao favoriteDao;

    @Override
    //查询全部
    public List<Favorite> queryList() {
        return this.favoriteDao.queryList();
    }

    //查询特定条件
    @Override
    public List<Favorite> queryListByLimit(Favorite favorite) {
        List<Favorite> favorites = this.favoriteDao.queryListByLimit(favorite);
        if (ObjectUtil.isEmpty(favorites))
            return null;
        return favorites;
    }

    //查询特定条件单条数据
    @Override
    public Favorite queryOne(Favorite favorite) {
        if (ObjectUtil.isEmpty(favoriteDao.queryListByLimit(favorite)))
            return null;
        return this.favoriteDao.queryListByLimit(favorite).get(0);
    }


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Favorite queryById(Integer id) {
        return this.favoriteDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param favorite 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    /**@Override public Page<Favorite> queryByPage(Favorite favorite, PageRequest pageRequest) {
    long total = this.favoriteDao.count(favorite);
    return new PageImpl<>(this.favoriteDao.queryAllByLimit(favorite, pageRequest), pageRequest, total);
    }*/

    /**
     * 新增数据
     *
     * @param favorite 实例对象
     * @return 实例对象
     */
    @Override
    public Favorite insert(Favorite favorite) {
        this.favoriteDao.insert(favorite);
        return favorite;
    }

    /**
     * 修改数据
     *
     * @param favorite 实例对象
     * @return 实例对象
     */
    @Override
    public Favorite update(Favorite favorite) {
        this.favoriteDao.update(favorite);
        return this.queryById(favorite.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.favoriteDao.deleteById(id) > 0;
    }

    /**
     * 通过关键字模糊搜索
     */
    public List<Favorite> getDimList(Favorite favorite) {
        return this.favoriteDao.getDimList(favorite);
    }
}
