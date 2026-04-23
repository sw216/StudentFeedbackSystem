package service;

import db.FeedbackDAO;
import model.Feedback;

import java.util.List;

public class FeedbackService implements FeedbackOperations {

    @Override
    public void submitFeedback(Feedback feedback) throws Exception {
        FeedbackDAO.saveFeedback(feedback);
    }

    @Override
    public List<Feedback> viewAllFeedback() throws Exception {
        return FeedbackDAO.getAllFeedback();
    }
}
