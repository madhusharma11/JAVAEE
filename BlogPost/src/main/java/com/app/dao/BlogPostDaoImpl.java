package com.app.dao;import static com.app.utils.HibernateUtils.getFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.app.entities.Category;
import com.app.entities.BlogPost;


public class BlogPostDaoImpl implements BlogPostDao{

	@Override
	public String addNewBlogPost(BlogPost post) {
		Session session=getFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		String msg="Post  creation faild!!!!";
		try {
			session.persist(post);	
		tx.commit();	
		msg="Post is created successfully!!!!";
		}catch(RuntimeException e) {
			if(tx!=null)
			{
				tx.rollback();
				throw e;
			}
		}
		return msg;
	}

}
