package com.viremp.employeemanagement.db;



import com.viremp.employeemanagement.models.Country;
import com.viremp.employeemanagement.models.Course;
import com.viremp.employeemanagement.models.Employee;
import com.viremp.employeemanagement.models.Fine;
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
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ems_db?useSSL=false","root","admin");
        } catch(SQLException ex){
            ex.printStackTrace();
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        
    }

    
    public List<Country> getAllCountries() throws SQLException{
        return Countries.getAllCountries();
    }
    
    public List<Rank> getAllRanks() throws SQLException{
        return Ranks.getAllRanks();
    }
    public int addNewRank(Rank rank) throws SQLException{
        return Ranks.addRank(rank);
    }
    public boolean deleteRank(int rankId) throws SQLException{
        return Ranks.deleteRank(rankId);
    }
    public boolean addNewFine(Fine fine) throws SQLException{
        return Fines.insertNewFine(fine);
    }
    public Rank getRankByName(String name) throws SQLException{
        return Ranks.getRankByName(name);
    }
    public int addNewCountry(Country country) throws SQLException{
        return Countries.addNewCountry(country);
    }
    public Country getCountryByName(String name ) throws SQLException{
        return Countries.getByName(name);
    }
    public int addNewEmployee(Employee employee) throws SQLException{
        return Employees.addNewEmployee(employee);
    }
    public boolean addNewIntCourse(Course course) throws SQLException{
        return InteriorCourses.insertNewInteriorCourse(course);
    }
    public boolean addNewExtCourse(Course course) throws SQLException{
        return ExteriorCourses.insertNewExteriorCourse(course);
    }
    public boolean updateEmployee(Employee employee) throws SQLException{
        return Employees.updateEmployee(employee);
    }
    public Employee getByServiceNum(String svc) throws SQLException{
        return Employees.getByServiceNum(svc);
    }
    public Employee getByCPR(String cpr) throws SQLException{
        return Employees.getByCPR(cpr);
    }
    public List<Fine> getAllFines(int employeeId) throws SQLException{
        return Fines.getAllFines(employeeId);
    }
    public List<Course> getAllInteriorCourses(int employeeId) throws SQLException{
        return InteriorCourses.getAllInteriorCourses(employeeId);
    }
    public List<Course> getAllExteriorCourses(int employeeId) throws SQLException{
        return ExteriorCourses.getAllExteriorCourses(employeeId);
    }
    public boolean deleteAllFines(int employeeId) throws SQLException{
        return Fines.deleteAllFines(employeeId);
    }
    public boolean deleteAllIntCourses(int employeeId) throws SQLException{
        return InteriorCourses.deleteAllIntCourses(employeeId);
    }
    public boolean deleteAllExtCourses(int employeeId) throws SQLException{
        return ExteriorCourses.deleteAllExtCourses(employeeId);
    }
    public boolean deleteEmployee(int employeeId) throws SQLException{
        return Employees.deleteEmployee(employeeId);
    }
   
//    public boolean addNewCertificate(Certificate cert) throws SQLException{
//        return Certificates.insertNewCertificate(cert);
//    }
    

}
