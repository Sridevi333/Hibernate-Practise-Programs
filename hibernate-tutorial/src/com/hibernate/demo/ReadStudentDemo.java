package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		
		//Create Session Factory
		
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();
		
		//Create Session
		Session session = factory.getCurrentSession();
		
		try {
			
			//create a student object
			System.out.println("Creating the student object..");
			Student theStudent = new Student("sita", "gita", "sri@gmail.com");
			
			//start a transaction
			session.beginTransaction();
			
			
			//save the student object
			System.out.println("Saving the student object...");
			session.save(theStudent);
			
			//commit transaction
			session.getTransaction().commit();
			
			
			//find 	out the student id: Primary key
			System.out.println("Saved Student. Generated id: " + theStudent.getId());
			
			//now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: " + theStudent.getId());
			
			Student myStudent = session.get(Student.class, theStudent.getId());
			
			System.out.println("Retrieved Student details: " + myStudent);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
			
		}
		finally {
			factory.close();
		}

	}

}
