package GUI;

import components.Driving;

import javax.swing.*;
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
        setSize(new Dimension(100,200));
        panel=new JPanel();
        panel.setLayout(new GridLayout(2,1));
        setVisible(true);
        driving=d;
        text=new JTextField();
        text.setText("0");
        btn=new JButton("Submit");
        btn.addActionListener(this);
        panel.add(text);
        panel.add(btn);
        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn){
            driving.getVehicles().add(Driving.getVehicle(getData()));
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
            System.out.println("Error! value isn't an integer! -1 will returned");
        }
        return -1;
    }
}
