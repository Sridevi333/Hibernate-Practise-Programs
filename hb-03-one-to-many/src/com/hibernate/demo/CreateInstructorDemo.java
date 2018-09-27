package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
 
public class CreateInstructorDemo {
 
    public static void main(String[] args) {
        
        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
        		.addAnnotatedClass(Instructor.class)
        		.addAnnotatedClass(InstructorDetail.class)
        		.addAnnotatedClass(Course.class)
                .buildSessionFactory();
 
        // create a session
        Session session = factory.getCurrentSession();
 
        try {
        	
        	Instructor tempInstructor = new Instructor("Susan", "john", "susan@gmail.com");
        	
        	InstructorDetail tempInstructorDetail = new InstructorDetail(
        			"http://www.susan.com/youtube",
        			"Gaming");
        	
        	//associate the objects
        	tempInstructor.setInstructorDetail(tempInstructorDetail);        	
            
            // start transaction
            session.beginTransaction();
 
            //save the instructor
            //This will also saves the instructor details object because of cascadeType.ALL
            
            System.out.println("Saving instructor: "+ tempInstructor);
            session.save(tempInstructor);
            
 
            // commit transaction
            session.getTransaction().commit();
 
            System.out.println("Success!");
            
        } catch (Exception exc) {
            exc.printStackTrace();
            
        } finally {
        	
        	session.close();
        	
            factory.close();
        }
    }
    
}