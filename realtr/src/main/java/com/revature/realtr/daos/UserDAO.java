package com.revature.realtr.daos;

import com.revature.realtr.connection.DatabaseConnection;
import com.revature.realtr.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements CrudDAO<User> {
    Connection con = DatabaseConnection.getCon();

    @Override
    public int save(User obj) {

        int n = 0;

        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (username, password, firstname, lastname, email, address, city, state, zip, usertype) VALUES (?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, obj.getUsername());
            ps.setString(2, obj.getPassword());
            ps.setString(3, obj.getFirstName());
            ps.setString(4, obj.getLastName());
            ps.setString(5, obj.getEmail());
            ps.setString(6, obj.getAddress());
            ps.setString(7, obj.getCity());
            ps.setString(8, obj.getState());
            ps.setString(9, obj.getZip());
            ps.setString(10, obj.getUsertype());

            n = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setUsertype(rs.getString("usertype"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
                user.setCity(rs.getString("city"));
                user.setState(rs.getString("state"));
                user.setZip(rs.getString("zip"));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User findById(int user_id) {
        User user = new User();

        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE user_id = ?");
            ps.setInt(1,user_id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user.setUser_id(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
                user.setCity(rs.getString("city"));
                user.setState(rs.getString("state"));
                user.setZip(rs.getString("zip"));
                user.setUsertype(rs.getString("usertype"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findAllById(int id) {
        return null;
    }

    @Override
    public boolean update(User updatedObj) {
        return false;
    }

    @Override
    public boolean removeById(int user_id) {
        return false;
    }

    public List<String> findAllUsernames() {
        List<String> usernameList = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT (username) FROM users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                usernameList.add(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usernameList;
    }

    public User findByUsername(String username) {
        User user = new User();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username = ?");
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user.setUser_id(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
                user.setCity(rs.getString("city"));
                user.setState(rs.getString("state"));
                user.setZip(rs.getString("zip"));
                user.setUsertype(rs.getString("usertype"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public List<User> findByName(String firstname, String lastname) {

        List<User> userList = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE firstname LIKE ? AND lastname LIKE ?");
            ps.setString(1, "%" + firstname + "%");
            ps.setString(2, "%" + lastname + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();

                user.setUser_id(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
                user.setCity(rs.getString("city"));
                user.setState(rs.getString("state"));
                user.setZip(rs.getString("zip"));
                user.setUsertype(rs.getString("usertype"));

                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }
}
