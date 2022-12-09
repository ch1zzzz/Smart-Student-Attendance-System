package com.example.ssas_project.database;

import com.example.ssas_project.entity.Course;
import com.example.ssas_project.entity.CourseOffering;
import com.example.ssas_project.entity.Student;

import java.util.Date;
import java.util.List;
import java.util.Map;

/*
    Data Access Object
    1.student_table: store information about student(_id, name, email, status, payment_info)
    2.course_table: store information about course(_id, name, status, numberOfferings)
    3.courseOffering_table: store information about courseOfferings,
    including which course it belongs to(_id, course_id, studentsNum, classroom)
    4.schedule_table: store all the times of each courseOfferings(courseOffering_id, class_time)
    5.enroll_table: store enroll relation between students and courseOfferings(student_id, courseOffering_id)
    6.attendance_table: store record of attendance(student_id, courseOffering_id, class_time, attendance)
 */
public interface DAO {

    //drop all the tables and recreate them
    void resetDatabase();
    //create table if it's not existed
    void updateDatabase();

    //------------------student_table------------------

    //get all students in the table
    List<Student> getStudent();
    //get student by student id
    Student getStudent(int id);
    //search students by name
    List<Student> searchStudentName(String name);
    //insert a new student
    void insertStudent(Student student);
    //delete a student by student id
    void deleteStudent(int id);
    //update an existed student
    void updateStudent(Student student);

    //------------------course_table------------------

    //get all courses in the table
    List<Course> getCourse();
    //get course by course id
    Course getCourse(int id);
    //insert a new course
    void insertCourse(Course course);
    //delete a course by course id
    void deleteCourse(int id);
    //update an existed course
    void updateCourse(Course course);

    //------------------courseOffering_table------------------

    //get all courseOfferings in the table
    List<CourseOffering> getCourseOffering();
    //get courseOffering by courseOffering id
    CourseOffering getCourseOffering(int id);
    //get all courseOfferings of the specific course
    List<CourseOffering> getCourseOfferingByCourse(int course_id);
    //insert a new course courseOffering
    void insertCourseOffering(CourseOffering courseOffering);
    //delete a courseOffering by courseOffering id
    void deleteCourseOffering(int id);
    //update an existed courseOffering
    void updateCourseOffering(CourseOffering courseOffering);

    //------------------schedule_table------------------

    //get all class times for a courseOffering
    List<Date> getTime(int courseOfferingId);
    //get all courseOffering at date
    List<CourseOffering> getCourseOfferingByTime(Date date);
    //insert a new time for a courseOffering
    void insertTime(int courseOfferingId, Date date);
    //get all courseOffering on the day of date
    List<CourseOffering> getCourseOfferingByDay(Date date);
    //update an old time to a new time for a courseOffering
    void updateTime(int courseOfferingId, Date oldDate, Date newDate);
    //delete an existed time for a courseOffering
    void deleteTime(int courseOfferingId, Date date);

    //------------------enroll_table------------------

    //get all students who take the courseOffering
    List<Integer> getEnrollStudents(int courseOffering_id);
    //get all courseOfferings the student take
    List<Integer> getEnrollCourses(int student_id);
    //enroll a student to a courseOffering
    void insertEnroll(int courseOffering_id, int student_id);
    //delete a student from a courseOffering
    void deleteEnroll(int courseOffering_id, int student_id);

    //------------------attendance_table------------------

    //get the student's attendance at the time in the courseOffering
    boolean getAttendance(int student_id, int courseOffering_id, Date date);
    //get the student's attendance for all time in the courseOffering
    Map<Date, Boolean> getAttendance(int student_id, int courseOffering_id);
    //insert the student's attendance at the time in the courseOffering
    void insertAttendance(int student_id, int courseOffering_id, Date date, boolean attendance);
    //update the existed record of the student's attendance at the time in the courseOffering
    void updateAttendance(int student_id, int courseOffering_id, Date date, boolean attendance);


    //------------------login_table------------------

    //insert user and information
    void insertUser(String username, String password, String first_name, String last_name, String email_addr);
    //update user's password
    void updateUser(String username, String password);
    //check if username exists(if the login database is empty, it throws a runtimeException)
    Boolean checkUsername(String username);
    //check if username and password exsits
    Boolean checkUserPass(String username, String password);
    //get user's information
    String[] getUserInfo(String username);
}
