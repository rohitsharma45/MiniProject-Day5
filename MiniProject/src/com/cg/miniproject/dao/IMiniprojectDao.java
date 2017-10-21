package com.cg.miniproject.dao;

import java.sql.Date;
import java.util.ArrayList;

import com.cg.miniproject.bean.MacBean;
import com.cg.miniproject.bean.MiniprojectBean;

public interface IMiniprojectDao {

	int insertData(MiniprojectBean bean);

	String getProgramId();


	ArrayList<MacBean> allLogin(MacBean mac);

	ArrayList<MacBean> adminLogin(MacBean mac);

	String getId(String id);

	ArrayList<String> retrieveDetails();

	ArrayList<MiniprojectBean> getApplicantList(String programName);

	int updateStatus(MiniprojectBean bean);

	ArrayList<MiniprojectBean> retrieveStatus(String pgmName);

	int updateCnfStatus(MiniprojectBean bean);

	ArrayList<MiniprojectBean> getCnfApplicantList(String programName);

	ArrayList<String> retrievePgms(Date stDate, Date edDate);

	ArrayList<MiniprojectBean> getApplicantStatus(int applicationId);
}
