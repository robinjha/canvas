package server;

public class FrontLogoProtocol {

    private final int width = 30;
    private final int length = 30;
    final int[][] dir = {{0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}};
    private final int initX = 15;
    private final int initY = 15;
    private char[][] board;
    private int currentDirIndex = 0;
    private String mode;
    private Point currIndex;

    public FrontLogoProtocol() {
        this.board = new char[length][width];
        currIndex = new Point(initX, initY);
        this.mode = "draw";
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public int getInitX() {
        return initX;
    }

    public int getInitY() {
        return initY;
    }

    public int getCurrentDirIndex() {
        return currentDirIndex;
    }

    public void setCurrentDirIndex(int currentDirIndex) {
        this.currentDirIndex = currentDirIndex;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Point getCurrIndex() {
        return currIndex;
    }

    public void setCurrIndex(Point currIndex) {
        this.currIndex = currIndex;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public String processInput(String input) {

        String output = null;

        final String command = input.trim().toLowerCase();
        if(command.contains("right") || command.contains("left")) {
            changeDirection(command, getNumber(command));
        } else if(command.contains("steps")) {
            moveSteps(getNumber(command));
        } else if (command.equals("coord")) {
            output = currIndex.toString();
        } else if (command.equals("draw")) {
            this.mode = "draw";
        } else if(command.equals("hover")) {
            this.mode = "hover";
        } else if (command.equals("eraser")) {
            this.mode = "eraser";
        } else if (command.equals("clear")) {
            board = new char[30][30];
        } else if (command.equals("render")) {
            output = printBoard();
        }
        return output;
    }

    private void changeDirection(String direction, int N) {

        if(direction.contains("left")) {
            currentDirIndex = (currentDirIndex+8-N)%8;
        } else {
            currentDirIndex = (currentDirIndex+N)%8;
        }
    }

    private String printBoard() {
        StringBuilder sb = new StringBuilder();
        sb.append(BoxSide.TOPLEFT.getC());
        for(int i = 0; i < width; i++){
            sb.append(BoxSide.HORIZONTAL.getC());
        }
        sb.append(BoxSide.TOPRIGHT.getC());
        sb.append("\r\n");

        for (int r = 0; r < board.length; r++) {
            sb.append(BoxSide.VERTICAL.getC());
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] == '*') {
                    sb.append('*');
                } else {
                    sb.append(' ');
                }
            }
            sb.append(BoxSide.VERTICAL.getC());
            sb.append("\r\n");
        }
        sb.append(BoxSide.BOTTOMLEFT.getC());
        for(int i = 0; i < width; i++){
            sb.append(BoxSide.HORIZONTAL.getC());
        }
        sb.append(BoxSide.BOTTOMRIGHT.getC());
        sb.append("\r\n");
        return String.valueOf(sb);
    }

    private void moveSteps(int N){
        for(int i = 0; i < N; i++){
            if(mode.equals("draw")) {
                board[currIndex.getY()][currIndex.getX()] = '*';
            }
            if(mode.equals("eraser")) {
                board[currIndex.getY()][currIndex.getX()] = ' ';
            }

            int[] moveDir = dir[currentDirIndex];
            switch (currentDirIndex) {
                case 0:
                    if (currIndex.getY() > 0) {
                        setNewCoordinates(moveDir);
                        break;
                    }
                case 1:
                    if (currIndex.getY() > 0 && currIndex.getX() < this.width - 1) {
                        setNewCoordinates(moveDir);
                        break;
                    }
                case 2:
                    if(currIndex.getX() < width-1) {
                        setNewCoordinates(moveDir);
                        break;
                    }
                case 3:
                    if(currIndex.getX() < width-1 && currIndex.getY() < length-1){
                        setNewCoordinates(moveDir);
                        break;
                    }
                case 4:
                    if(currIndex.getY() < length-1){
                        setNewCoordinates(moveDir);
                        break;
                    }
                case 5:
                    if(currIndex.getX() > 0 && currIndex.getY() < length-1){
                        setNewCoordinates(moveDir);
                        break;
                    }
                case 6:
                    if(currIndex.getX() > 0){
                        setNewCoordinates(moveDir);
                        break;
                    }
                case 7:
                    if(currIndex.getX() > 0 && currIndex.getY() > 0){
                        setNewCoordinates(moveDir);
                        break;
                    }
            }

        }
    }

    private int getNumber(String cmd){
        String[] tokens = cmd.split(" ");
        if(tokens.length == 1) {
            return 1;
        }
        int num = Integer.parseInt(tokens[1]);
        return num > 0 ? num : 0;

    }

    private void setNewCoordinates(int[] moveDir){
        currIndex.setX(currIndex.getX()+moveDir[0]);
        currIndex.setY(currIndex.getY()+moveDir[1]);
    }

}
