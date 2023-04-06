package org.prog.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.testng.annotations.Test;

public class MySQLTestNG {

  @Test
  public void iniInsertData() throws SQLException, ClassNotFoundException {
    Class.forName("com.mysql.cj.jdbc.Driver");

    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "user", "password");
    Statement stmt = con.createStatement();

    ResultSet sqlResults = stmt.executeQuery("SELECT * FROM Persons");

    while (sqlResults.next()) {
      String values = sqlResults.getString("PersonId") + " " + sqlResults.getString(2) + " " +
          sqlResults.getString(3) + " " + sqlResults.getString(4) + " " + sqlResults.getString(5);

      System.out.println(values);
    }
  }
}

