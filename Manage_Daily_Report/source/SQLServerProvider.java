package com.sohatec;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLServerProvider {

    private static final String URLJUNIORSTAFF = "jdbc:sqlserver://localhost:1433;databaseName=JuniorStaff;encrypt=true;trustServerCertificate=true;";
    private static final String URLSENIORSTAFF = "jdbc:sqlserver://localhost:1433;databaseName=SeniorStaff;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "YOUR_USER_NAME";
    private static final String PASS = "YOUR_PASSWORD";

    public static Connection getDatabaseJuniorStaff() throws SQLException {
        return DriverManager.getConnection(URLJUNIORSTAFF, USER, PASS);
    }

    public static Connection getDatabaseSeniorStaff() throws SQLException {
        return DriverManager.getConnection(URLSENIORSTAFF, USER, PASS);
    }

    public static String saveJuniorStaffToSQL(JuniorStaff juniorStaff){
        String sql = "INSERT INTO Junior (ID, Name, Email, Language) VALUES (?, ?, ?, ?)";
        try (Connection conn = getDatabaseJuniorStaff();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, juniorStaff.getJuniorId());
            pstmt.setString(2, juniorStaff.getJuniorName());
            pstmt.setString(3, juniorStaff.getJuniorEmail());
            pstmt.setString(4, juniorStaff.getJuniorLanguage());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return juniorStaff.getJuniorId();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String saveSeniorStaffToSQL(SeniorStaff seniorStaff) {
        String sql = "INSERT INTO Senior (ID, Name, Email, Language) VALUES (?, ?, ?, ?)";
        try (Connection conn = getDatabaseSeniorStaff();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, seniorStaff.getSeniorId());
            pstmt.setString(2, seniorStaff.getSeniorName());
            pstmt.setString(3, seniorStaff.getSeniorEmail());
            pstmt.setString(4, seniorStaff.getSeniorLanguage());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return seniorStaff.getSeniorId();
            }
        } catch (SQLException e) {
            System.err.println("=== Error Of SQL Server: " + e.getMessage() + " ===");
        }
        return null;
    }

    public static JuniorStaff getJuniorById(String id) {
        String sql = "SELECT ID, Name, Email, Language FROM Junior WHERE ID = ?";
        try (Connection conn = getDatabaseJuniorStaff();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new JuniorStaff(
                            rs.getString("ID"),
                            rs.getString("Name"),
                            rs.getString("Email"),
                            rs.getString("Language")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("=== Error Query: " + e.getMessage() + " ===");
        }
        return null;
    }

    public static SeniorStaff getSeniorById(String id) {
        String sql = "SELECT ID, Name, Email, Language FROM Senior WHERE ID = ?";
        try (Connection conn = getDatabaseSeniorStaff();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new SeniorStaff(
                            rs.getString("ID"),
                            rs.getString("Name"),
                            rs.getString("Email"),
                            rs.getString("Language")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("=== Error Query: " + e.getMessage() + " ===");
        }
        return null;
    }

    public static boolean updateJuniorField(String id, String newName, String newValue) {
        String sql = "UPDATE Junior SET " + newName + " = ? WHERE ID = ?";

        try (Connection conn = getDatabaseJuniorStaff();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newValue);
            pstmt.setString(2, id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("=== Error Update SQL: " + e.getMessage() + " ===");
            return false;
        }
    }

    public static boolean updateSeniorField(String id, String newName, String newValue) {
        String sql = "UPDATE Senior SET " + newName + " = ? WHERE ID = ?";

        try (Connection conn = getDatabaseSeniorStaff();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newValue);
            pstmt.setString(2, id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("=== Error Update SQL: " + e.getMessage() + " ===");
            return false;
        }
    }

    public static boolean deleteJuniorFromSQL(String id) {
        String sql = "DELETE FROM Junior WHERE ID = ?";
        try (Connection conn = getDatabaseJuniorStaff();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteSeniorFromSQL(String id) {
        String sql = "DELETE FROM Senior WHERE ID = ?";
        try (Connection conn = getDatabaseSeniorStaff();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<JuniorStaff> getAllJuniorStaff() {
        List<JuniorStaff> list = new ArrayList<>();
        String sql = "SELECT * FROM Junior";
        try (Connection conn = getDatabaseJuniorStaff();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new JuniorStaff(
                        rs.getString("ID"), rs.getString("Name"),
                        rs.getString("Email"), rs.getString("Language")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public static List<SeniorStaff> getAllSeniorStaff() {
        List<SeniorStaff> list = new ArrayList<>();
        String sql = "SELECT * FROM Senior";
        try (Connection conn = getDatabaseSeniorStaff();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new SeniorStaff(
                        rs.getString("ID"), rs.getString("Name"),
                        rs.getString("Email"), rs.getString("Language")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}
