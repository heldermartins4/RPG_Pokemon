package pokegame.ifc;

import javax.swing.JFrame;

// import controllers.game.Pokeleague;
import controllers.interfaces.GamePanel;

public class Container extends JFrame {
    public Container() {
        this.setTitle("PokeGame | IFC");
        
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // public void startBattle() {}
    // public void endBattle() {}

    public void initialize() {
        // Pokeleague poke = new Pokeleague();
        GamePanel screen = new GamePanel();
        this.add(screen);
        this.pack();
        this.addKeyListener(screen.key);
        this.requestFocus();
        this.setLocationRelativeTo(null);

        screen.startGameThread();
    }
}
