package com.omkarsh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.omkarsh.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
	
		//create session factory
		SessionFactory factory= new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		try{
			//create a student object 
			System.out.println("Creating new Student Object...");
			Student tempStudent1 = new Student("Sandeep","Batule","sandeep@omkarsh.com");
			
			//start a transaction
			session.beginTransaction();
			
			//save the student object
			System.out.println("Saving the Student..");
			System.out.println(tempStudent1);
			session.save(tempStudent1);
			
			//commit transaction
			session.getTransaction().commit();
			
			
			//find out student's id: primary key
			System.out.println("Saved student Generated id: "+tempStudent1.getId());
			
			//now get a	new session
			session=factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve the student based on the id: primary key
			System.out.println("\nGetting student with id: "+tempStudent1.getId());
			
			Student myStudent = session.get(Student.class, tempStudent1.getId());
			
			System.out.println("Get Complete: "+myStudent);
			
			//commit the transaction
			session.getTransaction().commit();

			System.out.println("Done");
		}
		finally{
			factory.close();
		}

	}

}
