package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {
    private final JdbcTemplate jdbcTemplate;
    //define field for entity manager
    private EntityManager entityManager;

    //inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager, JdbcTemplate jdbcTemplate) {
        this.entityManager = entityManager;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        //retrieve the instructor
        Instructor theInstructor = entityManager.find(Instructor.class, theId);

        //get the courses
        List<Course> courses = theInstructor.getCourses();

        //break association of all courses for the instructor
        for (Course tempCourse : courses) {
            tempCourse.setInstructor(null);
        }

        //delete the instructor
        entityManager.remove(theInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        InstructorDetail theInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        //remove the bidirectional reference when you delete
        theInstructorDetail.getInstructor().setInstructorDetail(null);

        entityManager.remove(theInstructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        //create query
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);

        //set parameter value
        query.setParameter("data", theId);

        //execute the query
        List<Course> courses = query.getResultList();

        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        //create query
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i " + "JOIN FETCH i.courses " + "JOIN FETCH i.instructorDetail " + "where i.id = :data", Instructor.class);
        query.setParameter("data", theId);

        //execute query
        Instructor instructor = query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor theInstructor) {
        entityManager.merge(theInstructor); //updates existing entity
    }

    @Override
    @Transactional
    public void update(Course theCourse) {
        entityManager.merge(theCourse);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        Course tempCourse = entityManager.find(Course.class, theId);
        entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse); //saves reviews too because of cascade type all
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        //create query
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c " + "JOIN FETCH c.reviews " + "where c.id = :data", Course.class);
        query.setParameter("data", theId);

        //execute query
        Course course = query.getSingleResult();
        return course;
    }
}
