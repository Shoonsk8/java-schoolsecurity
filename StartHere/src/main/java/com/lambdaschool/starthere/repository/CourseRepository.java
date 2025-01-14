package com.lambdaschool.starthere.repository;


import com.lambdaschool.starthere.models.Course;
import com.lambdaschool.starthere.view.CountStudentsInCourses;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Long>
{
 //   List<Course> findCoursesByCoursenameEquals(String name);

    @Modifying
    @Query(value = "DELETE FROM studcourses WHERE courseid = :courseid", nativeQuery = true)
    void deleteCourseFromStudcourses(long courseid);

    @Query(value = "SELECT s.courseid, coursename, count(studid) as countstudents FROM studcourses s INNER JOIN course c on s.courseid=c.courseid GROUP BY s.courseid, coursename", nativeQuery = true)
    List<CountStudentsInCourses> getCountStudentsInCourse();

    Course getCourseByCourseid(long courseid);

}

