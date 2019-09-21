/**
 * 
 */
package com.pradheep.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author pradheep.p
 * 
 */
@Entity
@Table(name="user_permission_info")
public class UserPermission implements Serializable{

  @Id
  @Column(name="user_id")
  private int userID;

  @Id
  @Column(name="permission_id")
  private int permissionID;

  public int getUserID() {
    return userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }

  public int getPermissionID() {
    return permissionID;
  }

  public void setPermissionID(int permissionID) {
    this.permissionID = permissionID;
  }

}
