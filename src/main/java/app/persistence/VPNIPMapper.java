package app.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.config.ConnectionPool;

public class VPNIPMapper {

    // Method to get all VPN IPs
    public List<String> getAllVPNIPs() {
        List<String> vpnIPs = new ArrayList<>();
        String query = "SELECT vpnip FROM VPNIP";

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                vpnIPs.add(rs.getString("vpnip"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vpnIPs;
    }

    // Method to add a VPN IP to the database
    public boolean addVPNIP(String vpnIP) {
        String query = "INSERT INTO VPNIP (vpnip) VALUES (?)";

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, vpnIP);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Method to check if an IP is in the VPNIP list
    public boolean isVPNIP(String ip) {
        String query = "SELECT 1 FROM VPNIP WHERE vpnip = ?";

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, vpnIP);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}