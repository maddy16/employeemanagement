package com.viremp.employeemanagement.db;


import com.viremp.employeemanagement.models.Country;
import com.viremp.employeemanagement.models.Rank;
import java.sql.*;
import java.util.List;

public class DatabaseHandler {

    public static final int DUPLICATE_PK = 1;
    static Connection con;
    private static final DatabaseHandler DATABASE_HANDLER = new DatabaseHandler();

    public static DatabaseHandler getDatabaseHandler() {
        return DATABASE_HANDLER;
    }
    private DatabaseHandler() {
        
    }

    static void connect() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ems_db?useSSL=false","root","admin");
    }

    
    public List<Country> getAllCountries() throws SQLException{
        return Countries.getAllCountries();
    }
    
    public List<Rank> getAllRanks() throws SQLException{
        return Ranks.getAllRanks();
    }
    public boolean addNewRank(Rank rank) throws SQLException{
        return Ranks.addRank(rank);
    }
    public boolean deleteRank(int rankId) throws SQLException{
        return Ranks.deleteRank(rankId);
    }
    

}
