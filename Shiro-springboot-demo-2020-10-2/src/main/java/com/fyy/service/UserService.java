package com.fyy.service;

import com.fyy.pojo.User;

/**
 *  @ClassName: UserService   
 *  @Description: 
 *  @Author: fu yuanyuan
 *  @CreateDate: 2020/10/2 23:25
 *  @UpdateUser:    
 *  @UpdateDate: 
 *  @UpdateRemark:    
 *  @Version: 1.0   
 */
public interface UserService {
    public User queryUserByName(String name);
}
