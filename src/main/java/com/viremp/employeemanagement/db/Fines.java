/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viremp.employeemanagement.db;

import com.viremp.employeemanagement.models.Fine;
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
public class Fines {
    public static final String TABLE = "fines";
    public static final String COL_FINE_ID = "fine_id";
    public static final String COL_FINE_NAME = "fine_name";
    public static final String COL_FINE_IMAGE = "fine_image";
    public static final String COL_EMPLOYEE_ID = "employee_id";
    
    public static boolean insertNewFine(Fine fine) throws SQLException{
        return GenericDB.insert(TABLE, getValuesMap(fine));
    }
    public static List<Fine> getAllFines(int employeeId) throws SQLException{
        ResultSet rs = GenericDB.query(TABLE, null, COL_EMPLOYEE_ID+" = ?", new Object[]{employeeId}, null);
        List<Fine> list = new ArrayList<>();
        while(rs.next()){
            Fine fine = new Fine();
            fine.setEmployeeId(employeeId);
            fine.setFineId(rs.getInt(COL_FINE_ID));
            fine.setFineName(rs.getString(COL_FINE_NAME));
            fine.setFineImage(rs.getString(COL_FINE_IMAGE));
            list.add(fine);
        }
        return list;
    }
    public static boolean deleteAllFines(int employeeId) throws SQLException{
        String sql = "DELETE FROM "+TABLE+" where "+COL_EMPLOYEE_ID+" = " + employeeId;
        return GenericDB.executeUpdate(sql);
    }
    private static Map<String,Object> getValuesMap(Fine fine){
        Map<String,Object> values = new HashMap<>();
        values.put(COL_FINE_ID, fine.getFineId());
        values.put(COL_FINE_NAME, fine.getFineName());
        values.put(COL_FINE_IMAGE, fine.getFineImage());
        values.put(COL_EMPLOYEE_ID, fine.getEmployeeId());
        return values;
    }
}
