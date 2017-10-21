package com.cg.miniproject.service;

import java.sql.Date;
import java.util.ArrayList;

import com.cg.miniproject.bean.MacBean;
import com.cg.miniproject.bean.MiniprojectBean;

public interface IMiniprojectService {

	int insertData(MiniprojectBean bean);

	String getProgramId();

	boolean checkLogin(MacBean mac);

	boolean checkAdminLogin(MacBean mac);

	String getId(String string);

	ArrayList<String> retrieveDetails();

	ArrayList<MiniprojectBean> getApplicantList(String programName);

	int updateStatus(MiniprojectBean bean);

	ArrayList<MiniprojectBean> retrieveStatus(String pgmName);

	int updateCnfStatus(MiniprojectBean bean);

	ArrayList<MiniprojectBean> getCnfApplicantList(String programName);

	ArrayList<String> retrievePgms(Date stDate, Date edDate);

	ArrayList<MiniprojectBean> getApplicantStatus(int applicationId);

	//boolean validateUser(ArrayList<MacBean> list);

}
