package com.revature.realtr.daos;

import com.revature.realtr.connection.DatabaseConnection;
import com.revature.realtr.models.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationDAO implements CrudDAO<Location> {

    Connection con = DatabaseConnection.getCon();

    @Override
    public int save(Location obj) {
        int n = 0;

        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO locations (address, city, state, zip) VALUES (?,?,?,?)");
            ps.setString(1, obj.getAddress());
            ps.setString(2, obj.getCity());
            ps.setString(3, obj.getState());
            ps.setString(4, obj.getZip());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public List<Location> findAll() {
        List<Location> locList = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM locations");
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Location loc = new Location();

                loc.setLoc_id(rs.getInt("loc_id"));
                loc.setAddress(rs.getString("address"));
                loc.setCity(rs.getString("city"));
                loc.setState(rs.getString("state"));
                loc.setZip(rs.getString("zip"));

                locList.add(loc);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locList;
    }

    @Override
    public Location findById(int id) {
        return null;
    }

    @Override
    public List<Location> findAllById(int id) {
        return null;
    }

    @Override
    public boolean update(Location updatedObj) {
        return false;
    }

    @Override
    public boolean removeById(int user_id) {
        return false;
    }

}