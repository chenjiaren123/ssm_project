package cn.itcast.service;

import cn.itcast.domain.Role;

import java.util.List;

public interface RoleService {
    /**
     *查询所有用户信息
     * @return
     * @throws Exception
     */
    List<Role> findAll() throws Exception;

    /**
     * 保存角色信息
     * @param role
     */
    void save(Role role) throws Exception;

    /**
     * 根据id查询角色信息
     * @param roleId
     * @return
     */
    Role findById(String roleId) throws Exception;

    /**
     * 给角色添加权限
     *
     * @param roleId
     * @param permissionIds
     */
    void addPermissionToRole(String roleId, String[] permissionIds) throws Exception;

    /**
     * 删除角色
     * @param roleId
     */
    void deleteRoleById(String roleId) throws Exception;
}
