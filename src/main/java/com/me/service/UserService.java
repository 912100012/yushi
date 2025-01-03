package com.me.service;

import com.me.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.*;

/**
 * 用户信息表(User)表服务接口
 *
 * @author yushi
 * @since 2024-12-20 12:02:02
 */
public interface UserService {

    //查询全部
    List<User> queryList();

    //查询特定条件
    List<User> queryListByLimit(User user);

    //查询特定条件单调数据
    User queryOne(User user);


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Integer id);

    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    //Page<User> queryByPage(User user, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

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
    List<User> getDimList(User user);
}
