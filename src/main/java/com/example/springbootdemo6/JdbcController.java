package com.example.springbootdemo6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

@RestController
public class JdbcController {


    @Autowired
    private DataSource dataSource;

    @RequestMapping("/user/get")
    public Map<String, Object> getUser(@RequestParam(value = "id", defaultValue = "1") String id) {


        Map<String, Object> data = new HashMap<String, Object>();

        Connection connection = null;

        try {
            connection = dataSource.getConnection();

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("");


            while (resultSet.next()) {


            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return data;

    }


}





















































