package GUI;

import javax.swing.*;
import java.awt.*;

public class reportsFrame extends JFrame {
    JLabel dataLbl;
    public reportsFrame(String data){
        super("Reports");
        dataLbl=new JLabel();
        dataLbl.setText(convertToMultiline(data));
        Font f=new Font(Font.SERIF,Font.PLAIN,14);
        dataLbl.setFont(f);
        this.add(dataLbl);
    }

    public static String convertToMultiline(String orig)
    {
        return "<html>" + orig.replaceAll("\n", "<br>");
    }
}
