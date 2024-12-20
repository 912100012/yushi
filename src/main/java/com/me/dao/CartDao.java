package com.me.dao;

import com.me.entity.Cart;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 购物车表(Cart)表数据库访问层
 *
 * @author yushi
 * @since 2024-12-20 12:01:59
 */
public interface CartDao {

    //获取集合
    List<Cart> queryList();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Cart queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param cart 查询条件
     * @return 对象列表
     */
    List<Cart> queryListByLimit(Cart cart);

    /**
     * 统计总行数
     *
     * @param cart 查询条件
     * @return 总行数
     */
    long count(Cart cart);

    /**
     * 新增数据
     *
     * @param cart 实例对象
     * @return 影响行数
     */
    int insert(Cart cart);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Cart> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Cart> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Cart> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Cart> entities);

    /**
     * 修改数据
     *
     * @param cart 实例对象
     * @return 影响行数
     */
    int update(Cart cart);

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
    List<Cart> getDimList(Cart cart);
}

