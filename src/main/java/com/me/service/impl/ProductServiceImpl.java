package com.me.service.impl;

import com.me.entity.Product;
import com.me.dao.ProductDao;
import com.me.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.*;

import cn.hutool.core.util.*;

/**
 * 珠宝产品表(Product)表服务实现类
 *
 * @author yushi
 * @since 2024-12-20 12:02:01
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductDao productDao;

    @Override
    //查询全部
    public List<Product> queryList() {
        return this.productDao.queryList();
    }

    //查询特定条件
    @Override
    public List<Product> queryListByLimit(Product product) {
        List<Product> products = this.productDao.queryListByLimit(product);
        if (ObjectUtil.isEmpty(products))
            return null;
        return products;
    }

    //查询特定条件单条数据
    @Override
    public Product queryOne(Product product) {
        if (ObjectUtil.isEmpty(productDao.queryListByLimit(product)))
            return null;
        return this.productDao.queryListByLimit(product).get(0);
    }


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Product queryById(Integer id) {
        return this.productDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param product 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    /**@Override public Page<Product> queryByPage(Product product, PageRequest pageRequest) {
    long total = this.productDao.count(product);
    return new PageImpl<>(this.productDao.queryAllByLimit(product, pageRequest), pageRequest, total);
    }*/

    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
    public Product insert(Product product) {
        this.productDao.insert(product);
        return product;
    }

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
    public Product update(Product product) {
        this.productDao.update(product);
        return this.queryById(product.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.productDao.deleteById(id) > 0;
    }

    /**
     * 通过关键字模糊搜索
     */
    public List<Product> getDimList(Product product) {
        return this.productDao.getDimList(product);
    }
}
