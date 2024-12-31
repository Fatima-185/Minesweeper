/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.nothing;

/**
 *
 * @author Fatima
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Fatima
 */
import java.awt.*;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.lang.Math;
import java.util.HashSet;
import javax.swing.border.LineBorder;
public class Nothing extends JFrame {//why to extend not to make an obj
    JLabel statusbar = new JLabel("");
    
     private JPanel contentPanel;

    private void setIcon(ImageIcon imageIcon) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private class Board extends JPanel{

        private class MineCell extends JButton {
            int row, col, val = 0;
            boolean cov = true, mar = false;
            public MineCell(int r, int c){
                this.row = r;
                this.col = c;
            }
        }

        private  int CELL_SIZE ;
    
        private  int N_MINES ;
        private  int N_ROWS ;
        private  int N_COLS ;
    
        private  int BOARD_WIDTH ;
        private  int BOARD_HEIGHT ;

        private boolean inGame;
        private int minesLeft ;
        private int cellsLeft;
        private ImageIcon[] imgs;
    
        MineCell[][] allCells ;
    private ImageIcon scaleIcon(ImageIcon icon, int width, int height) {
    Image img = icon.getImage();
    Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    return new ImageIcon(scaledImg);
}

        public Board(int level, int cellsize) {
          CELL_SIZE=  cellsize;
    this.N_ROWS = level;
    this.N_COLS = level;
    this.N_MINES = (int) (0.2 * N_ROWS * N_COLS);

    BOARD_WIDTH = N_COLS * CELL_SIZE + 1;
    BOARD_HEIGHT = N_ROWS * CELL_SIZE + 1;

    cellsLeft = N_ROWS * N_COLS - N_MINES;
    allCells = new MineCell[N_ROWS][N_COLS];
            // why no to use setSze 
            setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
    
            imgs = new ImageIcon[13];

           // Resize the icon to fit the button size

            for (int i = 0; i < 13; i++) {
     var path = "D:/New folder (2)/Java-Minesweeper-Game/src/resources/" + i + ".png";
    imgs[i] = scaleIcon(new ImageIcon(path), CELL_SIZE, CELL_SIZE);
            }
            
            setLayout(new GridLayout(N_ROWS, N_COLS));
            
            
            for(int r = 0; r < N_ROWS; r++){
                for(int c = 0; c < N_COLS; c++){
                    MineCell cell = new MineCell(r, c);
                    cell.setBackground(new Color(187,187,187,255));
                    allCells[r][c] = cell;
                    cell.setFocusable(false);
                    cell.setMargin(new Insets(0, 0, 0, 0));
                    cell.setIcon(imgs[10]);
                    cell.setRolloverEnabled(false);
                    cell.setContentAreaFilled(false);
                    cell.setOpaque(true);
                    add(cell);
                    cell.addMouseListener(new MinesAdapter());
                }
            }
            setMines();
        }
    
        
        
        
        
        private void beginGame(){
            minesLeft = N_MINES;
            cellsLeft = N_ROWS * N_COLS - N_MINES;
            for(int r = 0; r < N_ROWS; r++){
                for(int c = 0; c < N_COLS; c++){//reset
                    MineCell cell = allCells[r][c];
                    cell.cov = true;
                    cell.mar = false;
                    cell.val = 0;
                    allCells[r][c] = cell;
                    cell.setIcon(imgs[10]);
                }
            }
            setMines();
        }
        
        private void setMines() {
            var random = new Random();
            inGame = true;

            statusbar.setText(Integer.toString(minesLeft));

            int i = 0;

            while (i < N_MINES) {
                //int curRow = random.nextInt(N_ROWS), curCol = random.nextInt(N_COLS);

                int curRow = Math.abs(random.nextInt()) % N_ROWS, curCol = Math.abs(random.nextInt()) % N_COLS;
                
                
                if ((allCells[curRow][curCol].val != 9)) {

                    allCells[curRow][curCol].val = 9;
                    i++;

                    if (curCol > 0) {
                        if (curRow > 0 && allCells[curRow - 1][curCol - 1].val != 9) {
                            allCells[curRow - 1][curCol - 1].val++;
                        }
                        if (allCells[curRow][curCol - 1].val != 9)
                        allCells[curRow][curCol - 1].val++;
                        if (curRow < N_ROWS - 1) {
                        if (allCells[curRow + 1][curCol - 1].val != 9)
                        allCells[curRow + 1][curCol - 1].val++;
                        }
                    }

                    if (curRow > 0) {
                        if (allCells[curRow - 1][curCol].val != 9)
                        allCells[curRow - 1][curCol].val++;
                    }
                    if (curRow < N_ROWS - 1) {
                        if (allCells[curRow + 1][curCol].val != 9)
                        allCells[curRow + 1][curCol].val++;
                    }

                    if (curCol < N_COLS - 1) {
                        if (curRow > 0) {
                        if (allCells[curRow - 1][curCol + 1].val != 9)
                        allCells[curRow - 1][curCol + 1].val++;
                        }
                        if (allCells[curRow][curCol + 1].val != 9)
                        allCells[curRow][curCol + 1].val++;
                        if (curRow < N_ROWS - 1) {
                        if (allCells[curRow + 1][curCol + 1].val != 9)
                        allCells[curRow + 1][curCol + 1].val++;
                        }
                    }

                }
            }
        }

        private class MinesAdapter extends MouseAdapter {
        // why not use the mouselistner 
            @Override
            public void mousePressed(MouseEvent e) {
                MineCell cell = (MineCell) e.getSource();
                
                if(!inGame){
                    inGame = true;
                    beginGame();
                    return;
                }
                if(e.getButton() == MouseEvent.BUTTON1 && cell.cov && !cell.mar){
                    if(cell.val == 9){
                        inGame = false;
                        statusbar.setText("Game Over");
                        revealMines();
                    }
                    else {
                        revealEmpty(cell.row, cell.col);
                    }
                }
                else if(e.getButton() == MouseEvent.BUTTON3 && cell.cov && cell.mar){
                    cell.mar = false;
                    cell.setIcon(imgs[10]);
                    minesLeft++;
                    String msg = Integer.toString(minesLeft); //""+minesleft  or String.ValueOf
                    statusbar.setText(msg);
                }
                else if(e.getButton() == MouseEvent.BUTTON3 && cell.cov && !cell.mar){
                    if(minesLeft != 0) {
                        cell.mar = true;
                        cell.setIcon(imgs[11]);
                        minesLeft--;
                        String msg = Integer.toString(minesLeft);
                        statusbar.setText(msg);
                    }
                    else {
                        statusbar.setText("No marks left");
                    }
                    
                }
                if(cellsLeft == 0){
                    inGame = false;
                    statusbar.setText("You Won");
                    revealMines();
                }
            }
        }

        void revealEmpty(int curRow, int curCol) {
            // how did you use return 
            if (curRow < 0 || curRow >= N_ROWS || curCol < 0 || curCol >= N_COLS || !allCells[curRow][curCol].cov) {
                return;
            }
            
            cellsLeft--;
            allCells[curRow][curCol].cov = false;
        
            allCells[curRow][curCol].setIcon(imgs[allCells[curRow][curCol].val]);
        
            if (allCells[curRow][curCol].val != 0) {
                return;
            }
        
            revealEmpty(curRow - 1, curCol - 1);
            revealEmpty(curRow - 1, curCol);
            revealEmpty(curRow - 1, curCol + 1);
            revealEmpty(curRow, curCol - 1);
            revealEmpty(curRow, curCol + 1);
            revealEmpty(curRow + 1, curCol - 1);
            revealEmpty(curRow + 1, curCol);
            revealEmpty(curRow + 1, curCol + 1);
        }
        


        public void revealMines(){
            for(int r = 0; r < N_ROWS; r++){
                for(int c = 0; c < N_COLS; c++){
                    if(!allCells[r][c].mar){
                        allCells[r][c].setIcon(imgs[allCells[r][c].val]);
                    }
                    else if(allCells[r][c].val != 9) allCells[r][c].setIcon(imgs[12]);
                    
                }
            }
        }
    }
    
    

