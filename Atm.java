import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AtmApp extends JFrame {
    private static final int[] PIN = {6, 7, 7, 6};
    private static int balance = 1000000;
    private static List<String> transactionHistory = new ArrayList<>();
    private JTextField pinField;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public AtmApp() {
        setTitle("ATM Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        initWelcomeScreen();
        initMainMenu();
        add(cardPanel);
        setVisible(true);
    }

    private void initWelcomeScreen() {
        JPanel welcomePanel = new JPanel(new BorderLayout());
        welcomePanel.setBackground(Color.decode("#D0E0E3"));
        JLabel welcomeLabel = new JLabel("WELCOME TO KRM BANK", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomePanel.add(welcomeLabel, BorderLayout.NORTH);
        JLabel pinLabel = new JLabel("PLEASE ENTER YOUR PIN NUMBER:", SwingConstants.CENTER);
        pinLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        welcomePanel.add(pinLabel, BorderLayout.CENTER);
        pinField = new JTextField();
        welcomePanel.add(pinField, BorderLayout.SOUTH);
        JButton enterButton = new JButton("Enter");
        enterButton.setBackground(Color.decode("#007BFF"));
        enterButton.setForeground(Color.WHITE);
        enterButton.addActionListener(e -> verifyPin());
        welcomePanel.add(enterButton, BorderLayout.EAST);
        cardPanel.add(welcomePanel, "Welcome");
    }

    private void initMainMenu() {
        JPanel menuPanel = new JPanel(new GridLayout(5, 1));
        menuPanel.setBackground(Color.decode("#F8F9FA"));
        JButton addAmountButton = new JButton("Add Amount");
        addAmountButton.addActionListener(e -> addAmount());
        menuPanel.add(addAmountButton);
        JButton withdrawAmountButton = new JButton("Withdraw Amount");
        withdrawAmountButton.addActionListener(e -> withdrawAmount());
        menuPanel.add(withdrawAmountButton);
        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.addActionListener(e -> checkBalance());
        menuPanel.add(checkBalanceButton);
        JButton viewHistoryButton = new JButton("View Transaction History");
        viewHistoryButton.addActionListener(e -> viewTransactionHistory());
        menuPanel.add(viewHistoryButton);
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        menuPanel.add(exitButton);
        cardPanel.add(menuPanel, "MainMenu");
    }

    private void verifyPin() {
        try {
            int enteredPin = Integer.parseInt(pinField.getText());
            if (isPinCorrect(enteredPin)) {
                cardLayout.show(cardPanel, "MainMenu");
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect PIN. Try again.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "PIN must be a 4-digit number.");
        }
    }

    private boolean isPinCorrect(int enteredPin) {
        return enteredPin / 1000 == PIN[0] && (enteredPin / 100) % 10 == PIN[1] && (enteredPin / 10) % 10 == PIN[2] && enteredPin % 10 == PIN[3];
    }

    private void addAmount() {
        String input = JOptionPane.showInputDialog(this, "Enter amount to add:");
        try {
            int addAmount = Integer.parseInt(input);
            if (addAmount <= 0) {
                JOptionPane.showMessageDialog(this, "Amount must be positive.");
                return;
            }
            balance += addAmount;
            String transaction = String.format("Added: %d, New Balance: %d, Date: %s", addAmount, balance, new Date());
            transactionHistory.add(transaction);
            JOptionPane.showMessageDialog(this, "Successfully added: " + addAmount);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount.");
        }
    }

    private void withdrawAmount() {
        String input = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
        try {
            int withdrawal = Integer.parseInt(input);
            if (withdrawal <= balance) {
                balance -= withdrawal;
                String transaction = String.format("Withdrawn: %d, New Balance: %d, Date: %s", withdrawal, balance, new Date());
                transactionHistory.add(transaction);
                JOptionPane.showMessageDialog(this, "Successfully withdrawn: " + withdrawal);
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient balance.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount.");
        }
    }

    private void checkBalance() {
        JOptionPane.showMessageDialog(this, "Your total balance: " + balance);
    }

    private void viewTransactionHistory() {
        StringBuilder history = new StringBuilder("Transaction History:\n");
        if (transactionHistory.isEmpty()) {
            history.append("No transactions available.");
        } else {
            for (String transaction : transactionHistory) {
                history.append(transaction).append("\n");
            }
        }
        JOptionPane.showMessageDialog(this, history.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AtmApp::new);
    }
}
