package cn.itcast.service;

import cn.itcast.domain.Role;
import cn.itcast.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    /**
     * 查询所有用户信息
     * @return
     */
    List<UserInfo> findAll() throws Exception;

    /**
     * 保存用户信息
     * @param user
     */
    void save(UserInfo user) throws Exception;
    /**
     * 通过id查询用户信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    UserInfo findById(String id) throws Exception;

    List<Role> findOtherRole(String userId) throws Exception;

    void addRoleToUser(String userId, String[] roleIds) throws Exception;
}
