/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viremp.employeemanagement.db;

/**
 *
 * @author ahmed
 */
public class TableDefinitions {

    static final String TABLE_ADMINS = "admins";
    static final String ADMIN_ID = "admin_id";
    static final String ADMIN_UNAME = "uname";
    static final String ADMIN_PASS = "pass";
    // Computers table name
    static final String TABLE_COMPUTER = "computer_data_1";

    static final String KEY_ID = "comp_id";
    static final String KEY_COMP_NAME = "comp_name";
    static final String KEY_COMP_PROCESSOR = "comp_processor";
    static final String KEY_RAM = "comp_ram";
    static final String KEY_HARDDISK = "comp_harddisk";
    static final String KEY_OS = "comp_os";
    static final String KEY_OTHER_SOFTWARE = "comp_othersoftware";
    static final String KEY_DATE_OF_ISSUE = "comp_dateofissue";
    static final String KEY_IS_ASSIGN = "comp_is_assign";

    //Employee table attributes
    static String TABLE_EMPLOYEE = "employee_data";
    static String EMP_FIRSTNAME = "emp_fname";
    static String EMP_LASTNAME = "emp_lname";
    static String EMP_SECURITY_NO = "emp_security_no";
    static String EMP_PHONE_NO = "emp_phone_no";
    static String EMP_EMAIL = "emp_email";
//     static String IS_PROD_ASSIGN = "emp_is_comp_assign";

    //registeredComputers attributes
    static String TABLE_AC = "assigned_computers_data";
    static String KEY_AC_EMP_SEC_NO = "emp_security_no";
    static String KEY_AC_COMP_ID = "comp_id";

    //metadata table attr
    static String TABLE_METADATA = "products_meta_data";
    static String TABLE_VALUES = "products_values_data";
    static String METADATA_PRODUCT_ID = "metadata_product_id";
    static String PRODUCT_VALUE_ID = "values_product_id";
    static String METADATA_PRODUCT_NAME = "metadata_product_name";

    static final String CREATE_TABLE
            = "CREATE TABLE " + TABLE_COMPUTER + "("
            + KEY_ID + " NUMBER PRIMARY KEY NOT NULL ," + KEY_COMP_NAME + " TEXT ," + KEY_COMP_PROCESSOR + " TEXT ,"
            + KEY_RAM + " TEXT ," + KEY_HARDDISK + " TEXT ," + KEY_OS + " TEXT ," + KEY_OTHER_SOFTWARE + " TEXT ," + KEY_DATE_OF_ISSUE + " TEXT ," + KEY_IS_ASSIGN + " TEXT " + ")";

    static final String CREATE_EMPLOYEE_TABLE = "CREATE TABLE " + TABLE_EMPLOYEE + "("
            + EMP_FIRSTNAME + " TEXT ," + EMP_LASTNAME + " TEXT ,"
            + EMP_SECURITY_NO + " NUMBER PRIMARY KEY NOT NULL," + EMP_PHONE_NO + " TEXT ," + EMP_EMAIL + " TEXT)";

    static final String CREATE_ASSIGNED_COMPUTERS
            = "CREATE TABLE " + TABLE_AC + "("
            + KEY_AC_EMP_SEC_NO + " TEXT ," + KEY_AC_COMP_ID + " TEXT " + ")";

    static final String CREATE_METADATA_TABLE
            = "CREATE TABLE " + TABLE_METADATA + "("
            + METADATA_PRODUCT_ID + " NUMBER PRIMARY KEY NOT NULL,"
            + METADATA_PRODUCT_NAME + " TEXT,"
            + "attr_1 TEXT,"
            + "attr_2 TEXT,"
            + "attr_3 TEXT,"
            + "attr_4 TEXT,"
            + "attr_5 TEXT,"
            + "attr_6 TEXT,"
            + "attr_7 TEXT,"
            + "attr_8 TEXT,"
            + "attr_9 TEXT,"
            + "attr_10 TEXT,"
            + "attr_11 TEXT,"
            + "attr_12 TEXT,"
            + "attr_13 TEXT,"
            + "attr_14 TEXT,"
            + "attr_15 TEXT,"
            + "attr_16 TEXT,"
            + "attr_17 TEXT,"
            + "attr_18 TEXT,"
            + "attr_19 TEXT,"
            + "attr_20 TEXT,"
            + "attr_21 TEXT,"
            + "attr_22 TEXT,"
            + "attr_23 TEXT,"
            + "attr_24 TEXT,"
            + "attr_25 TEXT,"
            + "attr_26 TEXT,"
            + "attr_27 TEXT,"
            + "attr_28 TEXT,"
            + "attr_29 TEXT,"
            + "attr_30 TEXT )";

    static final String CREATE_VALUES_TABLE
            = "CREATE TABLE " + TABLE_VALUES + "("
            + METADATA_PRODUCT_ID + " NUMBER NOT NULL,"
            + PRODUCT_VALUE_ID+" NUMBER,"
            + "attr_1 TEXT,"
            + "attr_2 TEXT,"
            + "attr_3 TEXT,"
            + "attr_4 TEXT,"
            + "attr_5 TEXT,"
            + "attr_6 TEXT,"
            + "attr_7 TEXT,"
            + "attr_8 TEXT,"
            + "attr_9 TEXT,"
            + "attr_10 TEXT,"
            + "attr_11 TEXT,"
            + "attr_12 TEXT,"
            + "attr_13 TEXT,"
            + "attr_14 TEXT,"
            + "attr_15 TEXT,"
            + "attr_16 TEXT,"
            + "attr_17 TEXT,"
            + "attr_18 TEXT,"
            + "attr_19 TEXT,"
            + "attr_20 TEXT,"
            + "attr_21 TEXT,"
            + "attr_22 TEXT,"
            + "attr_23 TEXT,"
            + "attr_24 TEXT,"
            + "attr_25 TEXT,"
            + "attr_26 TEXT,"
            + "attr_27 TEXT,"
            + "attr_28 TEXT,"
            + "attr_29 TEXT,"
            + "attr_30 TEXT,"
            + EMP_SECURITY_NO+" NUMBER,"
            + "PRIMARY KEY ( "+METADATA_PRODUCT_ID+","+ PRODUCT_VALUE_ID+"))";

}
