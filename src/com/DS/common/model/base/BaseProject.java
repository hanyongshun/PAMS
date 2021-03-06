package com.DS.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseProject<M extends BaseProject<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public void setProjectName(java.lang.String projectName) {
		set("projectName", projectName);
	}
	
	public java.lang.String getProjectName() {
		return getStr("projectName");
	}

	public void setStartDate(java.util.Date startDate) {
		set("startDate", startDate);
	}
	
	public java.util.Date getStartDate() {
		return get("startDate");
	}

	public void setFinshDate(java.util.Date finshDate) {
		set("finshDate", finshDate);
	}
	
	public java.util.Date getFinshDate() {
		return get("finshDate");
	}

	public void setUserId(java.lang.Integer userId) {
		set("userId", userId);
	}
	
	public java.lang.Integer getUserId() {
		return getInt("userId");
	}

	public void setUserName(java.lang.String userName) {
		set("userName", userName);
	}
	
	public java.lang.String getUserName() {
		return getStr("userName");
	}

	public void setAddTime(java.util.Date addTime) {
		set("addTime", addTime);
	}
	
	public java.util.Date getAddTime() {
		return get("addTime");
	}

}
