package com.revature.realtr.services;

import com.revature.realtr.daos.LocationDAO;

public class LocationService {
    private final LocationDAO locDAO;

    public LocationService (LocationDAO locDAO) {
        this.locDAO = locDAO;
    }

    public LocationDAO getLocDAO() {
        return locDAO;
    }

}
