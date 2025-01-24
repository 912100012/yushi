package com.me.service;

import com.me.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.*;

/**
 * 珠宝产品种类表(Type)表服务接口
 *
 * @author yushi
 * @since 2025-01-24 20:03:22
 */
public interface TypeService {

    //查询全部
    List<Type> queryList();

    //查询特定条件
    List<Type> queryListByLimit(Type type);

    //查询特定条件单调数据
    Type queryOne(Type type);


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Type queryById(Integer id);

    /**
     * 分页查询
     *
     * @param type 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    //Page<Type> queryByPage(Type type, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param type 实例对象
     * @return 实例对象
     */
    Type insert(Type type);

    /**
     * 修改数据
     *
     * @param type 实例对象
     * @return 实例对象
     */
    Type update(Type type);

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
    List<Type> getDimList(Type type);
}
