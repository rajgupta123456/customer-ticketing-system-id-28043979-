package com.ticketingsystem.dao;

import com.ticketingsystem.util.DBConnection;
import com.ticketingsystem.exception.TicketingException;
import com.ticketingsystem.model.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketDAO {

    public void createTicket(Ticket ticket) throws TicketingException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO Ticket (customer_id, creation_date, issue_description, status) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ticket.getCustomerId());
            ps.setDate(2, new java.sql.Date(ticket.getCreationDate().getTime()));
            ps.setString(3, ticket.getIssueDescription());
            ps.setString(4, ticket.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new TicketingException("Error creating ticket", e);
        }
    }

    public Ticket viewTicket(int ticketId) throws TicketingException {
        Ticket ticket = null;
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM Ticket WHERE ticket_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ticketId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ticket = new Ticket();
                ticket.setTicketId(rs.getInt("ticket_id"));
                ticket.setCustomerId(rs.getInt("customer_id"));
                ticket.setCreationDate(rs.getDate("creation_date"));
                ticket.setIssueDescription(rs.getString("issue_description"));
                ticket.setStatus(rs.getString("status"));
            }
        } catch (SQLException e) {
            throw new TicketingException("Error viewing ticket", e);
        }
        return ticket;
    }

    public void updateTicket(Ticket ticket) throws TicketingException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE Ticket SET issue_description = ?, status = ? WHERE ticket_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ticket.getIssueDescription());
            ps.setString(2, ticket.getStatus());
            ps.setInt(3, ticket.getTicketId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new TicketingException("Error updating ticket", e);
        }
    }

    public void deleteTicket(int ticketId) throws TicketingException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM Ticket WHERE ticket_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ticketId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new TicketingException("Error deleting ticket", e);
        }
    }

    // Additional methods for handling tickets can be added here
}
