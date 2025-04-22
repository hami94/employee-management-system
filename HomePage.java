package employeemanagementsystem;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class HomePage extends javax.swing.JFrame {
    public HomePage() {
        initComponents();
        loadEmployeeData();
    }

    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        employeeTable = new javax.swing.JTable();
        refreshButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Employee Management System - Homepage");

        employeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Name", "Department", "Salary"}
        ));
        jScrollPane1.setViewportView(employeeTable);

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(evt -> refreshButtonActionPerformed());

        addButton.setText("Add Employee");
        addButton.addActionListener(evt -> addButtonActionPerformed());

        editButton.setText("Edit Employee");
        editButton.addActionListener(evt -> editButtonActionPerformed());

        deleteButton.setText("Delete Employee");
        deleteButton.addActionListener(evt -> deleteButtonActionPerformed());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(refreshButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(refreshButton)
                    .addComponent(addButton)
                    .addComponent(editButton)
                    .addComponent(deleteButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void refreshButtonActionPerformed() {
        loadEmployeeData();
    }

    private void addButtonActionPerformed() {
        String name = JOptionPane.showInputDialog(this, "Enter Employee Name:");
        String department = JOptionPane.showInputDialog(this, "Enter Department:");
        String salaryStr = JOptionPane.showInputDialog(this, "Enter Salary:");
        double salary = Double.parseDouble(salaryStr);

        try {
            EmployeeDAO.addEmployee(new Employee(0, name, department, salary));
            loadEmployeeData();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding employee: " + e.getMessage());
        }
    }

    private void editButtonActionPerformed() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an employee to edit.");
            return;
        }

        int id = (int) employeeTable.getValueAt(selectedRow, 0);
        String name = JOptionPane.showInputDialog(this, "Enter New Name:", employeeTable.getValueAt(selectedRow, 1));
        String department = JOptionPane.showInputDialog(this, "Enter New Department:", employeeTable.getValueAt(selectedRow, 2));
        String salaryStr = JOptionPane.showInputDialog(this, "Enter New Salary:", employeeTable.getValueAt(selectedRow, 3));
        double salary = Double.parseDouble(salaryStr);

        try {
            EmployeeDAO.updateEmployee(new Employee(id, name, department, salary));
            loadEmployeeData();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating employee: " + e.getMessage());
        }
    }

    private void deleteButtonActionPerformed() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an employee to delete.");
            return;
        }

        int id = (int) employeeTable.getValueAt(selectedRow, 0);
        try {
            EmployeeDAO.deleteEmployee(id);
            loadEmployeeData();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting employee: " + e.getMessage());
        }
    }

    private void loadEmployeeData() {
        try {
            List<Employee> employees = EmployeeDAO.getAllEmployees();
            DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
            model.setRowCount(0); // Clear existing data
            for (Employee emp : employees) {
                model.addRow(new Object[]{
                    emp.getId(),
                    emp.getName(),
                    emp.getDepartment(),
                    emp.getSalary()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new HomePage().setVisible(true));
    }

    // Variables declaration
    private javax.swing.JTable employeeTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton addButton;
    private javax.swing.JButton editButton;
    private javax.swing.JButton deleteButton;
}