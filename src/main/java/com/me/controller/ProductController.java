package com.me.controller;

import com.me.entity.Product;
import com.me.service.ProductService;
import com.me.config.SuccessResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

import io.swagger.annotations.*;
import cn.hutool.core.util.*;

/**
 * 珠宝产品表(Product)表控制层
 *
 * @author yushi
 * @since 2025-01-24 20:03:21
 */
@Api(tags = "珠宝产品表")
@RestController
@RequestMapping("product")
public class ProductController {
    /**
     * 服务对象
     */
    @Resource
    private ProductService productService;


    /**
     * 获取全部数据
     *
     * @return 全部数据
     */
    @ApiOperation("SELECT *")
    @GetMapping("/list")
    public SuccessResponse<?> queryList(Product product) {
        List<Product> products = this.productService.queryListByLimit(product);
        return new SuccessResponse<List<Product>>(products, "列表");

    }


    /**
     * 通过条件查找多条数据
     */
    @ApiOperation("查找多条数据")
    @PostMapping("/some")
    public SuccessResponse<?> queryListByLimit(@RequestBody Product product) {
        List<Product> products = this.productService.queryListByLimit(product);
        if (ObjectUtil.isEmpty(products))
            return new SuccessResponse<>("无匹配数据");
        else
            return new SuccessResponse<List<Product>>(products, products.size() + "条数据");
    }

    /**
     * 通过条件查找一条数据
     */
    @ApiOperation("查找单条数据")
    @PostMapping("/one")
    public SuccessResponse<?> queryOne(@RequestBody Product product) {
        Product oneproduct = this.productService.queryOne(product);
        if (ObjectUtil.isEmpty(oneproduct))
            return new SuccessResponse<>("没有满足的条件数据");
        else
            return new SuccessResponse<Product>(oneproduct);
    }


    /**
     * 分页查询
     *
     * @param product 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    /*@GetMapping
    public ResponseEntity<Page<Product>> queryByPage(Product product, PageRequest pageRequest) {
        return ResponseEntity.ok(this.productService.queryByPage(product, pageRequest));
    }*/

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("根据主键ID进行查找某条数据")
    @GetMapping("/{id}")
    public SuccessResponse<?> queryById(@ApiParam("主键ID") @PathVariable("id") Integer id) {
        Product product = this.productService.queryById(id);
        if (ObjectUtil.isEmpty(product))
            return new SuccessResponse<>("没有满足该ID的数据");
        else
            return new SuccessResponse<Product>(product);
    }

    /**
     * 新增数据
     *
     * @param product 实体
     * @return 新增结果
     */
    @ApiOperation("INSERT")
    @PostMapping
    public SuccessResponse<?> add(@RequestBody Product product) {
        return new SuccessResponse<Product>(this.productService.insert(product), "添加成功");
    }

    /**
     * 编辑数据
     *
     * @param product 实体
     * @return 编辑结果
     */
    @PutMapping
    @ApiOperation("UPDATE")
    public SuccessResponse<?> edit(@RequestBody Product product) {
        return new SuccessResponse<Product>(this.productService.update(product), "修改成功");
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/{id}")
    @ApiOperation("DELETE")
    public SuccessResponse<?> deleteById(@ApiParam("主键ID") @PathVariable("id") Integer id) {
        return new SuccessResponse<Boolean>(this.productService.deleteById(id), "删除成功");
    }

    @ApiOperation("LIKE模糊查找")
    @PostMapping("/dim")
    public SuccessResponse<?> queryDimList(@RequestBody Product product) {
        List<Product> products = this.productService.getDimList(product);
        if (ObjectUtil.isEmpty(products))
            return new SuccessResponse<>(products, "", 200);
        else
            return new SuccessResponse<List<Product>>(products, products.size() + "条数据");
    }

}

