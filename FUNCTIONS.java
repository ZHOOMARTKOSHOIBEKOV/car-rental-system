package zhoma.company;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.List;
import java.util.*;
class Director extends AUTH{
    public static ArrayList<Integer> listForChart = new ArrayList<>();
    public static void DirEnterToMenu(){
        JFrame Dir = new JFrame();
        JPanel layer = new JPanel();
        layer.setVisible(true);
        layer.setLayout(null);
        Dir.setTitle("Menu (Director)");
        layer.setBackground(new Color(183, 11, 83));
        Dir.setBounds(450, 175, 500, 480);
        Dir.setVisible(true);
        JButton employee_list = new JButton("Employee list");
        AUTH instance = new AUTH();
        employee_list.addActionListener(instance);
        employee_list.setBounds(190, 20, 120, 25);
        employee_list.addActionListener(e -> {
            try {
                Director.employeeList();
            } catch (IOException ex) {
                ex.printStackTrace();}});

        JButton time = new JButton("Time");
        time.setBounds(190, 50, 120, 25);
        time.addActionListener(instance);
        time.addActionListener(e -> {
            Director.time();
            try {
                Director.attendance();
            } catch (IOException ex) {
                ex.printStackTrace();}});

        JButton clients = new JButton("Clients");
        clients.addActionListener(instance);
        clients.setBounds(190, 80, 120, 25);
        clients.addActionListener(instance);
        clients.addActionListener(e -> {
            try {
                Director.clients();
            } catch (IOException ex) {
                ex.printStackTrace();}});

        JButton salary = new JButton("Salary");
        salary.addActionListener(instance);
        salary.setBounds(190, 140, 120, 25);
        salary.addActionListener(e -> {
            try {
                Director.salary();
            } catch (IOException ex) {
                ex.printStackTrace();}});
        JButton addEmployee = new JButton("Add employee");
        addEmployee.setBounds(190, 170, 120, 25);
        addEmployee.addActionListener(instance);
        addEmployee.addActionListener(e -> {
            try {
                Director.addEmployee();
            } catch (IOException ex) {
                ex.printStackTrace();}});
        JButton deleteEmployee = new JButton("Delete employee");
        deleteEmployee.setBounds(175, 200, 150, 25);
        deleteEmployee.addActionListener(instance);
        deleteEmployee.addActionListener(e -> {
            try {
                Director.deleteEmployee();
            } catch (IOException | CsvException ex) {
                ex.printStackTrace();}});
        JButton mySalary = new JButton("My salary");
        mySalary.setBounds(190, 170, 120, 25);
        mySalary.addActionListener(instance);
        mySalary.addActionListener(e -> {
            Manager.mySalary(1);
        });
        Dir.add(layer);
        layer.add(employee_list);
        layer.add(time);
        layer.add(clients);
        layer.add(salary);
        layer.add(addEmployee);
        layer.add(deleteEmployee);
        layer.add(mySalary);}

