package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
 
public class GetInstructorCoursesDemo {
 
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
        	
            // start transaction
            session.beginTransaction();
 
            //get the instructor from the db
            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class, theId);
            System.out.println("Instructor: " + tempInstructor);
            
            //get the courses for instructor
            System.out.println("Courses: "+tempInstructor.getCourses());
            
            
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