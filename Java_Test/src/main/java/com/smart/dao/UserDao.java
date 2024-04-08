package com.smart.dao;

import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    private void setUser(User user, ResultSet resultSet) throws SQLException {
        user.setUser_id(resultSet.getInt("uCode"));
        user.setUserName(resultSet.getString("uName"));
        user.setPassword(resultSet.getString("uPwd"));
        user.setUser_memo(resultSet.getString("uMemo"));
        user.setUser_type(resultSet.getBoolean("uType"));
        user.setUserType(user.getUser_type());
    }
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    //查询数据表所有数据
    public List<User> select() {
        List<User> user_list = new ArrayList<>();
        String sql_string = "SELECT * from UserTable";
        jdbcTemplate.query(sql_string,
                resultSet -> {
                    User user = new User();
                    setUser(user, resultSet);
                    user_list.add(user);
                });
        return user_list;
    }
    //根据登录界面提交的表单数据进行相应的查询
    public User select_loginUser(User loginUser) {
        String sql_String = "SELECT * FROM UserTable WHERE uName = ? and uPwd = ?";
        User user = new User();
        jdbcTemplate.query(sql_String,
                new Object[]{loginUser.getUserName(), loginUser.getPassword()},
                resultSet -> {
                    setUser(user, resultSet);
                });
        if (user.getUserName() == null)
            return null;
        else
            return user;
    }
    //根据用户ID进行查询
    public User selectForUserId(int userId) {
        String sql_String = "SELECT * FROM UserTable WHERE uCode = ?";
        User user = new User();
        jdbcTemplate.query(sql_String, new Object[]{userId},
                resultSet -> {
                    setUser(user, resultSet);
                });
        return user;
    }
    //删除用户
    public void deleteUser(int userId) {
        String sql_String = "DELETE FROM UserTable WHERE uCode = ?";
        jdbcTemplate.update(sql_String, userId);
    }
    //新增用户
    public void addUser(User user) {
        String sql_String =
                "INSERT INTO UserTable (uName, uPwd, uMemo, uType) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sql_String,
                user.getUserName(), user.getPassword(),
                user.getUser_memo(), user.getUser_type());
    }
    //修改用户信息
    public void modifyUserMessage(User user) {
        String sql_String =
                "UPDATE UserTable set uName = ?, uPwd = ?, uType = ?, uMemo = ? where uCode = ?";
        jdbcTemplate.update(sql_String, user.getUserName(), user.getPassword(),
                user.getUser_type(), user.getUser_memo(), user.getUser_id());
    }
}