    public static void employeeList() throws IOException {
        String path = "/Users/user/IdeaProjects/CPR/infoclient.csv";
        String line;
        ArrayList <String> employeeList = new ArrayList <>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            employeeList.add(Arrays.toString(values));
            int number = 0;
            for (int i = 0; i < employeeList.size(); i++) {
                number = i;
            }System.out.println(number +" "+ (values[0]));}
    }

    public static void clients() throws IOException {
        clientsList("/Users/user/IdeaProjects/CPR/infoclient111.csv",0);
        clientsList("/Users/user/IdeaProjects/CPR/infoclient111.csv",1);
        clientsList("/Users/user/IdeaProjects/CPR/infoclient111.csv",2);
        clientsList("/Users/user/IdeaProjects/CPR/infoclient111.csv",3);
        clientsList("/Users/user/IdeaProjects/CPR/infoclient111.csv",4);
        writeNumberOfClientsToFile();
        FUNCTIONS demo = new FUNCTIONS("Clients");
        demo.setSize(660, 460);
        demo.setBounds(450,215,560,400);
        demo.setVisible(true);
    }

    public static void salary() throws IOException {
        String path = "/Users/user/IdeaProjects/CPR/salary.csv";
        String line;
        BufferedReader br = new BufferedReader(new FileReader(path));
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            System.out.println((values[0]));}}

    public static void addEmployee() throws IOException {
        System.out.println("Enter your name and position");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine().toUpperCase(Locale.ROOT);
        String csv = "/Users/user/IdeaProjects/CPR/addemployee.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
        String [] nameRecord =name.split(",");
        writer.writeNext(nameRecord);
        System.out.println("New employee added");
        writer.close();
    }

    public static void deleteEmployee() throws IOException, CsvException {
        System.out.println("Enter the line number");
        Scanner sc = new Scanner(System.in);
        int rowNumber = sc.nextInt();
        String path = "/Users/user/IdeaProjects/CPR/addemployee.csv";
        CSVReader reader2 = new CSVReader(new FileReader(path));
        List<String[]> allElements = reader2.readAll();
        allElements.remove(rowNumber);
        FileWriter sw = new FileWriter(path);
        CSVWriter writer = new CSVWriter(sw);
        writer.writeAll(allElements);
        System.out.println("Deletion completed");
        writer.close();
    }

    public static void time() {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ignored) {}
            JFrame frame = new JFrame("Real time");
            frame.setBounds(450, 200, 150, 30);
            frame.add(new MyPanel());
            frame.pack();
            frame.setVisible(true);});}

    public static void attendance() throws IOException {
        String path = "/Users/user/IdeaProjects/CPR/attendance.csv";
        String line;
        ArrayList <String> employeeList = new ArrayList <>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        while ((line = br.readLine()) != null) {
            String[] values = line.split(";");
            employeeList.add(Arrays.toString(values));
            int number = 0;
            for (int i = 0; i < employeeList.size(); i++) {
                number = i;
            }System.out.println(number +" "+ (values[0]));}}

    public static void clientsList(String path, int index) throws IOException {
        String line;
        ArrayList <String> list = new ArrayList <>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            list.add(Arrays.toString(values));
            int number = 0;
            for (int i = 0; i < list.size(); i++) {
                number = i;
            }System.out.println(number +" "+ (values[0]));
            listForChart.add(index, list.size());}}

    public static void writeNumberOfClientsToFile() throws IOException {
        String csv = "/Users/user/IdeaProjects/CPR/infoclient111.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
        writer.writeNext(new String[]{String.valueOf(listForChart)});
        writer.close();
    }

    public static class MyPanel extends JPanel {
        public final JLabel horloge;
        public MyPanel() {
            setLayout(new BorderLayout());
            horloge = new JLabel();
            horloge.setFont(
                    UIManager.getFont("Label.font").deriveFont(Font.BOLD, 14f));
            horloge.setText(
                    DateFormat.getDateTimeInstance().format(new Date()));
            add(horloge);
            Timer t = new Timer(500, e -> horloge.setText(DateFormat.getDateTimeInstance().format(new Date())));
            t.setRepeats(true);
            t.setCoalesce(true);
            t.setInitialDelay(0);
            t.start();}}}



class Repairmen extends AUTH{

