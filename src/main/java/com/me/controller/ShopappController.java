package com.me.controller;

import com.me.entity.Shopapp;
import com.me.service.ShopappService;
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
 * 店铺申请表(Shopapp)表控制层
 *
 * @author yushi
 * @since 2024-12-19 21:01:05
 */
@Api(tags = "店铺申请表")
@RestController
@RequestMapping("shopapp")
public class ShopappController {
    /**
     * 服务对象
     */
    @Resource
    private ShopappService shopappService;


    /**
     * 获取全部数据
     *
     * @return 全部数据
     */
    @ApiOperation("参数无，获取该表的所有数据")
    @GetMapping("/list")
    public SuccessResponse<?> queryList(Shopapp shopapp) {
        List<Shopapp> shopapps = this.shopappService.queryListByLimit(shopapp);
        return new SuccessResponse<List<Shopapp>>(shopapps, "列表");

    }


    /**
     * 通过条件查找多条数据
     */
    @ApiOperation("通过属性赋值查找多条数据")
    @PostMapping("/some")
    public SuccessResponse<?> queryListByLimit(@RequestBody Shopapp shopapp) {
        List<Shopapp> shopapps = this.shopappService.queryListByLimit(shopapp);
        if (ObjectUtil.isEmpty(shopapps))
            return new SuccessResponse<>("无匹配数据");
        else
            return new SuccessResponse<List<Shopapp>>(shopapps, shopapps.size() + "条数据");
    }

    /**
     * 通过条件查找一条数据
     */
    @ApiOperation("通过属性赋值查找单条数据")
    @PostMapping("/one")
    public SuccessResponse<?> queryOne(@RequestBody Shopapp shopapp) {
        Shopapp oneshopapp = this.shopappService.queryOne(shopapp);
        if (ObjectUtil.isEmpty(oneshopapp))
            return new SuccessResponse<>("没有满足的条件数据");
        else
            return new SuccessResponse<Shopapp>(oneshopapp);
    }


    /**
     * 分页查询
     *
     * @param shopapp 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    /*@GetMapping
    public ResponseEntity<Page<Shopapp>> queryByPage(Shopapp shopapp, PageRequest pageRequest) {
        return ResponseEntity.ok(this.shopappService.queryByPage(shopapp, pageRequest));
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
        Shopapp shopapp = this.shopappService.queryById(id);
        if (ObjectUtil.isEmpty(shopapp))
            return new SuccessResponse<>("没有满足该ID的数据");
        else
            return new SuccessResponse<Shopapp>(shopapp);
    }

    /**
     * 新增数据
     *
     * @param shopapp 实体
     * @return 新增结果
     */
    @ApiOperation("新增一条数据")
    @PostMapping
    public SuccessResponse<?> add(@RequestBody Shopapp shopapp) {
        return new SuccessResponse<Shopapp>(this.shopappService.insert(shopapp), "添加成功");
    }

    /**
     * 编辑数据
     *
     * @param shopapp 实体
     * @return 编辑结果
     */
    @PutMapping
    @ApiOperation("修改一条数据")
    public SuccessResponse<?> edit(@RequestBody Shopapp shopapp) {
        return new SuccessResponse<Shopapp>(this.shopappService.update(shopapp), "修改成功");
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/{id}")
    @ApiOperation("通过主键ID删除一条数据")
    public SuccessResponse<?> deleteById(@ApiParam("主键ID") @PathVariable("id") Integer id) {
        return new SuccessResponse<Boolean>(this.shopappService.deleteById(id), "删除成功");
    }

    @ApiOperation("通过关键字模糊查找，sql的LIKE")
    @PostMapping("/dim")
    public SuccessResponse<?> queryDimList(@RequestBody Shopapp shopapp) {
        List<Shopapp> shopapps = this.shopappService.getDimList(shopapp);
        if (ObjectUtil.isEmpty(shopapps))
            return new SuccessResponse<>(shopapps, "", 200);
        else
            return new SuccessResponse<List<Shopapp>>(shopapps, shopapps.size() + "条数据");
    }

}

