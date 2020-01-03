import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PracownikGUI extends JFrame implements ActionListener {

    String[] rodzajeGorzkiej = {"","50 %", "70 %", "90 %"};
    String[] rodzajeMlecznej = {"","18 %", "20 %", "25 %"};
    String[] rodzajeZ_dodatkami = {"","orzechy", "nadzienie", "bakalie"};
    JComboBox gorzkaCobo,mlecznaCobo,z_DodatkamiCobo;
    JTextField gorzkaText,mlecznaText,z_DodatkamiText,gorzkaNazwa,mlecznaNazwa,z_DodatkamiNazwa;
    JButton sprzedaj,gorzkaPrzegladaj, mlecznaPrzegladaj, z_dodatkamiPrzegladaj;
    JLabel gorzka,mleczna,z_dodatkami,ilosc,nazwa;


    PracownikGUI(){
        setSize(850, 500);
        setTitle("CzekoFab: okno pracownika");
        setLocation(300,300);
        this.getContentPane().setBackground(Color.blue);
        setLayout(null);
        JLabel infoStartowe1 = new JLabel("Wybierz jaki typ ile sztuk oraz nazwę czekolad, jakie chcesz sprzedać: ");
        JLabel infoStartowe2 = new JLabel("Wybierz jaki typ czekolad chcesz przeglądać: ");
        infoStartowe1.setForeground(Color.BLACK);
        infoStartowe2.setForeground(Color.BLACK);

        infoStartowe1.setBounds(10,10,500,30);
        add(infoStartowe1);
        infoStartowe2.setBounds(570,10,350,30);
        add(infoStartowe2);

        ilosc = new JLabel("ILOŚĆ");
        nazwa = new JLabel("NAZWA");
        gorzka = new JLabel("GORZKA");
        mleczna = new JLabel("MLECZNA");
        z_dodatkami = new JLabel("Z DODATKAMI");
        gorzkaCobo = new JComboBox(rodzajeGorzkiej);
        mlecznaCobo = new JComboBox(rodzajeMlecznej);
        z_DodatkamiCobo = new JComboBox(rodzajeZ_dodatkami);
        gorzkaText = new JTextField();
        mlecznaText = new JTextField();
        z_DodatkamiText = new JTextField();
        gorzkaNazwa = new JTextField();
        mlecznaNazwa = new JTextField();
        z_DodatkamiNazwa = new JTextField();
        sprzedaj = new JButton("SPRZEDAJ");
        gorzkaPrzegladaj = new JButton("GORZKA");
        mlecznaPrzegladaj = new JButton("MLECZNA");
        z_dodatkamiPrzegladaj = new JButton("Z DODATKAMI");

        ilosc.setForeground(Color.black);
        ilosc.setBounds(270,50,200,30);
        add(ilosc);
        nazwa.setForeground(Color.black);
        nazwa.setBounds(360,50,200,30);
        add(nazwa);

        gorzka.setForeground(Color.black);
        gorzka.setBounds(100,50,200,30);
        add(gorzka);
        gorzkaCobo.setBounds(50,90,200,30);
        add(gorzkaCobo);
        gorzkaCobo.setSelectedIndex(0);
        add(gorzkaText);
        gorzkaText.setBounds(270,90,50,30);
        add(gorzkaNazwa);
        gorzkaNazwa.setBounds(360,90,150,30);
        add(gorzkaPrzegladaj);
        gorzkaPrzegladaj.setBounds(590,90,150,30);
        gorzkaPrzegladaj.addActionListener(this);


        mleczna.setForeground(Color.black);
        mleczna.setBounds(100,130,200,30);
        add(mleczna);
        mlecznaCobo.setBounds(50,170,200,30);
        add(mlecznaCobo);
        mlecznaCobo.setSelectedIndex(0);
        add(mlecznaText);
        mlecznaText.setBounds(270,170,50,30);
        add(mlecznaNazwa);
        mlecznaNazwa.setBounds(360,170,150,30);
        add(mlecznaPrzegladaj);
        mlecznaPrzegladaj.setBounds(590,170,150,30);
        mlecznaPrzegladaj.addActionListener(this);

        z_dodatkami.setForeground(Color.black);
        z_dodatkami.setBounds(100,210,200,30);
        add(z_dodatkami);
        z_DodatkamiCobo.setBounds(50,250,200,30);
        add(z_DodatkamiCobo);
        z_DodatkamiCobo.setSelectedIndex(0);
        add(z_DodatkamiText);
        z_DodatkamiText.setBounds(270,250,50,30);
        add(z_DodatkamiNazwa);
        z_DodatkamiNazwa.setBounds(360,250,150,30);
        add(z_dodatkamiPrzegladaj);
        z_dodatkamiPrzegladaj.setBounds(590,250,150,30);
        z_dodatkamiPrzegladaj.addActionListener(this);

        add(sprzedaj);
        sprzedaj.setBounds(100, 350,200,30);
        sprzedaj.addActionListener(this);

    }

    public void uruchomPracownikGUI(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gorzkaPrzegladaj) {
            System.out.println("Chcesz przeglądać gorzką czekoladę");
            System.out.format("| %s | %s | %s | %s |%n","Producent: ", "Nazwa: ", "Zawartość kakao: ", "Cena: ");
            try {
                PreparedStatement preparedStmt1 = Main.myCon.prepareStatement("call wyswietlProdukt('Gorzka');");
                ResultSet rs = preparedStmt1.executeQuery();
                while (rs.next()){
                    System.out.format("| %s | %s | %s | %s |%n",rs.getString("producent"),rs.getString("nazwa"),
                            rs.getString("zawartosc_kakao"),rs.getString("cena"));
                    // System.out.println(rs.getString("nazwa") + " " + rs.getString("ilosc"));
                }

            } catch (SQLException ex) {
                System.err.println("Got an exception!");
            }
        } if (e.getSource() == mlecznaPrzegladaj) {
            System.out.println("Chcesz przeglądać mleczną czekoladę");
            System.out.format("| %s | %s | %s | %s |%n","Producent: ", "Nazwa: ", "Zawartość tłuszczu: ", "Cena: ");
            try {
                PreparedStatement preparedStmt2 = Main.myCon.prepareStatement("call wyswietlProdukt('Mleczna');");
                ResultSet rs = preparedStmt2.executeQuery();
                while (rs.next()){
                    System.out.format("| %s | %s | %s | %s |%n",rs.getString("producent"),rs.getString("nazwa"),
                            rs.getString("zawartosc_tluszczu"),rs.getString("cena"));
                    // System.out.println(rs.getString("nazwa") + " " + rs.getString("ilosc"));
                }

            } catch (SQLException ex) {
                System.err.println("Got an exception!");
            }
        } if (e.getSource() == z_dodatkamiPrzegladaj) {
            System.out.println("Chcesz przeglądać czekoladę z dodatkami");
            System.out.format("| %s | %s | %s | %s |%n","Producent: ", "Nazwa: ", "Dodatki: ", "Cena: ");
            try {
                PreparedStatement preparedStmt3 = Main.myCon.prepareStatement("call wyswietlProdukt('Z_dodatkami');");
                ResultSet rs = preparedStmt3.executeQuery();
                while (rs.next()){
                    System.out.format("| %s | %s | %s | %s |%n",rs.getString("producent"),rs.getString("nazwa"),
                            rs.getString("dodatki"),rs.getString("cena"));
                    // System.out.println(rs.getString("nazwa") + " " + rs.getString("ilosc"));
                }

            } catch (SQLException ex) {
                System.err.println("Got an exception!");
            }
        }
        if (e.getSource() == sprzedaj) {
            String msg1 = (String) gorzkaCobo.getSelectedItem();
            String msg2 = (String) mlecznaCobo.getSelectedItem();
            String msg3 = (String) z_DodatkamiCobo.getSelectedItem();
            switch (msg1) {
                case "50 %":
                    if (!gorzkaText.getText().isEmpty() && !gorzkaNazwa.getText().isEmpty()) {
                        System.out.println("Chcesz sprzedać " + gorzkaText.getText() + " gorzkich czekolad o 50% zawartości kakao, o nazwie: " + gorzkaNazwa.getText());
                        try {
                            PreparedStatement preparedStmt1 = Main.myCon.prepareStatement("call sprzedaj(?,?,?,?)");
                            preparedStmt1.setString (1, "gorzka");
                            preparedStmt1.setString (2, gorzkaText.getText());
                            preparedStmt1.setString (3, "50");
                            preparedStmt1.setString (4, gorzkaNazwa.getText());
                            preparedStmt1.executeQuery();
                           // ResultSet rs = preparedStmt1.executeQuery();
                            //if (rs.next()){
                              //  System.out.println(rs.getString("error"));
                           // }
                        } catch (SQLException ex) {
                            System.err.println("Got an exception!");
                        }
                    }
                    break;
                case "70 %":
                    if (!gorzkaText.getText().isEmpty() && !gorzkaNazwa.getText().isEmpty()) {
                        System.out.println("Chcesz sprzedać " + gorzkaText.getText() + " gorzkich czekolad o 70% zawartości kakao, o nazwie: " + gorzkaNazwa.getText());
                        try {
                            PreparedStatement preparedStmt1 = Main.myCon.prepareStatement("call sprzedaj(?,?,?,?)");
                            preparedStmt1.setString (1, "gorzka");
                            preparedStmt1.setString (2, gorzkaText.getText());
                            preparedStmt1.setString (3, "70");
                            preparedStmt1.setString (4, gorzkaNazwa.getText());
                            preparedStmt1.executeQuery();
                        } catch (SQLException ex) {
                            System.err.println("Got an exception!");
                        }
                    }
                    break;
                case "90 %":
                    if (!gorzkaText.getText().isEmpty() && !gorzkaNazwa.getText().isEmpty()) {
                        System.out.println("Chcesz sprzedać " + gorzkaText.getText() + " gorzkich czekolad o 90% zawartości kakao, o nazwie: " + gorzkaNazwa.getText());
                        try {
                            PreparedStatement preparedStmt1 = Main.myCon.prepareStatement("call sprzedaj(?,?,?,?)");
                            preparedStmt1.setString (1, "gorzka");
                            preparedStmt1.setString (2, gorzkaText.getText());
                            preparedStmt1.setString (3, "90");
                            preparedStmt1.setString (4, gorzkaNazwa.getText());
                            preparedStmt1.executeQuery();
                        } catch (SQLException ex) {
                            System.err.println("Got an exception!");
                        }
                    }
                    break;
                case "":
                    break;
                default:
                    System.out.println("upss, coś poszło nie tak");
            }
            switch (msg2) {
                case "18 %":
                    if (!mlecznaText.getText().isEmpty()  && !mlecznaNazwa.getText().isEmpty()) {
                        System.out.println("Chcesz sprzedać " + mlecznaText.getText() + " mlecznych czekolad o 18% zawartości tłuszczu, o nazwie: " + mlecznaNazwa.getText());
                        try {
                            PreparedStatement preparedStmt1 = Main.myCon.prepareStatement("call sprzedaj(?,?,?,?)");
                            preparedStmt1.setString (1, "mleczna");
                            preparedStmt1.setString (2, mlecznaText.getText());
                            preparedStmt1.setString (3, "18");
                            preparedStmt1.setString (4, mlecznaNazwa.getText());
                            preparedStmt1.executeQuery();
                        } catch (SQLException ex) {
                            System.err.println("Got an exception!");
                        }
                    }
                    break;
                case "20 %":
                    if (!mlecznaText.getText().isEmpty()  && !mlecznaNazwa.getText().isEmpty()){
                        System.out.println("Chcesz sprzedać " + mlecznaText.getText() + " mlecznych czekolad o 20% zawartości tłuszczu, o nazwie: " + mlecznaNazwa.getText());
                        try {
                            PreparedStatement preparedStmt1 = Main.myCon.prepareStatement("call sprzedaj(?,?,?,?)");
                            preparedStmt1.setString (1, "mleczna");
                            preparedStmt1.setString (2, mlecznaText.getText());
                            preparedStmt1.setString (3, "20");
                            preparedStmt1.setString (4, mlecznaNazwa.getText());
                            preparedStmt1.executeQuery();
                        } catch (SQLException ex) {
                            System.err.println("Got an exception!");
                        }
                    }
                    break;
                case "25 %":
                    if (!mlecznaText.getText().isEmpty()  && !mlecznaNazwa.getText().isEmpty()) {
                        System.out.println("Chcesz sprzedać " + mlecznaText.getText() + " mlecznych czekolad o 25% zawartości tłuszczu, o nazwie: " + mlecznaNazwa.getText());
                        try {
                            PreparedStatement preparedStmt1 = Main.myCon.prepareStatement("call sprzedaj(?,?,?,?)");
                            preparedStmt1.setString (1, "mleczna");
                            preparedStmt1.setString (2, mlecznaText.getText());
                            preparedStmt1.setString (3, "25");
                            preparedStmt1.setString (4, mlecznaNazwa.getText());
                            preparedStmt1.executeQuery();
                        } catch (SQLException ex) {
                            System.err.println("Got an exception!");
                        }
                    }
                    break;
                case "":
                    break;
                default:
                    System.out.println("upss, coś poszło nie tak");
            }
            switch (msg3) {
                case "orzechy":
                    if (!z_DodatkamiText.getText().isEmpty()  && !z_DodatkamiNazwa.getText().isEmpty()) {
                        System.out.println("Chcesz sprzedać " + z_DodatkamiText.getText() + " czekolad z orzechami, o nazwie: " + z_DodatkamiNazwa.getText());
                        try {
                            PreparedStatement preparedStmt1 = Main.myCon.prepareStatement("call sprzedaj(?,?,?,?)");
                            preparedStmt1.setString (1, "z_dodatkami");
                            preparedStmt1.setString (2, z_DodatkamiText.getText());
                            preparedStmt1.setString (3, "orzechy");
                            preparedStmt1.setString (4, z_DodatkamiNazwa.getText());
                            preparedStmt1.executeQuery();
                        } catch (SQLException ex) {
                            System.err.println("Got an exception!");
                        }
                    }
                    break;
                case "nadzienie":
                    if (!z_DodatkamiText.getText().isEmpty()  && !z_DodatkamiNazwa.getText().isEmpty()){
                        System.out.println("Chcesz sprzedać " + z_DodatkamiText.getText() + " czekolad z nadzieniem, o nazwie: " + z_DodatkamiNazwa.getText());
                        try {
                            PreparedStatement preparedStmt1 = Main.myCon.prepareStatement("call sprzedaj(?,?,?,?)");
                            preparedStmt1.setString (1, "z_dodatkami");
                            preparedStmt1.setString (2, z_DodatkamiText.getText());
                            preparedStmt1.setString (3, "nadzienie");
                            preparedStmt1.setString (4, z_DodatkamiNazwa.getText());
                            preparedStmt1.executeQuery();
                        } catch (SQLException ex) {
                            System.err.println("Got an exception!");
                        }
                    }
                    break;
                case "bakalie":
                    if (!z_DodatkamiText.getText().isEmpty()  && !z_DodatkamiNazwa.getText().isEmpty()) {
                        System.out.println("Chcesz sprzedać " + z_DodatkamiText.getText() + " czekolad z bakaliami, o nazwie: " + z_DodatkamiNazwa.getText());
                        try {
                            PreparedStatement preparedStmt1 = Main.myCon.prepareStatement("call sprzedaj(?,?,?,?)");
                            preparedStmt1.setString (1, "z_dodatkami");
                            preparedStmt1.setString (2, z_DodatkamiText.getText());
                            preparedStmt1.setString (3, "bakalie");
                            preparedStmt1.setString (4, z_DodatkamiNazwa.getText());
                            preparedStmt1.executeQuery();
                        } catch (SQLException ex) {
                            System.err.println("Got an exception!");
                        }
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
