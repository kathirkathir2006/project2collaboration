package com.colloboration.chatapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colloboration.chatapp.dao.ChatuserDao;
import com.colloboration.chatapp.model.ChatUser;

@Service
public class ChatuserService {
 
	@Autowired	
    private ChatuserDao chd;
	
	@Transactional
	public void addProd(ChatUser usr) {
		System.out.println("in chat user - service - add user");
		chd.addProd(usr);
	}
	@Transactional
	public List<ChatUser> getAllUsers() {
		System.out.println("in chat user - service - get all user");
		List<ChatUser> l=chd.getAllUsers();
		return l;
	}
	public boolean verifyOtp(String m,String o)
	{
		List<ChatUser> l=chd.verifyOtp(o, o);
		if(l.isEmpty())
		return false	;
		else
		return true;
	}
	
}
