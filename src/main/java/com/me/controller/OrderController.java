package com.me.controller;

import com.me.entity.Order;
import com.me.service.OrderService;
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
 * 历史购买订单表(Order)表控制层
 *
 * @author yushi
 * @since 2024-12-20 12:02:00
 */
@Api(tags = "历史购买订单表")
@RestController
@RequestMapping("order")
public class OrderController {
    /**
     * 服务对象
     */
    @Resource
    private OrderService orderService;


    /**
     * 获取全部数据
     *
     * @return 全部数据
     */
    @ApiOperation("SELECT *")
    @GetMapping("/list")
    public SuccessResponse<?> queryList(Order order) {
        List<Order> orders = this.orderService.queryListByLimit(order);
        return new SuccessResponse<List<Order>>(orders, "列表");

    }


    /**
     * 通过条件查找多条数据
     */
    @ApiOperation("查找多条数据")
    @PostMapping("/some")
    public SuccessResponse<?> queryListByLimit(@RequestBody Order order) {
        List<Order> orders = this.orderService.queryListByLimit(order);
        if (ObjectUtil.isEmpty(orders))
            return new SuccessResponse<>("无匹配数据");
        else
            return new SuccessResponse<List<Order>>(orders, orders.size() + "条数据");
    }

    /**
     * 通过条件查找一条数据
     */
    @ApiOperation("查找单条数据")
    @PostMapping("/one")
    public SuccessResponse<?> queryOne(@RequestBody Order order) {
        Order oneorder = this.orderService.queryOne(order);
        if (ObjectUtil.isEmpty(oneorder))
            return new SuccessResponse<>("没有满足的条件数据");
        else
            return new SuccessResponse<Order>(oneorder);
    }


    /**
     * 分页查询
     *
     * @param order 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    /*@GetMapping
    public ResponseEntity<Page<Order>> queryByPage(Order order, PageRequest pageRequest) {
        return ResponseEntity.ok(this.orderService.queryByPage(order, pageRequest));
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
        Order order = this.orderService.queryById(id);
        if (ObjectUtil.isEmpty(order))
            return new SuccessResponse<>("没有满足该ID的数据");
        else
            return new SuccessResponse<Order>(order);
    }

    /**
     * 新增数据
     *
     * @param order 实体
     * @return 新增结果
     */
    @ApiOperation("INSERT")
    @PostMapping
    public SuccessResponse<?> add(@RequestBody Order order) {
        return new SuccessResponse<Order>(this.orderService.insert(order), "添加成功");
    }

    /**
     * 编辑数据
     *
     * @param order 实体
     * @return 编辑结果
     */
    @PutMapping
    @ApiOperation("UPDATE")
    public SuccessResponse<?> edit(@RequestBody Order order) {
        return new SuccessResponse<Order>(this.orderService.update(order), "修改成功");
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
        return new SuccessResponse<Boolean>(this.orderService.deleteById(id), "删除成功");
    }

    @ApiOperation("LIKE模糊查找")
    @PostMapping("/dim")
    public SuccessResponse<?> queryDimList(@RequestBody Order order) {
        List<Order> orders = this.orderService.getDimList(order);
        if (ObjectUtil.isEmpty(orders))
            return new SuccessResponse<>(orders, "", 200);
        else
            return new SuccessResponse<List<Order>>(orders, orders.size() + "条数据");
    }

}

