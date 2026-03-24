import javax.swing.JOptionPane;

public class ShowTwoNumbers {
    public static void main(String[] args) {
        String strNum1 = JOptionPane.showInputDialog(null, "Please input the first number: ", "Input", JOptionPane.INFORMATION_MESSAGE);
        String strNum2 = JOptionPane.showInputDialog(null, "Please input the second number: ", "Input", JOptionPane.INFORMATION_MESSAGE);

        double num1 = Double.parseDouble(strNum1);
        double num2 = Double.parseDouble(strNum2);

        String results = "First number: " + num1 + "\nSecond number: " + num2 + "\n";
        results += "Sum: " + (num1 + num2) + "\n";
        results += "Difference: " + (num1 - num2) + "\n";
        results += "Product: " + (num1 * num2) + "\n";

        if (num2 != 0) {
            results += "Quotient: " + (num1 / num2);
        } else {
            results += "Quotient: Cannot divide by zero!";
        }

        JOptionPane.showMessageDialog(null, results, "Calculation Results", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}