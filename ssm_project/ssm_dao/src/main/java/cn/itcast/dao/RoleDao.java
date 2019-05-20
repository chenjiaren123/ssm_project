package cn.itcast.dao;

import cn.itcast.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao {
    /**
     * 根据用户id查询出所对应的角色
     *
     * @param userId
     * @return
     */
    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "roleName", property = "roleName"),
            @Result(column = "roleDesc", property = "roleDesc"),
            @Result(column = "id", property = "permissions", javaType = java.util.List.class,
                    many = @Many(select = "cn.itcast.dao.PermissionDao.findByRoleId"))
    })
    List<Role> findByUserId(String userId) throws Exception;

    /**
     * 查询所有角色信息
     *
     * @return
     */
    @Select("select * from role")
    List<Role> findAll() throws Exception;

    /**
     * 保存角色信息
     *
     * @param role
     */
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role) throws Exception;

    @Select("select * from role where id not in(select roleId from user_role where userId=#{id})")
    List<Role> findOtherRole(String id) throws Exception;

    @Insert("insert into role_permission (roleId,permissionId) values(#{roleId},#{permissionId}) ")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId) throws Exception;

    @Select("select * from role where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "roleName", property = "roleName"),
            @Result(column = "roleDesc", property = "roleDesc"),
            @Result(column = "id", property = "permissions",javaType = List.class,
                    many = @Many(select = "cn.itcast.dao.PermissionDao.findByRoleId")),
    })
    Role findById(String id) throws Exception;

    @Delete("delete from role where id = #{roleId}")
    void deleteRoleById(String id) throws Exception;

    @Delete("delete from users_role where roleId=#{roleId}")
    void deleteFromUser_RoleByRoleId(String roleId) throws Exception;
    @Delete("delete from role_permission where roleId=#{roleId}")
    void deleteFromRole_PermissionByRoleId(String roleId) throws Exception;
}
