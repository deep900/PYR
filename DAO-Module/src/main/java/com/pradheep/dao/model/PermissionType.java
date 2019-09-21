/**
 * 
 */
package com.pradheep.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author pradheep.p
 *
 */
@Entity
@Table(name="permission_type_info")
public class PermissionType {

  @Id
  @Column(name="permission_id")
  @GeneratedValue
  private int permissionID;
  
  @Column(name="user_role")
  private String userRole;

  public int getPermissionID() {
    return permissionID;
  }

  public void setPermissionID(int permissionID) {
    this.permissionID = permissionID;
  }

  public String getUserRole() {
    return userRole;
  }

  public void setUserRole(String userRole) {
    this.userRole = userRole;
  }
}
