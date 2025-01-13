package app.persistence;

import app.entities.Champion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChampionMapper {

    private final Connection connection;

    public ChampionMapper(Connection connection) {
        this.connection = connection;
    }

    public void insert(Champion champion) throws SQLException {
        String sql = "INSERT INTO Champion (name, description) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, champion.getName());
            ps.setString(2, champion.getDescription());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    champion.setChampionID(rs.getInt(1));
                }
            }
        }
    }

    public Champion findById(int championID) throws SQLException {
        String sql = "SELECT * FROM Champion WHERE championID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, championID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Champion(rs.getInt("championID"), rs.getString("name"), rs.getString("description"));
                }
            }
        }
        return null;
    }

    public List<Champion> findAll() throws SQLException {
        String sql = "SELECT * FROM Champion";
        List<Champion> champions = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                champions.add(new Champion(rs.getInt("championID"), rs.getString("name"), rs.getString("description")));
            }
        }
        return champions;
    }

    public void delete(int championID) throws SQLException {
        String sql = "DELETE FROM Champion WHERE championID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, championID);
            ps.executeUpdate();
        }
    }
}
