package com.crud.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;

import com.crud.beans.Details;

@Service
public class DetailsDao {

	@Autowired
	private HibernateTemplate template;

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

}
