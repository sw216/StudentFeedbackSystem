package Ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLogin extends JFrame {
    
    private JTextField usernameField;
    private JPasswordField passwordField;

    public AdminLogin() {
        setTitle("Admin Login");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Main panel with gradient background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(70, 4, 56);
                Color color2 = new Color(120, 10, 100);
                GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());
        
        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));
        
        // Title
        JLabel titleLabel = new JLabel("Admin Portal", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Username field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Poppins", Font.PLAIN, 14));
        usernameField.setMaximumSize(new Dimension(300, 30));
        usernameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.WHITE, 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        
        // Password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Poppins", Font.PLAIN, 14));
        passwordField.setMaximumSize(new Dimension(300, 30));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.WHITE, 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        
        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Poppins", Font.BOLD, 16));
        loginButton.setBackground(new Color(30, 144, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Hover effect
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(65, 105, 225));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(30, 144, 255));
            }
        });
        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(AdminLogin.this, 
                        "Please enter both username and password", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if ("admin".equals(username) && "admin1234".equals(password)) {
                    new AdminDashboard();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(AdminLogin.this, 
                        "Invalid credentials. Please try again.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // Add components to form panel
        formPanel.add(Box.createVerticalGlue());
        formPanel.add(titleLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        formPanel.add(usernameLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        formPanel.add(usernameField); 
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(passwordLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        formPanel.add(passwordField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        formPanel.add(loginButton);
        formPanel.add(Box.createVerticalGlue());
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);
    }
}