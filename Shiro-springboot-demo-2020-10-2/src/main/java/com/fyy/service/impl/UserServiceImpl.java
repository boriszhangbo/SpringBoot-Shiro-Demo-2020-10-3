package com.fyy.service.impl;

import com.fyy.mapper.UserMapper;
import com.fyy.pojo.User;
import com.fyy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  @ClassName: UserServiceImpl   
 *  @Description: 
 *  @Author: fu yuanyuan
 *  @CreateDate: 2020/10/2 23:26
 *  @UpdateUser:    
 *  @UpdateDate: 
 *  @UpdateRemark:    
 *  @Version: 1.0   
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }
}
