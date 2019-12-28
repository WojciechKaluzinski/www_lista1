import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KierownikGUI extends JFrame implements ActionListener {

    String[] rodzajeGorzkiej = {"","50 %", "70 %", "90 %"};
    String[] rodzajeMlecznej = {"","18 %", "20 %", "25 %"};
    String[] rodzajeZ_dodatkami = {"","orzechy", "nadzienie", "bakalie"};
    JComboBox gorzkaCobo,mlecznaCobo,z_DodatkamiCobo;
    JTextField gorzkaText,mlecznaText,z_DodatkamiText,gorzkaCena,mlecznaCena,z_dodatkamiCena;
    JButton dodaj,przegladajMagazyn,pokazPrawiePrzeterminowane,podsumowanieMiesieczne;
    JLabel gorzka,mleczna,z_dodatkami,ilosc,cena;


    KierownikGUI(){
        setSize(750, 500);
        setTitle("CzekoFab: okno kierownika");
        setLocation(100,100);
        this.getContentPane().setBackground(Color.yellow);
        setLayout(null);
        JLabel infoStartowe1 = new JLabel("Wybierz jaki typ czekolad, ile sztuk i w jakiej cenie chcesz dodać: ");
        JLabel infoStartowe2 = new JLabel("Dodatkowe akcje kierownika: ");
        infoStartowe1.setForeground(Color.BLACK);
        infoStartowe2.setForeground(Color.BLACK);

        infoStartowe1.setBounds(10,10,400,30);
        add(infoStartowe1);
        infoStartowe2.setBounds(440,10,350,30);
        add(infoStartowe2);

        gorzka = new JLabel("GORZKA");
        mleczna = new JLabel("MLECZNA");
        z_dodatkami = new JLabel("Z DODATKAMI");
        ilosc = new JLabel("ILOŚĆ");
        cena = new JLabel("CENA");
        gorzkaCobo = new JComboBox(rodzajeGorzkiej);
        mlecznaCobo = new JComboBox(rodzajeMlecznej);
        z_DodatkamiCobo = new JComboBox(rodzajeZ_dodatkami);
        gorzkaText = new JTextField();
        mlecznaText = new JTextField();
        z_DodatkamiText = new JTextField();
        gorzkaCena = new JTextField();
        mlecznaCena = new JTextField();
        z_dodatkamiCena = new JTextField();
        dodaj = new JButton("DODAJ");
        przegladajMagazyn = new JButton("PREGLĄDAJ MAGAZYN");
        pokazPrawiePrzeterminowane = new JButton("O KRÓTKIM TERMINIE WAŻNOŚCI");
        podsumowanieMiesieczne = new JButton("PODSUMOWANIE MIESIĘCZNE");

        ilosc.setForeground(Color.black);
        ilosc.setBounds(250,50,200,30);
        add(ilosc);
        cena.setForeground(Color.black);
        cena.setBounds(320,50,200,30);
        add(cena);

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
        add(przegladajMagazyn);
        przegladajMagazyn.setBounds(440,90,250,30);
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
        add(pokazPrawiePrzeterminowane);
        pokazPrawiePrzeterminowane.setBounds(440,170,250,30);
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
        add(podsumowanieMiesieczne);
        podsumowanieMiesieczne.setBounds(440,250,250,30);
        podsumowanieMiesieczne.addActionListener(this);

        add(dodaj);
        dodaj.setBounds(100, 350,200,30);
        dodaj.addActionListener(this);

    }

    public void uruchomKierownikaGUI(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
          if (e.getSource() == przegladajMagazyn) {
            System.out.println("Chcesz przeglądać stan magazynu");
        } if (e.getSource() == pokazPrawiePrzeterminowane) {
            System.out.println("Chcesz przeglądać produkt o krótkiej dacie przydatności");
        } if (e.getSource() == podsumowanieMiesieczne) {
            System.out.println("Chcesz zrobić podsumowanie miesięczne");
        }
        if (e.getSource() == dodaj) {
            String msg1 = (String) gorzkaCobo.getSelectedItem();
            String msg2 = (String) mlecznaCobo.getSelectedItem();
            String msg3 = (String) z_DodatkamiCobo.getSelectedItem();
            switch (msg1) {
                case "50 %":
                    if (!gorzkaText.getText().isEmpty() && !gorzkaCena.getText().isEmpty())
                        System.out.println("Dodałeś " + gorzkaText.getText() + " gorzkich czekolad o 50% zawartości kakao za: " + gorzkaCena.getText() + " zł");
                    break;
                case "70 %":
                    if (!gorzkaText.getText().isEmpty() && !gorzkaCena.getText().isEmpty())
                        System.out.println("Dodałeś " + gorzkaText.getText() + " gorzkich czekolad o 70% zawartości kakao za: " + gorzkaCena.getText() + " zł");
                    break;
                case "90 %":
                    if (!gorzkaText.getText().isEmpty() && !gorzkaCena.getText().isEmpty())
                        System.out.println("Dodałeś " + gorzkaText.getText() + " gorzkich czekolad o 90% zawartości kakao za: " + gorzkaCena.getText() + " zł");
                    break;
                case "":
                    break;
                default:
                    System.out.println("upss, coś poszło nie tak");
            }
            switch (msg2) {
                case "18 %":
                    if (!mlecznaText.getText().isEmpty() && !mlecznaCena.getText().isEmpty())
                        System.out.println("Dodałeś " + mlecznaText.getText() + " gorzkich czekolad o 18% zawartości kakao za: " + mlecznaCena.getText() + " zł");
                    break;
                case "20 %":
                    if (!mlecznaText.getText().isEmpty() && !mlecznaCena.getText().isEmpty())
                        System.out.println("Dodałeś " + mlecznaText.getText() + " gorzkich czekolad o 20% zawartości kakao za: " + mlecznaCena.getText() + " zł");
                    break;
                case "25 %":
                    if (!mlecznaText.getText().isEmpty() && !mlecznaCena.getText().isEmpty())
                        System.out.println("Dodałeś " + mlecznaText.getText() + " gorzkich czekolad o 25% zawartości kakao za: " + mlecznaCena.getText() + " zł");
                    break;
                case "":
                    break;
                default:
                    System.out.println("upss, coś poszło nie tak");
            }
            switch (msg3) {
                case "orzechy":
                    if (!z_DodatkamiText.getText().isEmpty() && !z_dodatkamiCena.getText().isEmpty())
                        System.out.println("Dodałeś " + z_DodatkamiText.getText() + " czekolad z orzechami za: " + z_dodatkamiCena.getText() + " zł");
                    break;
                case "nadzienie":
                    if (!z_DodatkamiText.getText().isEmpty() && !z_dodatkamiCena.getText().isEmpty())
                        System.out.println("Dodałeś " + z_DodatkamiText.getText() + " czekolad z nadzieniem za: " + z_dodatkamiCena.getText() + " zł");
                    break;
                case "bakalie":
                    if (!z_DodatkamiText.getText().isEmpty() && !z_dodatkamiCena.getText().isEmpty())
                        System.out.println("Dodałeś " + z_DodatkamiText.getText() + " czekolad z bakaliami za: " + z_dodatkamiCena.getText() + " zł");
                    break;
                case "":
                    break;
                default:
                    System.out.println("upss, coś poszło nie tak");
            }
        }
    }
}
