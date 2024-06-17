package com.app.dao;

import static com.app.utils.HibernateUtils.getFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.entities.Category;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public String addNewCategory(Category category) {
		String mesg="adding category failed";
		// 1. get session from SF (getCurrentSession)
		Session session = getFactory().getCurrentSession();
		// 2. Begin a Tx
		Transaction tx = session.beginTransaction();
		try {
			session.persist(category);
			tx.commit();
			mesg="Added new category with ID="+category.getId();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the exc to the caller
			throw e;
		}
		return mesg;
	}
	
	@Override
	public Category getCategory(String name)
	{
		Category category=null;
	
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		String jpql="select u from Category u where name=:nm";
		try {
			category=session.createQuery(jpql,Category.class)
			.setParameter("nm", name).getSingleResult();
			tx.commit();
			
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the exc to the caller
			throw e;
		}
		return category;
	}

}
