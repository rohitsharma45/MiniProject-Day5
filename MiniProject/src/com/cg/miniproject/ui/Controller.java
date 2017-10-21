package com.cg.miniproject.ui;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Scanner;



import com.cg.miniproject.bean.MacBean;
import com.cg.miniproject.bean.MiniprojectBean;
import com.cg.miniproject.service.IMiniprojectService;
import com.cg.miniproject.service.MiniprojectServiceImpl;

public class Controller {

	static Scanner sc=new Scanner(System.in);
	static MiniprojectBean bean=null;
	static MacBean mac=null;
	
	
	
	public static void main(String[] args) {
		Controller  c=new Controller();
		bean=new MiniprojectBean();
		mac=new MacBean();
		ArrayList<MacBean> list=new ArrayList<MacBean>();
		ArrayList<String> list1=new ArrayList<String>();
		int res=0;
		IMiniprojectService service=new MiniprojectServiceImpl();
		
		
	
		System.out.println("Enter your role");
		System.out.println("\n 1.Applicant"
						  +"\n 2.MAC"
						  +"\n 3.Admin"
						  +"\n 4.Exit");
		int role=sc.nextInt();
		switch(role){
		
		case 1:
			System.out.println("Enter your desired operation");
			System.out.println("\n 1.Application Fillup Form"
					+ "\n 2.Application Status check"
					+ "\n 3.Exit");
			int choose=sc.nextInt();
			switch(choose){
			case 1:
				System.out.println("List of Universities ");
			System.out.println("\n 1.Mumbai"
						  	  +"\n 2.Pune"
						  	  +"\n 3.Delhi"
						  	  +"\n 4.Hyderabad"
						  	  +"\n 5.Exit");
			System.out.println("Enter your desired university:");
			int university=sc.nextInt();
			
			
			switch(university){
			
			case 1:
				list1=service.retrieveDetails();
				System.out.println("Program Courses available are: "+"\n");
				for(String l:list1){
					System.out.println(l+"\t");
				}
				
				System.out.println("Enter your desired program course");
				System.out.println("\n 1.EXTC(E100)"
						+ "\n 2.IT(I100)"
						+ "\n 3.Exit");
				
				int course=sc.nextInt();
				
				switch(course){
				
				case 1:
					String programId=null;
					System.out.println("Application form for EXTC course");
					bean=c.fillForm();
					programId=service.getId("EXTC");
					bean.setProgramId(programId);
					bean.setStatus("Applied");
					bean.setInterviewDate(null);
					res=service.insertData(bean);
					System.out.println("Your application id is:"+res);
					break;
					
				case 2:
					System.out.println("Application form for IT course");
					bean=c.fillForm();
					programId=service.getId("IT");
					bean.setProgramId(programId);
					bean.setStatus("Applied");
					bean.setInterviewDate(null);
					res=service.insertData(bean);
					System.out.println("Your application id is:"+res);
					break;
					
				case 3:
					System.exit(0);
					break;
				}
				break;
				
			}
		break;
			case 2:
				ArrayList<MiniprojectBean> statusCheck=new ArrayList<MiniprojectBean>();
				System.out.println("Enter application id");
				int applicationId=sc.nextInt();
				
				statusCheck=service.getApplicantStatus(applicationId);
				for(MiniprojectBean out:statusCheck){
					
					System.out.print("\n Application Id:"+out.getApplicationId());
					System.out.print("\n Full Name:"+out.getFullName());
					System.out.print("\n Status:"+out.getStatus());
				}
				break;
			case 3:
				System.exit(0);
				break;
			}
			break;
		
		case 2:
			boolean b=true;
			System.out.println("Enter the login id");
			String id=sc.next();
			mac.setLoginId(id);
			
			System.out.println("Enter the password");
			mac.setPassword(sc.next());
			
			b=service.checkLogin(mac);
			System.out.println(b);
			
			if(b==true){
				System.out.println("Authenticated");
				System.out.println("Choose your desired operation");
				
				System.out.println("\n 1.View Applicant for a specific program"
						+ "\n 2.Update status and Date of Interview"
						+ "\n 3.Update confirmation status");
				
				int macOp=sc.nextInt();
			
				switch(macOp){
				
				case 1:
					ArrayList<MiniprojectBean> list2=new ArrayList<MiniprojectBean>();
					System.out.println("Enter program name");
					String programName=sc.next();
					
					list2=service.getApplicantList(programName);
					for(MiniprojectBean out:list2){
						
						System.out.print(out.getApplicationId()+"\t");
						System.out.print(out.getFullName()+"\t");
						System.out.print(out.getStatus()+"\n");
					}
					break;
				case 2:
					System.out.println("Enter the Application Id:");
					int applicationId=sc.nextInt();
					bean.setApplicationId(applicationId);
					System.out.println("Enter the Status of applicant like ACCEPTED/REJECTED:");
					String status=sc.next();
					bean.setStatus(status);
					if(status.equalsIgnoreCase("accepted"))
					{
						System.out.println("Enter your date of interview in this dd-mm-yyyy format");
						String intdate=sc.next();
						DateTimeFormatter df=DateTimeFormatter.ofPattern("dd-MM-yyyy");
						
						try {
							LocalDate date=LocalDate.parse(intdate,df);
							Date interviewDate=Date.valueOf(date);
							
							
							bean.setInterviewDate(interviewDate);
						} catch (DateTimeParseException e) {
							
							e.printStackTrace();
						}
					
					res=service.updateStatus(bean);
					}

					else
					{
					bean.setInterviewDate(null);
					res=service.updateStatus(bean);
					}
					if(res==1)
					System.out.println("Updated  successfully");
					else
					System.out.println("Error");
					break;
				
				case 3:
					ArrayList<MiniprojectBean> list3=new ArrayList<MiniprojectBean>();
					System.out.println("Enter program name");
					String pgmName=sc.next();
					list3=service.retrieveStatus(pgmName);
					for(MiniprojectBean out:list3){
						
						System.out.print(out.getApplicationId()+"\t");
						System.out.print(out.getFullName()+"\t");
						System.out.print(out.getStatus()+"\n");
					}
					System.out.println("Enter the application Id");
					int appId=sc.nextInt();
					bean.setApplicationId(appId);
					System.out.println("Enter the Status of applicant like CONFIRMED/REJECTED:");
					String status1=sc.next();
					bean.setStatus(status1);
					res=service.updateCnfStatus(bean);
					if(res==1)
					System.out.println("Updated  successfully");
					else
					System.out.println("Error");
					break;
			
				}
			}
			
			
			else{
				System.out.println("Wrong credentials");}
			
			
			break;
			
		case 3:
			boolean d=true;
			System.out.println("Enter the login id");
			String login=sc.next();
			mac.setLoginId(login);
			System.out.println("Enter the password");
			mac.setPassword(sc.next());
			d=service.checkAdminLogin(mac);
			System.out.println(d);
			
			if(d==true){
				System.out.println("Authenticated");
				System.out.println("Choose your desired operation");
				
				System.out.println("\n 1.Update Programs Offered"
						+ "\n 2.Update schedule of programs offered"
						+ "\n 3.View Status of Applicant for a specific program"
						+ "\n 4.View program scheduled in a given time period");
				
				int adminOp=sc.nextInt();
			
				switch(adminOp){
				
				case 1:
					break;
				case 2:
					break;
				case 3:
					
					ArrayList<MiniprojectBean> list2=new ArrayList<MiniprojectBean>();
					System.out.println("Enter program name");
					String programName=sc.next();
					
					list2=service.getCnfApplicantList(programName);
					for(MiniprojectBean out:list2){
						
						System.out.print(out.getApplicationId()+"\t");
						System.out.print(out.getFullName()+"\t");
						System.out.print(out.getStatus()+"\n");
					}
					break;
				case 4:
					ArrayList<String> list4=new ArrayList<String>();	
					System.out.println(" Enter Start Duration in dd-mm-yyyy format");
					String startDate=sc.next();
					System.out.println(" Enter End Duration in dd-mm-yyyy format");
					String endDate=sc.next();
					DateTimeFormatter df=DateTimeFormatter.ofPattern("dd-MM-yyyy");
					try {
						LocalDate date=LocalDate.parse(startDate,df);
						Date stDate=Date.valueOf(date);
						LocalDate date2=LocalDate.parse(endDate,df);
						Date edDate=Date.valueOf(date2);
						
						list4=service.retrievePgms(stDate,edDate);
						for(String out1:list4){
							System.out.println(out1);
						}
					} catch (DateTimeParseException e) {
						
						e.printStackTrace();
					}

					
					
				}
				break;
			}
			else{
				System.out.println("Wrong credentials");
		}
			
			
			
			
			break;
			
		case 4:
			System.exit(0);
			break;
		}}
		
			
	
	
	public MiniprojectBean fillForm()
	{	
		bean=new MiniprojectBean();
		System.out.println("Enter your full name");
		String fullName=sc.next()+" ";
		bean.setFullName(fullName);
		sc.nextLine();
		System.out.println("Enter your date of birth in this dd-mm-yyyy format");
		String dob=sc.nextLine();
		DateTimeFormatter df=DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		try {
			LocalDate date=LocalDate.parse(dob,df);
			Date d=Date.valueOf(date);
			
			
			bean.setDateOfBirth(d);
		} catch (DateTimeParseException e) {
			
			e.printStackTrace();
		}
		
		System.out.println("Enter the highest qualification");
		String qualification=sc.next();
		bean.setQualification(qualification);
		System.out.println("Enter the marks obtained");
		int marksObt=sc.nextInt();
		bean.setMarks(marksObt);
		sc.nextLine();
		System.out.println("Enter the goals");
		String goals=sc.nextLine();
		bean.setGoals(goals);
		System.out.println("Enter your emailid");
		String emailId=sc.next();
		bean.setEmail(emailId);
		return bean;
	}
}
