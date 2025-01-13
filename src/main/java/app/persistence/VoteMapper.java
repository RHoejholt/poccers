package app.persistence;

import app.entities.Vote;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VoteMapper {

    private final Connection connection;

    public VoteMapper(Connection connection) {
        this.connection = connection;
    }

    public void insert(Vote vote) throws SQLException {
        String sql = "INSERT INTO Vote (rating, itemID, championID, ipaddress) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, vote.getRating());
            ps.setInt(2, vote.getItemID());
            ps.setInt(3, vote.getChampionID());
            ps.setString(4, vote.getIpaddress());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    vote.setVoteID(rs.getInt(1));
                }
            }
        }
    }

package app.persistence;

import app.entities.Vote;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

    public class VoteMapper {

        private final Connection connection;

        public VoteMapper(Connection connection) {
            this.connection = connection;
        }

        public void insert(Vote vote) throws SQLException {
            String sql = "INSERT INTO Vote (rating, itemID, championID, ipaddress) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, vote.getRating());
                ps.setInt(2, vote.getItemID());
                ps.setInt(3, vote.getChampionID());
                ps.setString(4, vote.getIpaddress());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        vote.setVoteID(rs.getInt(1));
                    }
                }
            }
        }

        public Vote findByIpAndIds(String ipaddress, int itemID, int championID) throws SQLException {
            String sql = "SELECT * FROM Vote WHERE ipaddress = ? AND itemID = ? AND championID = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, ipaddress);
                ps.setInt(2, itemID);
                ps.setInt(3, championID);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return new Vote(
                                rs.getInt("voteID"),
                                rs.getInt("rating"),
                                rs.getInt("itemID"),
                                rs.getInt("championID"),
                                rs.getString("ipaddress")
                        );
                    }
                }
            }
            return null;
        }

        public List<Vote> findAll() throws SQLException {
            String sql = "SELECT * FROM Vote";
            List<Vote> votes = new ArrayList<>();
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    votes.add(new Vote(
                            rs.getInt("voteID"),
                            rs.getInt("rating"),
                            rs.getInt("itemID"),
                            rs.getInt("championID"),
                            rs.getString("ipaddress")
                    ));
                }
            }
            return votes;
        }

        public void delete(int voteID) throws SQLException {
            String sql = "DELETE FROM Vote WHERE voteID = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, voteID);
                ps.executeUpdate();
            }
        }
    }


    public List<Vote> findAll() throws SQLException {
        String sql = "SELECT * FROM Vote";
        List<Vote> votes = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                votes.add(new Vote(
                        rs.getInt("voteID"),
                        rs.getInt("rating"),
                        rs.getInt("itemID"),
                        rs.getInt("championID"),
                        rs.getString("ipaddress")
                ));
            }
        }
        return votes;
    }

    public void delete(int voteID) throws SQLException {
        String sql = "DELETE FROM Vote WHERE voteID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, voteID);
            ps.executeUpdate();
        }
    }
}
