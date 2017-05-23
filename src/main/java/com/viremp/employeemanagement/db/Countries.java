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
import java.util.List;

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
}
