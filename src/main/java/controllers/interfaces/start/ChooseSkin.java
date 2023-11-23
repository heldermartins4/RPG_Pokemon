package controllers.interfaces.start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseSkin extends JPanel {

    private JButton skin1Button;
    private JButton skin2Button;
    private String selectedSkin;

    public ChooseSkin() {
        setLayout(new GridLayout(1, 2));

        skin1Button = new JButton(new ImageIcon(getClass().getResource("/sprites/characters/zeze/d1.png")));
        skin2Button = new JButton(new ImageIcon(getClass().getResource("/sprites/characters/heldin/d1.png")));

        skin1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedSkin = "/sprites/characters/zeze/";
            }
        });

        skin2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedSkin = "/sprites/characters/heldin/";
            }
        });

        add(skin1Button);
        add(skin2Button);
    }

    public String getSelectedSkin() {
        return selectedSkin;
    }
}
