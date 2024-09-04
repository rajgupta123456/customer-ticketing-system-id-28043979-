package com.ticketingsystem.dao;

import com.ticketingsystem.util.DBConnection;
import com.ticketingsystem.exception.TicketingException;
import com.ticketingsystem.model.Resolution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResolutionDAO {

    public void resolveTicket(Resolution resolution) throws TicketingException {
        String sql = "INSERT INTO Resolution (ticket_id, resolution_date, resolution_details, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, resolution.getTicketId());
            ps.setDate(2, new java.sql.Date(resolution.getResolutionDate().getTime()));
            ps.setString(3, resolution.getResolutionDetails());
            ps.setString(4, resolution.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new TicketingException("Error resolving ticket", e);
        }
    }

    public Resolution viewResolution(int resolutionId) throws TicketingException {
        Resolution resolution = null;
        String sql = "SELECT * FROM Resolution WHERE resolution_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, resolutionId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    resolution = new Resolution();
                    resolution.setResolutionId(rs.getInt("resolution_id"));
                    resolution.setTicketId(rs.getInt("ticket_id"));
                    resolution.setResolutionDate(rs.getDate("resolution_date"));
                    resolution.setResolutionDetails(rs.getString("resolution_details"));
                    resolution.setStatus(rs.getString("status"));
                }
            }
        } catch (SQLException e) {
            throw new TicketingException("Error viewing resolution", e);
        }
        return resolution;
    }

    public void updateResolution(Resolution resolution) throws TicketingException {
        String sql = "UPDATE Resolution SET resolution_details = ?, status = ? WHERE resolution_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, resolution.getResolutionDetails());
            ps.setString(2, resolution.getStatus());
            ps.setInt(3, resolution.getResolutionId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new TicketingException("Error updating resolution", e);
        }
    }

    public void deleteResolution(int resolutionId) throws TicketingException {
        String sql = "DELETE FROM Resolution WHERE resolution_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, resolutionId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new TicketingException("Error deleting resolution", e);
        }
    }
}
