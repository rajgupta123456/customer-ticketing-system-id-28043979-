package com.ticketingsystem.dao;

import com.ticketingsystem.util.DBConnection;
import com.ticketingsystem.exception.TicketingException;
import com.ticketingsystem.model.Assignment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AssignmentDAO {

    public void assignTicket(Assignment assignment) throws TicketingException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO Assignment (ticket_id, representative_id, assignment_date, status) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, assignment.getTicketId());
            ps.setInt(2, assignment.getRepresentativeId());
            ps.setDate(3, new java.sql.Date(assignment.getAssignmentDate().getTime()));
            ps.setString(4, assignment.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new TicketingException("Error assigning ticket", e);
        }
    }

    public Assignment viewAssignment(int assignmentId) throws TicketingException {
        Assignment assignment = null;
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM Assignment WHERE assignment_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, assignmentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                assignment = new Assignment();
                assignment.setAssignmentId(rs.getInt("assignment_id"));
                assignment.setTicketId(rs.getInt("ticket_id"));
                assignment.setRepresentativeId(rs.getInt("representative_id"));
                assignment.setAssignmentDate(rs.getDate("assignment_date"));
                assignment.setStatus(rs.getString("status"));
            }
        } catch (SQLException e) {
            throw new TicketingException("Error viewing assignment", e);
        }
        return assignment;
    }

    public void updateAssignment(Assignment assignment) throws TicketingException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE Assignment SET status = ? WHERE assignment_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, assignment.getStatus());
            ps.setInt(2, assignment.getAssignmentId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new TicketingException("Error updating assignment", e);
        }
    }

    public void deleteAssignment(int assignmentId) throws TicketingException {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM Assignment WHERE assignment_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, assignmentId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new TicketingException("Error deleting assignment", e);
        }
    }

    // Additional methods for handling assignments can be added here
}
