package GUI;

import javax.swing.*;
import java.awt.*;


/**
 * Class show the reports from reports.txt file as GUI.
 */
public class reportsFrame extends JFrame {
    JLabel dataLbl;

    /**
     * Frame constructor.
     * @param data
     */
    public reportsFrame(String data){
        super("Reports");
        dataLbl=new JLabel();
        dataLbl.setText(convertToMultiline(data));
        Font f=new Font(Font.SERIF,Font.PLAIN,14);
        dataLbl.setFont(f);
        this.add(dataLbl);
    }

    /**
     * Function convert reports data to multiline because /n is ignored.
     * @param orig
     * @return
     */
    public static String convertToMultiline(String orig)
    {
        return "<html>" + orig.replaceAll("\n", "<br>");
    }
}
