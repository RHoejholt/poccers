package app.persistence;

import app.entities.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemMapper {

    private final Connection connection;

    public ItemMapper(Connection connection) {
        this.connection = connection;
    }

    public void insert(Item item) throws SQLException {
        String sql = "INSERT INTO Item (name, description) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getDescription());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    item.setItemID(rs.getInt(1));
                }
            }
        }
    }

    public Item findById(int itemID) throws SQLException {
        String sql = "SELECT * FROM Item WHERE itemID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, itemID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Item(rs.getInt("itemID"), rs.getString("name"), rs.getString("description"));
                }
            }
        }
        return null;
    }

    public List<Item> findAll() throws SQLException {
        String sql = "SELECT * FROM Item";
        List<Item> items = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                items.add(new Item(rs.getInt("itemID"), rs.getString("name"), rs.getString("description")));
            }
        }
        return items;
    }

    public void delete(int itemID) throws SQLException {
        String sql = "DELETE FROM Item WHERE itemID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, itemID);
            ps.executeUpdate();
        }
    }
}
