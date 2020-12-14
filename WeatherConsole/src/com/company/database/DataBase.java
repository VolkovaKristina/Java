package com.company.database;

import com.company.exceptions.CityNotFoundException;

import java.sql.*;

public class DataBase {
    private static DataBase instance;

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    private DataBase() {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:src/database.db");
            stmt = con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    public void close() {
        try {
            con.close();
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Double[] getCoordsByCity(String cityName) {
        Double lat = null;
        Double lon = null;

        String query = String.format(
                "select `lat`, `lon` " +
                "from `cities` " +
                "where `city` like '%s%s%s'",
                "%", cityName, "%"
        );

        try {
            rs = stmt.executeQuery(query);
            lat = rs.getDouble("lat");
            lon = rs.getDouble("lon");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Double[]{lat, lon};
    }

    public String getCity(String cityName) throws CityNotFoundException {
        String query = String.format(
                "select `city` " +
                        "from `cities` " +
                        "where `city` like '%s'",
                cityName
        );

        try {
            rs = stmt.executeQuery(query);
            cityName = rs.getString("city");
        } catch (SQLException e) {
            throw new CityNotFoundException();
        }
        return cityName;
    }

    public String getService(Integer id) {
        String serviceName = "";
        String query = String.format(
                "select `service_name` " +
                        "from `weather_services` " +
                        "where `id` = '%s'",
                id
        );

        try {
            rs = stmt.executeQuery(query);
            serviceName = rs.getString("service_name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serviceName;
    }

    public User getUser(String username) {
        User user = new User();
        user.setUsername(username);

        String query = String.format(
                "select `id`, `username`, `city`, `service_id` " +
                "from `users` " +
                "where `username` = '%s'",
                user.getUsername()
        );

        try {
            rs = stmt.executeQuery(query);
            user.setId(rs.getInt("id"));
            user.setCity(rs.getString("city"));
            user.setServiceId(rs.getInt("service_id"));
        } catch (SQLException e) {
            createUser(user);
        }

        return user;
    }

    public void setCity(User user) {
        String query = String.format(
                "update `users` " +
                "set `city` = '%s' " +
                "where `username` = '%s'",
                user.getCity(), user.getUsername()
        );

        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setServiceId(User user) {
        String query = String.format(
                "update `users` " +
                "set `service_id` = '%s' " +
                "where `username` = '%s'",
                user.getServiceId(), user.getUsername()
        );

        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createUser(User user) {
        String query = String.format(
                "insert into `users` ('username') " +
                "values ('%s')",
                user.getUsername()
        );

        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
