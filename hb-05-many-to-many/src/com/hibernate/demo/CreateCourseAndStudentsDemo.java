package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Review;
import com.hibernate.demo.entity.Student;
 
public class CreateCourseAndStudentsDemo {
 
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
 
            //create a course
            Course tempCourse = new Course("Java Programming");
            
            System.out.println("\nSaving the course...");
            session.save(tempCourse);
            System.out.println("\nSaved the course: " + tempCourse);
            
            
            //create the students
            System.out.println("\nCreating the students");
            Student tempStudent1 = new Student("sri", "devi", "sri@gmail.com");
            Student tempStudent2 = new Student("sita", "gita", "sita@gmail.com");
            
            
            //add the students to the course
            tempCourse.addStudent(tempStudent1);
            tempCourse.addStudent(tempStudent2);
            
            
            //save the students
            System.out.println("\nSaving the students...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            System.out.println("\nSaved the students: " + tempCourse.getStudents());
            
            
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