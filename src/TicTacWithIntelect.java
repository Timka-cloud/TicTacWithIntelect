import java.util.Random;
import java.util.Scanner;

public class TicTacWithIntelect {
    private static char humanSigh = 'X';
    private static char aiSigh = '0';
    private static char emptySymbol = '_';
    private static final Scanner scanner = new Scanner(System.in);
    //AI input
    private static final Random random = new Random();
    //fieldSizeX
    private static int fieldSizeX;
    //fieldSizeY
    private static int fieldSizeY;
    //field
    private static char[][] field;

    private static void initField() {
        fieldSizeX = 3;
        fieldSizeY = 3;
        field = new char[fieldSizeX][fieldSizeY];
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                field[i][j] = emptySymbol;
            }
        }
    }

    private static void printField() {
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void humanTurn() {
        int x;
        int y;
        do {
            System.out.print("Provide X and Y coordinates between 1 to " + fieldSizeX + " ");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[x][y] = humanSigh;


    }
    public static void setSym(int x, int y, char sym) {
        field[x][y] = sym;
    }


    private static void aiTurn() {
        //Ищкем выигрышный ход компьютера
        for (int i = 0; i < fieldSizeX; i++)
            for (int j = 0; j < fieldSizeY; j++) {
                if (isCellValid(i, j)) {
                    setSym(i, j, aiSigh);
                    if (chechWin(aiSigh)) return;
                    setSym(i, j, emptySymbol);
                }
            }


        //Проверим игрока а нет ли у него будующего выигрошного хода
        for (int i = 0; i < fieldSizeX; i++)
            for (int j = 0; j < fieldSizeY; j++) {
                if (isCellValid(i, j)) {
                    setSym(i, j, humanSigh);
                    if (chechWin(humanSigh)) {
                        setSym(i, j, aiSigh);
                        return;
                    }
                    setSym(i, j, emptySymbol);
                }
            }


        int x;
        int y;


        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);

        } while (!isCellEmpty(x, y));

        field[x][y] = aiSigh;
    }

    private static boolean isCellEmpty(int x, int y) {

        return field[x][y] == emptySymbol;
    }


    public static boolean isCellValid(int x, int y) {
        if (y < 0 || x < 0 || y > fieldSizeX - 1 || x > fieldSizeY - 1) {
            return false;
        }
        return field[x][y] == emptySymbol;
    }

    private static boolean checkDraw() {
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                if (field[i][j] == emptySymbol) {
                    return false;
                }
            }

        }
        return true;
    }

    private static boolean chechWin(char c) {
        int countDiagonalA = 0;
        int countDiagonalB = 0;
        for (int i = 0; i < field.length; i++) {
            if (field[i][0] == c && field[i][1] == c && field[i][2] == c) return true;
        }
        for (int i = 0; i < field.length; i++) {
            if (field[0][i] == c && field[1][i] == c && field[2][i] == c) return true;
        }
        for (int i = 0; i < field.length; i++) {
            if(field[i][i] == c){
                countDiagonalA++;
                if(countDiagonalA == field.length) return true;

            }
            else {
                countDiagonalA = 0;
            }
        }
        for (int i = 0; i < field.length; i++) {
            if(field[i][field.length - 1 - i] == c){
                countDiagonalB++;
                if(countDiagonalB == field.length) return true;
            } else{
                countDiagonalB = 0;
            }
        }
        return false;
    }




    public static void main (String[]args){
        initField();
        printField();
        while (true){
            humanTurn();
            printField();
            if(chechWin(humanSigh)){
                System.out.println("Human won");
                break;
            }
            if(checkDraw()){
                System.out.println("Draw");
                break;
            }
            System.out.println("======");


            aiTurn();

            printField();
            if(chechWin(aiSigh)){
                System.out.println("AI won");
                break;
            }
            if(checkDraw()){
                System.out.println("Draw");
                break;
            }
        }

    }
}
