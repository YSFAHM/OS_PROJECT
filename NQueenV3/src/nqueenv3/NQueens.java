
import java.util.ArrayList;
public class NQueens implements Runnable{
    private  int[][] board;
    private final   int boardSize;
    private final BoardGUI newBoard;
    ArrayList<Thread> ThreadList = new ArrayList();
    Object sync = new Object();
    private boolean solved=false;
    private int updateTime;
    private String report;
    private int selectedColumn =0;
    public NQueens(int size,int updateTime,BoardGUI newBoard) {
        this.updateTime=updateTime;
        this.newBoard=newBoard;
        this.boardSize=size;
        
        newBoard.setVisible(true);
        board = new int[boardSize][boardSize];
        prepareBoard(board);
    }
    private void prepareBoard(int [][] b){
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                b[i][j]=0;
            }
        }
    }
    public void printBoard(int [][] b) throws InterruptedException{

        synchronized(sync){

                Thread.sleep(updateTime);
                newBoard.updateBoard(b,report);

            
        }
    }
    private boolean isLegal(int [][] b,int row,int col){
        
        return (checkDiagonal(b,row,col) && checkUpper(b,row,col));
    }
    private boolean checkUpper(int [][] b,int row,int col){
        for (int i = row-1; i >= 0; i--) {
            if(b[i][col]==1)
                return false;
        }
        return true;
    }
    private boolean checkDiagonal(int [][] b,int row,int col){
        int r=row-1,c=col-1;
        
        for (; r >= 0 && c>=0; r--,c--) {
            if(b[r][c]==1)
                return false;
        }
        r=row-1;
        c=col+1;
        
        for (; r >= 0 &&  c < boardSize; r--,c++) {
            if(b[r][c]==1)
                return false;
        }
        return true;
    }
    private boolean placeQueens(int row,int [][] local_board) throws InterruptedException{
        //Base case
        if (row == boardSize)
            return true;
        
        for (int i = 0; i < boardSize; i++) {
            if(Thread.interrupted()) throw new InterruptedException();
            if(isLegal(local_board,row, i))
            {
                synchronized (sync) {  //check for it
                    if(!solved)
                    {

                        report="Thread "+Thread.currentThread().getName();
                        
                        printBoard(local_board);
                    }
                }  // check for it
                
                local_board[row][i]=1;
                
                //try the row after me (Recursion)
                if(placeQueens(row+1,local_board))
                    return true;
                //if Q after me is not possible try next col and reset position
                local_board[row][i]=0;
            }
        }
        return false;
    }
    private void terminateOthers(int id){
        for (int i = 0; i < boardSize; i++) {
            if(i != id)
            {
                ThreadList.get(i).interrupt();
            }
        }
    } 
    public void terminateAll(){
            for (Thread t : ThreadList){
                t.interrupt();
            }
  
    }
    public void solve(int column) throws InterruptedException{
        int[][] t_board = new int[boardSize][boardSize];
        prepareBoard(t_board);
        t_board[0][column]=1;
        boolean temp = placeQueens(1, t_board);
        
        synchronized (sync){
            
            if(temp && !Thread.interrupted())
            {
                solved=temp;
                board=t_board;
                terminateOthers(column);
                report="Thread "+Thread.currentThread().getName()+" Solved the Problem";
                printBoard(board);
                
            }
        }   
    }
    public void setUpdateTime(int updateTime){
     this.updateTime=updateTime;
    } 
    public void startQueen(){
        //Start all threads
        for (Thread t : this.ThreadList) {
            t.start();
        }
}
    public void initQueen(){
        //Create ( n ) Number of threads
        for (int i = 0; i < boardSize; i++) {
            this.ThreadList.add(new Thread(this,""+i));
        }
        
    }  
    public void joinAll(){
    for(Thread t : ThreadList){
        try {
            t.join();
        } catch (InterruptedException ex) {
            
        }
    }
}
    @Override
    public void run() {
        int myColumn;
        synchronized(this){
            myColumn = selectedColumn;
            selectedColumn++;
        }
        try {
            solve(myColumn);
            System.out.println("Thread done the job");
        } catch (InterruptedException ex) {
            System.out.println("Thread Interrupted");
        }
        
        
    }
}



