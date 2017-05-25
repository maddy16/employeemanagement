/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viremp.employeemanagement.db;


import com.viremp.employeemanagement.models.Course;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Maddy
 */
public class InteriorCourses {
    public static final String TABLE = "int_courses";
    public static final String COL_COURSE_ID = "course_id";
    public static final String COL_COURSE_NAME = "course_name";
    public static final String COL_COURSE_IMAGE = "course_image";
    public static final String COL_EMPLOYEE_ID = "employee_id";
    
    public static boolean insertNewInteriorCourse(Course course) throws SQLException{
        return GenericDB.insert(TABLE, getValuesMap(course));
    }
    private static Map<String,Object> getValuesMap(Course course){
        Map<String,Object> values = new HashMap<>();
        values.put(COL_COURSE_ID, course.getCourseId());
        values.put(COL_COURSE_NAME, course.getCourseName());
        values.put(COL_COURSE_IMAGE, course.getCourseImage());
        values.put(COL_EMPLOYEE_ID, course.getEmployeeId());
        return values;
    }
}
