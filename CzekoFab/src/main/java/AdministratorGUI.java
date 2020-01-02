import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdministratorGUI extends JFrame implements ActionListener {

    String[] rodzajeOsob = {"","Pracownik", "Kierownik", "Administrator"};

    JComboBox osobyCobo;
    JPasswordField haslo1,haslo2;
    JButton dodaj;
    JLabel infoAdd;



    AdministratorGUI(){
        setSize(850, 250);
        setTitle("CzekoFab: okno administratora");
        setLocation(300,500);
        this.getContentPane().setBackground(Color.red);
        setLayout(null);
        JLabel infoStartowe1 = new JLabel("Wybierz jaką nową osobę chcesz dodać: ");
        JLabel infoStartowe2 = new JLabel("Podaj nowe hasło: ");
        JLabel infoStartowe3 = new JLabel("Potwierdź hasło: ");
        infoStartowe1.setForeground(Color.BLACK);
        infoStartowe2.setForeground(Color.BLACK);
        infoStartowe3.setForeground(Color.BLACK);

        infoStartowe1.setBounds(10,10,350,30);
        add(infoStartowe1);
        infoStartowe2.setBounds(370,10,200,30);
        add(infoStartowe2);
        infoStartowe3.setBounds(580,10,200,30);
        add(infoStartowe3);


        osobyCobo = new JComboBox(rodzajeOsob);
        haslo1 = new JPasswordField();
        haslo2 = new JPasswordField();
        dodaj = new JButton("DODAJ");
        infoAdd = new JLabel();

        infoAdd.setForeground(Color.BLACK);
        infoAdd.setBounds(30, 150,500,30);
        add(infoAdd);

        osobyCobo.setBounds(30,50,200,30);
        add(osobyCobo);
        osobyCobo.setSelectedIndex(0);
        add(haslo1);
        haslo1.setBounds(380,50,190,30);
        add(haslo2);
        haslo2.setBounds(590,50,190,30);
        add(dodaj);
        dodaj.setBounds(325,120,150,30);
        dodaj.addActionListener(this);

    }

    public void uruchomAdministratorGUI(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == dodaj) {
            String msg1 = (String) osobyCobo.getSelectedItem();

            switch (msg1) {
                case "Pracownik":
                    if (!haslo1.getText().isEmpty() && !haslo2.getText().isEmpty()) {
                        if (haslo1.getText().equals(haslo2.getText())) {
                            StartGUI.hasla1.add(haslo1.getText());
                            PreparedStatement preparedStmt;
                            try {
                                preparedStmt = Main.myCon.prepareStatement("INSERT INTO uzytkownicy(uzytkownik,haslo)" + "VALUES (?,?)");
                                preparedStmt.setString (1, "pracownik");
                                preparedStmt.setString (2, haslo1.getText());
                                preparedStmt.execute();
                            } catch (SQLException ex) {
                                System.err.println("Got an exception!");
                            }

                            infoAdd.setText("Dodano nowego pracownika");
                            System.out.println("Dodano nowego pracownika, HASŁO: " + haslo1.getText());
                        }
                        else infoAdd.setText("Podane hasła się różnią :( .. ");
                    }
                    break;
                case "Kierownik":
                    if (!haslo1.getText().isEmpty() && !haslo2.getText().isEmpty()) {
                        if (haslo1.getText().equals(haslo2.getText())) {
                            StartGUI.hasla2.add(haslo1.getText());
                            PreparedStatement preparedStmt;
                            try {
                                preparedStmt = Main.myCon.prepareStatement("INSERT INTO uzytkownicy(uzytkownik,haslo)" + "VALUES (?,?)");
                                preparedStmt.setString (1, "kierownik");
                                preparedStmt.setString (2, haslo1.getText());
                                preparedStmt.execute();
                            } catch (SQLException ex) {
                                System.err.println("Got an exception!");
                            }
                            infoAdd.setText("Dodano nowego kierownika");
                            System.out.println("Dodano nowego kierownika, HASŁO: " + haslo1.getText());
                        }
                        else infoAdd.setText("Podane hasła się różnią :( .. ");
                    }
                    break;
                case "Administrator":
                    if (!haslo1.getText().isEmpty() && !haslo2.getText().isEmpty()) {
                        if (haslo1.getText().equals(haslo2.getText())) {
                            StartGUI.hasla3.add(haslo1.getText());
                            PreparedStatement preparedStmt;
                            try {
                                preparedStmt = Main.myCon.prepareStatement("INSERT INTO uzytkownicy(uzytkownik,haslo)" + "VALUES (?,?)");
                                preparedStmt.setString (1, "administrator");
                                preparedStmt.setString (2, haslo1.getText());
                                preparedStmt.execute();
                            } catch (SQLException ex) {
                                System.err.println("Got an exception!");
                            }
                            System.out.println("Dodano nowego administratora, HASŁO: " + haslo1.getText());
                            infoAdd.setText("Dodano nowego administratora");
                        }
                        else infoAdd.setText("Podane hasła się różnią :( .. ");
                    }
                    break;
                case "":
                    break;
                default:
                    System.out.println("upss, coś poszło nie tak");
            }
        }
    }
}
