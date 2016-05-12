package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class UpdateQuestion extends MouseAdapter implements ActionListener{

    public static boolean status = true;
    JComboBox<Object> users;
    JLabel label;
    public UpdateQuestion (JComboBox users, JLabel label ) {
        this.label = label;
        this.users = users;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (status)
        update();
    }
    void update () {
        String ques;
        int serial = ((data.Profile)users.getSelectedItem()).getQuestion();
        ques = graphics.User.questions[serial - 1].toString();
        label.setToolTipText(ques);
        if ( ques.length() > 33 ) {
            ques = ques.substring(0, 31);
            ques = ques + "...";
        }
        label.setText(ques);
    }
    @Override
    public void mouseReleased ( MouseEvent me ) {
        if ( status )
        update();
    }
}
