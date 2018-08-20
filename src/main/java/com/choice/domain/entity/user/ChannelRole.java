package com.choice.domain.entity.user;

import java.util.Date;

public class ChannelRole {
    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 添加人
     */
    private Integer addPerson;

    /**
     * 修改时间
     */
    private Date editTime;

    /**
     * 修改人
     */
    private Integer editPerson;

    /**
     * 0为正常1为删除
     */
    private Integer isDelete;

    /**
     * 0为系统角色，1为自定义角色
     */
    private Integer flag;

    /**
     * 角色id
     * @return role_id 角色id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 角色id
     * @param roleId 角色id
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 角色名称
     * @return role_name 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 角色名称
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 添加时间
     * @return add_time 添加时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 添加时间
     * @param addTime 添加时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 添加人
     * @return add_person 添加人
     */
    public Integer getAddPerson() {
        return addPerson;
    }

    /**
     * 添加人
     * @param addPerson 添加人
     */
    public void setAddPerson(Integer addPerson) {
        this.addPerson = addPerson;
    }

    /**
     * 修改时间
     * @return edit_time 修改时间
     */
    public Date getEditTime() {
        return editTime;
    }

    /**
     * 修改时间
     * @param editTime 修改时间
     */
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    /**
     * 修改人
     * @return edit_person 修改人
     */
    public Integer getEditPerson() {
        return editPerson;
    }

    /**
     * 修改人
     * @param editPerson 修改人
     */
    public void setEditPerson(Integer editPerson) {
        this.editPerson = editPerson;
    }

    /**
     * 0为正常1为删除
     * @return is_delete 0为正常1为删除
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 0为正常1为删除
     * @param isDelete 0为正常1为删除
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 0为系统角色，1为自定义角色
     * @return flag 0为系统角色，1为自定义角色
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * 0为系统角色，1为自定义角色
     * @param flag 0为系统角色，1为自定义角色
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }    
}