import java.sql.*;

public class Main {
    public static StartGUI start;
    public static Statement myStmt;
    public static Connection myCon;
    public static void main(String[] args)
    {
        String url = "jdbc:mysql://localhost/czekofab?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String password = "dupa";
        try {
            myCon = DriverManager.getConnection(url,user,password);
            myStmt = myCon.createStatement();
           // String sql = "select * from czekofab.Produkty";
            //ResultSet rs = myStmt.executeQuery(sql);

           // while (rs.next()) System.out.println(rs.getString("typ") + ", " + (rs.getString("cena")));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        start = new StartGUI();
        start.uruchomStartGUI();
    }
}
