/**
 * 
 */
package com.pradheep.dao.config;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pradheep.dao.model.PermissionType;
import com.pradheep.dao.model.UserPermission;
import com.pradheep.dao.model.UserType;




/**
 * @author pradheep.p
 * 
 */

@Service
@Transactional
public class UserManagementService {

  static {
    System.out.println("Loading the UserManagementService ..");
  }

  @Autowired
  SessionFactory sessionFactory;

  Logger logger = ApplicationLogger.getLogBean(this.getClass());

  /**
   * Generic method to get all users by type//
   * 
   * @param type
   * @return
   * @see com.mangement.usertype.User.UserType
   */
  public Collection<UserType> getAllUsersByType(String type) {
    return null;
  }

  public List<UserType> getAllUsersInCompany(int companyId) {
    Session session = null;
    try {
      String sql = "from UserType";// where country='" + companyId + "'";
      session = sessionFactory.openSession();
      Query query = session.createQuery(sql);
      List<UserType> list = query.list();
      logger.info("Loading the user info : " + list);
      return list;
    } catch (Exception err) {
      err.printStackTrace();
    } finally {
      session.close();
    }
    return null;
  }

  public UserType getUserByloginName(String loginName) {
    Session session = null;
    try {
      String sql = "from UserType where loginName='" + loginName + "'";
      session = sessionFactory.openSession();
      Query query = session.createQuery(sql);
      List<UserType> list = query.list();
      logger.info("Loading the user info : " + list);
      if (list.isEmpty()) {
        return null;
      } else {
        UserType ut = (UserType) list.get(0);
        return ut;
      }
    } catch (Exception err) {
      err.printStackTrace();
    } finally {
      session.close();
    }
    return null;
  }

  public UserType getUserByUserId(int userId) {
    Session session = null;
    try {
      String sql = "from UserType ut where ut.userID = " + userId;
      session = sessionFactory.openSession();
      Query query = session.createQuery(sql);
      List<UserType> list = query.list();
      logger.info("Loading the user info : " + list);
      if (list.isEmpty()) {
        return null;
      } else {
        UserType ut = (UserType) list.get(0);
        return ut;
      }
    } catch (Exception err) {
      err.printStackTrace();
    } finally {
      session.close();
    }
    return null;
  }

  public List<PermissionType> getAllPermission() {
    Session session = null;
    String sql = "from PermissionType";
    logger.info(sql);
    session = sessionFactory.openSession();
    Query query = session.createQuery(sql);
    List<PermissionType> list = query.list();
    return list;
  }

/*  public List<CompanyType> getAllCompanies() {
    Session session = null;
    String sql = "from CompanyType";
    logger.info(sql);
    session = sessionFactory.openSession();
    Query query = session.createQuery(sql);
    List<CompanyType> list = query.list();
    return list;
  }*/

  /**
   * @param userName
   * @return
   */
  public Collection<GrantedAuthority> getUserAuthorities(String loginName) {
    Session session = null;
    try {
      // String sql =
      // "from PermissionType as pt where UserPermission.userID=UserType.userID and UserPermission.permissionID=pt.permissionID and UserType.loginName='"
      // + userName + "'";
      String authSql =
          "select permInfo.userRole from UserType as userType join UserPermission userPerm left outer join PermissionType as permInfo";
      String nativeSQL =
          "select pti.user_role from admin_user_info as ui,user_permission_info as upi , permission_type_info as pti where ui.user_id = upi.user_id and pti.permission_id = upi.permission_id and ui.login_name ='"
              + loginName + "'";
      String sql = "from PermissionType";
      logger.info(sql);
      session = sessionFactory.openSession();
      Query query = session.createSQLQuery(nativeSQL);
      List<PermissionType> list = query.list();
      logger.debug("Authorities - " + list.size() + " items Found");
      Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
      Iterator iter = list.iterator();
      while (iter.hasNext()) {
        Object obj = iter.next();
        System.out.println("Permission " + obj.toString());
        authorities.add(new MyGrantedAuthority(obj.toString()));
      }
      return authorities;
    } catch (Exception err) {
      err.printStackTrace();
    } finally {
      session.close();
    }
    return null;
  }

  /**
   * Generic method to add a user into database
   * 
   * @param userobj
   * @return
   */

