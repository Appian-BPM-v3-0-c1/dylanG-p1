package com.revature.realtr.daos;

import com.revature.realtr.connection.DatabaseConnection;
import com.revature.realtr.models.Pen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PenDAO implements CrudDAO<Pen> {
    Connection con = DatabaseConnection.getCon();

    @Override
    public int save(Pen obj) {

        int n = 0;
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO fountain_pens (brand, model, price, " +
                    "qty, description, nib, loc_id) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getBrand());
            ps.setString(2, obj.getModel());
            ps.setFloat(3, obj.getPrice());
            ps.setInt(4, obj.getQty());
            ps.setString(5, obj.getDescription());
            ps.setString(6, obj.getNib());
            ps.setInt(7, obj.getLoc_id());

            n = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return n;
    }

    @Override
    public List<Pen> findAll() {
        List<Pen> penList = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM fountain_pens ORDER BY fp_id ASC");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pen pen = new Pen();

                pen.setFp_id(rs.getInt("fp_id"));
                pen.setBrand(rs.getString("brand"));
                pen.setModel(rs.getString("model"));
                pen.setPrice(rs.getFloat("price"));
                pen.setQty(rs.getInt("qty"));
                pen.setDescription(rs.getString("description"));
                pen.setNib(rs.getString("nib"));
                pen.setLoc_id(rs.getInt("loc_id"));

                penList.add(pen);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return penList;

    }

    @Override
    public Pen findById(int id) {

        return null;
    }

    @Override
    public List<Pen> findAllById(int id) {
        return null;
    }

    @Override
    public boolean update(Pen updatedObj) {
        return false;
    }

    @Override
    public boolean removeById(int fp_id) {
        Pen pen = new Pen();

        try {
            PreparedStatement ps = con.prepareStatement("UPDATE fountain_pens SET qty = qty - 1 WHERE fp_id = ?");
            ps.setInt(1, fp_id);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Pen findPenById(int fp_id) {
        Pen pen = new Pen();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM fountain_pens WHERE fp_id = ?");
            ps.setInt(1, fp_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                pen.setFp_id(rs.getInt("fp_id"));
                pen.setBrand(rs.getString("brand"));
                pen.setModel(rs.getString("model"));
                pen.setPrice(rs.getFloat("price"));
                pen.setQty(rs.getInt("qty"));
                pen.setDescription(rs.getString("description"));
                pen.setNib(rs.getString("nib"));
                pen.setLoc_id(rs.getInt("loc_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pen;
    }

    public List<Pen> findPenByLocation(int loc_id) {
        List<Pen> penList = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM fountain_pens WHERE loc_id = ?");
            ps.setInt(1, loc_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pen pen = new Pen();

                pen.setFp_id(rs.getInt("fp_id"));
                pen.setBrand(rs.getString("brand"));
                pen.setModel(rs.getString("model"));
                pen.setPrice(rs.getFloat("price"));
                pen.setQty(rs.getInt("qty"));
                pen.setDescription(rs.getString("description"));
                pen.setNib(rs.getString("nib"));
                pen.setLoc_id(rs.getInt("loc_id"));

                penList.add(pen);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return penList;
    }

    public void adjustPenQuantity(int fp_id, int qty) {

        try {
            PreparedStatement ps = con.prepareStatement("UPDATE fountain_pens SET qty = ? WHERE fp_id = ?");

            ps.setInt(1, qty);
            ps.setInt(2, fp_id);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
