package Ui;

import javax.swing.*;
import javax.swing.border.*;
import db.DBConnection; 
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collections;

public class FeedbackForm extends JFrame {
    private JButton submitButton;
    private JComboBox<String> facultyCombo;
    private JComboBox<String> subjectCombo;
    private ButtonGroup[] ratingGroups;

    public FeedbackForm(String username) {
        setTitle("Anonymous Teacher Feedback Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Heading
        JLabel headingLabel = new JLabel("Anonymous Feedback Form", SwingConstants.CENTER);
        headingLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        headingLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(headingLabel, BorderLayout.NORTH);

        // Main form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40)); 
        formPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        // Selection Panel (Faculty and Subject)
        JPanel selectionPanel = new JPanel(new GridLayout(2, 2));
        selectionPanel.setBackground(Color.WHITE);
        selectionPanel.setBorder(new EmptyBorder(10, 40, 20, 40));

        selectionPanel.add(createLabel("Select Faculty:"));
        facultyCombo = createComboBox(new String[]{"-- Select Faculty --", "Dr. Smith", "Prof. Johnson", "Dr. Williams"});
        selectionPanel.add(facultyCombo);

        selectionPanel.add(createLabel("Select Subject:"));
        subjectCombo = createComboBox(new String[]{"-- Select Subject --", "Mathematics", "Physics", "Computer Science"});
        selectionPanel.add(subjectCombo);

        formPanel.add(selectionPanel);

        // 10 option-based questions (Rating)
        String[] questionsOptionBased = {
                "Teacher explains the concepts clearly.",
                "Teacher encourages class participation.",
                "Teacher is punctual for the classes.",
                "Teacher’s communication skills are good.",
                "Teacher gives timely feedback on assessments.",
                "Teacher connects theory with practical examples.",
                "Teacher handles doubts and queries well.",
                "Class environment is interactive and healthy.",
                "Teacher motivates students for better performance.",
                "Overall satisfaction with the teacher."
        };

        ratingGroups = new ButtonGroup[questionsOptionBased.length];

        for (int i = 0; i < questionsOptionBased.length; i++) {
            JPanel questionPanel = new JPanel();
            questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS)); 
            questionPanel.setBackground(Color.WHITE);

            JLabel label = new JLabel((i + 1) + ". " + questionsOptionBased[i]);
            label.setFont(new Font("SansSerif", Font.PLAIN, 16));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            questionPanel.add(label);

            JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            optionPanel.setBackground(Color.WHITE);

            JRadioButton excellent = new JRadioButton("Excellent");
            JRadioButton good      = new JRadioButton("Good");
            JRadioButton average   = new JRadioButton("Average");
            JRadioButton poor      = new JRadioButton("Poor");

            // ADD THESE 4 LINES ↓
            excellent.setActionCommand("Excellent");
            good.setActionCommand("Good");
            average.setActionCommand("Average");
            poor.setActionCommand("Poor");

            ratingGroups[i] = new ButtonGroup();
            ratingGroups[i].add(excellent);
            ratingGroups[i].add(good);
            ratingGroups[i].add(average);
            ratingGroups[i].add(poor);

            optionPanel.add(excellent);
            optionPanel.add(good);
            optionPanel.add(average);
            optionPanel.add(poor);

            questionPanel.add(optionPanel);
            formPanel.add(questionPanel);
            formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        // 5 description-based questions
        String[] questionsDescription = {
                "What did you like the most about the teacher's teaching?",
                "What areas could the teacher improve?",
                "Any memorable experience you had with this teacher?",
                "Suggestions for improving the subject learning experience?",
                "Additional comments or feedback?"
        };

        for (int i = 0; i < questionsDescription.length; i++) {
            JPanel questionPanel = new JPanel();
            questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS)); 
            questionPanel.setBackground(Color.WHITE);

            JLabel label = new JLabel((i + 11) + ". " + questionsDescription[i]);
            label.setFont(new Font("SansSerif", Font.PLAIN, 16));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            questionPanel.add(label);

            JTextArea textArea = new JTextArea(4, 50);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
            textArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            questionPanel.add(textArea);
            formPanel.add(questionPanel);
            formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        }

        submitButton = new JButton("Submit Feedback");
        submitButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        submitButton.setBackground(new Color(30, 144, 255));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        submitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submitButton.setBackground(new Color(65, 105, 225));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                submitButton.setBackground(new Color(30, 144, 255));
            }
        });

        // Submit Action
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (facultyCombo.getSelectedIndex() <= 0 || subjectCombo.getSelectedIndex() <= 0) {
                    JOptionPane.showMessageDialog(null, "Please select both faculty and subject.",
                            "Incomplete Form", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Check all 10 ratings are selected
                for (int i = 0; i < ratingGroups.length; i++) {
                    if (ratingGroups[i].getSelection() == null) {
                        JOptionPane.showMessageDialog(null,
                                "Please answer question " + (i + 1) + ".",
                                "Incomplete Form", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }

                try {
                    Connection conn = DBConnection.getConnection();
                    String sql = "INSERT INTO feedback (studentId, teacherName, subject, " +
                            "rating1, rating2, rating3, rating4, rating5, " +
                            "rating6, rating7, rating8, rating9, rating10, " +
                            "comment1, comment2, comment3, comment4, comment5) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                    PreparedStatement stmt = conn.prepareStatement(sql);

                   
                    stmt.setString(1, username);
               
                    stmt.setString(2, (String) facultyCombo.getSelectedItem());
                  
                    stmt.setString(3, (String) subjectCombo.getSelectedItem());

                 
                    for (int i = 0; i < ratingGroups.length; i++) {
                        String selected = ratingGroups[i].getSelection().getActionCommand();
                        stmt.setString(i + 4, selected);
                    }

                    int commentIndex = 14;
                    for (Component c : formPanel.getComponents()) {
                        if (c instanceof JPanel) {
                            JPanel panel = (JPanel) c;
                            for (Component inner : panel.getComponents()) {
                                if (inner instanceof JTextArea) {
                                    stmt.setString(commentIndex++, ((JTextArea) inner).getText());
                                    break;
                                }
                            }
                        }
                        if (commentIndex > 18) break;
                    }

                    int rows = stmt.executeUpdate();
                    stmt.close();
                    conn.close();

                    if (rows > 0) {
                        JOptionPane.showMessageDialog(null,
                                "Thank you! Feedback submitted successfully.",
                                "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "Error: " + ex.getMessage(),
                            "Submission Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(submitButton);

        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.PLAIN, 16));
        return label;
    }

    private JComboBox<String> createComboBox(String[] options) {
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
        comboBox.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        return comboBox;
    }
}
