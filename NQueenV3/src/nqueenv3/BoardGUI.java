
import java.awt.Color;
import javax.swing.BorderFactory;
/**
 *
 * @author ysfah
 */
public class BoardGUI extends javax.swing.JFrame {

    /**
     * Creates new form BoardGUI
     */
    private int boardSize;
    private Color s1;
    private Color s2;
    public BoardGUI() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    public void setBoardColor(Color s1,Color s2){
        this.s1=s1;
        this.s2=s2;
            javax.swing.JButton buttonSquare;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                
                buttonSquare=buttonArray[i][j];
                buttonSquare.setBackground((i + j) % 2 == 0 ? s1 : s2);
            }
        }
        jPanel1.revalidate();
        jPanel1.repaint();
        
    }
    public void setBoard(int boardSize, Color s1, Color s2) {
        this.s1=s1;
        this.s2=s2;
        this.boardSize=boardSize;
        buttonArray = new javax.swing.JButton[boardSize][boardSize];
        jPanel1.setLayout(new java.awt.GridLayout(boardSize, boardSize));
        jPanel1.removeAll();
        System.gc();
        jPanel1.revalidate();
        jPanel1.repaint();

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                javax.swing.JButton buttonSquare = new javax.swing.JButton();
                buttonArray[i][j] = buttonSquare;
                buttonSquare.setBackground((i + j) % 2 == 0 ? s1 : s2);
                buttonSquare.setSize(60,60);
                buttonSquare.setBorder(BorderFactory.createEmptyBorder());
                jPanel1.add(buttonSquare);
            }
        }
        //setSize(60 * boardSize, 62 * boardSize);
    }
    public void updateBoard(int[][] myBoard,String report){
        javax.swing.JButton buttonSquare;
                for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                buttonSquare=buttonArray[i][j];
                if(myBoard[i][j]==1){
                    buttonSquare.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nqueenv3/crown.png")));
                    buttonSquare.setBackground(Color.RED);
                }else{
                buttonSquare.setIcon(null);
                buttonSquare.setBackground((i + j) % 2 == 0 ? s1 : s2);
                }
            }
        }
                workingThread.setText(report);
        jPanel1.revalidate();
        jPanel1.repaint();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        workingThread = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BoardGUI");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setToolTipText("");
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        workingThread.setText("Working Thread");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(workingThread, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(workingThread)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private javax.swing.JButton[][] buttonArray;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel workingThread;
    // End of variables declaration//GEN-END:variables
}
