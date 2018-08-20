package com.choice.domain.entity.user;
public class ChannelPower {
    /**
     * 
     */
    private Integer powerId;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 用户菜单
     */
    private Integer menuId;

    /**
     * 
     * @return power_id 
     */
    public Integer getPowerId() {
        return powerId;
    }

    /**
     * 
     * @param powerId 
     */
    public void setPowerId(Integer powerId) {
        this.powerId = powerId;
    }

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
     * 用户菜单
     * @return menu_id 用户菜单
     */
    public Integer getMenuId() {
        return menuId;
    }

    /**
     * 用户菜单
     * @param menuId 用户菜单
     */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
}