    public static void RepairmenEnterToMenu(){
        JFrame Repairmen = new JFrame();
        JPanel layer = new JPanel();
        Repairmen.setTitle("Menu (Repairmen)");
        Repairmen.setBounds(450, 175, 500, 480);
        layer.setBackground(new Color(168, 13, 147));
        Repairmen.setVisible(true);
        JButton employee_list = new JButton("Employee list");
        AUTH instance = new AUTH();
        employee_list.addActionListener(instance);
        employee_list.setBounds(190, 20, 120, 25);
        employee_list.addActionListener(e -> {
            try {
                Director.employeeList();
            } catch (IOException ex) {
                ex.printStackTrace();}});


        JButton clients = new JButton("Clients");
        clients.addActionListener(instance);
        clients.setBounds(190, 80, 120, 25);
        clients.addActionListener(e -> {
            try {
                Director.clients();
            } catch (IOException ex) {
                ex.printStackTrace();}});

        JButton coverageArea = new JButton("Coverage area");
        coverageArea.setBounds(190, 110, 120, 25);
        coverageArea.addActionListener(instance);
        coverageArea.addActionListener(e -> {
            try {
                Director.clients();
            } catch (IOException ex) {
                ex.printStackTrace();}});

        JButton addCars = new JButton("Add cars");
        addCars.setBounds(190, 140, 120, 25);
        addCars.addActionListener(instance);
        addCars.addActionListener(e -> {
            try {
                Director.clients();
                chooseTheCar();
            } catch (IOException ex) {
                ex.printStackTrace();}});
        JButton mySalary = new JButton("My salary");
        mySalary.setBounds(190, 170, 120, 25);
        mySalary.addActionListener(instance);
        mySalary.addActionListener(e -> {
            try {
                mySalary(4);
            } catch (IOException ex) {
                ex.printStackTrace();}});
        JButton deleteCars = new JButton("Delete Cars");
        deleteCars.setBounds(190, 200, 120, 25);
        deleteCars.addActionListener(instance);
        deleteCars.addActionListener(e -> {
            try {
                chooseTheCarForDeletingClient();
            } catch (IOException | CsvException ex) {
                ex.printStackTrace();}});
        JButton setTask = new JButton("Set task");
        setTask.setBounds(190, 230, 120, 25);
        setTask.addActionListener(instance);
        setTask.addActionListener(e -> {
            try {
                chooseAccountForSettingTask();
            } catch (IOException ex) {
                ex.printStackTrace();}});
        Repairmen.add(layer);
        layer.add(employee_list);
        layer.add(clients);
        layer.add(addCars);
        layer.add(mySalary);
        layer.add(deleteCars);
        layer.add(setTask);}

    public static void chooseTheCar() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the Car: ");
        String computer = sc.nextLine().toUpperCase(Locale.ROOT);
        switch (computer) {
            case "Audi" -> addCar("/Users/user/IdeaProjects/CPR/List of computers.csv");
            case "BMV" -> addCar("/Users/user/IdeaProjects/CPR/List of computers.csv");
            case "Honda" -> addCar("/Users/user/IdeaProjects/CPR/List of computers.csv");
            case "Fit" -> addCar("/Users/user/IdeaProjects/CPR/List of computers.csv");
            case "OTHERS" -> addCar("/Users/user/IdeaProjects/CPR/List of computers.csv");
            default -> {
                System.out.println("Something go wrong.Try again");
                chooseTheCar();}}}

    public static void addCar(String path) throws IOException {
        System.out.println("Enter the name of car: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine().toUpperCase(Locale.ROOT);
        CSVWriter writer = new CSVWriter(new FileWriter(path, true));
        String [] nameRecord =name.split(",");
        writer.writeNext(nameRecord);
        System.out.println("New car added. If you want add something else, tab + ");
        String again= sc.nextLine();
        if(again.equals("+")){
            chooseTheCar();
        }else{
            return;}
        writer.close();}

    public static void mySalary(int number) throws IOException {
        String path = "/Users/user/IdeaProjects/CPR/salary.csv";
        String line = Files.readAllLines(Paths.get(path)).get(number);
        System.out.println(line);}

    public static void chooseTheCarForDeletingClient() throws IOException, CsvException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the car: ");
        String car = sc.nextLine().toUpperCase(Locale.ROOT);
        switch (car) {
            case "Honda" -> deleteCar("/Users/user/IdeaProjects/CPR/b.txt");
            case "Lexus" -> deleteCar("/Users/user/IdeaProjects/CPR/b.txt");
            case "Audi" -> deleteCar("/Users/user/IdeaProjects/CPR/b.txt");
            case "BMV" -> deleteCar("/Users/user/IdeaProjects/CPR/b.txt");
            case "OTHERS" -> deleteCar("/Users/user/IdeaProjects/CPR/b.txt");
            default -> {
                System.out.println("Something go wrong.Try again");
                chooseTheCarForDeletingClient();}}}

    public static void deleteCar(String path) throws IOException, CsvException {
        System.out.println("Enter the line number");
        Scanner sc = new Scanner(System.in);
        int rowNumber = sc.nextInt();
        CSVReader reader2 = new CSVReader(new FileReader(path));
        List<String[]> allElements = reader2.readAll();
        allElements.remove(rowNumber);
        FileWriter sw = new FileWriter(path);
        CSVWriter writer = new CSVWriter(sw);
        writer.writeAll(allElements);
        System.out.println("Deletion completed");
        writer.close();}

    public static void chooseAccountForSettingTask() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose the account: ");
        String city = sc.nextLine().toUpperCase(Locale.ROOT);
        switch (city) {
            case "EMPLOYEE" -> setTask("/Users/user/IdeaProjects/CPR/infoclient.csv");
            case "SALE MANAGER" -> setTask("/Users/user/IdeaProjects/CPR/src/main/java/zhoma/company/FUNCTIONS.java");}}

    public static void setTask(String csv) throws IOException {
        System.out.println(": ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
        String [] nameRecord =name.split("_");
        writer.writeNext(nameRecord);
        System.out.println("New task added");
        writer.close();}}

