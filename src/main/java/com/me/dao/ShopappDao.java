package com.me.dao;

import com.me.entity.Shopapp;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 店铺申请表(Shopapp)表数据库访问层
 *
 * @author yushi
 * @since 2024-12-20 12:02:01
 */
public interface ShopappDao {

    //获取集合
    List<Shopapp> queryList();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Shopapp queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param shopapp 查询条件
     * @return 对象列表
     */
    List<Shopapp> queryListByLimit(Shopapp shopapp);

    /**
     * 统计总行数
     *
     * @param shopapp 查询条件
     * @return 总行数
     */
    long count(Shopapp shopapp);

    /**
     * 新增数据
     *
     * @param shopapp 实例对象
     * @return 影响行数
     */
    int insert(Shopapp shopapp);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Shopapp> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Shopapp> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Shopapp> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Shopapp> entities);

    /**
     * 修改数据
     *
     * @param shopapp 实例对象
     * @return 影响行数
     */
    int update(Shopapp shopapp);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 通过关键字搜索
     */
    List<Shopapp> getDimList(Shopapp shopapp);
}

