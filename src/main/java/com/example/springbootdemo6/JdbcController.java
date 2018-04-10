package com.example.springbootdemo6;

import com.example.springbootdemo6.domain.User;
import com.example.springbootdemo6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class JdbcController {


    @Autowired
    private DataSource dataSource;


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserService userService;

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


    @RequestMapping("/user/add")
    @ResponseBody
    public Map<String, Object> getUser(@RequestBody User user) {


        Map<String, Object> data = new HashMap<>();

        Boolean result = jdbcTemplate.execute("", new PreparedStatementCallback<Boolean>() {

            @Override
            public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {

                preparedStatement.setString(1, user.getName());

                preparedStatement.setInt(2, user.getAge());

                return preparedStatement.executeUpdate() > 0;
            }
        });

        data.put("success", userService.save(user));

        return data;

    }


}





















































