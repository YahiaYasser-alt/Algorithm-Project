import java.util.ArrayList;
import java.util.List;

public class DominoTiling {

    public record Cell(int row, int col) {}
    public record Domino(Cell first, Cell second) {}
    public record Result(boolean possible, List<Domino> dominoes) {}

    public static Result tileDominoes(int n, Cell missing1, Cell missing2){
        if (n<2 || n>8 || missing1.equals(missing2)){
            return new Result(false, new ArrayList<>());
        }

        boolean[][] covered = new boolean[n][n];
        covered[missing1.row][missing1.col] = true;
        covered[missing2.row][missing2.col] = true;

        List<Domino> dominoes = new ArrayList<>();
        boolean possible = solve(n, covered, dominoes);

        if(!possible){
            dominoes.clear();
        }
        return new Result(possible, dominoes);
    }
    private static boolean solve(int n, boolean[][] covered, List<Domino> dominoes){
        int row = -1;
        int col = -1;

        for (int r=0; r<n; r++){
            for (int c=0; c<n; c++){
                if(!covered[r][c]){
                    row = r;
                    col = c;
                    break;
                }
            }
            if (row != -1){
                break;
            }
        }
        if (row == -1){
            return true;
        }

        if(col + 1 < n && !covered[row][col+1]){
            covered[row][col+1] = true;
            covered[row][col] = true;
            dominoes.add(new Domino(new Cell(row,col), new Cell(row,col+1)));

            if(solve(n,covered, dominoes)){
                return true;
            }

            dominoes.remove(dominoes.size()-1);
            covered[row][col] = false;
            covered[row][col+1] = false;
        }

        if (row +1 < n && !covered[row+1][col]){
            covered[row+1][col] = true;
            covered[row][col] = true;
            dominoes.add(new Domino(new Cell(row,col), new Cell(row+1,col)));

            if(solve(n,covered, dominoes)){
                return true;
            }
            dominoes.remove(dominoes.size()-1);
            covered[row+1][col] = false;
            covered[row][col] = false;
        }
        return false;


    }

    public static void printResult(int n, Cell m1, Cell m2, Result res) {
        System.out.println("n = " + n + " | Missing: (" + m1.row() + "," + m1.col() + "), (" + m2.row() + "," + m2.col() + ")");
        System.out.println("Possible: " + res.possible());

        if (!res.possible()) {
            System.out.println("Dominoes: []\n");
            return;
        }

        String[][] board = new String[n][n];
        board[m1.row()][m1.col()] = " XX ";
        board[m2.row()][m2.col()] = " XX ";

        int count = 1;
        for (Domino d : res.dominoes()) {
            String tag = String.format(" D%02d", count++);
            board[d.first().row()][d.first().col()] = tag;
            board[d.second().row()][d.second().col()] = tag;
        }

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println("Dominoes: " + res.dominoes() + "\n");
    }

}
