package com.me.controller;

import com.me.entity.Type;
import com.me.service.TypeService;
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
 * 珠宝产品种类表(Type)表控制层
 *
 * @author yushi
 * @since 2024-12-20 12:02:01
 */
@Api(tags = "珠宝产品种类表")
@RestController
@RequestMapping("type")
public class TypeController {
    /**
     * 服务对象
     */
    @Resource
    private TypeService typeService;


    /**
     * 获取全部数据
     *
     * @return 全部数据
     */
    @ApiOperation("SELECT *")
    @GetMapping("/list")
    public SuccessResponse<?> queryList(Type type) {
        List<Type> types = this.typeService.queryListByLimit(type);
        return new SuccessResponse<List<Type>>(types, "列表");

    }


    /**
     * 通过条件查找多条数据
     */
    @ApiOperation("查找多条数据")
    @PostMapping("/some")
    public SuccessResponse<?> queryListByLimit(@RequestBody Type type) {
        List<Type> types = this.typeService.queryListByLimit(type);
        if (ObjectUtil.isEmpty(types))
            return new SuccessResponse<>("无匹配数据");
        else
            return new SuccessResponse<List<Type>>(types, types.size() + "条数据");
    }

    /**
     * 通过条件查找一条数据
     */
    @ApiOperation("查找单条数据")
    @PostMapping("/one")
    public SuccessResponse<?> queryOne(@RequestBody Type type) {
        Type onetype = this.typeService.queryOne(type);
        if (ObjectUtil.isEmpty(onetype))
            return new SuccessResponse<>("没有满足的条件数据");
        else
            return new SuccessResponse<Type>(onetype);
    }


    /**
     * 分页查询
     *
     * @param type 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    /*@GetMapping
    public ResponseEntity<Page<Type>> queryByPage(Type type, PageRequest pageRequest) {
        return ResponseEntity.ok(this.typeService.queryByPage(type, pageRequest));
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
        Type type = this.typeService.queryById(id);
        if (ObjectUtil.isEmpty(type))
            return new SuccessResponse<>("没有满足该ID的数据");
        else
            return new SuccessResponse<Type>(type);
    }

    /**
     * 新增数据
     *
     * @param type 实体
     * @return 新增结果
     */
    @ApiOperation("INSERT")
    @PostMapping
    public SuccessResponse<?> add(@RequestBody Type type) {
        return new SuccessResponse<Type>(this.typeService.insert(type), "添加成功");
    }

    /**
     * 编辑数据
     *
     * @param type 实体
     * @return 编辑结果
     */
    @PutMapping
    @ApiOperation("UPDATE")
    public SuccessResponse<?> edit(@RequestBody Type type) {
        return new SuccessResponse<Type>(this.typeService.update(type), "修改成功");
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
        return new SuccessResponse<Boolean>(this.typeService.deleteById(id), "删除成功");
    }

    @ApiOperation("LIKE模糊查找")
    @PostMapping("/dim")
    public SuccessResponse<?> queryDimList(@RequestBody Type type) {
        List<Type> types = this.typeService.getDimList(type);
        if (ObjectUtil.isEmpty(types))
            return new SuccessResponse<>(types, "", 200);
        else
            return new SuccessResponse<List<Type>>(types, types.size() + "条数据");
    }

}

