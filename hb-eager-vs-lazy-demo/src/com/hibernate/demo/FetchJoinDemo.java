package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
 
public class FetchJoinDemo {
 
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
 
            //Hibernate query with HQL
            
            //get the instructor from the db
            int theId = 1;
           
            Query<Instructor> query = session.createQuery("select i from Instructor i "
            											+ "JOIN FETCH i.courses "
            											+ "where i.id=:theInstructorId",
            											Instructor.class);
            
            //set parameter on query
            query.setParameter("theInstructorId", theId);
            
            //execute the query and get the instructor
            Instructor tempInstructor = query.getSingleResult();
            
            System.out.println("Instructor: " + tempInstructor);
            
            
            // commit transaction
            session.getTransaction().commit();
 
            
            //Closing the session
            session.close();
            System.out.println("The session is closed now...");
            
            //get the courses for instructor
            System.out.println("Courses: "+tempInstructor.getCourses());
            
            System.out.println("Success!");
            
        } catch (Exception exc) {
            exc.printStackTrace();
            
        } finally {
        	
        	session.close();
        	
            factory.close();
        }
    }
    
}