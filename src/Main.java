//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.io.*;
import java.net.*;

public class Main extends JFrame implements ActionListener {
    // Declaring attributes for the GUI
    private static final int MAX=5;
    private JLayeredPane lp;
    private ImageIcon background = new ImageIcon("Monopoly.jpg");
    private ImageIcon[] faces = new ImageIcon[MAX];
    private JLabel[] lblFaces = new JLabel[MAX];
    private JButton[] btnRoll=new JButton[MAX];
    private JButton btnStart = new JButton("Start");
    private JButton btnQuit = new JButton("Quit");
    private JLabel lblMessage = new JLabel();
    private JLabel lblBackground, lblCash;
    private int myPosition=0;
    private JLabel lblChat = new JLabel();
    private JTextField txtChat;
    private JTextArea taChat, taDetails;
    private JScrollPane scrollDisplay, scrollDetails;
    // Declaring initiate cash for each player
    private int cash = 5000;
    // Declaring the last rolled dice figure
    private int lastRolled;
    // Create a hashtable for storing the values of property
    public Hashtable<String,String> propertyHT = new Hashtable<String, String>();

    // Declaring attributes
    private int iconWidth, iconHeight, width = 47, height = 47;
    private static Socket client;
    private static DataInputStream dIn;
    private static DataOutputStream dOut;
    private final int SENDPD=100;
    private final int CALWINNER=101;
    private final int START=80;
    private final int MOVE=81;
    private final int NEXT=82;
    private final int ROLL=83;
    private final int WIN=84;
    private final int QUIT=88;
    private final int CHAT=89;
    private final int BUY=90;
    // Declaring attributes for the community chest
    private final int BIRTHDAY=91;
    private final int GENEROUS=92;
    // Declaring client name
    private static String name;
    // Declaring client IP Address
    private static String ipAddr = "";
    private static int player, number;
    private static int rounds=1;

    //Creating property
    Property p1 = new Property("Student Plaza", "60", "Vacant");
    Property p2 = new Property("Sports Hall", "60", "Vacant");
    Property p3 = new Property("NP Co-op", "100", "Vacant");
    Property p4 = new Property("Atrium", "100", "Vacant");
    Property p5 = new Property("Convention Centre", "120", "Vacant");
    Property p6 = new Property("Library", "140", "Vacant");
    Property p7 = new Property("Green Mall", "140", "Vacant");
    Property p8 = new Property("Super Mart", "160", "Vacant");
    Property p9 = new Property("EE Mart", "180", "Vacant");
    Property p10 = new Property("@.com", "180", "Vacant");
    Property p11 = new Property("Vin Enterprise", "200", "Vacant");
    Property p12 = new Property("Our Space @72", "220", "Vacant");
    Property p13 = new Property("Teaching Hub", "220", "Vacant");
    Property p14 = new Property("Lifestyle centre", "240", "Vacant");
    Property p15 = new Property("Ultra Supplies", "260", "Vacant");
    Property p16 = new Property("Alumni Clubhhouse", "260", "Vacant");
    Property p17 = new Property("Studio27", "280", "Vacant");
    Property p18 = new Property("Blk 27", "300", "Vacant");
    Property p19 = new Property("Blk 31", "300", "Vacant");
    Property p20 = new Property("Blk 5", "320", "Vacant");
    Property p21 = new Property("The Dot", "350", "Vacant");
    Property p22 = new Property("e-Garage", "400", "Vacant");
    Property p23 = new Property("Canteen 2", "200", "Vacant");
    Property p24 = new Property("Canteen 3", "200", "Vacant");
    Property p25 = new Property("Canteen 4", "200", "Vacant");
    Property p26 = new Property("Makan Place", "200", "Vacant");

    public Main(){
        JPanel details=
    }

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }
}