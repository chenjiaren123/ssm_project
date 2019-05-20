package cn.itcast.service;

import cn.itcast.domain.Permission;

import java.util.List;

public interface PermissionService {
    /**
     * 查询所有权限
     *
     * @return
     */
    List<Permission> findAll() throws Exception;

    /**
     * 资源权限添加
     *
     * @param permission
     */
    void save(Permission permission) throws Exception;

    List<Permission> findOtherPermission(String roleId) throws Exception;

    Permission findById(String id) throws Exception;

    void deleteById(String id) throws Exception;
}
