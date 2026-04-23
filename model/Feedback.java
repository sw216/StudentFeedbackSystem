package model;

public class Feedback {
    private String teacherName;
    private String feedbackText;

    public Feedback(String teacherName, String feedbackText) {
        this.teacherName = teacherName;
        this.feedbackText = feedbackText;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public String getFacultyName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFacultyName'");
    }

    public String getFeedbackCount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFeedbackCount'");
    }
}
