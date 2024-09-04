package com.ticketingsystem.Main;

import com.ticketingsystem.model.Ticket;
import com.ticketingsystem.model.Assignment;
import com.ticketingsystem.model.Resolution;
import com.ticketingsystem.dao.TicketDAO;
import com.ticketingsystem.dao.AssignmentDAO;
import com.ticketingsystem.dao.ResolutionDAO;
import com.ticketingsystem.exception.TicketingException;

import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TicketDAO ticketDAO = new TicketDAO();
        AssignmentDAO assignmentDAO = new AssignmentDAO();
        ResolutionDAO resolutionDAO = new ResolutionDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        // Create Ticket
                        System.out.println("Enter Customer ID:");
                        int customerId = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        System.out.println("Enter Issue Description:");
                        String issueDescription = scanner.nextLine();

                        Ticket ticket = new Ticket();
                        ticket.setCustomerId(customerId);
                        ticket.setCreationDate(new Date());
                        ticket.setIssueDescription(issueDescription);
                        ticket.setStatus("Open");

                        ticketDAO.createTicket(ticket);
                        System.out.println("Ticket created successfully.");
                        break;

                    case 2:
                        // View Ticket
                        System.out.println("Enter Ticket ID:");
                        int ticketId = scanner.nextInt();

                        Ticket existingTicket = ticketDAO.viewTicket(ticketId);
                        if (existingTicket != null) {
                            System.out.println("Ticket ID: " + existingTicket.getTicketId());
                            System.out.println("Customer ID: " + existingTicket.getCustomerId());
                            System.out.println("Creation Date: " + existingTicket.getCreationDate());
                            System.out.println("Issue Description: " + existingTicket.getIssueDescription());
                            System.out.println("Status: " + existingTicket.getStatus());
                        } else {
                            System.out.println("Ticket not found.");
                        }
                        break;

                    case 3:
                        // Update Ticket
                        System.out.println("Enter Ticket ID to update:");
                        int updateTicketId = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        System.out.println("Enter new Issue Description:");
                        String newIssueDescription = scanner.nextLine();
                        System.out.println("Enter new Status:");
                        String newStatus = scanner.nextLine();

                        Ticket updatedTicket = new Ticket();
                        updatedTicket.setTicketId(updateTicketId);
                        updatedTicket.setIssueDescription(newIssueDescription);
                        updatedTicket.setStatus(newStatus);

                        ticketDAO.updateTicket(updatedTicket);
                        System.out.println("Ticket updated successfully.");
                        break;

                    case 4:
                        // Delete Ticket
                        System.out.println("Enter Ticket ID to delete:");
                        int deleteTicketId = scanner.nextInt();

                        ticketDAO.deleteTicket(deleteTicketId);
                        System.out.println("Ticket deleted successfully.");
                        break;

                    case 5:
                        // Assign Ticket
                        System.out.println("Enter Ticket ID to assign:");
                        int assignTicketId = scanner.nextInt();
                        System.out.println("Enter Representative ID:");
                        int representativeId = scanner.nextInt();

                        Assignment assignment = new Assignment();
                        assignment.setTicketId(assignTicketId);
                        assignment.setRepresentativeId(representativeId);
                        assignment.setAssignmentDate(new Date());
                        assignment.setStatus("Assigned");

                        assignmentDAO.assignTicket(assignment);
                        System.out.println("Ticket assigned successfully.");
                        break;

                    case 6:
                        // View Assigned Ticket
                        System.out.println("Enter Assignment ID:");
                        int assignmentId = scanner.nextInt();

                        Assignment existingAssignment = assignmentDAO.viewAssignment(assignmentId);
                        if (existingAssignment != null) {
                            System.out.println("Assignment ID: " + existingAssignment.getAssignmentId());
                            System.out.println("Ticket ID: " + existingAssignment.getTicketId());
                            System.out.println("Representative ID: " + existingAssignment.getRepresentativeId());
                            System.out.println("Assignment Date: " + existingAssignment.getAssignmentDate());
                            System.out.println("Status: " + existingAssignment.getStatus());
                        } else {
                            System.out.println("Assignment not found.");
                        }
                        break;

                    case 7:
                        // Resolve Ticket
                        System.out.println("Enter Ticket ID to resolve:");
                        int resolveTicketId = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        System.out.println("Enter Resolution Details:");
                        String resolutionDetails = scanner.nextLine();

                        Resolution resolution = new Resolution();
                        resolution.setTicketId(resolveTicketId);
                        resolution.setResolutionDate(new Date());
                        resolution.setResolutionDetails(resolutionDetails);
                        resolution.setStatus("Resolved");

                        resolutionDAO.resolveTicket(resolution);
                        System.out.println("Ticket resolved successfully.");
                        break;

                    case 8:
                        // View Resolution
                        System.out.println("Enter Resolution ID:");
                        int resolutionId = scanner.nextInt();

                        Resolution existingResolution = resolutionDAO.viewResolution(resolutionId);
                        if (existingResolution != null) {
                            System.out.println("Resolution ID: " + existingResolution.getResolutionId());
                            System.out.println("Ticket ID: " + existingResolution.getTicketId());
                            System.out.println("Resolution Date: " + existingResolution.getResolutionDate());
                            System.out.println("Resolution Details: " + existingResolution.getResolutionDetails());
                            System.out.println("Status: " + existingResolution.getStatus());
                        } else {
                            System.out.println("Resolution not found.");
                        }
                        break;

                    case 9:
                        // Exit
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (TicketingException e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n=== Ticketing System Menu ===");
        System.out.println("1. Create Ticket");
        System.out.println("2. View Ticket");
        System.out.println("3. Update Ticket");
        System.out.println("4. Delete Ticket");
        System.out.println("5. Assign Ticket");
        System.out.println("6. View Assigned Ticket");
        System.out.println("7. Resolve Ticket");
        System.out.println("8. View Resolution");
        System.out.println("9. Exit");
        System.out.print("Enter your choice: ");
    }
}
