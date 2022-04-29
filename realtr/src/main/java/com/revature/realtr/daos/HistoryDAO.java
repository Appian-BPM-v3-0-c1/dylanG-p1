package com.revature.realtr.daos;

import com.revature.realtr.connection.DatabaseConnection;
import com.revature.realtr.models.History;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HistoryDAO implements CrudDAO<History> {

    Connection con = DatabaseConnection.getCon();


    @Override
    public int save(History obj) {

        int n = 0;
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String date = formattedDate.format(dateTime);

        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO history (fp_id, user_id, loc_id, date, price) VALUES (?, ?, ?, ?, ?)");
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
    public List<History> findAll() {
        return null;
    }

    @Override
    public History findById(int user_id) {
        List<History> history = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM history WHERE user_id = ?");
            ps.setInt(1, user_id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                History historyView = new History();

                historyView.setHist_id(rs.getInt("history_id"));
                historyView.setFp_id(rs.getInt("fp_id"));
                historyView.setUser_id(rs.getInt("user_id"));
                historyView.setLoc_id(rs.getInt("loc_id"));
                historyView.setPrice(rs.getFloat("price"));

                history.add(historyView);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return null;
    }

    @Override
    public List<History> findAllById(int user_id) {
        return null;
    }

    @Override
    public boolean update(History updatedObj) {
        return false;
    }

    @Override
    public boolean removeById(int user_id) {

        return false;
    }

    public List<History> findHistoryById(int user_id) {
        List<History> historyList = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM history WHERE user_id = ?");
            ps.setInt(1, user_id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                History history = new History();

                history.setHist_id(rs.getInt("hist_id"));
                history.setFp_id(rs.getInt("fp_id"));
                history.setUser_id(rs.getInt("user_id"));
                history.setLoc_id(rs.getInt("loc_id"));
                history.setDate(rs.getString("date"));
                history.setPrice(rs.getFloat("price"));

                historyList.add(history);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return historyList;
    }

    public List<History> findHistoryByLocId(int loc_id) {
        List<History> historyList = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM history WHERE loc_id = ?");
            ps.setInt(1, loc_id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                History history = new History();

                history.setHist_id(rs.getInt("hist_id"));
                history.setFp_id(rs.getInt("fp_id"));
                history.setUser_id(rs.getInt("user_id"));
                history.setLoc_id(rs.getInt("loc_id"));
                history.setDate(rs.getString("date"));
                history.setPrice(rs.getFloat("price"));

                historyList.add(history);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return historyList;
    }

    public List<History> sortByDate() {
        List<History> historyList = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM history ORDER BY date ASC");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                History history = new History();

                history.setHist_id(rs.getInt("hist_id"));
                history.setFp_id(rs.getInt("fp_id"));
                history.setUser_id(rs.getInt("user_id"));
                history.setLoc_id(rs.getInt("loc_id"));
                history.setDate(rs.getString("date"));
                history.setPrice(rs.getFloat("price"));

                historyList.add(history);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return historyList;

    }

    public float totalPrice(int user_id) {
        float f = 0.00f;

        try {
            PreparedStatement ps = con.prepareStatement("SELECT SUM(price) FROM history WHERE user_id = ?");
            ps.setInt(1, user_id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                f = (rs.getFloat("sum"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }


}

