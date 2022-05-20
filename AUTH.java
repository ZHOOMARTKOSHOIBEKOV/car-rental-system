package zhoma.company;
import com.opencsv.CSVWriter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
public class AUTH implements ActionListener {
    public static JLabel userlabel;
    public static JTextField userText;
    public static JLabel passwordLabel;
    public static JPasswordField passwordText;
    public static JButton button;
    public static JLabel success;
    public static void main(String[] args) {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        panel.setBackground(Color.red);
        frame.setTitle("Coursework AIN-3-21. Accounting for car sales");
        frame.setBounds(450, 150, 500, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);

        userlabel = new JLabel("Choose type of account");
        userlabel.setFont(new Font("stalker", Font.BOLD, 13));
        userlabel.setBounds(10, 20, 200, 25);
        panel.add(userlabel);

        userText = new JTextField(20);
        userText.setBounds(170, 20, 165, 25);
        panel.add(userText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("stalker", Font.BOLD, 13));
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(170, 50, 165, 25);
        panel.add(passwordText);

        button = new JButton("Enter");
        button.setBounds(210, 80, 80, 25);
        button.addActionListener(new AUTH());
        panel.add(button);

        success = new JLabel("");
        success.setBounds(10, 110, 500, 25);
        panel.add(success);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userText.getText();
        String password = passwordText.getText();
        if (user.equals("Director") && password.equals("director")) {
            Director.DirEnterToMenu();
            try {attendance(new String[]{"KOSHOYBEKOV ZHOOMART"});
            } catch (IOException ex) {
                ex.printStackTrace();}
        } else if (user.equals("Repairmen") && password.equals("repairmen")) {
            Repairmen.RepairmenEnterToMenu();
            try {attendance(new String[]{"DADAKEEV SANYCH"});
            } catch (IOException ex) {
                ex.printStackTrace();}
        } else if (user.equals("Sale manager") && password.equals("salesman")) {
            SaleManager.SaleManagerEnterToMenu();
            try {attendance(new String[]{"????????"});
            } catch (IOException ex) {
                ex.printStackTrace();}
        } else if (user.equals("Employee") && password.equals("employee")) {
            try {
                Employee.EmployeeEnterToMenu();
            } catch (IOException ex) {
                ex.printStackTrace();}
            try {attendance(new String[]{"????????"});
            } catch (IOException ex) {
                ex.printStackTrace();}
        } else {
            success.setText("Sorry, but we didn't find this type of account, please\t" +
                    "repeat.");
            success.setFont(new Font("stalker", Font.ITALIC, 14));}}
        public static void attendance(String [] user) throws IOException {
            String currentTime = String.valueOf(Calendar.getInstance().getTime());
            String csv = "/Users/user/IdeaProjects/CPR/src/main/java/zhoma/company/AUTH.java";
            CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
            String[] array = {Arrays.toString(user), currentTime};
            writer.writeNext(array);}}
