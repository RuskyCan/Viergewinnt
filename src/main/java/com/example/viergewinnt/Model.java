public class Model {

    private static final int NUMBER_OF_ROWS = 6;
    private static final int NUMBER_OF_COLUMNS = 7;

    private int[][] matrix = new int[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
    private String[] names = new String[2];
    private String[] colors = new String[2];
    private int acting_player = 1;

    public Model(String name0, String name1, String color0, String color1) {
        this.names[0] = name0;
        this.names[1] = name1;
        this.colors[0] = color0;
        this.colors[1] = color1;
    }

    public String getActingPlayer() {
        return names[acting_player - 1];
    }

    public String getFieldColor(int iRow, int iColumn) {
        int player = matrix[iRow][iColumn];
        if (player == 1) {
            return colors[0];
        } else if (player == 2) {
            return colors[1];
        } else {
            return "WHITE";
        }
    }

    public int getFieldContent(int iRow, int iColumn) {
        return matrix[iRow][iColumn];
    }

    public int getActingPlayerNumber() {
        return acting_player;
    }

    public int getNumberOfRows() {
        return NUMBER_OF_ROWS;
    }

    public int getNumberOfColumns() {
        return NUMBER_OF_COLUMNS;
    }

    public boolean Move(int iColumn) {
        if (iColumn < 0 || iColumn >= NUMBER_OF_COLUMNS) {
            return false;
        }

        for (int iRow = NUMBER_OF_ROWS - 1; iRow >= 0; iRow--) {
            if (matrix[iRow][iColumn] == 0) {
                matrix[iRow][iColumn] = acting_player;
                return true;
            }
        }
        return false;
    }

    public String Winner() {
        if (isGameWon(acting_player)) {
            return names[acting_player - 1];
        }
        boolean full = true;
        for (int c = 0; c < NUMBER_OF_COLUMNS; c++) {
            if (matrix[0][c] == 0) {
                full = false;
                break;
            }
        }
        if (full) return "Unentschieden";

        return null;
    }

    private boolean checkForQuadruplet(int playernumber, int iStartingRow, int iStartingColumn, int dx, int dy) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int r = iStartingRow + i * dy;
            int c = iStartingColumn + i * dx;

            if (r >= 0 && r < NUMBER_OF_ROWS && c >= 0 && c < NUMBER_OF_COLUMNS) {
                if (matrix[r][c] == playernumber) {
                    count++;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        return count >= 4;
    }

    public boolean isGameWon(int playernumber) {
        for (int c = 0; c < NUMBER_OF_COLUMNS; c++) {
            for (int r = 0; r <= NUMBER_OF_ROWS - 4; r++) {
                if (checkForQuadruplet(playernumber, r, c, 0, 1)) return true;
            }
        }
        for (int r = 0; r < NUMBER_OF_ROWS; r++) {
            for (int c = 0; c <= NUMBER_OF_COLUMNS - 4; c++) {
                if (checkForQuadruplet(playernumber, r, c, 1, 0)) return true;
            }
        }
        for (int r = 3; r < NUMBER_OF_ROWS; r++) {
            for (int c = 0; c <= NUMBER_OF_COLUMNS - 4; c++) {
                if (checkForQuadruplet(playernumber, r, c, 1, -1)) return true;
            }
        }
        for (int r = 0; r <= NUMBER_OF_ROWS - 4; r++) {
            for (int c = 0; c <= NUMBER_OF_COLUMNS - 4; c++) {
                if (checkForQuadruplet(playernumber, r, c, 1, 1)) return true;
            }
        }

        return false;
    }

    public void nextPlayer() {
        acting_player = (acting_player % 2) + 1;
    }
}