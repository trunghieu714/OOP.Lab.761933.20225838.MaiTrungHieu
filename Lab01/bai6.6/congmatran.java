import java.util.Scanner;

public class congmatran {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bạn muốn nhập ma trận từ bàn phím (1) hay sử dụng ma trận cố định (2)?");
        int choice = 0;
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
        }

        int rows, cols;
        double[][] matrix1, matrix2, sumMatrix;

        if (choice == 1) {
            System.out.print("Nhập số hàng của ma trận: ");
            rows = scanner.nextInt();
            System.out.print("Nhập số cột của ma trận: ");
            cols = scanner.nextInt();

            matrix1 = new double[rows][cols];
            matrix2 = new double[rows][cols];

            System.out.println("Nhập các phần tử của ma trận thứ nhất:");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    System.out.printf("matrix1[%d][%d] = ", i, j);
                    matrix1[i][j] = scanner.nextDouble();
                }
            }

            System.out.println("Nhập các phần tử của ma trận thứ hai:");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    System.out.printf("matrix2[%d][%d] = ", i, j);
                    matrix2[i][j] = scanner.nextDouble();
                }
            }
        } else {
            // Ma trận cố định
            rows = 2;
            cols = 3;
            matrix1 = new double[][]{{1, 2, 3}, {4, 5, 6}};
            matrix2 = new double[][]{{7, 8, 9}, {10, 11, 12}};
            System.out.println("Sử dụng ma trận cố định.");
        }

        sumMatrix = new double[rows][cols];

        // Cộng hai ma trận
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sumMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }

        // Hiển thị kết quả
        System.out.println("Ma trận thứ nhất:");
        displayMatrix(matrix1);
        System.out.println("Ma trận thứ hai:");
        displayMatrix(matrix2);
        System.out.println("Ma trận tổng:");
        displayMatrix(sumMatrix);

        scanner.close();
    }

    private static void displayMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double element : row) {
                System.out.print(element + "\t");
            }
            System.out.println();
        }
    }
}
