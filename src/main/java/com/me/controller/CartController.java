package com.me.controller;

import com.me.entity.Cart;
import com.me.service.CartService;
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
 * 购物车表(Cart)表控制层
 *
 * @author yushi
 * @since 2025-01-24 20:03:19
 */
@Api(tags = "购物车表")
@RestController
@RequestMapping("cart")
public class CartController {
    /**
     * 服务对象
     */
    @Resource
    private CartService cartService;


    /**
     * 获取全部数据
     *
     * @return 全部数据
     */
    @ApiOperation("SELECT *")
    @GetMapping("/list")
    public SuccessResponse<?> queryList(Cart cart) {
        List<Cart> carts = this.cartService.queryListByLimit(cart);
        return new SuccessResponse<List<Cart>>(carts, "列表");

    }


    /**
     * 通过条件查找多条数据
     */
    @ApiOperation("查找多条数据")
    @PostMapping("/some")
    public SuccessResponse<?> queryListByLimit(@RequestBody Cart cart) {
        List<Cart> carts = this.cartService.queryListByLimit(cart);
        if (ObjectUtil.isEmpty(carts))
            return new SuccessResponse<>("无匹配数据");
        else
            return new SuccessResponse<List<Cart>>(carts, carts.size() + "条数据");
    }

    /**
     * 通过条件查找一条数据
     */
    @ApiOperation("查找单条数据")
    @PostMapping("/one")
    public SuccessResponse<?> queryOne(@RequestBody Cart cart) {
        Cart onecart = this.cartService.queryOne(cart);
        if (ObjectUtil.isEmpty(onecart))
            return new SuccessResponse<>("没有满足的条件数据");
        else
            return new SuccessResponse<Cart>(onecart);
    }


    /**
     * 分页查询
     *
     * @param cart 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    /*@GetMapping
    public ResponseEntity<Page<Cart>> queryByPage(Cart cart, PageRequest pageRequest) {
        return ResponseEntity.ok(this.cartService.queryByPage(cart, pageRequest));
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
        Cart cart = this.cartService.queryById(id);
        if (ObjectUtil.isEmpty(cart))
            return new SuccessResponse<>("没有满足该ID的数据");
        else
            return new SuccessResponse<Cart>(cart);
    }

    /**
     * 新增数据
     *
     * @param cart 实体
     * @return 新增结果
     */
    @ApiOperation("INSERT")
    @PostMapping
    public SuccessResponse<?> add(@RequestBody Cart cart) {
        return new SuccessResponse<Cart>(this.cartService.insert(cart), "添加成功");
    }

    /**
     * 编辑数据
     *
     * @param cart 实体
     * @return 编辑结果
     */
    @PutMapping
    @ApiOperation("UPDATE")
    public SuccessResponse<?> edit(@RequestBody Cart cart) {
        return new SuccessResponse<Cart>(this.cartService.update(cart), "修改成功");
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
        return new SuccessResponse<Boolean>(this.cartService.deleteById(id), "删除成功");
    }

    @ApiOperation("LIKE模糊查找")
    @PostMapping("/dim")
    public SuccessResponse<?> queryDimList(@RequestBody Cart cart) {
        List<Cart> carts = this.cartService.getDimList(cart);
        if (ObjectUtil.isEmpty(carts))
            return new SuccessResponse<>(carts, "", 200);
        else
            return new SuccessResponse<List<Cart>>(carts, carts.size() + "条数据");
    }

}

