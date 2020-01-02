import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StartGUI extends JFrame implements ActionListener {
    public static LogInGUI logIn;
    public static ArrayList<String> hasla1, hasla2, hasla3;
    private enum Actions {
        PRACOWNIK,
        KIEROWNIK,
        ADMINISTRATOR
    }

    public StartGUI(){
        setSize(400, 230);
        setTitle("CzekoFab: okno startowe");
        setLocation(300,300);
        this.getContentPane().setBackground(Color.green);
        setLayout(null);
        JLabel infoStartowe = new JLabel("Wybierz z jakiego poziomu chcesz obsługiwać bazę: ");
        infoStartowe.setForeground(Color.BLACK);
        JButton pracownik = new JButton("PRACOWNIK");
        JButton kierownik = new JButton("KIEROWNIK");
        JButton administrator = new JButton("ADMINISTRATOR");

        infoStartowe.setBounds(10,10,350,30);
        add(infoStartowe);

        pracownik.setBounds(100,50,200,30);
        add(pracownik);
        pracownik.addActionListener(this);
        pracownik.setActionCommand(Actions.PRACOWNIK.name());


        kierownik.setBounds(100,90,200,30);
        add(kierownik);
        kierownik.addActionListener(this);
        kierownik.setActionCommand(Actions.KIEROWNIK.name());


        administrator.setBounds(100,130,200,30);
        add(administrator);
        administrator.addActionListener(this);
        administrator.setActionCommand(Actions.ADMINISTRATOR.name());

        // ------------------------- AKCJA DOTYCZĄCA HASEŁ -----------------------------
        hasla1 = new ArrayList<String>();
        hasla2 = new ArrayList<String>();
        hasla3 = new ArrayList<String>();
        hasla1.add("hasłoPracownik");
        hasla2.add("hasłoKierownik");
        hasla3.add("hasłoAdministrator");

        try {
            PreparedStatement preparedStmt = Main.myCon.prepareStatement("select * from uzytkownicy where uzytkownik = 'pracownik'");
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()){
                hasla1.add(rs.getString("haslo"));
            }

        } catch (SQLException ex) {
            System.err.println("Got an exception!");
        }

        try {
            PreparedStatement preparedStmt = Main.myCon.prepareStatement("select * from uzytkownicy where uzytkownik = 'kierownik'");
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()){
                hasla2.add(rs.getString("haslo"));
            }

        } catch (SQLException ex) {
            System.err.println("Got an exception!");
        }

        try {
            PreparedStatement preparedStmt = Main.myCon.prepareStatement("select * from uzytkownicy where uzytkownik = 'administrator'");
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()){
                hasla3.add(rs.getString("haslo"));
            }

        } catch (SQLException ex) {
            System.err.println("Got an exception!");
        }

    }
//------------------------------------------------------------------------------------------------------

    public void uruchomStartGUI(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       logIn = new LogInGUI();

        if (e.getActionCommand().equals(Actions.PRACOWNIK.name())) {
            logIn.uruchomLogInGUI("Pracownik", hasla1);
        } else if (e.getActionCommand().equals(Actions.KIEROWNIK.name())) {
            logIn.uruchomLogInGUI("Kierownik",hasla2);
        } else if (e.getActionCommand().equals(Actions.ADMINISTRATOR.name())) {
            logIn.uruchomLogInGUI("Administrator", hasla3);
        }

    }

}
