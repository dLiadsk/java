import java.util.Random;

public class MatrixOperations {

    public static void main(String[] args) {
        try {
            int studentId = 10;
            int C5 = 0; // Дія з матрицею (C = aB)
            int C7 = 3; // Тип елементів (int)
            int C11 = 10; // Дія з матрицею C (середнє значення)

            // Параметри матриці
            int rows = 7; // Кількість рядків
            int cols = 7; // Кількість стовпців
            int[][] matrixB = generateMatrix(rows, cols, 1, 10);
            int constantA = 10; // Константа a для обчислення C = aB

            // Виконання дії C = aB
            int[][] matrixC = scalarMultiply(matrixB, constantA);
            System.out.println("Матриця B:");
            printMatrix(matrixB);
            System.out.println("\nМатриця C (C = aB):");
            printMatrix(matrixC);

            // Обчислення середнього значення елементів матриці C
            double average = calculateMatrixAverage(matrixC);
            System.out.println("\nСереднє значення елементів матриці C: " + average);

        } catch (Exception e) {
            System.err.println("Помилка: " + e.getMessage());
        }
    }

    // Метод для генерації матриці з випадковими числами
    public static int[][] generateMatrix(int rows, int cols, int min, int max) {
        Random random = new Random();
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(max - min + 1) + min;
            }
        }
        return matrix;
    }

    // Метод для множення матриці на скаляр
    public static int[][] scalarMultiply(int[][] matrix, int scalar) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix[i][j] * scalar;
            }
        }
        return result;
    }

    // Метод для обчислення середнього значення елементів матриці
    public static double calculateMatrixAverage(int[][] matrix) {
        int sum = 0;
        int count = 0;
        for (int[] row : matrix) {
            for (int element : row) {
                sum += element;
                count++;
            }
        }
        return (double) sum / count;
    }

    // Метод для друку матриці
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + "\t");
            }
            System.out.println();
        }
    }
}
