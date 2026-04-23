package thread;

import model.Feedback;
import service.FeedbackService;

import javax.swing.*;
import java.util.List;

public class SummaryThread extends Thread {
    private JTextArea summaryArea;
    private FeedbackService service;

    public SummaryThread(JTextArea summaryArea) {
        this.summaryArea = summaryArea;
        this.service = new FeedbackService();
    }

    @Override
    public void run() {
        try {
            summaryArea.setText("Generating summary...\nPlease wait...\n");
            Thread.sleep(2000); // simulate delay

            List<Feedback> feedbackList = service.viewAllFeedback();
            StringBuilder sb = new StringBuilder();
            for (Feedback fb : feedbackList) {
                sb.append("Teacher: ").append(fb.getTeacherName())
                  .append("\nFeedback: ").append(fb.getFeedbackText())
                  .append("\n\n");
            }

            summaryArea.setText(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            summaryArea.setText("Error generating summary.");
        }
    }
}
