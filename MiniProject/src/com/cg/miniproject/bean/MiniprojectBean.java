package com.cg.miniproject.bean;

import java.sql.Date;

public class MiniprojectBean {
	private int applicationId;
	
	private	String fullName;
	private Date dateOfBirth;
	private String qualification;
	private int marks;
	private String goals;
	private String email;
	private String programId;
	private String status;
	private Date interviewDate;
	

	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public MiniprojectBean(int applicationId, String fullName, String status) {
		super();
		this.applicationId = applicationId;
		this.fullName = fullName;
		this.status = status;
	}
	public MiniprojectBean() {
		// TODO Auto-generated constructor stub
	}

	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public java.sql.Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date date) {
		this.dateOfBirth = date;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public String getGoals() {
		return goals;
	}
	public void setGoals(String goals) {
		this.goals = goals;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public java.sql.Date getInterviewDate() {
		return interviewDate;
	}
	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}
	

	
	
}
