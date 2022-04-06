package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.example.demo.entity.query.PageQuery;
import com.example.demo.result.PageResponse;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("add")
    public Long add(@RequestBody User user) {
        return userService.add(user);
    }

    @PostMapping("update")
    public Boolean update(@RequestBody User user) {
        return userService.update(user);
    }

    @PostMapping("delete")
    public Boolean delete(@RequestBody List<Long> ids) {
        return userService.batchDelete(ids);
    }

    @PostMapping("enable")
    public Boolean enable(@RequestBody List<Long> ids, @RequestBody Boolean enable) {
        return userService.batchEnable(ids, enable);
    }

    @GetMapping("list")
    public PageResponse<User> list(PageQuery pageQuery) {
        Page<User> page = userService.list(pageQuery);
        return PageResponse.convert(page);
    }

    @GetMapping("detail")
    public User detail(Long id) {
        return userService.get(id);
    }
}