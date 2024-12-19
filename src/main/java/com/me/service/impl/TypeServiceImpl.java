package com.me.service.impl;

import com.me.entity.Type;
import com.me.dao.TypeDao;
import com.me.service.TypeService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.*;

import cn.hutool.core.util.*;

/**
 * 珠宝产品种类表(Type)表服务实现类
 *
 * @author yushi
 * @since 2024-12-19 21:01:05
 */
@Service("typeService")
public class TypeServiceImpl implements TypeService {
    @Resource
    private TypeDao typeDao;

    @Override
    //查询全部
    public List<Type> queryList() {
        return this.typeDao.queryList();
    }

    //查询特定条件
    @Override
    public List<Type> queryListByLimit(Type type) {
        List<Type> types = this.typeDao.queryListByLimit(type);
        if (ObjectUtil.isEmpty(types))
            return null;
        return types;
    }

    //查询特定条件单条数据
    @Override
    public Type queryOne(Type type) {
        if (ObjectUtil.isEmpty(typeDao.queryListByLimit(type)))
            return null;
        return this.typeDao.queryListByLimit(type).get(0);
    }


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Type queryById(Integer id) {
        return this.typeDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param type 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    /**@Override public Page<Type> queryByPage(Type type, PageRequest pageRequest) {
    long total = this.typeDao.count(type);
    return new PageImpl<>(this.typeDao.queryAllByLimit(type, pageRequest), pageRequest, total);
    }*/

    /**
     * 新增数据
     *
     * @param type 实例对象
     * @return 实例对象
     */
    @Override
    public Type insert(Type type) {
        this.typeDao.insert(type);
        return type;
    }

    /**
     * 修改数据
     *
     * @param type 实例对象
     * @return 实例对象
     */
    @Override
    public Type update(Type type) {
        this.typeDao.update(type);
        return this.queryById(type.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.typeDao.deleteById(id) > 0;
    }

    /**
     * 通过关键字模糊搜索
     */
    public List<Type> getDimList(Type type) {
        return this.typeDao.getDimList(type);
    }
}
