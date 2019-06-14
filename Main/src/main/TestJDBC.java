/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.Driver;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.util.Properties;

/**
 *
 * @author Mr.Nour
 */
public class TestJDBC {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        //jdbc:mysql://localhost/sakila
        //C:\\Users\Youssef\Documents\NetBeansProjects\tables\
        Driver driver = (Driver) Class.forName("jdbc.Driver").newInstance();
//        Properties p = new Properties();
//        p.setProperty("user", "root");
//        p.setProperty("password", "12345678");

        try (Connection con = driver.connect("C:\\Users\\Youssef\\Desktop\\assignment2_4319_4140_4257_3784\\Main\\tables\\", null);
                Statement statement = con.createStatement();) {
//                            statement.executeUpdate("Delete from courses where credit_hours = 3");
            for (int i = 0; i < 3; i++) {
//                statement.executeUpdate("INSERT INTO courses (course_name , credit_hours )  VALUES ('Programming " + i + "' ,3)");
            }
            
//            statement.execute("CREATE TABLE table(col1   VARCHAR , col2  INT)");            
            ResultSet res = statement.executeQuery("SELECT course_name, credit_hours FROM courses");
//            boolean success = statement.execute(" SELECT course_name, credit_hours FROM courses ");
//            ResultSet res = null;
//            if (success) {
//                res = statement.getResultSet();
//            }

//            res.first();
//            System.out.println(res.getObject(1) + "  " + res.getObject(2));
//            res.last();
//            System.out.println(res.getObject(1) + "  " + res.getObject(2));

            ResultSetMetaData resMeta = res.getMetaData();
            System.out.println(resMeta.getColumnCount());
            System.out.println(resMeta.getColumnType(1));
            System.out.println(resMeta.getColumnType(2));
            System.out.println("");
            
            System.out.printf("%-20s  %-20s", resMeta.getColumnName(1), resMeta.getColumnName(2));
            System.out.println("");

            while (res.next()) {
                System.out.printf("%-20s  %-20s", res.getString(1), res.getString(2));
                System.out.println("");
            }
            res.close();
        }
    }
}
