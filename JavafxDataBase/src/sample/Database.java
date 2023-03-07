package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    final static String DATABASE_URL = "jdbc:postgresql://localhost:5432/students";
    final static String user = "ahmet";
    final static String pass = "postgre";
    final static String SELECT_QUERY = "SELECT * FROM STUDENTS";

    //static Connection connection = null;

    public static List<Students> init(){

        Statement statement = null;

        List<Students> students = new ArrayList<>();

        try{
            Connection connection = DriverManager.getConnection(DATABASE_URL,user, pass);

            statement = connection.createStatement();
            ResultSet res = statement.executeQuery(SELECT_QUERY);


            while(res.next()){
                Students s = new Students();

                s.setNo(Integer.toString(res.getInt("No")));
                s.setNameSurname(res.getString("NameSurname"));
                s.setDepartment(res.getString("Department"));
                students.add(s);
            }
            connection.close();

        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return students;
    }

    public static List<Students> getStudents() throws SQLException{
        Connection connection = DriverManager.getConnection(DATABASE_URL,user, pass);
        List<Students> students = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery(SELECT_QUERY);
        while(res.next()) {
            Students s = new Students();

            s.setNo(Integer.toString(res.getInt("No")));
            s.setNameSurname(res.getString("NameSurname"));
            s.setDepartment(res.getString("Department"));
            students.add(s);
        }
        return students;
    }


    public static void addStudent(Students student){

        try{
            Connection connection = DriverManager.getConnection(DATABASE_URL,user, pass);
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO students (no, nameSurname, department, startingYear, email) " + "VALUES ('" +  student.getNo() +"','"+ student.getNameSurname() +"','" +student.getDepartment() + "','" +student.getYear() + "','" +student.getMail() + "')");
            connection.close();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
    public static void deleteStudent(int id){
        try{
            Connection connection = DriverManager.getConnection(DATABASE_URL,user, pass);
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM students WHERE no=" + Integer.toString(id));
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