class SaleManager extends AUTH {

    public static void SaleManagerEnterToMenu()  {
        JFrame Sal = new JFrame();
        JPanel layer = new JPanel();
        Sal.setTitle("Menu (Sale manager)");
        Sal.setBounds(450, 175, 500, 480);
        layer.setBackground(new Color(182, 12, 159));
        Sal.setVisible(true);
        AUTH instance = new AUTH();
        JButton clientsList = new JButton("Clients list");
        clientsList.setBounds(190, 50, 120, 25);
        clientsList.addActionListener(instance);
        clientsList.addActionListener(e -> {
            try {
                SaleManager.clientslist();
            } catch (IOException ex) {
                ex.printStackTrace();}});

        JButton addingApartmentsForSale = new JButton("Add new cars for sale");
        addingApartmentsForSale.setBounds(150, 200, 200, 25);
        addingApartmentsForSale.addActionListener(instance);
        addingApartmentsForSale.addActionListener(e -> {
            try {
                SaleManager.addingApartmentsForSale();
            } catch (IOException ex) {
                ex.printStackTrace();}});
        JButton addingSoldApartments= new JButton("Add sold car");
        addingSoldApartments.setBounds(150, 230, 200, 25);
        addingSoldApartments.addActionListener(instance);
        addingSoldApartments.addActionListener(e -> {
            try {
                SaleManager.addingSoldApartments();
            } catch (IOException ex) {
                ex.printStackTrace();}});
        JButton deleteApartments= new JButton("Delete cars");
        deleteApartments.setBounds(150, 230, 200, 25);
        deleteApartments.addActionListener(instance);
        deleteApartments.addActionListener(e -> {
            try {
                SaleManager.deleteApartments();
            } catch (IOException | CsvException ex) {
                ex.printStackTrace();}});
        JButton mySalary = new JButton("My salary");
        mySalary.setBounds(190, 260, 120, 25);
        mySalary.addActionListener(instance);
        mySalary.addActionListener(e -> {
            Manager.mySalary(5);
        });
        JButton myTasks = new JButton("My tasks");
        myTasks.setBounds(190, 290, 120, 25);
        myTasks.addActionListener(instance);
        String path = "/Users/user/IdeaProjects/CPR/e.txt";
        myTasks.addActionListener(e -> {
            try {
                myTasks(path);
            } catch (IOException ex) {
                ex.printStackTrace();}});
        Sal.add(layer);
        layer.add(clientsList);
        layer.add(addingApartmentsForSale);
        layer.add(addingSoldApartments);
        layer.add(deleteApartments);
        layer.add(mySalary);
        layer.add(myTasks);}

    public static void clientslist() throws IOException {
        String sold_path = "/Users/user/IdeaProjects/CPR/listclients.txt";
        String line;
        BufferedReader br = new BufferedReader(new FileReader(sold_path));
        System.out.println("The entire clients list: ");
        String header = br.readLine();
        while ((line = br.readLine()) != null) {
            String[] str = line.split(",");
            System.out.println(str[0]);}}

