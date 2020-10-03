package com.fyy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  @ClassName: User   
 *  @Description: 
 *  @Author: fu yuanyuan
 *  @CreateDate: 2020/10/2 23:16
 *  @UpdateUser:    
 *  @UpdateDate: 
 *  @UpdateRemark:    
 *  @Version: 1.0   
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String pwd;
    private String perms;
}
