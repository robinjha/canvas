package server;

public class CopyFrontLogoProtocol {

    class Point {
        private int x;
        private int y;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    final int width = 30;
    final int length = 30;
    char[][] board = new char[length][width];
    int[][] dir = {{0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}};
    //int[][] dir = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
    int currentDirIndex = 0;
    String mode = "draw";

    Point currIndex = new Point(15, 15);



//    String processInput(String input) {
//
//        if (input == null) {
//            return "Empty String sent!!";
//        }
//
//        //StringBuilder output = new StringBuilder();
//        String output = null;
//        System.out.println("INPUT IS: " + input);
//        String[] tokens = input.split(" ");
//        //System.out.println("TOKENS: " + Arrays.toString(tokens));
//        //for(int i = 0; i < tokens.length; i++) {
//            final String command = input.trim().toLowerCase();
//            if ("coord".equals(command)) {
//                output = currIndex.toString();
//            } else if ("draw".equals(command)) {
//                board[currIndex.x][currIndex.y] = '*';
//            } else if ("eraser".equals(command)) {
//                board[currIndex.x][currIndex.y] = ' ';
//            } else if ("steps".equals(command)) {
//                moveSteps(tokens[1]);
//            } else if ("right".equals(command) || "left".equals(command)) {
//                changeDirection(command, tokens[1]);
//            } else if ("clear".equals(command)) {
//                board = new char[30][30];
//            } else if ("render".equals(command)) {
//                //output.append(printBoard());
//                output = printBoard();
//            }
//        //}
//        return output;
//    }

    String processInput(String input) {

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

        if(direction.equals("left")) {
            //currentDirIndex = (currentDirIndex+8-N%8)%8;
            currentDirIndex = (currentDirIndex-N)%8;
        } else {
            //currentDirIndex = (currentDirIndex+N)%8;
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
        //for (int r = length-1; r >= 0; r--) {
            sb.append(BoxSide.VERTICAL.getC());
            //for (int c = width-1; c >= 0; c--) {
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
                board[currIndex.y][currIndex.x] = '*';
            }
            if(mode.equals("eraser")) {
                board[currIndex.y][currIndex.x] = ' ';
            }

            int[] moveDir = dir[currentDirIndex];
            switch (currentDirIndex) {
                case 0:
                    if (currIndex.y > 0) {
                        //setNewCoordinates(moveDir);
                        currIndex.setY(currIndex.y-1);
                        break;
                    }
                case 1:
                    if (currIndex.y > 0 && currIndex.x < this.width - 1) {
                        currIndex.setX(currIndex.x+1);
                        currIndex.setY(currIndex.y-1);
                        //setNewCoordinates(moveDir);
                        break;
                    }
                case 2:
                    if(currIndex.x < width-1) {
                        currIndex.setX(currIndex.x+1);
                        //setNewCoordinates(moveDir);
                        break;
                    }
                case 3:
                    if(currIndex.x < width-1 && currIndex.y < length-1){
                        currIndex.setX(currIndex.x+1);
                        currIndex.setY(currIndex.y+1);
                        //setNewCoordinates(moveDir);
                        break;
                    }
                case 4:
                    if(currIndex.y < length-1){
                        currIndex.setY(currIndex.y+1);
                        //setNewCoordinates(moveDir);
                        break;
                    }
                case 5:
                    if(currIndex.x > 0 && currIndex.y < length-1){
                        currIndex.setX(currIndex.x-1);
                        currIndex.setY(currIndex.y+1);
                        //setNewCoordinates(moveDir);
                        break;
                    }
                case 6:
                    if(currIndex.x > 0){
                        currIndex.setX(currIndex.x-1);
                        //setNewCoordinates(moveDir);
                        break;
                    }
                case 7:
                    if(currIndex.x > 0 && currIndex.y > 0){
                        currIndex.setX(currIndex.x-1);
                        currIndex.setY(currIndex.y-1);
                        //setNewCoordinates(moveDir);
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
        currIndex.setX(currIndex.x+moveDir[0]);
        currIndex.setY(currIndex.y+moveDir[1]);
    }

//    private void changeDirection(String direction, String N) {
//        int dirIndex = Integer.parseInt(N);
//        if(direction.equals("left")) {
//            if(dirIndex > 0 && currentDirIndex - dirIndex < 0) {
//                currentDirIndex = 0;
//            } else {
//                currentDirIndex = currentDirIndex - dirIndex;
//            }
//        } else {
//            if(dirIndex > 0 && currentDirIndex+dirIndex >= dir.length) {
//                currentDirIndex = dir.length-1;
//            } else {
//                currentDirIndex = currentDirIndex+dirIndex;
//            }
//        }
//    }

//    private void moveSteps(String N){
//        int stepCount = Integer.parseInt(N);
//
//        int[] moveDir = dir[currentDirIndex];
//        while(stepCount > 0) {
//            int newX = currIndex.x+moveDir[0];
//            int newY = currIndex.y+moveDir[1];
//
//            if(this.mode == "draw") {
//                board[newY][newX] = '*';
//            }
//            if(this.mode == "eraser") {
//                board[newY][newX] = ' ';
//            }
//
//            if(newX < 30 && newY < 30 && newX >= 0 && newY >= 0) {
//                currIndex.setX(currIndex.x + moveDir[0]);
//                currIndex.setY(currIndex.y + moveDir[1]);
//            }
//            stepCount--;
//        }
//    }
}