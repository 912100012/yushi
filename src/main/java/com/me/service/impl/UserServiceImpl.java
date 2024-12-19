package com.me.service.impl;

import com.me.entity.User;
import com.me.dao.UserDao;
import com.me.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.*;

import cn.hutool.core.util.*;

/**
 * 用户信息表(User)表服务实现类
 *
 * @author yushi
 * @since 2024-12-19 21:01:06
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    //查询全部
    public List<User> queryList() {
        return this.userDao.queryList();
    }

    //查询特定条件
    @Override
    public List<User> queryListByLimit(User user) {
        List<User> users = this.userDao.queryListByLimit(user);
        if (ObjectUtil.isEmpty(users))
            return null;
        return users;
    }

    //查询特定条件单条数据
    @Override
    public User queryOne(User user) {
        if (ObjectUtil.isEmpty(userDao.queryListByLimit(user)))
            return null;
        return this.userDao.queryListByLimit(user).get(0);
    }


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return this.userDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    /**@Override public Page<User> queryByPage(User user, PageRequest pageRequest) {
    long total = this.userDao.count(user);
    return new PageImpl<>(this.userDao.queryAllByLimit(user, pageRequest), pageRequest, total);
    }*/

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userDao.deleteById(id) > 0;
    }

    /**
     * 通过关键字模糊搜索
     */
    public List<User> getDimList(User user) {
        return this.userDao.getDimList(user);
    }
}
