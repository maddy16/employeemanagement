/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viremp.employeemanagement.db;

import com.viremp.employeemanagement.models.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Maddy
 */
public class Employees {
    public static final String TABLE = "employees";
    public static final String COL_EMP_ID = "employee_id";
    public static final String COL_EMP_NAME = "emp_name";
    public static final String COL_SVC_NUM = "svc_num";
    public static final String COL_RANK_ID = "rank_id";
    public static final String COL_CPR_NUM = "cpr_num";
    public static final String COL_COUNTRY_ID = "country_id";
    public static final String COL_MOBILE = "mobile";
    public static final String COL_REL_CONTACT = "rel_contact";
    public static final String COL_REL_RELATION = "rel_relation";
    public static final String COL_BLOOD_GROUP = "blood_group";
    public static final String COL_DATE_OF_JOINING = "doj";
    public static final String COL_ADDRESS = "address";
    public static final String COL_MARITAL_STATUS = "marital_status";
    public static final String COL_DUTY = "duty";
    public static final String COL_LAST_RANK_DATE = "last_rank_date";
    public static final String COL_LEAVE_TYPE = "leave_type";
    public static final String COL_LEAVE_DAYS = "leave_days";
    public static final String COL_EMP_PIC = "emp_pic";
    
    public static int addNewEmployee(Employee employee) throws SQLException{
        return GenericDB.insertReturningId(TABLE, getValuesMap(employee));
    }
    
    public static boolean updateEmployee(Employee employee) throws SQLException{
        return GenericDB.update(TABLE, getValuesMap(employee), new String[]{COL_EMP_ID}, new Object[]{employee.getEmployeeId()});
    }
    public static boolean deleteEmployee(int employeeId) throws SQLException{
        return GenericDB.delete(TABLE, COL_EMP_ID, employeeId);
    }
    public static Employee getByServiceNum(String svcNum) throws SQLException{
        ResultSet rs = GenericDB.query(TABLE, null, COL_SVC_NUM+" = ?", new Object[]{svcNum}, null);
        Employee emp = null;
        if(rs.next())
            emp = getMappedModel(rs);
        return emp;
    }
    public static Employee getByCPR(String cpr) throws SQLException{
        ResultSet rs = GenericDB.query(TABLE, null, COL_CPR_NUM+" = ?", new Object[]{cpr}, null);
        Employee emp = null;
        if(rs.next())
            emp = getMappedModel(rs);
        return emp;
    }
    
    public static Map<String,Object> getValuesMap(Employee employee){
        Map<String,Object> values = new HashMap<>();
        values.put(COL_EMP_ID, employee.getEmployeeId());
        values.put(COL_EMP_NAME, employee.getEmpName());
        values.put(COL_SVC_NUM, employee.getSvcNum());
        values.put(COL_RANK_ID, employee.getRankId());
        values.put(COL_CPR_NUM, employee.getCprNum());
        values.put(COL_COUNTRY_ID, employee.getCountryId());
        values.put(COL_MOBILE, employee.getMobile());
        values.put(COL_REL_CONTACT, employee.getRelContact());
        values.put(COL_REL_RELATION, employee.getRelRelation());
        values.put(COL_BLOOD_GROUP, employee.getBloodGroup());
        values.put(COL_DATE_OF_JOINING, employee.getDoj());
        values.put(COL_ADDRESS, employee.getAddress());
        values.put(COL_MARITAL_STATUS, employee.getMaritalStatus());
        values.put(COL_DUTY, employee.getDuty());
        values.put(COL_LAST_RANK_DATE, employee.getLastRankDate());
        values.put(COL_LEAVE_TYPE, employee.getLeaveType());
        values.put(COL_LEAVE_DAYS, employee.getLeaveDays());
        values.put(COL_EMP_PIC, employee.getEmpPic());
        return values;
    }
    public static Employee getMappedModel(ResultSet rs) throws SQLException{
        Employee emp = new Employee();
        emp.setEmployeeId(rs.getInt(COL_EMP_ID));
        emp.setEmpName(rs.getString(COL_EMP_NAME));
        emp.setSvcNum(rs.getString(COL_SVC_NUM));
        emp.setRankId(rs.getInt(COL_RANK_ID));
        emp.setCprNum(rs.getString(COL_CPR_NUM));
        emp.setCountryId(rs.getInt(COL_COUNTRY_ID));
        emp.setMobile(rs.getString(COL_MOBILE));
        emp.setRelContact(rs.getString(COL_REL_CONTACT));
        emp.setRelRelation(rs.getString(COL_REL_RELATION));
        emp.setBloodGroup(rs.getString(COL_BLOOD_GROUP));
        emp.setDoj(rs.getString(COL_DATE_OF_JOINING));
        emp.setAddress(rs.getString(COL_ADDRESS));
        emp.setMaritalStatus(rs.getString(COL_MARITAL_STATUS));
        emp.setDuty(rs.getString(COL_DUTY));
        emp.setLastRankDate(rs.getString(COL_LAST_RANK_DATE));
        emp.setLeaveDays(rs.getString(COL_LEAVE_DAYS));
        emp.setLeaveType(rs.getString(COL_LEAVE_TYPE));
        emp.setEmpPic(rs.getString(COL_EMP_PIC));
        return emp;
    }
    
}
