/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viremp.employeemanagement.db;


import static com.viremp.employeemanagement.db.Fines.COL_EMPLOYEE_ID;
import static com.viremp.employeemanagement.db.Fines.TABLE;
import com.viremp.employeemanagement.models.Course;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public static List<Course> getAllInteriorCourses(int employeeId) throws SQLException{
        ResultSet rs = GenericDB.query(TABLE, null, COL_EMPLOYEE_ID+" = ?", new Object[]{employeeId}, null);
        List<Course> list = new ArrayList<>();
        while(rs.next()){
            Course course = new Course();
            course.setEmployeeId(employeeId);
            course.setCourseId(rs.getInt(COL_COURSE_ID));
            course.setCourseImage(rs.getString(COL_COURSE_IMAGE));
            course.setCourseName(rs.getString(COL_COURSE_NAME));
            list.add(course);
        }
        return list;
    }
    public static boolean deleteAllIntCourses(int employeeId) throws SQLException{
        String sql = "DELETE FROM "+TABLE+" where "+COL_EMPLOYEE_ID+" = " + employeeId;
        return GenericDB.executeUpdate(sql);
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
