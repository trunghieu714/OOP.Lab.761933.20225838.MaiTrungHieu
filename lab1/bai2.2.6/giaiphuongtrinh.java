import java.util.Scanner;
public class giaiphuongtrinh{
public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Solve quadratic equation: ax^2 + bx + c = 0");
        System.out.print("Enter a: ");
        double a = scanner.nextDouble();
        System.out.print("Enter b: ");
        double b = scanner.nextDouble();
        System.out.print("Enter c: ");
        double c = scanner.nextDouble();

        if (a == 0) {
            // Giải phương trình bậc nhất bx + c = 0
            if (b == 0) {
                System.out.println(c == 0 ? "Infinite solutions." : "No solution.");
            } else {
                System.out.println("One solution: x = " + (-c / b));
            }
        } else {
            double delta = b * b - 4 * a * c;
            if (delta > 0) {
                double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                System.out.println("Two real roots: x1 = " + x1 + ", x2 = " + x2);
            } else if (delta == 0) {
                System.out.println("Double root: x = " + (-b / (2 * a)));
            } else {
                System.out.println("No real root.");
            }
        }
    }
    
}