  @Secured({"ROLE_ADMIN"})
  public boolean addUser(UserType userobj) {
    Session session = null;
    Transaction transact = null;
    boolean flag = false;
    try {
      
      session = sessionFactory.openSession();
      transact = session.beginTransaction();
      session.save(userobj);
      transact.commit();
      logger.info("User created successfully");
      flag = true;
    } catch (Exception err) {
      logger.error("Error while saving user information " + err.getMessage());
      logErrorMessage(err);
      err.printStackTrace();
      transact.rollback();
    } finally {
      session.close();
    }
    return flag;
  }


  /**
   * This method is used to update a user type object.
   * 
   * @param userobj
   * @return
   */
  @Secured({"ROLE_ADMIN"})  
  public boolean updateUser(UserType userobj) {
    Session session = null;
    Transaction transact = null;
    boolean flag = false;
    try {
     
      session = sessionFactory.openSession();
      transact = session.beginTransaction();
      session.update(userobj);
      transact.commit();
      logger.info("User created successfully");
      flag = true;
    } catch (Exception err) {
      logger.error("Error while saving user information " + err.getMessage());
      logErrorMessage(err);
      err.printStackTrace();
      transact.rollback();
    } finally {
      session.close();
    }
    return flag;
  }

  private void logErrorMessage(Exception err) {
    StringWriter stack = new StringWriter();
    err.printStackTrace(new PrintWriter(stack));
    logger.debug("LOG Error : " + stack.toString());
  }

  /**
   * Generic method to delete a user.
   * 
   * @param userObj
   * @return
   */
  public boolean deleteUser(UserType userObj) {
    Session session = null;
    try {
      Transaction transact = null;
      logger.info("Session ## " + sessionFactory.toString());
      session = sessionFactory.openSession();
      transact = session.beginTransaction();
      session.delete(userObj);
      transact.commit();
      logger.info("User deleted successfully");
      return true;
    } catch (Exception err) {
      logger.error("Error while deleting user information " + err.getMessage());
      logErrorMessage(err);
      err.printStackTrace();
    } finally {
      session.close();
    }
    return false;
  }

  public Collection<UserType> getAllUsersInACompany(int companyCode) {
    return null;
  }

  public Collection<UserType> getAllUsersInABranch(int branchCode) {
    return null;
  }

  

  public boolean createAnUserPermission(String permissionId, String userId) {
    boolean flag = false;
    UserPermission userPermission = new UserPermission();
    int permissionID = Integer.parseInt(permissionId);
    userPermission.setPermissionID(permissionID);
    int userID = Integer.parseInt(userId);
    userPermission.setUserID(userID);
    Session session = null;
    Transaction transact = null;
    try {

      session = sessionFactory.openSession();
      transact = session.beginTransaction();
      session.save(userPermission);
      transact.commit();
      logger.info("User permission created successfully");
      flag = true;
    } catch (Exception err) {
      logger.error("Error while saving user permission information " + err.getMessage());
      logErrorMessage(err);
      err.printStackTrace();
      flag = false;
      transact.rollback();
    } finally {
      session.close();
    }
    return flag;
  }

/*  @Secured({"ROLE_ADMIN"})  
  public boolean addCompany(CompanyType companyType) {
    Session session = null;
    Transaction transact = null;
    boolean flag = false;
    try {
      logger.info("Trying to create the company information : " + companyType.getName());
      session = sessionFactory.openSession();
      transact = session.beginTransaction();
      session.save(companyType);
      transact.commit();
      logger.info("Company created successfully");
      flag = true;
    } catch (Exception err) {
      logger.error("Error while saving company information " + err.getMessage());
      logErrorMessage(err);
      err.printStackTrace();
      transact.rollback();
    } finally {
      session.close();
    }
    return flag;
  }*/

/*  public List<CompanyType> getAllCompany() {
    logger.info("Loading all company info..");
    Session session = null;
    try {
      String sql = "from CompanyType";
      session = sessionFactory.openSession();
      Query query = session.createQuery(sql);
      List<CompanyType> list = query.list();
      logger.info("Loading the company info : " + list);
      return list;
    } catch (Exception err) {
      err.printStackTrace();
    } finally {
      session.close();
    }
    return null;
  }*/
  
  public void changePassword(int userId,String oldPassword,String newPassword){
	  logger.info("Changing password ..");
  }
  
  
  
  

}
