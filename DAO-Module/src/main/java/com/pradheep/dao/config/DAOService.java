/**
 * 
 */
package com.pradheep.dao.config;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pradheep.dao.model.DidYouKnow;
import com.pradheep.dao.model.Subscription;
import com.pry.security.utility.PublicUtility;

/**
 * @author pradheep.p
 *
 */
@Service
public class DAOService implements InitializingBean {

	private Logger logger;	
	
	@Autowired
	private PublicUtility publicUtility;

	private static DidYouKnow previousDidYouKnow = null;

	private Logger getLogger() {
		if (logger == null) {
			return ApplicationLogger.getLogBean(getClass());
		}
		return logger;
	}

	public DAOService() {
		getLogger().info("Loading DAO Service ... Praise Your Redeemer.");
	}

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public void saveObject(Object obj) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction transObj = session.beginTransaction();
			session.save(obj);
			transObj.commit();
			getLogger().info("Object saved.." + obj.getClass().getName());
		} catch (Exception err) {
			getLogger().error(err.getMessage(), err);
		} finally {
			if (null != session && session.isOpen()) {
				session.close();
				session = null;
			}
		}
	}
	
	@Transactional
	public Object saveObjectAndReturn(Object obj) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction transObj = session.beginTransaction();
			session.save(obj);
			transObj.commit();
			getLogger().info("Object saved.." + obj.getClass().getName());
			return obj;
		} catch (Exception err) {
			getLogger().error(err.getMessage(), err);
		} finally {
			if (null != session && session.isOpen()) {
				session.close();
				session = null;
			}
		}
		return null;
	}

	@Transactional
	public void updateObject(Object obj) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction transObj = session.beginTransaction();
			session.update(obj);
			transObj.commit();
			getLogger().info("Object saved.." + obj.getClass().getName());
		} catch (Exception err) {
			getLogger().error(err.getMessage(), err);
		} finally {
			if (null != session && session.isOpen()) {
				session.close();
			}
		}
	}

	@Transactional
	public List<Object> getObjects(Class classRef) {
		Session session = null;
		try {
			getLogger().debug("You are about to invoke getObjects method with " + classRef);
			String sql = "from " + classRef.getName();
			session = sessionFactory.openSession();
			Query query = session.createQuery(sql);
			List<Object> list = query.list();
			return list;
		} catch (Exception err) {
			getLogger().error(err.getMessage(), err);
		} finally {
			if (null != session && session.isOpen()) {
				session.close();
			}
		}
		return Collections.emptyList();
	}

	@Transactional
	public void deleteObject(Object object) {
		Session session = null;
		try {
			getLogger().info("About to delete the entity : " + object);
			session = sessionFactory.openSession();
			Transaction transObj = session.beginTransaction();
			session.delete(object);
			transObj.commit();
			getLogger().info("Deleted the entry successfully");
		} catch (Exception err) {
			getLogger().error(err.getMessage(), err);
		} finally {
			if (null != session && session.isOpen()) {
				session.close();
			}
		}
	}

	@Transactional
	public Object getObjectsById(Class classRef, String queryParameter, String reference) {
		Session session = null;
		try {
			getLogger().debug("You are about to invoke getObjectsById method with " + classRef);
			String sql = "FROM " + classRef.getName() + " WHERE " + queryParameter + " ='" + reference + "'";
			session = sessionFactory.openSession();
			Query query = session.createQuery(sql);
			List<Object> list = query.list();
			if (list.isEmpty()) {
				return null;
			} else {
				return list.get(0);
			}
		} catch (Exception err) {
			getLogger().error(err.getMessage(), err);
		} finally {
			if (null != session && session.isOpen()) {
				session.close();
			}
		}
		return null;
	}
	
	@Transactional
	public List getObjectsListId(Class classRef, String queryParameter, String reference) {
		Session session = null;
		try {
			getLogger().debug("You are about to invoke getObjectsById method with " + classRef);
			String sql = "FROM " + classRef.getName() + " WHERE " + queryParameter + " ='" + reference + "'";
			session = sessionFactory.openSession();
			Query query = session.createQuery(sql);
			List<Object> list = query.list();
			if (list.isEmpty()) {
				return null;
			} else {
				return list;
			}
		} catch (Exception err) {
			getLogger().error(err.getMessage(), err);
		} finally {
			if (null != session && session.isOpen()) {
				session.close();
			}
		}
		return null;
	}

	@Transactional
	public Object getObjectsLike(Class classRef, String queryParameter, String reference) {
		Session session = null;
		try {
			getLogger().debug("You are about to invoke getObjectsById method with " + classRef);
			String sql = "FROM " + classRef.getName() + " WHERE " + queryParameter + " like '%" + reference + "%'";
			session = sessionFactory.openSession();
			Query query = session.createQuery(sql);
			List<Object> list = query.list();
			if (list.isEmpty()) {
				return null;
			} else {
				return list.get(0);
			}
		} catch (Exception err) {
			getLogger().error(err.getMessage(), err);
		} finally {
			if (null != session && session.isOpen()) {
				session.close();
			}
		}
		return null;
	}

	@Transactional
	public Object getObjectsInRandom(Class classRef, Integer maxLimit) {
		Session session = null;
		try {
			getLogger().debug("You are about to invoke getObjectsInRandom method with " + classRef);
			Criteria criteria = session.createCriteria(classRef);
			// criteria.add(Restrictions.eq('fieldVariable', anyValue));
			criteria.add(Restrictions.sqlRestriction("1=1 order by rand()"));
			criteria.setMaxResults(maxLimit);
			List<Object> list = criteria.list();
			if (list.isEmpty()) {
				return null;
			} else {
				return list.get(0);
			}
		} catch (Exception err) {
			getLogger().error(err.getMessage(), err);
		} finally {
			if (null != session && session.isOpen()) {
				session.close();
			}
		}
		return null;
	}

	@Transactional
	public List<Object> getObjectsListById(Class classRef, String queryParameter, Object reference, String symbol,
			int limits) {
		Session session = null;
		try {
			getLogger().debug("You are about to invoke getObjectsById method with " + classRef);
			session = sessionFactory.openSession();
			Criteria cr = session.createCriteria(classRef);
			if (limits != -1) {
				cr.setMaxResults(limits);
			}
			if (symbol.equals("=")) {
				cr.add(Restrictions.eq(queryParameter, reference));
			}
			List list = cr.list();
			if (list.isEmpty()) {
				return null;
			} else {
				return list;
			}
		} catch (Exception err) {
			getLogger().error(err.getMessage(), err);
		} finally {
			if (null != session && session.isOpen()) {
				session.close();
			}
		}
		return null;
	}

	/**
	 * 
	 * @param classRef
	 * @param queryParameters
	 * @param reference
	 * @param symbol
	 * @param limits
	 *            - Use -1 if there are no limits for number of records to return;
	 * @return
	 */
	@Transactional
	public List<Object> getObjectsListByMultipleParameters(Class classRef, String[] queryParameters, Object[] reference,
			String[] symbol, int limits, boolean isRandom) {
		Session session = null;
		try {
			getLogger().debug("You are about to invoke getObjectsById method with " + classRef);
			session = sessionFactory.openSession();
			Criteria cr = session.createCriteria(classRef);
			if (limits != -1) {
				cr.setMaxResults(limits);
			}
			int i = 0;
			for (; i < queryParameters.length; i++) {
				if (symbol[i].equals("=")) {
					cr.add(Restrictions.eq(queryParameters[i], reference[i]));
				}
			}
			if (isRandom) {
				cr.add(Restrictions.sqlRestriction("1=1 order by rand()"));
			}
			List list = cr.list();
			if (list.isEmpty()) {
				return null;
			} else {
				return list;
			}
		} catch (Exception err) {
			getLogger().error(err.getMessage(), err);
		} finally {
			if (null != session && session.isOpen()) {
				session.close();
			}
		}
		return null;
	}

	@Transactional
	public List<Object> getObjects(Class classRef, String criteria) {
		Session session = null;
		try {
			getLogger().debug("You are about to invoke getObjects method with " + classRef + " Criteria :" + criteria);
			session = sessionFactory.openSession();
			Criteria c = session.createCriteria(classRef);
			c.addOrder(Order.desc(criteria));
			return c.list();
		} catch (Exception err) {
			getLogger().error(err.getMessage(), err);
		} finally {
			if (null != session && session.isOpen()) {
				session.close();
			}
		}
		return Collections.emptyList();
	}
	
	@Transactional
	public List<Object> getObjectsNotIn(Class classRef,String propertyName, List<Integer> existingQuizIds) {
		Session session = null;
		try {
			getLogger().debug("You are about to invoke getObjects method with " + classRef);
			session = sessionFactory.openSession();
			Criteria c = session.createCriteria(classRef);
			Conjunction andConditions = Restrictions.conjunction();
			andConditions.add(Restrictions.not(Restrictions.in(propertyName, existingQuizIds)));						
			c.add(andConditions); 			
			return c.list();
		} catch (Exception err) {
			getLogger().error(err.getMessage(), err);
		} finally {
			if (null != session && session.isOpen()) {
				session.close();
			}
		}
		return Collections.emptyList();
	}

	@Transactional
	public String getCountOfObjects(Class classRef) {
		Session session = null;
		try {
			getLogger().debug("You are about to invoke getObjects method with " + classRef);
			session = sessionFactory.openSession();
			Criteria c = session.createCriteria(classRef);
			c.setProjection(Projections.rowCount());
			return c.uniqueResult().toString();
		} catch (Exception err) {
			getLogger().error(err.getMessage(), err);
		} finally {
			if (null != session && session.isOpen()) {
				session.close();
			}
		}
		return "0";
	}

	@Transactional
	public List<Object> getObjectsWithPagination(Class classRef, String criteria, int startIndex, int maxRecordsPerPage,
			boolean isOrderRequired) {
		Session session = null;
		try {
			getLogger().debug("You are about to invoke getObjects method with " + classRef + " Criteria :" + criteria);
			session = sessionFactory.openSession();
			Criteria c = session.createCriteria(classRef);
			if (isOrderRequired) {
				c.addOrder(Order.desc(criteria));
			}
			getLogger().info("Printing the start index : " + startIndex);
			c.setFirstResult((startIndex - 1) * maxRecordsPerPage);			
			c.setMaxResults(maxRecordsPerPage);
			return c.list();
		} catch (Exception err) {
			getLogger().error(err.getMessage(), err);
		} finally {
			if (null != session && session.isOpen()) {
				session.close();
			}
		}
		return Collections.emptyList();
	}

	public DidYouKnow getRandomDidYouKnow() {
		Session session = null;
		try {
			getLogger().debug("Trying to fetch the random did you know content..");
			String sql = "from DidYouKnow";
			session = sessionFactory.openSession();
			Query query = session.createQuery(sql);
			List<Object> list = query.list();
			Iterator listIterate = list.iterator();
			DidYouKnow didYouKnow = null;
			if (null != list && !list.isEmpty()) {
				getLogger().info("You have got " + list.size() + " entries ");
				if (previousDidYouKnow == null) {
					int index = 0;
					didYouKnow = (DidYouKnow) list.get(index);
				} else {
					while (listIterate.hasNext()) {
						DidYouKnow obj = (DidYouKnow) listIterate.next();
						if (obj.getId() == previousDidYouKnow.getId()) {
							if (listIterate.hasNext()) {
								didYouKnow = (DidYouKnow) listIterate.next();
								break;
							} else {
								int index = 0;
								didYouKnow = (DidYouKnow) list.get(index);
							}
						}
					}
				}
				previousDidYouKnow = didYouKnow;
				return didYouKnow;
			}
		} catch (Exception err) {
			getLogger().error(err.getMessage(), err);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return null;
	}

	@Transactional
	public boolean saveOrUpdateEntity(Object obj) {
		Session session = null;
		getLogger().info("Saving : " + obj.getClass().toString());
		try {
			session = sessionFactory.openSession();
			Transaction transObj = session.beginTransaction();
			session.saveOrUpdate(obj);
			transObj.commit();
			session.close();
			getLogger().debug("Save entity operation completed successfully");
			return true;
		} catch (Exception err) {
			getLogger().info(err.getMessage(), err);
			return false;
		} finally {
			if (null != session && session.isOpen()) {
				session.close();
			}
		}
	}

	@Transactional
	public boolean updateEntity(Object obj) {
		Session session = null;
		getLogger().info("Saving : " + obj.getClass().toString());
		try {
			session = sessionFactory.openSession();
			Transaction transObj = session.beginTransaction();
			session.update(obj);
			transObj.commit();
			session.close();
			getLogger().debug("Update entity operation completed successfully");
			return true;
		} catch (Exception err) {
			getLogger().info(err.getMessage(), err);
			return false;
		} finally {
			if (null != session && session.isOpen()) {
				session.close();
			}
		}
	}

	public List<Object> queryUsingNativeSQL(String sql,Class T) {
		getLogger().info("Trying to execute sql " + sql);
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(new AliasToBeanResultTransformer(T));
			List<Object> list = query.list();
			return list;
		} catch (Exception err) {
			getLogger().error(err.getMessage(), err);
		} finally {
			if (null != session && session.isOpen()) {
				session.close();
				session = null;
			}
		}
		return Collections.emptyList();
	}
	
	public List<Object> queryUsingNativeSQL(String sql) {
		getLogger().info("Trying to execute sql " + sql);
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery(sql);			
			List<Object> list = query.list();
			return list;
		} catch (Exception err) {
			getLogger().error(err.getMessage(), err);
		} finally {
			if (null != session && session.isOpen()) {
				session.close();
				session = null;
			}
		}
		return Collections.emptyList();
	}
	
	public int executeUsingNativeSQL(String sql) {
		getLogger().info("Trying to execute sql " + sql);
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery(sql);
			int result = query.executeUpdate();
			return result;
		} catch (Exception err) {
			getLogger().error(err.getMessage(), err);
		} finally {
			if (null != session && session.isOpen()) {
				session.close();
				session = null;
			}
		}
		return 0;
	}

	public boolean unSubscribeMember(String key) {
		boolean flag = false;
		getLogger().info("Trying to unsubscribe the member");		
		String parsedValue = publicUtility.DecryptText(key);
		StringTokenizer tokenizer = new StringTokenizer(parsedValue, ",");
		String email = "";
		String date = "";
		if (tokenizer.hasMoreTokens()) {
			email = tokenizer.nextToken();
		}
		if (tokenizer.hasMoreTokens()) {
			date = tokenizer.nextToken();
		}
		getLogger().info("Printing the email:" + email);
		getLogger().info("Printing the date:" + date);
		Subscription subscriptionObj = (Subscription) getObjectsById(Subscription.class, "emailId", email);
		if (subscriptionObj != null) {
			subscriptionObj.setUnsubscribe(true);
			saveOrUpdateEntity(subscriptionObj);
			flag = true;
		}
		return flag;
	}

	public void afterPropertiesSet() throws Exception {
		// getLogger().info("Testing the DB connection ");
		// getLogger().info("Printing the size " +
		// getObjects(VisitCounter.class).size());
	}
}
