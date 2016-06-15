package com.colloboration.chatapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class ChatUser {

	public String getQrotp() {
		return qrotp;
	}
	public void setQrotp(String qrotp) {
		this.qrotp = qrotp;
	}
	@Id
	@GeneratedValue
	private String userId;
	private boolean enabled;
	@NotBlank
	private String username;
	@NotBlank
	private String mobnum;
	@NotNull(message="Please select a password")
	@Length(min=4, max=10, message="Password should be between 5 - 10 charactes")
	@NotBlank
	private String pass;
	@Pattern(regexp = ".+@.+\\..+", message = "Wrong email!")
	@Size(max=50)
	private String mailId;
	
	@NotBlank
	@Length(min = 4, max = 10, message = "Password should be between 5 - 10 characters")
	private String cpass;
	private String friendId;
	private String fpath;
	private transient MultipartFile file1;
	private String qrotp;
	
	
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public void setFpath(String fpath) {
		this.fpath = fpath;
	}
	public String getFpath() {
		return fpath;
	}
	
	public MultipartFile getFile1() {
		return file1;
	}
	public void setFile1(MultipartFile file1) {
		this.file1 = file1;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(
			String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMobnum() {
		return mobnum;
	}
	public void setMobnum(String mobnum) {
		this.mobnum = mobnum;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getCpass() {
		return cpass;
	}
	public void setCpass(String cpass) {
		this.cpass = cpass;
	}
	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	
	
}
