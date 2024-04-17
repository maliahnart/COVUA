package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import main.Sound;
public class Wallpaper {
    private JFrame frame;
    private static JPanel gamePanel;
	private JLabel startLabel, exitLabel;
    private Sound backgroundMusic; 
    private JLabel flashModeLabel; 

    public Wallpaper() {
        // create frame
        frame = new JFrame("ChessGame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        // Set the application icon
        ImageIcon icon2 = new ImageIcon("wall.png");
        frame.setIconImage(icon2.getImage());
        // Theme
        backgroundMusic = new Sound("theme.wav"); 
        backgroundMusic.playSound(); // 
        // create start label with image
        ImageIcon icon = new ImageIcon(new ImageIcon("start.png").getImage().getScaledInstance(100, 50, Image.SCALE_DEFAULT));
        startLabel = new JLabel(icon);

        startLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startGame();
            }
        });

        // create exit label with image
        ImageIcon icon1 = new ImageIcon(new ImageIcon("exit.png").getImage().getScaledInstance(100, 50, Image.SCALE_DEFAULT));
        exitLabel = new JLabel(icon1);

        exitLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        // create flashMode label with image
        ImageIcon flashModeIcon = new ImageIcon(new ImageIcon("Flash.png").getImage().getScaledInstance(100, 50, Image.SCALE_DEFAULT));
        flashModeLabel = new JLabel(flashModeIcon);

        flashModeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startFlashMode();
            }
        });
     // set bounds of components
        flashModeLabel.setBounds(350, 400, 100, 50); // adjust these as necessary

       
        // create and add background image
        JLabel backgroundLabel = new JLabel(new ImageIcon("wall.png"));
        backgroundLabel.setBounds(0, 0, 800, 600);

        // create layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));

        // set bounds of components
        startLabel.setBounds(350, 200, 100, 50); // adjust these as necessary
        exitLabel.setBounds(350, 300, 100, 50); // adjust these as necessary

        // add components to layered pane
        layeredPane.add(flashModeLabel, Integer.valueOf(2));
        layeredPane.add(backgroundLabel, Integer.valueOf(1));
        layeredPane.add(startLabel, Integer.valueOf(2));
        layeredPane.add(exitLabel, Integer.valueOf(2));

        // add layered pane to frame
        frame.add(layeredPane);

        frame.pack();
        frame.setVisible(true);
    }
    public static JPanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(JPanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
    public void startGame() {
    	  backgroundMusic.stopSound();
        // remove labels
        startLabel.setVisible(false);
        exitLabel.setVisible(false);

        // create gamePanel
        gamePanel = new GamePanel();

        // add gamePanel in Game
        frame.add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);

        // Update JFrame
        frame.validate();
        frame.repaint();

        // Launch Game
        ((GamePanel) gamePanel).launchGame();
    }
    public void startFlashMode() {
        backgroundMusic.stopSound();
        // remove labels
        startLabel.setVisible(false);
        exitLabel.setVisible(false);
        flashModeLabel.setVisible(false); // Hide the flashModeLabel

        // create gamePanel
        gamePanel = new FlashMode(); // Use FlashMode instead of GamePanel

        // add gamePanel in Game
        frame.add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);

        // Update JFrame
        frame.validate();
        frame.repaint();

        // Launch Game
        ((FlashMode) gamePanel).launchGame(); // Cast to FlashMode instead of GamePanel
    }
}

