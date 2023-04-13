package org.prog.steps;

import io.cucumber.java.en.Given;
import org.prog.api.dto.NameDto;
import org.prog.api.dto.UserDto;
import org.testng.Assert;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SqlSteps {

    private final static String SQL_INSERT =
            "INSERT INTO Persons (LastName, FirstName, Title, Gender) VALUES ('%s', '%s', '%s', '%s')";

    public static UserDto activeUser;

    private static final Random random = new Random();

    @Given("store persons data to DB")
    public void storePersonsDataToDB() {
        RestSteps.searchResultsDto.getResults().forEach(userDto -> storeUser(userDto));
    }

    @Given("i pick random user from DB")
    public void pickRandomPerson() {
        activeUser = getRandomUserFromDB();

        System.out.println(activeUser.getName().getFirst() + " " + activeUser.getName().getLast());
    }

    private void storeUser(UserDto userDto) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String dbHostName = null;
            if (InetAddress.getLocalHost().getHostName().equals("TSELSE3871808")) {
                dbHostName = "jdbc:mysql://localhost:3306/db";
            } else {
                dbHostName = "jdbc:mysql://mysql_db_1:3306/db";
            }
            Connection con = DriverManager.getConnection(dbHostName, "user", "password");
            Statement stmt = con.createStatement();

            String query = String.format(SQL_INSERT,
                    userDto.getName().getLast(), userDto.getName().getFirst(), userDto.getName().getTitle(), userDto.getGender());

            stmt.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("SQL Error");
        }
    }

    private UserDto getRandomUserFromDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String dbHostName = null;
            if (InetAddress.getLocalHost().getHostName().equals("TSELSE3871808")) {
                dbHostName = "jdbc:mysql://localhost:3306/db";
            } else {
                dbHostName = "jdbc:mysql://mysql_db_1:3306/db";
            }
            Connection con = DriverManager.getConnection(dbHostName, "user", "password");
            Statement stmt = con.createStatement();

            ResultSet sqlResults = stmt.executeQuery("SELECT * FROM Persons");
            List<UserDto> userDtos = new ArrayList<>();

            while (sqlResults.next()) {
                userDtos.add(
                        UserDto.builder()
                                .gender(sqlResults.getString("Gender"))
                                .name(
                                        NameDto.builder()
                                                .first(sqlResults.getString("FirstName"))
                                                .last(sqlResults.getString("LastName"))
                                                .title(sqlResults.getString("Title"))
                                                .build()
                                )
                                .build()
                );
            }
            return userDtos.get(random.nextInt(userDtos.size()));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("SQL Error");
        }
        return UserDto.builder().build();
    }
}
