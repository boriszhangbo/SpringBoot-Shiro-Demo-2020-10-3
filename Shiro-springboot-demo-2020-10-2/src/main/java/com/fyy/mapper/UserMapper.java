package com.fyy.mapper;

import com.fyy.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 *  @ClassName: UserMapper   
 *  @Description: 
 *  @Author: fu yuanyuan
 *  @CreateDate: 2020/10/2 23:19
 *  @UpdateUser:    
 *  @UpdateDate: 
 *  @UpdateRemark:    
 *  @Version: 1.0   
 */
@Repository
@Mapper
public interface UserMapper {
    public User queryUserByName(String name);
}
