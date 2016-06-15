package com.colloboration.chatapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name="user_authorize")
public class admin {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int adid;
	private String rolename;
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
	private ChatUser id_fk;
	public int getAdid() {
		return adid;
	}
	public void setAdid(int adid) {
		this.adid = adid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public ChatUser getId_fk() {
		return id_fk;
	}
	public void setId_fk(ChatUser id_fk) {
		this.id_fk = id_fk;
	}
	
	
}
