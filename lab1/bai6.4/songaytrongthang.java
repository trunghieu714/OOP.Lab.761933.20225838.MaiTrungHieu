import java.util.Scanner;

public class songaytrongthang {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int month = -1;
        int year = -1;

        while (true) {
            System.out.println("Nhập tháng (Tên đầy đủ, viết tắt, 3 chữ cái, hoặc số): ");
            String monthInput = scanner.nextLine().trim();
            month = parseMonth(monthInput);

            if (month == -1) {
                System.out.println("Tháng không hợp lệ. Vui lòng nhập lại.");
                continue;
            }

            System.out.println("Nhập năm (Số không âm, nhập đầy đủ các chữ số): ");
            String yearInput = scanner.nextLine().trim();
            year = parseYear(yearInput);

            if (year == -1) {
                System.out.println("Năm không hợp lệ. Vui lòng nhập lại.");
                continue;
            }

            int days = getDaysInMonth(month, year);
            System.out.println("Số ngày của " + getMonthName(month) + " năm " + year + " là: " + days);
            break;
        }
        scanner.close();
    }

    private static int parseMonth(String input) {
        input = input.toLowerCase();
        switch (input) {
            case "january": case "jan.": case "jan": case "1": return 1;
            case "february": case "feb.": case "feb": case "2": return 2;
            case "march": case "mar.": case "mar": case "3": return 3;
            case "april": case "apr.": case "apr": case "4": return 4;
            case "may": case "5": return 5;
            case "june": case "jun.": case "jun": case "6": return 6;
            case "july": case "jul.": case "jul": case "7": return 7;
            case "august": case "aug.": case "aug": case "8": return 8;
            case "september": case "sept.": case "sep": case "9": return 9;
            case "october": case "oct.": case "oct": case "10": return 10;
            case "november": case "nov.": case "nov": case "11": return 11;
            case "december": case "dec.": case "dec": case "12": return 12;
            default: return -1;
        }
    }

    private static int parseYear(String input) {
        if (!input.matches("\\d+")) {
            return -1;
        }
        try {
            int year = Integer.parseInt(input);
            if (year >= 0) {
                return year;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
        return -1;
    }

    private static int getDaysInMonth(int month, int year) {
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return 0;
        }
    }

    private static String getMonthName(int month) {
        String[] months = {
            "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6",
            "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"
        };
        return months[month - 1];
    }
}
