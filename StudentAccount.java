import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentAccount extends JFrame {
    private JTable accountTable;
    private DefaultTableModel tableModel;
    private List<StudentEmailAccount> accountList;

    public StudentAccount() {
        accountList = new ArrayList<>();
        setTitle("Account Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

       
        tableModel = new DefaultTableModel(new Object[]{"VM No", "Student Name", "Official College Gmail"}, 0);
        accountTable = new JTable(tableModel);

      
        accountTable.setBackground(new Color(230, 230, 250)); // Light lavender color
        accountTable.setGridColor(Color.GRAY); // Set the grid color

        JScrollPane scrollPane = new JScrollPane(accountTable);
        add(scrollPane, "Center");

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Account");
        JButton removeButton = new JButton("Remove Account");
        JButton viewButton = new JButton("View Details");

       
        buttonPanel.setBackground(new Color(176, 196, 222)); // Light steel blue
        addButton.setBackground(Color.LIGHT_GRAY);
        removeButton.setBackground(Color.LIGHT_GRAY);
        viewButton.setBackground(Color.LIGHT_GRAY);

   
        addButton.addActionListener(e -> addNewAccount());
        removeButton.addActionListener(e -> removeSelectedAccount());
        viewButton.addActionListener(e -> viewAccountDetails());

     
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(viewButton);

    
        add(buttonPanel, "South");
    }

    private void addNewAccount() {
        JTextField vmNoField = new JTextField();
        JTextField studentNameField = new JTextField();
        JTextField collegeGmailField = new JTextField();
        Object[] fields = {
                "VM No (5 digits):", vmNoField,
                "Student Name:", studentNameField,
                "Official College Gmail:", collegeGmailField
        };

        int option = JOptionPane.showConfirmDialog(null, fields, "Add New Account", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String vmNo = vmNoField.getText().trim();
            String studentName = studentNameField.getText().trim();
            String collegeGmail = collegeGmailField.getText().trim();

            if (vmNo.matches("\\d{5}")) {
                StudentEmailAccount account = new StudentEmailAccount(vmNo, studentName, collegeGmail);
                accountList.add(account);
                tableModel.addRow(new Object[]{account.getVmNo(), account.getStudentName(), account.getCollegeGmail()});
            } else {
                JOptionPane.showMessageDialog(null, "VM No must be exactly 5 digits.", "Invalid VM No", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void removeSelectedAccount() {
        int selectedRow = accountTable.getSelectedRow();
        if (selectedRow != -1) {
            accountList.remove(selectedRow);
            tableModel.removeRow(selectedRow);
        }
    }

    private void viewAccountDetails() {
        int selectedRow = accountTable.getSelectedRow();
        if (selectedRow != -1) {
            StudentEmailAccount account = accountList.get(selectedRow);
            JOptionPane.showMessageDialog(null, "VM No: " + account.getVmNo() + "\nStudent Name: " + account.getStudentName() + "\nOfficial College Gmail: " + account.getCollegeGmail());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentAccount().setVisible(true));
    }

    
    class StudentEmailAccount {
        private String vmNo;
        private String studentName;
        private String collegeGmail;

        public StudentEmailAccount(String vmNo, String studentName, String collegeGmail) {
            this.vmNo = vmNo;
            this.studentName = studentName;
            this.collegeGmail = collegeGmail;
        }

        public String getVmNo() {
            return vmNo;
        }

        public String getStudentName() {
            return studentName;
        }

        public String getCollegeGmail() {
            return collegeGmail;
        }
    }
}
