import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeesForm extends JFrame {

    public EmployeesForm() {
        super("Получение работников с БД");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        String[] columnsHeader = new String[]{"ID", "ФИО",
                "Возраст", "IP-address", "Должность", "Зарплата"};
        JTable table = new JTable(getInfoFromDB(), columnsHeader);

        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(table));

        setContentPane(contents);
        setSize(800, 400);
        setVisible(true);
    }


    private String[][] getInfoFromDB() {
        String query = "SELECT * FROM employees JOIN department d on d.id = employees.department_id";
        String[][] array = null;
        try {
            int rowCounter=0;
            DBWorker worker = new DBWorker();
            Statement statement = worker.getConnection().createStatement();

            //Получение количества строк в запросе
            ResultSet rs = statement.executeQuery("SELECT count(*) FROM employees JOIN department d on d.id = employees.department_id");
            rs.next();
            int rowCount = rs.getInt(1);

            array = new String[rowCount][6];

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                // ID
                array[rowCounter][0] = String.valueOf(resultSet.getInt(1));
                // ФИО
                array[rowCounter][1] = resultSet.getString(2);
                // Возраст
                array[rowCounter][2] = String.valueOf(resultSet.getInt(3));
                // IP-address
                array[rowCounter][3] = resultSet.getString(4);
                // Должность
                array[rowCounter][4] = resultSet.getString(7);
                // Зарплата
                array[rowCounter][5] = resultSet.getString(8);

                rowCounter++;
            }

        } catch (SQLException e) {
            System.out.println("Не удалось получить доступ к БД!");
        }

        return array;
    }
}