package com.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

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
			
			//retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: " + studentId);
			Student myStudent = session.get(Student.class, studentId);
			
			
			//Updating the student details
		    System.out.println("Updating the student...");
		    myStudent.setFirstName("Scooby");
		    
		    //retrieving the updated details
		    System.out.println("Retrieving the updated details");
		    Student theStudent = session.get(Student.class, studentId);
		    System.out.println(theStudent);
		    
		
			//commit transaction
			session.getTransaction().commit();

			System.out.println("Done");
				
			
			//New Code
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//update the email of the student with Id 1
			System.out.println("Updating the email of the student with Id 1 ");
			session.createQuery("update Student set email='john@gmail.com' " + "where id=1").executeUpdate();
			myStudent = session.get(Student.class, studentId);
			System.out.println(myStudent);
			
			//commit transaction
			session.getTransaction().commit();

			System.out.println("Done");
					
			
			
		}
		finally {
			factory.close();
		}

	}

}
