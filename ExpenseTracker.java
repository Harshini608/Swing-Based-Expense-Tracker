import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ExpenseTracker extends JFrame {

    private JTextField titleField, amountField;
    private JTable table;
    private DefaultTableModel model;
    private JLabel totalLabel;
    private int total = 0;

    public ExpenseTracker() {

        setTitle("Basic Expense Tracker");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ---------- TOP PANEL ----------
        JPanel topPanel = new JPanel(new GridLayout(3, 2, 5, 5));

        topPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        topPanel.add(titleField);

        topPanel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        topPanel.add(amountField);

        JButton addBtn = new JButton("Add");
        JButton deleteBtn = new JButton("Delete");

        topPanel.add(addBtn);
        topPanel.add(deleteBtn);

        add(topPanel, BorderLayout.NORTH);

        // ---------- TABLE ----------
        model = new DefaultTableModel(new String[]{"Title", "Amount"}, 0);
        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        // ---------- BOTTOM PANEL ----------
        JPanel bottomPanel = new JPanel();
        totalLabel = new JLabel("Total: ₹0");
        bottomPanel.add(totalLabel);

        add(bottomPanel, BorderLayout.SOUTH);

        // Button actions
        addBtn.addActionListener(e -> addExpense());
        deleteBtn.addActionListener(e -> deleteExpense());
    }

    private void addExpense() {
        String title = titleField.getText();
        String amountText = amountField.getText();

        if (title.isEmpty() || amountText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter valid details");
            return;
        }

        try {
            int amount = Integer.parseInt(amountText);

            model.addRow(new Object[]{title, amount});
            total += amount;
            totalLabel.setText("Total: ₹" + total);

            titleField.setText("");
            amountField.setText("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Amount must be a number");
        }
    }

    private void deleteExpense() {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a row to delete");
            return;
        }

        int amount = (int) model.getValueAt(row, 1);
        total -= amount;

        model.removeRow(row);
        totalLabel.setText("Total: ₹" + total);
    }

    public static void main(String[] args) {
        new ExpenseTracker().setVisible(true);
    }
}

