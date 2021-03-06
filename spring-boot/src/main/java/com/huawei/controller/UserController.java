package com.huawei.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.huawei.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController  
@RequestMapping(value = "/user")  
@Api(description = "用户")  
public class UserController {
	static Map<String,User> userMap = Collections.synchronizedMap(new HashMap<String, User>());  
	  
    @ApiOperation(value = "获取用户列表")  
    @RequestMapping(value = "/getUserList" , method = RequestMethod.GET)  
    public List<User> getUserList(){  
        List<User> userList = new ArrayList<User>(userMap.values());  
        return userList;  
    }  
  
  
    @ApiOperation(value="创建用户", notes="根据User对象创建用户")  
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")  
    @RequestMapping(value="/addUser", method=RequestMethod.POST)  
    public String addUser(@RequestBody User user) {  
        userMap.put(user.getId(), user);  
        return "success";  
    }  
  
    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")  
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String")  
    @RequestMapping(value="/getById/{id}", method=RequestMethod.GET)  
    public User getById(@PathVariable String id) {  
        return userMap.get(id);  
    }  
  
  
    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")  
    @ApiImplicitParams({  
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String"),  
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")  
    })  
    @RequestMapping(value="/updateById/{id}", method=RequestMethod.PUT)  
    public String updateById(@PathVariable String id, @RequestBody User user) {  
        User u = userMap.get(id);  
        u.setName(user.getName());  
        u.setAge(user.getAge());  
        userMap.put(id, u);  
        return "success";  
    }  
  
  
    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")  
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String")  
    @RequestMapping(value="/deleteById/{id}", method=RequestMethod.DELETE)  
    public String deleteById(@PathVariable String id) {  
        userMap.remove(id);  
        return "success";  
    } 
}
