package com.example.springbootdemo6.domain;

import com.example.springbootdemo6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@Service
@EnableTransactionManagement
public class UserServiceImpl implements UserService {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    @Transactional
    public boolean save(User user) {

        Map<String, Object> data = new HashMap<>();

        Boolean result = jdbcTemplate.execute("", new PreparedStatementCallback<Boolean>() {

            @Override
            public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {

                preparedStatement.setString(1, user.getName());

                preparedStatement.setInt(2, user.getAge());

                return preparedStatement.executeUpdate() > 0;
            }
        });

        return result;
    }
}




















