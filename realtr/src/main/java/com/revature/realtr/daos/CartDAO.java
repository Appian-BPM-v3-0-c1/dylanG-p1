package com.revature.realtr.daos;

import com.revature.realtr.connection.DatabaseConnection;
import com.revature.realtr.models.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CartDAO implements CrudDAO<Cart> {

    Connection con = DatabaseConnection.getCon();


    @Override
    public int save(Cart obj) {
        int n = 0;

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String date = formattedDate.format(dateTime);

        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO carts (fp_id, user_id, loc_id, date, price) VALUES (?,?,?,?, ?)");
            ps.setInt(1, obj.getFp_id());
            ps.setInt(2, obj.getUser_id());
            ps.setInt(3, obj.getLoc_id());
            ps.setString(4, date);
            ps.setFloat(5, obj.getPrice());

            n = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return n;
    }

    @Override
    public List<Cart> findAll() {
        return null;
    }

    @Override
    public Cart findById(int id) {
        return null;
    }

    @Override
    public List<Cart> findAllById(int id) {
        return null;
    }

    @Override
    public boolean update(Cart updatedObj) {
        return false;
    }

    @Override
    public boolean removeById(int user_id) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM carts WHERE user_id = ?");
            ps.setInt(1, user_id);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Cart> findCartById(int user_id) {
        List<Cart> carts = new ArrayList<>();
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String date = formattedDate.format(dateTime);

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM carts WHERE user_id = ?");
            ps.setInt(1, user_id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cart cart = new Cart();

                cart.setCart_id(rs.getInt("cart_id"));
                cart.setFp_id(rs.getInt("fp_id"));
                cart.setUser_id(rs.getInt("user_id"));
                cart.setLoc_id(rs.getInt("loc_id"));
                cart.setDate(date);
                cart.setPrice(rs.getFloat("price"));

                carts.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carts;
    }

    public float totalPrice (int user_id) {


        float f = 0.00f;


        try {
            PreparedStatement ps = con.prepareStatement("SELECT SUM(price) FROM carts WHERE user_id = ?");
            ps.setInt(1, user_id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                f = (rs.getFloat("sum"));
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }



        return f;
    }

}
