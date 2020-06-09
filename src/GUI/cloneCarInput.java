package GUI;

import components.Driving;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cloneCarInput extends JFrame implements ActionListener {
    JTextField text;
    JButton btn;
    Driving driving;
    JPanel panel;
    public cloneCarInput(Driving d){
        super("Clone a vehicle");
        driving=d;
        setSize(new Dimension(300,300));
        panel=new JPanel();
        Border border = BorderFactory.createLineBorder(Color.BLUE, 1); //border of button
        panel.setLayout(new GridLayout(5, 1, 30, 30));
        panel.setBackground(Color.GRAY);
        Border blackline = BorderFactory.createTitledBorder("");
        JPanel panel1 = new JPanel();
        String spaces = "                   ";
        panel1.add(new JLabel(spaces + "Choose number of car for clone" + spaces));
        panel1.setBorder(blackline);
        panel.add(panel1);
        text=new JTextField();
        text.setText("0");
        btn=new JButton("Submit");
        btn.addActionListener(this);
        setVisible(true);
        panel.add(text);
        panel.add(btn);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn){
            driving.getVehicles().add(driving.getVehicle(getData()));
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            dispose();
        }
    }

    /**
     * Function convert jtextfield data to int and return it
     * @return text data
     */
    private int getData(){
        try {
            return Integer.parseInt(text.getText());
        }catch (NumberFormatException e){
            System.out.println("Error! value isn't an integer! -1 will return");
        }
        return -1;
    }
}
