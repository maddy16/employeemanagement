/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viremp.employeemanagement.db;

import com.viremp.employeemanagement.models.Country;
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
public class Countries {

    public static final String TABLE = "countries";
    public static final String COL_COUNTRY_ID = "country_id";
    public static final String COL_COUNTRY = "country";

    public static List<Country> getAllCountries() throws SQLException {
        List<Country> countries = new ArrayList<>();
        ResultSet rs = GenericDB.getByTableName(TABLE, null);
        while (rs.next()) {
            Country country = new Country();
            country.setCountryId(rs.getInt(COL_COUNTRY_ID));
            country.setCountry(rs.getString(COL_COUNTRY));
            countries.add(country);
        }
        return countries;
    }
    public static int addNewCountry(Country country)throws SQLException {
        return GenericDB.insertReturningId(TABLE, getValuesMap(country));
    }
    public static Country getByName(String name) throws SQLException{
        ResultSet rs = GenericDB.query(TABLE, null, COL_COUNTRY +" = ?", new Object[]{name}, null);
        Country country = null;
        if(rs.next()){
            country = new Country();
            country.setCountry(name);
            country.setCountryId(rs.getInt(COL_COUNTRY_ID));
        }
        return country;
    }
    private static Map<String,Object> getValuesMap(Country country){
        Map<String,Object> values = new HashMap<>();
        values.put(COL_COUNTRY_ID, country.getCountryId());
        values.put(COL_COUNTRY, country.getCountry());
        return values;
        
    }
}
