/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viremp.employeemanagement.db;

//import com.viremp.employeemanagement.models.Certificate;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Maddy
 */
public class Certificates {
    public static final String TABLE = "certificates";
    public static final String COL_CERT_ID = "certificate_id";
    public static final String COL_CERT_NAME = "certificate_name";
    public static final String COL_CERT_IMAGE = "certificate_image";
    public static final String COL_EMPLOYEE_ID = "employee_id";
    
//    public static boolean insertNewCertificate(Certificate cert) throws SQLException{
//        return GenericDB.insert(TABLE, getValuesMap(cert));
//    }
//    private static Map<String,Object> getValuesMap(Certificate cert){
//        Map<String,Object> values = new HashMap<>();
//        values.put(COL_CERT_ID, cert.getCertificateId());
//        values.put(COL_CERT_NAME, cert.getCertificateName());
//        values.put(COL_CERT_IMAGE, cert.getCertificateImage());
//        values.put(COL_EMPLOYEE_ID, cert.getEmployeeId());
//        return values;
//    }
}
