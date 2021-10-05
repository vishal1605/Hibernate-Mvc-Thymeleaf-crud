package com.crud.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;

import com.crud.beans.Details;

@Service
public class DetailsDao {

	@Autowired
	private HibernateTemplate template;
	
	@Autowired
	private SessionFactory factory;

	@Transactional
	public int save(Details details) {
		template.save(details);
		return 1;

	}

	public List<Details> getAllUsers() {
		List<Details> list = template.loadAll(Details.class);
		return list;
	}

	public Details getId(int id) {
		Details details2 = template.get(Details.class, id);
		System.out.println(details2);
		return details2;
	}

	@Transactional
	public void updateDetails(Details details) {
		template.update(details);

	}
	
	@Transactional
	public void deleteDetails(int id) {
		Details details = template.get(Details.class, id);
		template.delete(details);
	}
	
	public List<Details> getAllUsersOne(int firstPage) {
		int maxResult=8;
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from all_details");
		query.setFirstResult((firstPage-1)*maxResult);
		query.setMaxResults(maxResult);
		List<Details> list = query.list();
		tx.commit();
		session.close();
		
		return list;
	}
	
	public long totalPages() {
		int pageSize = 4;
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(Details.class);
		c.setFirstResult(0);
		c.setMaxResults(pageSize);
		c.setProjection(Projections.rowCount());
		Long result = (Long)c.uniqueResult();
		tx.commit();
		session.close();
		
		return result;
	}
	
	

}
