package employeemanagementsystem;

import java.sql.SQLException;
import javax.swing.JOptionPane;

public class EmployeeEntryForm extends javax.swing.JFrame {
    public EmployeeEntryForm() {
        initComponents();
    }

    private void initComponents() {
        idLabel = new javax.swing.JLabel();
        idField = new javax.swing.JTextField();
        nameLabel = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        departmentLabel = new javax.swing.JLabel();
        departmentField = new javax.swing.JTextField();
        salaryLabel = new javax.swing.JLabel();
        salaryField = new javax.swing.JTextField();
        submitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Employee Entry Form");

        idLabel.setText("ID:");
        nameLabel.setText("Name:");
        departmentLabel.setText("Department:");
        salaryLabel.setText("Salary:");

        submitButton.setText("Submit");
        submitButton.addActionListener(evt -> submitButtonActionPerformed());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idLabel)
                    .addComponent(nameLabel)
                    .addComponent(departmentLabel)
                    .addComponent(salaryLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(idField)
                    .addComponent(nameField)
                    .addComponent(departmentField)
                    .addComponent(salaryField, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(submitButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idLabel)
                    .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(departmentLabel)
                    .addComponent(departmentField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salaryLabel)
                    .addComponent(salaryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(submitButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void submitButtonActionPerformed() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String department = departmentField.getText();
            double salary = Double.parseDouble(salaryField.getText());

            Employee employee = new Employee(id, name, department, salary);
            EmployeeDAO.addEmployee(employee);

            JOptionPane.showMessageDialog(this, "Employee added successfully!");
            clearFields();
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        departmentField.setText("");
        salaryField.setText("");
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new EmployeeEntryForm().setVisible(true));
    }

    // Variables declaration
    private javax.swing.JTextField idField;
    private javax.swing.JLabel idLabel;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField departmentField;
    private javax.swing.JLabel departmentLabel;
    private javax.swing.JTextField salaryField;
    private javax.swing.JLabel salaryLabel;
    private javax.swing.JButton submitButton;
}