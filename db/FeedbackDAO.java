package db;

import model.Feedback;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {

    public static void saveFeedback(Feedback feedback) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String query = "INSERT INTO feedback (teacherName, feedbackText) VALUES (?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, feedback.getTeacherName());
        pstmt.setString(2, feedback.getFeedbackText());
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    public static List<Feedback> getAllFeedback() throws SQLException {
        List<Feedback> feedbackList = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String query = "SELECT * FROM feedback";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            feedbackList.add(new Feedback(rs.getString("teacherName"), rs.getString("feedbackText")));
        }
        rs.close();
        stmt.close();
        conn.close();
        return feedbackList;
    }

    public List<Feedback> getFeedbackData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFeedbackData'");
    }
}
