import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class KierownikGUI extends JFrame implements ActionListener {

    String[] rodzajeGorzkiej = {"","50 %", "70 %", "90 %"};
    String[] rodzajeMlecznej = {"","18 %", "20 %", "25 %"};
    String[] rodzajeZ_dodatkami = {"","orzechy", "nadzienie", "bakalie"};
    JComboBox gorzkaCobo,mlecznaCobo,z_DodatkamiCobo;
    JTextField gorzkaText,mlecznaText,z_DodatkamiText,gorzkaCena,mlecznaCena,z_dodatkamiCena,gorzkaData,mlecznaData,z_DodatkamiData,
    gorzkaNazwa,mlecznaNazwa,z_DodatkamiNazwa,gorzkaProducent,mlecznaProducent,z_DodatkamiProducent;
    JButton dodaj,przegladajMagazyn,pokazPrawiePrzeterminowane,podsumowanieMiesieczne, usunPrzeterminowane;
    JLabel gorzka,mleczna,z_dodatkami,ilosc,cena,data,nazwa,producent,lista;
    JScrollPane scrollableLista;


    KierownikGUI(){
        setSize(1300, 500);
        setTitle("CzekoFab: okno kierownika");
        setLocation(100,100);
        this.getContentPane().setBackground(Color.yellow);
        setLayout(null);
        JLabel infoStartowe1 = new JLabel("Wybierz jaki typ, ile sztuk, cenę, datę ważności, nazwę oraz producenta czekolady, którą chcesz dodać: ");
        JLabel infoStartowe2 = new JLabel("Dodatkowe akcje kierownika: ");
        infoStartowe1.setForeground(Color.BLACK);
        infoStartowe2.setForeground(Color.BLACK);

        infoStartowe1.setBounds(10,10,900,30);
        add(infoStartowe1);
        infoStartowe2.setBounds(930,10,350,30);
        add(infoStartowe2);

        gorzka = new JLabel("GORZKA");
        mleczna = new JLabel("MLECZNA");
        z_dodatkami = new JLabel("Z DODATKAMI");
        ilosc = new JLabel("ILOŚĆ");
        cena = new JLabel("CENA");
        data = new JLabel("DATA WAŻNOŚCI");
        nazwa = new JLabel("NAZWA");
        producent = new JLabel("PRODUCENT");
        gorzkaCobo = new JComboBox(rodzajeGorzkiej);
        mlecznaCobo = new JComboBox(rodzajeMlecznej);
        z_DodatkamiCobo = new JComboBox(rodzajeZ_dodatkami);
        gorzkaText = new JTextField();
        mlecznaText = new JTextField();
        z_DodatkamiText = new JTextField();
        gorzkaCena = new JTextField();
        mlecznaCena = new JTextField();
        z_dodatkamiCena = new JTextField();
        gorzkaData = new JTextField();
        mlecznaData = new JTextField();
        z_DodatkamiData = new JTextField();
        gorzkaNazwa = new JTextField();
        mlecznaNazwa = new JTextField();
        z_DodatkamiNazwa = new JTextField();
        gorzkaProducent = new JTextField();
        mlecznaProducent = new JTextField();
        z_DodatkamiProducent = new JTextField();
        dodaj = new JButton("DODAJ");
        przegladajMagazyn = new JButton("PREGLĄDAJ MAGAZYN");
        pokazPrawiePrzeterminowane = new JButton("O KRÓTKIM TERMINIE WAŻNOŚCI");
        podsumowanieMiesieczne = new JButton("PODSUMOWANIE MIESIĘCZNE");
        usunPrzeterminowane = new JButton("USUŃ PRZETERMINOWANE");
        lista = new JLabel();
        scrollableLista = new JScrollPane(lista);


        ilosc.setForeground(Color.black);
        ilosc.setBounds(250,50,200,30);
        add(ilosc);
        cena.setForeground(Color.black);
        cena.setBounds(320,50,200,30);
        add(cena);
        data.setForeground(Color.black);
        data.setBounds(420,50,200,30);
        add(data);
        nazwa.setForeground(Color.black);
        nazwa.setBounds(570,50,200,30);
        add(nazwa);
        producent.setForeground(Color.black);
        producent.setBounds(720,50,200,30);
        add(producent);

        gorzka.setForeground(Color.black);
        gorzka.setBounds(100,50,200,30);
        add(gorzka);
        gorzkaCobo.setBounds(30,90,200,30);
        add(gorzkaCobo);
        gorzkaCobo.setSelectedIndex(0);
        add(gorzkaText);
        gorzkaText.setBounds(250,90,50,30);
        add(gorzkaCena);
        gorzkaCena.setBounds(320,90,50,30);
        add(gorzkaData);
        gorzkaData.setBounds(420,90,100,30);
        add(gorzkaNazwa);
        gorzkaNazwa.setBounds(570,90,100,30);
        add(gorzkaProducent);
        gorzkaProducent.setBounds(720,90,100,30);
        add(przegladajMagazyn);
        przegladajMagazyn.setBounds(930,90,250,30);
        przegladajMagazyn.addActionListener(this);


        mleczna.setForeground(Color.black);
        mleczna.setBounds(100,130,200,30);
        add(mleczna);
        mlecznaCobo.setBounds(30,170,200,30);
        add(mlecznaCobo);
        mlecznaCobo.setSelectedIndex(0);
        add(mlecznaText);
        mlecznaText.setBounds(250,170,50,30);
        add(mlecznaCena);
        mlecznaCena.setBounds(320,170,50,30);
        add(mlecznaData);
        mlecznaData.setBounds(420,170,100,30);
        add(mlecznaNazwa);
        mlecznaNazwa.setBounds(570,170,100,30);
        add(mlecznaProducent);
        mlecznaProducent.setBounds(720,170,100,30);
        add(pokazPrawiePrzeterminowane);
        pokazPrawiePrzeterminowane.setBounds(930,170,250,30);
        pokazPrawiePrzeterminowane.addActionListener(this);

        z_dodatkami.setForeground(Color.black);
        z_dodatkami.setBounds(100,210,200,30);
        add(z_dodatkami);
        z_DodatkamiCobo.setBounds(30,250,200,30);
        add(z_DodatkamiCobo);
        z_DodatkamiCobo.setSelectedIndex(0);
        add(z_DodatkamiText);
        z_DodatkamiText.setBounds(250,250,50,30);
        add(z_dodatkamiCena);
        z_dodatkamiCena.setBounds(320,250,50,30);
        add(z_DodatkamiData);
        z_DodatkamiData.setBounds(420,250,100,30);
        add(z_DodatkamiNazwa);
        z_DodatkamiNazwa.setBounds(570,250,100,30);
        add(z_DodatkamiProducent);
        z_DodatkamiProducent.setBounds(720,250,100,30);
        add(podsumowanieMiesieczne);
        podsumowanieMiesieczne.setBounds(930,250,250,30);
        podsumowanieMiesieczne.addActionListener(this);

        add(dodaj);
        dodaj.setBounds(100, 350,200,30);
        dodaj.addActionListener(this);

        add(usunPrzeterminowane);
        usunPrzeterminowane.setBounds(930, 330,250,30);
        usunPrzeterminowane.addActionListener(this);

        /*add(lista);
        lista.setBounds(350, 300, 800,250);
        lista.add(scrollableLista,BorderLayout.CENTER);*/

    }

    public void uruchomKierownikaGUI(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
          if (e.getSource() == przegladajMagazyn) {

              System.out.println("Chcesz przeglądać stan magazynu: ");
              System.out.format("| %s | %s | %s | %s | %s |%n","Producent: ","Nazwa: ","Typ: ", "Specyfikacja: ", "Ilość: ");
              try {
                  PreparedStatement preparedStmt3 = Main.myCon.prepareStatement("call  przegladajMagazyn()");
                  ResultSet rs = preparedStmt3.executeQuery();
                  while (rs.next()){
                      System.out.format("| %s | %s | %s | %s | %s |%n",rs.getString("producent"),rs.getString("nazwa"),
                              rs.getString("typ"),rs.getString("specyfikacja"),rs.getString("ilosc"));
                     // System.out.println(rs.getString("nazwa") + " " + rs.getString("ilosc"));
                  }

              } catch (SQLException ex) {
                  System.err.println("Got an exception!");
              }
        } if (e.getSource() == pokazPrawiePrzeterminowane) {
            System.out.println("Chcesz przeglądać produkt o krótkiej dacie przydatności: ");
            System.out.format("| %s | %s | %s | %s |%n","ID: ","Typ: ","Przydatność: ","Cena: ");
            try {
                PreparedStatement preparedStmt4 = Main.myCon.prepareStatement("call  pokazPrawiePrzeterminowane()");
                ResultSet rs = preparedStmt4.executeQuery();
                while (rs.next()){
                    System.out.format("| %s | %s | %s | %s |%n",rs.getString("id"),rs.getString("typ"),rs.getString("termin_przydatnosci") ,rs.getString("cena"));
                    //System.out.println(rs.getString("id") + " " + rs.getString("typ") + " " + rs.getString("termin_przydatnosci") + " " + rs.getString("cena"));
                }
            } catch (SQLException ex) {
                System.err.println("Got an exception!");
            }
        } if (e.getSource() == podsumowanieMiesieczne) {
            System.out.println("Chcesz zrobić podsumowanie miesięczne");
            try {
                PreparedStatement preparedStmt5 = Main.myCon.prepareStatement("call bilans()");
                ResultSet rs = preparedStmt5.executeQuery();
                while (rs.next()){
                    System.out.println("Twój zysk w tym miesiącu to: " + rs.getString("bilans") + " zł.");
                }
            } catch (SQLException ex) {
                System.err.println("Got an exception!");
            }
        } if (e.getSource() == usunPrzeterminowane) {
            System.out.println("Chcesz usunąć przeterminowane czekolady");
            try {
                PreparedStatement preparedStmt5 = Main.myCon.prepareStatement("call  usunPrzeterminowane()");
                preparedStmt5.executeQuery();
            } catch (SQLException ex) {
                System.err.println("Got an exception!");
            }
        }
        if (e.getSource() == dodaj) {
            String msg1 = (String) gorzkaCobo.getSelectedItem();
            String msg2 = (String) mlecznaCobo.getSelectedItem();
            String msg3 = (String) z_DodatkamiCobo.getSelectedItem();
            switch (msg1) {
                case "50 %":
                    if (!gorzkaText.getText().isEmpty() && !gorzkaCena.getText().isEmpty() && !gorzkaData.getText().isEmpty() && !gorzkaNazwa.getText().isEmpty() && !gorzkaProducent.getText().isEmpty()) {
                        try {
                            PreparedStatement preparedStmt = Main.myCon.prepareStatement("call przyjmijDostawe(?,?,?,?,?,?,?)");
                            preparedStmt.setString (1, "Gorzka");
                            preparedStmt.setString (2, gorzkaText.getText());
                            preparedStmt.setString (3, "50");
                            preparedStmt.setString (4, gorzkaCena.getText());
                            preparedStmt.setString (5, gorzkaData.getText());
                            preparedStmt.setString (6, gorzkaNazwa.getText());
                            preparedStmt.setString (7, gorzkaProducent.getText());
                            preparedStmt.executeQuery();
                        } catch (SQLException ex) {
                            System.err.println("Got an exception!");
                        }
                        System.out.println("Dodałeś " + gorzkaText.getText() + " gorzkich czekolad o 50% zawartości kakao za: " + gorzkaCena.getText() + " zł" +
                                " ważnych do: " + gorzkaData.getText() + " o nazwie: " + gorzkaNazwa.getText() + " producenta: " + gorzkaProducent.getText());
                    }
                    break;
                case "70 %":
                    if (!gorzkaText.getText().isEmpty() && !gorzkaCena.getText().isEmpty() && !gorzkaData.getText().isEmpty() && !gorzkaNazwa.getText().isEmpty() && !gorzkaProducent.getText().isEmpty()) {
                        try {
                            PreparedStatement preparedStmt = Main.myCon.prepareStatement("call przyjmijDostawe(?,?,?,?,?,?,?)");
                            preparedStmt.setString (1, "Gorzka");
                            preparedStmt.setString (2, gorzkaText.getText());
                            preparedStmt.setString (3, "70");
                            preparedStmt.setString (4, gorzkaCena.getText());
                            preparedStmt.setString (5, gorzkaData.getText());
                            preparedStmt.setString (6, gorzkaNazwa.getText());
                            preparedStmt.setString (7, gorzkaProducent.getText());
                            preparedStmt.executeQuery();
                        } catch (SQLException ex) {
                            System.err.println("Got an exception!");
                        }
                        System.out.println("Dodałeś " + gorzkaText.getText() + " gorzkich czekolad o 70% zawartości kakao za: " + gorzkaCena.getText() + " zł" +
                                " ważnych do: " + gorzkaData.getText() + " o nazwie: " + gorzkaNazwa.getText() + " producenta: " + gorzkaProducent.getText());
                    }
                    break;
                case "90 %":
                    if (!gorzkaText.getText().isEmpty() && !gorzkaCena.getText().isEmpty() && !gorzkaData.getText().isEmpty() && !gorzkaNazwa.getText().isEmpty() && !gorzkaProducent.getText().isEmpty()) {
                        try {
                            PreparedStatement preparedStmt = Main.myCon.prepareStatement("call przyjmijDostawe(?,?,?,?,?,?,?)");
                            preparedStmt.setString (1, "Gorzka");
                            preparedStmt.setString (2, gorzkaText.getText());
                            preparedStmt.setString (3, "90");
                            preparedStmt.setString (4, gorzkaCena.getText());
                            preparedStmt.setString (5, gorzkaData.getText());
                            preparedStmt.setString (6, gorzkaNazwa.getText());
                            preparedStmt.setString (7, gorzkaProducent.getText());
                            preparedStmt.executeQuery();
                        } catch (SQLException ex) {
                            System.err.println("Got an exception!");
                        }
                        System.out.println("Dodałeś " + gorzkaText.getText() + " gorzkich czekolad o 90% zawartości kakao za: " + gorzkaCena.getText() + " zł" +
                                " ważnych do: " + gorzkaData.getText() + " o nazwie: " + gorzkaNazwa.getText() + " producenta: " + gorzkaProducent.getText());
                    }
                    break;
                case "":
                    break;
                default:
                    System.out.println("upss, coś poszło nie tak");
            }
            switch (msg2) {
                case "18 %":
                    if (!mlecznaText.getText().isEmpty() && !mlecznaCena.getText().isEmpty() && !mlecznaData.getText().isEmpty() && !mlecznaNazwa.getText().isEmpty() && !mlecznaProducent.getText().isEmpty()) {
                        try {
                            PreparedStatement preparedStmt1 = Main.myCon.prepareStatement("call przyjmijDostawe(?,?,?,?,?,?,?)");
                            preparedStmt1.setString (1, "Mleczna");
                            preparedStmt1.setString (2, mlecznaText.getText());
                            preparedStmt1.setString (3, "18");
                            preparedStmt1.setString (4, mlecznaCena.getText());
                            preparedStmt1.setString (5, mlecznaData.getText());
                            preparedStmt1.setString (6, mlecznaNazwa.getText());
                            preparedStmt1.setString (7, mlecznaProducent.getText());
                            preparedStmt1.executeQuery();
                        } catch (SQLException ex) {
                            System.err.println("Got an exception!");
                        }
                        System.out.println("Dodałeś " + mlecznaText.getText() + " mlecznych czekolad o 18% zawartości tłuszczu za: " + mlecznaCena.getText() + " zł" +
                                " ważnych do: " + mlecznaData.getText() + " o nazwie: " + mlecznaNazwa.getText() + " producenta: " + mlecznaProducent.getText());
                    }
                break;
                case "20 %":
                    if (!mlecznaText.getText().isEmpty() && !mlecznaCena.getText().isEmpty() && !mlecznaData.getText().isEmpty() && !mlecznaNazwa.getText().isEmpty() && !mlecznaProducent.getText().isEmpty()) {
                        try {
                            PreparedStatement preparedStmt1 = Main.myCon.prepareStatement("call przyjmijDostawe(?,?,?,?,?,?,?)");
                            preparedStmt1.setString (1, "Mleczna");
                            preparedStmt1.setString (2, mlecznaText.getText());
                            preparedStmt1.setString (3, "20");
                            preparedStmt1.setString (4, mlecznaCena.getText());
                            preparedStmt1.setString (5, mlecznaData.getText());
                            preparedStmt1.setString (6, mlecznaNazwa.getText());
                            preparedStmt1.setString (7, mlecznaProducent.getText());
                            preparedStmt1.executeQuery();
                        } catch (SQLException ex) {
                            System.err.println("Got an exception!");
                        }
                        System.out.println("Dodałeś " + mlecznaText.getText() + " mlecznych czekolad o 20% zawartości tłuszczu za: " + mlecznaCena.getText() + " zł" +
                                " ważnych do: " + mlecznaData.getText() + " o nazwie: " + mlecznaNazwa.getText() + " producenta: " + mlecznaProducent.getText());
                    }
                    break;
                case "25 %":
                    if (!mlecznaText.getText().isEmpty() && !mlecznaCena.getText().isEmpty() && !mlecznaData.getText().isEmpty() && !mlecznaNazwa.getText().isEmpty() && !mlecznaProducent.getText().isEmpty()) {
                        try {
                            PreparedStatement preparedStmt1 = Main.myCon.prepareStatement("call przyjmijDostawe(?,?,?,?,?,?,?)");
                            preparedStmt1.setString (1, "Mleczna");
                            preparedStmt1.setString (2, mlecznaText.getText());
                            preparedStmt1.setString (3, "25");
                            preparedStmt1.setString (4, mlecznaCena.getText());
                            preparedStmt1.setString (5, mlecznaData.getText());
                            preparedStmt1.setString (6, mlecznaNazwa.getText());
                            preparedStmt1.setString (7, mlecznaProducent.getText());
                            preparedStmt1.executeQuery();
                        } catch (SQLException ex) {
                            System.err.println("Got an exception!");
                        } System.out.println("Dodałeś " + mlecznaText.getText() + " mlecznych czekolad o 25% zawartości tłuszczu za: " + mlecznaCena.getText() + " zł" +
                                " ważnych do: " + mlecznaData.getText() + " o nazwie: " + mlecznaNazwa.getText() + " producenta: " + mlecznaProducent.getText());
                    }
                    break;
                case "":
                    break;
                default:
                    System.out.println("upss, coś poszło nie tak");
            }
            switch (msg3) {
                case "orzechy":
                    if (!z_DodatkamiText.getText().isEmpty() && !z_dodatkamiCena.getText().isEmpty()&& !z_DodatkamiData.getText().isEmpty() && !z_DodatkamiNazwa.getText().isEmpty() && !z_DodatkamiProducent.getText().isEmpty()) {
                        try {
                            PreparedStatement preparedStmt1 = Main.myCon.prepareStatement("call przyjmijDostawe(?,?,?,?,?,?,?)");
                            preparedStmt1.setString (1, "Z_dodatkami");
                            preparedStmt1.setString (2, z_DodatkamiText.getText());
                            preparedStmt1.setString (3, "orzechy");
                            preparedStmt1.setString (4, z_dodatkamiCena.getText());
                            preparedStmt1.setString (5, z_DodatkamiData.getText());
                            preparedStmt1.setString (6, z_DodatkamiNazwa.getText());
                            preparedStmt1.setString (7, z_DodatkamiProducent.getText());
                            preparedStmt1.executeQuery();
                        } catch (SQLException ex) {
                            System.err.println("Got an exception!");
                        }
                        System.out.println("Dodałeś " + z_DodatkamiText.getText() + " czekolad z orzechami za: " + z_dodatkamiCena.getText() + " zł" +
                                " ważnych do: " + z_DodatkamiData.getText() + " o nazwie: " + z_DodatkamiNazwa.getText() + " producenta: " + z_DodatkamiProducent.getText());
                    }
                    break;
                case "nadzienie":
                    if (!z_DodatkamiText.getText().isEmpty() && !z_dodatkamiCena.getText().isEmpty()&& !z_DodatkamiData.getText().isEmpty() && !z_DodatkamiNazwa.getText().isEmpty() && !z_DodatkamiProducent.getText().isEmpty()) {
                        try {
                            PreparedStatement preparedStmt1 = Main.myCon.prepareStatement("call przyjmijDostawe(?,?,?,?,?,?,?)");
                            preparedStmt1.setString (1, "Z_dodatkami");
                            preparedStmt1.setString (2, z_DodatkamiText.getText());
                            preparedStmt1.setString (3, "nadzienie");
                            preparedStmt1.setString (4, z_dodatkamiCena.getText());
                            preparedStmt1.setString (5, z_DodatkamiData.getText());
                            preparedStmt1.setString (6, z_DodatkamiNazwa.getText());
                            preparedStmt1.setString (7, z_DodatkamiProducent.getText());
                            preparedStmt1.executeQuery();
                        } catch (SQLException ex) {
                            System.err.println("Got an exception!");
                        }
                        System.out.println("Dodałeś " + z_DodatkamiText.getText() + " czekolad z nadzieniem za: " + z_dodatkamiCena.getText() + " zł" +
                                " ważnych do: " + z_DodatkamiData.getText() + " o nazwie: " + z_DodatkamiNazwa.getText() + " producenta: " + z_DodatkamiProducent.getText());
                    }
                    break;
                case "bakalie":
                    if (!z_DodatkamiText.getText().isEmpty() && !z_dodatkamiCena.getText().isEmpty()&& !z_DodatkamiData.getText().isEmpty() && !z_DodatkamiNazwa.getText().isEmpty() && !z_DodatkamiProducent.getText().isEmpty()){
                        try {
                            PreparedStatement preparedStmt1 = Main.myCon.prepareStatement("call przyjmijDostawe(?,?,?,?,?,?,?)");
                            preparedStmt1.setString (1, "Z_dodatkami");
                            preparedStmt1.setString (2, z_DodatkamiText.getText());
                            preparedStmt1.setString (3, "bakalie");
                            preparedStmt1.setString (4, z_dodatkamiCena.getText());
                            preparedStmt1.setString (5, z_DodatkamiData.getText());
                            preparedStmt1.setString (6, z_DodatkamiNazwa.getText());
                            preparedStmt1.setString (7, z_DodatkamiProducent.getText());
                            preparedStmt1.executeQuery();
                        } catch (SQLException ex) {
                            System.err.println("Got an exception!");
                        }
                        System.out.println("Dodałeś " + z_DodatkamiText.getText() + " czekolad z bakaliami za: " + z_dodatkamiCena.getText() + " zł" +
                                " ważnych do: " + z_DodatkamiData.getText() + " o nazwie: " + z_DodatkamiNazwa.getText() + " producenta: " + z_DodatkamiProducent.getText());
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
