package com.me.dao;

import com.me.entity.Favorite;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 收藏表(Favorite)表数据库访问层
 *
 * @author yushi
 * @since 2024-12-20 12:02:00
 */
public interface FavoriteDao {

    //获取集合
    List<Favorite> queryList();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Favorite queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param favorite 查询条件
     * @return 对象列表
     */
    List<Favorite> queryListByLimit(Favorite favorite);

    /**
     * 统计总行数
     *
     * @param favorite 查询条件
     * @return 总行数
     */
    long count(Favorite favorite);

    /**
     * 新增数据
     *
     * @param favorite 实例对象
     * @return 影响行数
     */
    int insert(Favorite favorite);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Favorite> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Favorite> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Favorite> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Favorite> entities);

    /**
     * 修改数据
     *
     * @param favorite 实例对象
     * @return 影响行数
     */
    int update(Favorite favorite);

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
    List<Favorite> getDimList(Favorite favorite);
}

