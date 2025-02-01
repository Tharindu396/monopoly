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
        Container c = getContentPane();

        // player details panel
        JPanel details = new JPanel();
        details.setOpaque(true);
        details.setBounds(633,30,420,80);
        taDetails = new JTextArea("PlayerNo\tName\tCash\tRounds\n"+(player+1)+"\t"+name+"\t$"+cash+"\t"+rounds);
        taDetails.setCaretPosition(0);
        taDetails.setEditable(false);
        scrollDetails = new JScrollPane(taDetails);
        scrollDetails.setOpaque(true);
        scrollDetails.setBounds(653,60,380,35);
        TitledBorder detailsTitle = BorderFactory.createTitledBorder("Your Statistics");
        details.setBorder(detailsTitle);
        // chatPanel for chatting area
        JPanel chatArea = new JPanel();
        chatArea.setOpaque(true);
        chatArea.setBounds(633,112,420,591);
        chatArea.add(lblChat = new JLabel("Enter Chat Message:"));
        chatArea.add(txtChat = new JTextField(30));
        taChat = new JTextArea();
        taChat.setCaretPosition(0);
        taChat.setEditable(false);
        scrollDisplay = new JScrollPane(taChat);
        scrollDisplay.setOpaque(true);
        scrollDisplay.setBounds(653,207,380,476);
        //chatArea.add(scrollDisplay);
        TitledBorder chatTitle = BorderFactory.createTitledBorder("Chatting Interface");
        chatArea.setBorder(chatTitle);
        // layeredPane for the game board
        lp = new JLayeredPane();
        lblBackground = new JLabel(background);
        lblBackground.setOpaque(true);
        lblBackground.setBounds(20,110,593,593);
        lp.add(lblBackground, 0);
        lp.add(details, 0);
        lp.add(scrollDetails, 0);
        lp.add(chatArea, 0);
        lp.add(scrollDisplay, 0);
        TitledBorder gameTitle = BorderFactory.createTitledBorder("Monopoly Game");
        lp.setBorder(gameTitle);

        // create the ImageIcon objects with the files and the lables with the ImageIcon objects
        // set the starting location for the labels and add them
        for (int i=0; i<number; i++){
            faces[i] = new ImageIcon("icon"+(i+1)+".png");
            lblFaces[i] = new JLabel(faces[i]);
            iconWidth = faces[i].getIconWidth();
            iconHeight = faces[i].getIconHeight();
            lblFaces[i].setBounds(new Rectangle(540+i*20, 670, iconWidth, iconHeight));
            lp.add(lblFaces[i], new Integer(1));
        }

        // create the JButton objects with the dice and add them to a panel
        JPanel panButtons = new JPanel();
        panButtons.setLayout(new GridLayout(2,1));
        JPanel pan1 = new JPanel();
        JPanel pan2 = new JPanel();
        for (int i=0; i<number; i++){
            btnRoll[i] = new JButton("Dice"+(i+1));
            btnRoll[i].addActionListener(this);
            btnRoll[i].setEnabled(false);
            if (i != player)
                btnRoll[i].setVisible(false);
            pan1.add(btnRoll[i]);
        }
        // Adding of buttons into the JPanel
        pan1.add(btnStart);
        pan1.add(btnQuit);
        // Adding action listener to the buttons
        btnStart.addActionListener(this);
        btnQuit.addActionListener(this);
        txtChat.addActionListener(this);
        // Check which client starts the game first
        if (player == number-1)
            lblMessage.setText("Click start button to begin");
        else
            lblMessage.setText("Welcome! Waiting for other player to connect.");
        // Adding of labels into the JPanel
        pan2.add(lblMessage);
        panButtons.add(pan2);
        panButtons.add(pan1);
        panButtons.setBounds(145,30,360,65);

        c.add(panButtons);
        c.add(lp);
    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==btnStart){
            sendData(START);
            btnStart.setEnabled(false);
            btnRoll[player].setEnabled(false);
            reset();
        }
        else{
            if (e.getSource()==btnQuit){
                sendData(QUIT)
            }
        }
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