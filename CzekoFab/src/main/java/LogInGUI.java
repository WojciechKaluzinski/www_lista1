import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LogInGUI extends JFrame implements ActionListener {
    JPanel logPanel;
    JLabel uzytkownik, haslo, infoLog;
    JTextField uzytkownikTekst;
    JPasswordField hasloTekst;
    JButton potwierdz;
    String textUzytkownik;
    ArrayList<String> hasla;

    LogInGUI(){
        // Username Label
        uzytkownik = new JLabel();
        uzytkownik.setText("Użytkownik :");
        uzytkownikTekst = new JTextField();

        // Password Label

        haslo = new JLabel();
        haslo.setText("Hasło :");
        hasloTekst = new JPasswordField();

        // Submit

        potwierdz = new JButton("POTWIERDŹ");

        logPanel = new JPanel(new GridLayout(3, 1));
        logPanel.setLocation(300,300);
        logPanel.add(uzytkownik);
        logPanel.add(uzytkownikTekst);
        logPanel.add(haslo);
        logPanel.add(hasloTekst);

        infoLog = new JLabel();
        logPanel.add(infoLog);
        logPanel.add(potwierdz);


        // Adding the listeners to components..
        potwierdz.addActionListener(this);
        add(logPanel, BorderLayout.CENTER);
        setTitle("Proszę się zalogować !");
        setSize(450,350);

    }

    void uruchomLogInGUI(String uzytkownik, ArrayList<String> haslo){
        textUzytkownik = uzytkownik;
        hasla = haslo;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userName = uzytkownikTekst.getText();
        String password = hasloTekst.getText();
        if (userName.trim().equals(textUzytkownik) && hasla.contains(password.trim())){
            if (textUzytkownik == "Pracownik") {
                PracownikGUI pracownik = new PracownikGUI();
                pracownik.uruchomPracownikGUI();
            } else if (textUzytkownik == "Kierownik") {
                KierownikGUI kierownik = new KierownikGUI();
                kierownik.uruchomKierownikaGUI();
                PracownikGUI pracownik = new PracownikGUI();
                pracownik.uruchomPracownikGUI();
            } else if (textUzytkownik == "Administrator") {
                AdministratorGUI administrator = new AdministratorGUI();
                administrator.uruchomAdministratorGUI();
                KierownikGUI kierownik = new KierownikGUI();
                kierownik.uruchomKierownikaGUI();
                PracownikGUI pracownik = new PracownikGUI();
                pracownik.uruchomPracownikGUI();
            }
            StartGUI.logIn.dispose();
            //Main.start.dispose();
        }
        else {
            infoLog.setText(" Błąd Logowania :( .. ");
        }
    }
}