    public Nothing(){
        // Create the background panel with the image
ImageIcon backgroundIcon = new ImageIcon("D:/New folder (2)/down5load.jpg");
Image backgroundImage = backgroundIcon.getImage();

JPanel backgroundPanel = new JPanel() {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
};
backgroundPanel.setLayout(new BorderLayout()); // Ensure proper layout for components
setContentPane(backgroundPanel); // Set it as the content pane

// Configure JTextArea
JTextArea tipsArea = new JTextArea();

tipsArea.setText("Welcome to Minesweeper!\n\n"
        + "1. Left Click:\n" +
"Uncover a square.\n" +
"If the square is empty, it will reveal:\n" +
"A number: Indicates how many mines are in the adjacent squares (includes diagonals).\n" +
"Blank squares: Automatically uncovers surrounding squares until numbers appear.\n" +
"If you click on a mine, the game ends.\n"
        + "2. Right Click:\n" +
"Place a flag on a square to mark it as a potential mine. This helps keep track of suspected mines.\n" +
"Double Click (Optional on some versions):\n" +
"\n" +
"If a number is already revealed, double-click it to quickly uncover surrounding squares (only if the correct number of flags are placed around it).\n"
        + "3. Avoid clicking on mines!\n\n"
        + "Good luck!");
tipsArea.setEditable(false);
tipsArea.setWrapStyleWord(true);
tipsArea.setLineWrap(true);
//tipsArea.setOpaque(false); // Transparent background
tipsArea.setBackground(new Color(137, 207, 240,100));
tipsArea.setFont(new Font("Arial", Font.BOLD, 12));

JScrollPane tipsScrollPane = new JScrollPane(tipsArea);
tipsScrollPane.setPreferredSize(new Dimension(400, 100));
tipsScrollPane.setBackground(new Color(137, 207, 240,100)); // Transparent scroll pane
//tipsScrollPane.getViewport().setOpaque(false); // Transparent viewport

// Buttons and panels
JPanel btnpan = new JPanel();
btnpan.setLayout(new BoxLayout(btnpan, BoxLayout.Y_AXIS));
btnpan.setOpaque(false);

RoundedButton low_btn = new RoundedButton("Low Level");
RoundedButton mid_btn = new RoundedButton("Midd Level");
RoundedButton high_btn = new RoundedButton("High Level");

low_btn.setBackground(new Color(137, 207, 240));
mid_btn.setBackground(new Color(137, 207, 240));
high_btn.setBackground(new Color(137, 207, 240));

low_btn.addActionListener(e -> showGamePanel(4, 50));
mid_btn.addActionListener(e -> showGamePanel(8, 44));
high_btn.addActionListener(e -> showGamePanel(16, 20));

low_btn.setAlignmentX(Component.CENTER_ALIGNMENT);
mid_btn.setAlignmentX(Component.CENTER_ALIGNMENT);
high_btn.setAlignmentX(Component.CENTER_ALIGNMENT);

btnpan.add(low_btn);
btnpan.add(Box.createVerticalStrut(20)); // Spacer
btnpan.add(mid_btn);
btnpan.add(Box.createVerticalStrut(20)); // Spacer
btnpan.add(high_btn);

JPanel pan = new JPanel(new BorderLayout());
pan.setOpaque(false);
pan.add(btnpan, BorderLayout.CENTER);

JPanel panel = new JPanel();
pan.add(panel,BorderLayout.NORTH);
JPanel menuPanel = new JPanel(new BorderLayout());
menuPanel.setOpaque(false);
menuPanel.add(panel,BorderLayout.NORTH);
menuPanel.add(pan, BorderLayout.CENTER);
menuPanel.add(tipsScrollPane, BorderLayout.SOUTH);

contentPanel = new JPanel(new CardLayout());
contentPanel.setOpaque(false);
contentPanel.add(menuPanel, "menu");

// Add everything to the background panel
backgroundPanel.add(contentPanel, BorderLayout.CENTER);

// Frame settings
setResizable(false);
setSize(400, 400);
setTitle("Minesweeper");
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
  private void showGamePanel(int level , int cellsize) {
      
        CardLayout cl = (CardLayout) contentPanel.getLayout();
        JPanel boardPanel = new JPanel(new FlowLayout());  // Use BorderLayout here
        Board board = new Board(level,cellsize);
       board.setLocation(0,200);
      
        boardPanel.add(board);  // Place the game board at the center
         JPanel gamePanel = new JPanel(new BorderLayout()); 
         
      
       gamePanel.add(board,BorderLayout.CENTER);
       
       gamePanel.setBackground(new Color(137, 207, 240));
       gamePanel.add(statusbar, BorderLayout.NORTH);
        // Create a back button to return to the main menu
        JButton backButton = new JButton("change level");
        backButton.addActionListener(e -> switchToMenuPanel());

        // Add the back button at the bottom of the game panel
        gamePanel.add(backButton, BorderLayout.SOUTH);  // Place the button at the bottom
        
        contentPanel.add(gamePanel, "game");
          
        // Show the game panel
        cl.show(contentPanel, "game");
    }
   private void switchToMenuPanel() {
        CardLayout cl = (CardLayout) (contentPanel.getLayout());
        cl.show(contentPanel, "menu");
    }
    public static void main(String[] args) {
        
        Nothing ex = new Nothing();
        ex.setVisible(true);
    }
    
    
    public class RoundedButton extends JButton {

    public RoundedButton(String text) {
        super(text);
        setFocusPainted(false); // Remove focus border
        setContentAreaFilled(false); // Remove the content area
        setBorderPainted(false); // Remove default border
        setOpaque(false); // Make background transparent
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Enable anti-aliasing for smooth edges
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set button color
        if (getModel().isPressed()) {
            g2d.setColor(new Color(100, 150, 255)); // Color when pressed
        } else if (getModel().isRollover()) {
            g2d.setColor(new Color(137, 207, 240)); // Hover color
        } else {
            g2d.setColor(new Color(173, 216, 230)); // Default color
        }

        // Draw the rounded button
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

        // Draw the button text
        g2d.setColor(Color.BLACK);
        FontMetrics fm = g2d.getFontMetrics();
        String text = getText();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();
        int textX = (getWidth() - textWidth) / 2;
        int textY = (getHeight() + textHeight) / 2 - 3;
        g2d.drawString(text, textX, textY);

        g2d.dispose(); // Release resources
        //super.paintComponent(g);
    }
}
}
