package com.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		//Create Session Factory
		
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();
		
		//Create Session
		Session session = factory.getCurrentSession();
		
		try {
			
	
			//start a transaction
			session.beginTransaction();
			
			//query students table
			List<Student> theStudent = session.createQuery("from Student").list();
			
			// display the students
			/*for(Student tempStudent : theStudent) {
				System.out.println(tempStudent);
			}*/
			
			//display the students changing to method
			for (Student tempStudent : theStudent) {
				System.out.println(tempStudent);
			}
			
			//query students with last name 'devi'
			
			theStudent = session.createQuery("from Student s where " + "s.lastName='devi'").list();
			
			System.out.println("\nDisplaying the students with last name 'devi'");
			
			//displaying the results
			for (Student tempStudent : theStudent) {
				System.out.println(tempStudent);
			}
			
			
			
			//query for students with last name 'devi' or 'david'
			
			theStudent = session.createQuery("from Student s where s.lastName='devi' " + "OR s.lastName='david'").list();
			System.out.println("\nDisplaying the students with last name devi or david");
			for (Student tempStudent : theStudent) {
				System.out.println(tempStudent);
			}
		
			
			
			//query for students with email id ends with @gmail.com
			
			theStudent = session.createQuery("from Student s where " + "s.email LIKE '%@gmail.com'").list();
			System.out.println("\nDisplaying the students with email ends with @gmail.com");
			for (Student tempStudent : theStudent) {
				System.out.println(tempStudent);
			}
				
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done");
			
		}
		finally {
			factory.close();
		}

	}

	private static void displayStudents(List<Student> theStudent) {
		for(Student tempStudent : theStudent)
		{
			System.out.println(theStudent);
		}
	}

}
