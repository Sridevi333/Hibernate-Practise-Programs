package com.hibernate.demo;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Student;
 
public class GetInstructorDetailDemo {
 
    public static void main(String[] args) {
        
        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
        		.addAnnotatedClass(Instructor.class)
        		.addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();
 
        // create a session
        Session session = factory.getCurrentSession();
 
        try {
        	
            // start transaction
            session.beginTransaction();
 
           int theId = 29;
           
           InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
          
           System.out.println("tempInstructorDetail: "+ tempInstructorDetail);
           
           System.out.println("Associated instructor: " + tempInstructorDetail.getInstructor());
           
            // commit transaction
            session.getTransaction().commit();
 
            System.out.println("Success!");
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
        	
        	//handling connection leak issue
        	session.close();
        	
            factory.close();
        }
    }
    
}