package com.colloboration.chatapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.colloboration.chatapp.model.ChatUser;


@Repository

public class ChatuserDao {

	@Autowired(required=true)
	private SessionFactory sf;

public void addProd(ChatUser usr) {
	System.out.println("in chat user - dao - add user "+usr.getUserId());
		sf.getCurrentSession().save(usr);

	}

public List<ChatUser> getAllUsers() {
	System.out.println("in chat user - dao - get all user ");
	
		List<ChatUser> l= sf.getCurrentSession().createQuery("from ChatUser").list();
		//t.commit();
		System.out.println("---- in all prod"+l.get(0).getMailId());
		return l;
	}
	public List<ChatUser> verifyOtp(String g,String d)
	{
		Criteria c= sf.openSession().createCriteria(ChatUser.class);
		c.add(Restrictions.eq("qrotp",d));
		//c.add(Restrictions.eq("mailId",g));
		List<ChatUser> l=c.list();
		l.get(0).setEnabled(true);
		Session s=sf.openSession();
		s.saveOrUpdate(l.get(0));
		s.flush();
		System.out.println(l);
		return l;
	}
}
