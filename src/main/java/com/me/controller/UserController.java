package com.me.controller;

import com.me.entity.User;
import com.me.service.UserService;
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
 * 用户信息表(User)表控制层
 *
 * @author yushi
 * @since 2024-12-20 12:02:02
 */
@Api(tags = "用户信息表")
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;


    /**
     * 获取全部数据
     *
     * @return 全部数据
     */
    @ApiOperation("SELECT *")
    @GetMapping("/list")
    public SuccessResponse<?> queryList(User user) {
        List<User> users = this.userService.queryListByLimit(user);
        return new SuccessResponse<List<User>>(users, "列表");

    }


    /**
     * 通过条件查找多条数据
     */
    @ApiOperation("查找多条数据")
    @PostMapping("/some")
    public SuccessResponse<?> queryListByLimit(@RequestBody User user) {
        List<User> users = this.userService.queryListByLimit(user);
        if (ObjectUtil.isEmpty(users))
            return new SuccessResponse<>("无匹配数据");
        else
            return new SuccessResponse<List<User>>(users, users.size() + "条数据");
    }

    /**
     * 通过条件查找一条数据
     */
    @ApiOperation("查找单条数据")
    @PostMapping("/one")
    public SuccessResponse<?> queryOne(@RequestBody User user) {
        User oneuser = this.userService.queryOne(user);
        if (ObjectUtil.isEmpty(oneuser))
            return new SuccessResponse<>("没有满足的条件数据");
        else
            return new SuccessResponse<User>(oneuser);
    }


    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    /*@GetMapping
    public ResponseEntity<Page<User>> queryByPage(User user, PageRequest pageRequest) {
        return ResponseEntity.ok(this.userService.queryByPage(user, pageRequest));
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
        User user = this.userService.queryById(id);
        if (ObjectUtil.isEmpty(user))
            return new SuccessResponse<>("没有满足该ID的数据");
        else
            return new SuccessResponse<User>(user);
    }

    /**
     * 新增数据
     *
     * @param user 实体
     * @return 新增结果
     */
    @ApiOperation("INSERT")
    @PostMapping
    public SuccessResponse<?> add(@RequestBody User user) {
        return new SuccessResponse<User>(this.userService.insert(user), "添加成功");
    }

    /**
     * 编辑数据
     *
     * @param user 实体
     * @return 编辑结果
     */
    @PutMapping
    @ApiOperation("UPDATE")
    public SuccessResponse<?> edit(@RequestBody User user) {
        return new SuccessResponse<User>(this.userService.update(user), "修改成功");
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
        return new SuccessResponse<Boolean>(this.userService.deleteById(id), "删除成功");
    }

    @ApiOperation("LIKE模糊查找")
    @PostMapping("/dim")
    public SuccessResponse<?> queryDimList(@RequestBody User user) {
        List<User> users = this.userService.getDimList(user);
        if (ObjectUtil.isEmpty(users))
            return new SuccessResponse<>(users, "", 200);
        else
            return new SuccessResponse<List<User>>(users, users.size() + "条数据");
    }

}

