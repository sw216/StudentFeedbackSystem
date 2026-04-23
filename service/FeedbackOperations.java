package service;

import model.Feedback;
import java.util.List;

public interface FeedbackOperations {
    void submitFeedback(Feedback feedback) throws Exception;
    List<Feedback> viewAllFeedback() throws Exception;
}
