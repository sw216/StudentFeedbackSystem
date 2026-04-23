package Ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AdminDashboard extends JFrame {

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Sidebar Panel
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(38, 38, 38));
        sidebar.setPreferredSize(new Dimension(250, getHeight()));
        add(sidebar, BorderLayout.WEST);

        // Logo/Title
        JLabel logoLabel = new JLabel("Admin Dashboard", SwingConstants.CENTER);
        logoLabel.setFont(new Font("Poppins", Font.BOLD, 20));
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        sidebar.add(logoLabel);

        // Sidebar Menu Items
        String[] menuItems = {"Dashboard", "View Feedback", "Manage Users", "Settings"};
        for (String item : menuItems) {
            JButton button = createMenuButton(item);
            button.addActionListener(this::handleMenuAction);
            sidebar.add(button);
            sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        // Logout Button
        sidebar.add(Box.createVerticalGlue());
        JButton logoutButton = createMenuButton("Logout");
        logoutButton.setBackground(new Color(220, 53, 69));
        logoutButton.addActionListener(e -> {
            new LoginPage();
            dispose();
        });
        sidebar.add(logoutButton);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));

        // Main Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        add(contentPanel, BorderLayout.CENTER);

        // Dashboard Content
        showDashboardContent(contentPanel);

        setVisible(true);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Poppins", Font.PLAIN, 14));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(new Color(50, 50, 50));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setMaximumSize(new Dimension(220, 50));
        
        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 70, 70));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(50, 50, 50));
            }
        });
        
        return button;
    }

    private void handleMenuAction(ActionEvent e) {
        String command = e.getActionCommand();
        // Implement menu actions here
        JOptionPane.showMessageDialog(this, command + " selected");
    }

    private void showDashboardContent(JPanel contentPanel) {
        contentPanel.removeAll();
        
        // Header
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel headerLabel = new JLabel("Feedback Summary");
        headerLabel.setFont(new Font("Poppins", Font.BOLD, 22));
        headerLabel.setForeground(new Color(72, 61, 139));
        headerPanel.add(headerLabel);
        
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Stats Cards
        JPanel statsPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        statsPanel.setBackground(Color.WHITE);
        statsPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        
        String[] stats = {"Total Feedback", "Positive", "Needs Improvement"};
        String[] values = {"124", "89", "35"};
        Color[] colors = {new Color(30, 144, 255), new Color(40, 167, 69), new Color(255, 193, 7)};
        
        for (int i = 0; i < stats.length; i++) {
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setBackground(colors[i]);
            card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            JLabel statLabel = new JLabel(stats[i]);
            statLabel.setFont(new Font("Poppins", Font.BOLD, 16));
            statLabel.setForeground(Color.WHITE);
            statLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            JLabel valueLabel = new JLabel(values[i]);
            valueLabel.setFont(new Font("Poppins", Font.BOLD, 36));
            valueLabel.setForeground(Color.WHITE);
            valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            card.add(statLabel);
            card.add(Box.createRigidArea(new Dimension(0, 10)));
            card.add(valueLabel);
            
            statsPanel.add(card);
        }
        
        contentPanel.add(statsPanel, BorderLayout.CENTER);
        
        // Recent Feedback
        JPanel feedbackPanel = new JPanel();
        feedbackPanel.setLayout(new BoxLayout(feedbackPanel, BoxLayout.Y_AXIS));
        feedbackPanel.setBackground(Color.WHITE);
        feedbackPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        
        JLabel recentLabel = new JLabel("Recent Feedback");
        recentLabel.setFont(new Font("Poppins", Font.BOLD, 18));
        recentLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        feedbackPanel.add(recentLabel);
        feedbackPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // Sample feedback items
        String[] faculty = {"Dr. Smith", "Prof. Johnson", "Dr. Williams"};
        String[] feedback = {"Excellent teaching methods", "Could improve communication", "Very engaging lectures"};
        
        for (int i = 0; i < faculty.length; i++) {
            JPanel feedbackCard = new JPanel(new BorderLayout());
            feedbackCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
            feedbackCard.setBackground(Color.WHITE);
            feedbackCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
            
            JLabel facultyLabel = new JLabel(faculty[i]);
            facultyLabel.setFont(new Font("Poppins", Font.BOLD, 14));
            
            JTextArea feedbackText = new JTextArea(feedback[i]);
            feedbackText.setFont(new Font("Poppins", Font.PLAIN, 12));
            feedbackText.setEditable(false);
            feedbackText.setLineWrap(true);
            feedbackText.setBackground(Color.WHITE);
            
            feedbackCard.add(facultyLabel, BorderLayout.NORTH);
            feedbackCard.add(feedbackText, BorderLayout.CENTER);
            
            feedbackPanel.add(feedbackCard);
            feedbackPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        
        contentPanel.add(feedbackPanel, BorderLayout.SOUTH);
        
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdminDashboard();
        });
    }
}