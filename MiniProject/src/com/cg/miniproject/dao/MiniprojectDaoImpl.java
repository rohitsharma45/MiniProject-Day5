package com.cg.miniproject.dao;

import java.io.IOException;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cg.miniproject.bean.MacBean;
import com.cg.miniproject.bean.MiniprojectBean;
import com.cg.miniproject.dbutil.DbUtil;

public class MiniprojectDaoImpl implements IMiniprojectDao {

	Connection conn=null;
	int result=0;
	public int insertData(MiniprojectBean bean) {
		try {
			conn=DbUtil.getConnection();
			
		String insertQuery="Insert into Application values(Application_id_seq.nextval,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps=conn.prepareStatement(insertQuery);
		ps.setString(1,bean.getFullName());
		ps.setDate(2,bean.getDateOfBirth());
		ps.setString(3, bean.getQualification());
		ps.setInt(4,bean.getMarks());
		ps.setString(5,bean.getGoals());
		ps.setString(6,bean.getEmail());
		ps.setString(7,bean.getProgramId());
		ps.setString(8,bean.getStatus());
		ps.setDate(9,bean.getInterviewDate());
	
		int res=ps.executeUpdate();
		System.out.println(res);
		String sql="select Application_id_seq.currval from dual";
		Statement pst=conn.createStatement();
		
		ResultSet rs=pst.executeQuery(sql);
		
		
		while(rs.next())
		{
			result=rs.getInt(1);
		}
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return result;
	}
	public String getProgramId() {
		
		return null;
	}
	@Override
	public ArrayList<MacBean> allLogin(MacBean mac) {
		String login=null;
		String password=null;
		ArrayList<MacBean> list = new ArrayList<MacBean>();
		try {
			conn=DbUtil.getConnection();
			
			
			String sql="select login_id,password from users where role IN ('MAC')";
			Statement pst=conn.createStatement();
			
			ResultSet rs=pst.executeQuery(sql);
			
			
			while(rs.next())
			{
				
				login=rs.getString(1);
				//System.out.println(login);
				password=rs.getString(2);
				//System.out.println(password);
				list.add(new MacBean(login,password));
				
			}
			
		
			
		} catch (IOException | SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return list;
	}
	@Override
	public ArrayList<MacBean> adminLogin(MacBean mac) {
		String login=null;
		String password=null;
		ArrayList<MacBean> list = new ArrayList<MacBean>();
		try {
			conn=DbUtil.getConnection();
			
			
			String sql="select login_id,password from users where role IN ('Admin')";
			Statement pst=conn.createStatement();
			
			ResultSet rs=pst.executeQuery(sql);
			
			
			while(rs.next())
			{
				
				login=rs.getString(1);
				
				password=rs.getString(2);
				
				list.add(new MacBean(login,password));
			
			}
			
			
			
		} catch (IOException | SQLException e) {
			
			e.printStackTrace();
		}
		
		return list;
	}
	@Override
	public String getId(String id) {
		String programId=null;
		ArrayList list = new ArrayList();
		try {
			conn=DbUtil.getConnection();
			
			
			String sql="select Scheduled_program_id from Programs_Scheduled where ProgramName IN (?)";
			
			//Statement pst=conn.createStatement();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			
			
			while(rs.next())
			{
				
				programId=rs.getString(1);
				
			}
		
	}
		catch(IOException | SQLException e) {
			
			e.printStackTrace();
		}

		return programId;
}
	@Override
	public ArrayList<String> retrieveDetails() {
		ArrayList<String> list = new ArrayList<String>();
		try {
			conn=DbUtil.getConnection();
			
			
			String sql="select * from Programs_Offered";
			Statement pst=conn.createStatement();
			
			ResultSet rs=pst.executeQuery(sql);
			
			
			while(rs.next())
			{
				String programName=rs.getString(1);
				String description=rs.getString(2);
				String eligibility=rs.getString(3);
				Long duration=rs.getLong(4);
				String courseDuration=(duration).toString();
				String degreeCertificate=rs.getString(5);
				list.add("Program Name: "+programName);
				list.add("Description of course: "+description);
				list.add("Eligibility Criteria: "+eligibility);
				list.add("Duration of course(in years): "+courseDuration);
				list.add("Degree certificate: "+degreeCertificate);
				list.add(" ");
			}
			System.out.println(" ");
		
	}
		catch(IOException | SQLException e) {
			
			e.printStackTrace();
		}

		return list;
	}
	@Override
	public ArrayList<MiniprojectBean> getApplicantList(String programName) {
		ArrayList<MiniprojectBean> list = new ArrayList<MiniprojectBean>();
		int id=0;
		String fullName=null;
		String status=null;
		
		try {
			conn=DbUtil.getConnection();
			
			String sql="select Application_id,full_name,status from Application where status  LIKE 'Applied' AND Scheduled_program_id=(select Scheduled_program_id from Programs_Scheduled where ProgramName IN (?))";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1,programName);
			
			ResultSet rs=ps.executeQuery();
			
		
			while(rs.next())
			{
				id=rs.getInt(1);
				fullName=rs.getString(2);
				status=rs.getString(3);
				
				list.add(new MiniprojectBean(id,fullName,status));
			}
		}
		catch(IOException | SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public int updateStatus(MiniprojectBean bean) {
		int  result=0;
		
	       try
	       {
			conn= DbUtil.getConnection();
			String insertQuery= "Update application set status=?,date_of_interview=? where application_id=? ";
			PreparedStatement ps= conn.prepareStatement(insertQuery);
			ps.setString(1,bean.getStatus());
			ps.setDate(2,bean.getInterviewDate());
			System.out.println(bean.getInterviewDate());
			ps.setInt(3,bean.getApplicationId());
			
			result=ps.executeUpdate();
			System.out.println(result);
		}
	       catch(IOException | SQLException e) {
				
				e.printStackTrace();
			}
return result;
		
	}
	@Override
	public ArrayList<MiniprojectBean> retrieveStatus(String programName) {
		ArrayList<MiniprojectBean> list = new ArrayList<MiniprojectBean>();
		int id=0;
		String fullName=null;
		String status=null;
		
		try {
			conn=DbUtil.getConnection();
			
			String sql="select Application_id,full_name,status from Application where status LIKE 'ACCEPTED' "; 
			
			Statement pst=conn.createStatement();
			
			ResultSet rs=pst.executeQuery(sql);
			
		
			while(rs.next())
			{
				id=rs.getInt(1);
			
				fullName=rs.getString(2);
				
				status=rs.getString(3);
				
				list.add(new MiniprojectBean(id,fullName,status));
			}
		
		}
		catch(IOException | SQLException e) {
			
			e.printStackTrace();
		}

		return list;
	}
	@Override
	public int updateCnfStatus(MiniprojectBean bean) {
		int  result=0;
		
	       try
	       {
			conn= DbUtil.getConnection();
			String insertQuery= "Update application set status=? where application_id=? ";
			PreparedStatement ps= conn.prepareStatement(insertQuery);
			ps.setString(1,bean.getStatus());
			ps.setInt(2,bean.getApplicationId());
			
			result=ps.executeUpdate();
			System.out.println(result);
		}
	       catch(IOException | SQLException e) {
				
				e.printStackTrace();
			}
	       return result;
		
		
	}
	@Override
	public ArrayList<MiniprojectBean> getCnfApplicantList(String programName) {
		ArrayList<MiniprojectBean> list = new ArrayList<MiniprojectBean>();
		int id=0;
		String fullName=null;
		String status=null;
		
		try {
			conn=DbUtil.getConnection();
			
			String sql="select Application_id,full_name,status from Application where status NOT LIKE 'Applied' AND Scheduled_program_id=(select Scheduled_program_id from Programs_Scheduled where ProgramName IN (?))";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1,programName);
			
			ResultSet rs=ps.executeQuery();
			
		
			while(rs.next())
			{
				id=rs.getInt(1);
				fullName=rs.getString(2);
				status=rs.getString(3);
				
				list.add(new MiniprojectBean(id,fullName,status));
			}
		}
		catch(IOException | SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public ArrayList<String> retrievePgms(Date stDate, Date edDate) {
		ArrayList<String> list = new ArrayList<String>();
		try {
			conn=DbUtil.getConnection();
			
			
			String sql="select * from Programs_Scheduled where start_date=? and end_date=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setDate(1,stDate);
			ps.setDate(2,edDate);
			ResultSet rs=ps.executeQuery();
			
			
			while(rs.next())
			{
				String programName=rs.getString(1);
				String description=rs.getString(2);
				list.add("Program Name: "+programName);
				list.add("Description of course: "+description);
				
			}
			System.out.println(" ");
		
	}
		catch(IOException | SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public ArrayList<MiniprojectBean> getApplicantStatus(int applicationId) {
		ArrayList<MiniprojectBean> list = new ArrayList<MiniprojectBean>();
		int id=0;
		String fullName=null;
		String status=null;
		
		try {
			conn=DbUtil.getConnection();
			
			String sql="select Application_id,full_name,status from Application where Application_id=?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1,applicationId);
			
			ResultSet rs=ps.executeQuery();
			
			
			while(rs.next())
			{
				id=rs.getInt(1);
				fullName=rs.getString(2);
				status=rs.getString(3);
				
				list.add(new MiniprojectBean(id,fullName,status));
			}
		}
		catch(IOException | SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	
}