    public static void addingApartmentsForSale() throws IOException {
        System.out.println("Enter new available cars for sale: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine().toUpperCase(Locale.ROOT);
        String csv = "/Users/user/IdeaProjects/CPR/saleapartment.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
        String [] nameRecord =name.split(",");
        writer.writeNext(nameRecord);
        System.out.println("New cars added");
        writer.close();}

    public static void addingSoldApartments() throws IOException {
        System.out.println("Enter sold car: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine().toUpperCase(Locale.ROOT);
        String csv = "/Users/user/IdeaProjects/CPR/sold-cars.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
        String [] nameRecord =name.split(",");
        writer.writeNext(nameRecord);
        System.out.println("Sold car added");
        writer.close();}

    public static void deleteApartments() throws IOException, CsvException {
        System.out.println("Enter the line number");
        Scanner sc = new Scanner(System.in);
        int rowNumber = sc.nextInt();
        String path = "/Users/user/IdeaProjects/CPR/c.txt";
        CSVReader reader2 = new CSVReader(new FileReader(path));
        List<String[]> allElements = reader2.readAll();
        allElements.remove(rowNumber);
        FileWriter sw = new FileWriter(path);
        CSVWriter writer = new CSVWriter(sw);
        writer.writeAll(allElements);
        System.out.println("Deletion completed");
        writer.close();}

    public static void myTasks(String path) throws IOException {
        String line;
        ArrayList <String> list = new ArrayList <>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            list.add(Arrays.toString(values));
            System.out.println((values[0]));}
        Scanner sc = new Scanner(System.in);
        System.out.println("If you finished this tasks, enter 'done'. Else - click enter ");
        String done = sc.nextLine();
        if(done.equals("done")) {
            try {
                FileWriter fstream1 = new FileWriter(path);
                BufferedWriter out1 = new BufferedWriter(fstream1);
                out1.write("");
                out1.close();
                System.out.println("All tasks completed.Good job!");
            } catch (Exception e) {
                System.err.println("Error in file cleaning: " + e.getMessage());
            }
        }else if(done.equals("")){
            System.out.println("");
        }else{
            myTasks(path);}}}

class Employee extends AUTH {

    public static void EmployeeEnterToMenu() throws IOException {
        JFrame Wor = new JFrame();
        JPanel layer = new JPanel();
        Wor.setTitle("Menu (Employee)");
        Wor.setBounds(450, 175, 500, 480);
        layer.setBackground(new Color(149, 9, 164));
        Wor.setVisible(true);
        AUTH instance = new AUTH();

        JButton mySalary = new JButton("My salary");
        mySalary.setBounds(190, 170, 120, 25);
        mySalary.addActionListener(instance);
        mySalary.addActionListener(e -> {
            try {
                SaleManager.myTasks("/Users/user/IdeaProjects/CPR/salary.txt");
            } catch (IOException ex) {
                ex.printStackTrace();}});

        JButton myTasks = new JButton("My tasks");
        myTasks.setBounds(190, 200, 120, 25);
        myTasks.addActionListener(instance);
        myTasks.addActionListener(e -> {
            try {
                SaleManager.myTasks("/Users/user/IdeaProjects/CPR/a.txt");
            } catch (IOException ex) {
                ex.printStackTrace();}});
        Wor.add(layer);
        layer.add(mySalary);
        layer.add(myTasks);}}

class FUNCTIONS extends ApplicationFrame {

    public FUNCTIONS(String title) throws IOException {
        super(title);
        setContentPane(createDemoPanel());}

    public static PieDataset createDataset() {
        HashMap<String, Integer> hashmap = new HashMap<>();
        hashmap.put("BMV", Director.listForChart.get(0));
        hashmap.put("AUDI", Director.listForChart.get(1));
        hashmap.put("HYUNDAY", Director.listForChart.get(2));
        hashmap.put("LEXUS", Director.listForChart.get(3));
        hashmap.put("ELSE", Director.listForChart.get(4));
        int bish = hashmap.get("BMV");
        int talas = hashmap.get("AUDI");
        int karabalta = hashmap.get("HYUNDAY");
        int tokmok = hashmap.get("LEXUS");
        int others = hashmap.get("ELSE");
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("BMV", (bish));
        dataset.setValue("AUDI", (karabalta));
        dataset.setValue("HYUNDAY", (talas));
        dataset.setValue("LEXUS", (tokmok));
        dataset.setValue("Others", (others));
        return dataset;}

    public static JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart("Coverage area", dataset, true, true, false);
        return chart;}

    public static JPanel createDemoPanel() throws IOException {
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);}}

