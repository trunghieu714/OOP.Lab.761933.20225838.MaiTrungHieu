import java.util.Arrays;
import java.util.Scanner;

public class sapxepmang {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Bạn muốn nhập mảng từ bàn phím (1) hay sử dụng mảng cố định (2)?");
        int choice = 0;
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
        }

        double[] my_array;

        if (choice == 1) {
            System.out.println("Nhập số lượng phần tử của mảng: ");
            int n = scanner.nextInt();
            my_array = new double[n];
            System.out.println("Nhập các phần tử của mảng: ");
            for (int i = 0; i < n; i++) {
                my_array[i] = scanner.nextDouble();
            }
        } else {
            // Mảng cố định như trong ví dụ đề bài
            my_array = new double[]{1789, 2035, 1899, 1456, 2013};
            System.out.println("Sử dụng mảng cố định: " + Arrays.toString(my_array));
        }

        // Sắp xếp mảng
        Arrays.sort(my_array);

        // Tính tổng và trung bình
        double sum = 0;
        for (double num : my_array) {
            sum += num;
        }
        double average = sum / my_array.length;

        // Hiển thị kết quả
        System.out.println("Mảng sau khi sắp xếp: " + Arrays.toString(my_array));
        System.out.println("Tổng các phần tử: " + sum);
        System.out.println("Giá trị trung bình: " + average);

        scanner.close();
    }
}
