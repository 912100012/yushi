package com.me.controller;

import com.me.entity.Favorite;
import com.me.service.FavoriteService;
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
 * 收藏表(Favorite)表控制层
 *
 * @author yushi
 * @since 2025-01-24 20:03:20
 */
@Api(tags = "收藏表")
@RestController
@RequestMapping("favorite")
public class FavoriteController {
    /**
     * 服务对象
     */
    @Resource
    private FavoriteService favoriteService;


    /**
     * 获取全部数据
     *
     * @return 全部数据
     */
    @ApiOperation("SELECT *")
    @GetMapping("/list")
    public SuccessResponse<?> queryList(Favorite favorite) {
        List<Favorite> favorites = this.favoriteService.queryListByLimit(favorite);
        return new SuccessResponse<List<Favorite>>(favorites, "列表");

    }


    /**
     * 通过条件查找多条数据
     */
    @ApiOperation("查找多条数据")
    @PostMapping("/some")
    public SuccessResponse<?> queryListByLimit(@RequestBody Favorite favorite) {
        List<Favorite> favorites = this.favoriteService.queryListByLimit(favorite);
        if (ObjectUtil.isEmpty(favorites))
            return new SuccessResponse<>("无匹配数据");
        else
            return new SuccessResponse<List<Favorite>>(favorites, favorites.size() + "条数据");
    }

    /**
     * 通过条件查找一条数据
     */
    @ApiOperation("查找单条数据")
    @PostMapping("/one")
    public SuccessResponse<?> queryOne(@RequestBody Favorite favorite) {
        Favorite onefavorite = this.favoriteService.queryOne(favorite);
        if (ObjectUtil.isEmpty(onefavorite))
            return new SuccessResponse<>("没有满足的条件数据");
        else
            return new SuccessResponse<Favorite>(onefavorite);
    }


    /**
     * 分页查询
     *
     * @param favorite 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    /*@GetMapping
    public ResponseEntity<Page<Favorite>> queryByPage(Favorite favorite, PageRequest pageRequest) {
        return ResponseEntity.ok(this.favoriteService.queryByPage(favorite, pageRequest));
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
        Favorite favorite = this.favoriteService.queryById(id);
        if (ObjectUtil.isEmpty(favorite))
            return new SuccessResponse<>("没有满足该ID的数据");
        else
            return new SuccessResponse<Favorite>(favorite);
    }

    /**
     * 新增数据
     *
     * @param favorite 实体
     * @return 新增结果
     */
    @ApiOperation("INSERT")
    @PostMapping
    public SuccessResponse<?> add(@RequestBody Favorite favorite) {
        return new SuccessResponse<Favorite>(this.favoriteService.insert(favorite), "添加成功");
    }

    /**
     * 编辑数据
     *
     * @param favorite 实体
     * @return 编辑结果
     */
    @PutMapping
    @ApiOperation("UPDATE")
    public SuccessResponse<?> edit(@RequestBody Favorite favorite) {
        return new SuccessResponse<Favorite>(this.favoriteService.update(favorite), "修改成功");
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
        return new SuccessResponse<Boolean>(this.favoriteService.deleteById(id), "删除成功");
    }

    @ApiOperation("LIKE模糊查找")
    @PostMapping("/dim")
    public SuccessResponse<?> queryDimList(@RequestBody Favorite favorite) {
        List<Favorite> favorites = this.favoriteService.getDimList(favorite);
        if (ObjectUtil.isEmpty(favorites))
            return new SuccessResponse<>(favorites, "", 200);
        else
            return new SuccessResponse<List<Favorite>>(favorites, favorites.size() + "条数据");
    }

}

