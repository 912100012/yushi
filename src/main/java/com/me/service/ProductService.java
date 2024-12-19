package com.me.service;

import com.me.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.*;

/**
 * 珠宝产品表(Product)表服务接口
 *
 * @author yushi
 * @since 2024-12-19 21:01:05
 */
public interface ProductService {

    //查询全部
    List<Product> queryList();

    //查询特定条件
    List<Product> queryListByLimit(Product product);

    //查询特定条件单调数据
    Product queryOne(Product product);


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Product queryById(Integer id);

    /**
     * 分页查询
     *
     * @param product 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    //Page<Product> queryByPage(Product product, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    Product insert(Product product);

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    Product update(Product product);

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
    List<Product> getDimList(Product product);
}
