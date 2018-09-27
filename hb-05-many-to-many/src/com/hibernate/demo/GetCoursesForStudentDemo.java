package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Review;
import com.hibernate.demo.entity.Student;
 
public class GetCoursesForStudentDemo {
 
    public static void main(String[] args) {
        
        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
        		.addAnnotatedClass(Instructor.class)
        		.addAnnotatedClass(InstructorDetail.class)
        		.addAnnotatedClass(Course.class)
        		.addAnnotatedClass(Review.class)
        		.addAnnotatedClass(Student.class)
                .buildSessionFactory();
 
        // create a session
        Session session = factory.getCurrentSession();
 
        try {
        	
            // start transaction
            session.beginTransaction();
 
            //get the student from the database
            int studentId=1;
            Student tempStudent = session.get(Student.class, studentId);
            System.out.println("\nLoaded Student: "+ tempStudent);
            System.out.println("Course for the Student: "+ tempStudent.getCourses());
           
             
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