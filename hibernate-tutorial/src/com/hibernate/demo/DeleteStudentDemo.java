package com.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		
		//Create Session Factory
		
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();
		
		
		try {
			
			int studentId = 1;
			
			//Create Session
			Session session = factory.getCurrentSession();
			
			//start a transaction
			session.beginTransaction();
			
			
			/*
			//retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: " + studentId);
			Student myStudent = session.get(Student.class, studentId);
			
			
			//Deleting the student details
		    System.out.println("Deleting the student...");
		    session.delete(myStudent);
		    */
			
			
		    //retrieving the student details
		    System.out.println("Retrieving the details after delete operation");
		    List<Student> theStudent = session.createQuery("from Student").list();
		    System.out.println(theStudent);
		    
		    //Deleting the student with id = 2
		    session.createQuery("delete Student where id=2").executeUpdate();
		    System.out.println("Retrieving the details after delete operation 2");
		    theStudent = session.createQuery("from Student").list();
		    System.out.println(theStudent);
		    
		    
			//commit transaction
			session.getTransaction().commit();

			System.out.println("Done");
									
			
		}
		finally {
			factory.close();
		}

	}

}
