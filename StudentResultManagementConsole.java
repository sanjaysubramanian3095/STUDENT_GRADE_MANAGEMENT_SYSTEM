import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StudentResultManagementConsole {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("==== Student Result Management System (Console) ====");
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Roll Number: ");
        String roll = sc.nextLine();

        System.out.print("Enter marks in Subject 1 (0-100): ");
        int sub1 = readMark(sc);

        System.out.print("Enter marks in Subject 2 (0-100): ");
        int sub2 = readMark(sc);

        System.out.print("Enter marks in Subject 3 (0-100): ");
        int sub3 = readMark(sc);

        int total = sub1 + sub2 + sub3;
        double average = total / 3.0;
        String grade = gradeFromAverage(average);

        System.out.println("\n==== Result Summary ====");
        System.out.println("Name: " + name);
        System.out.println("Roll No: " + roll);
        System.out.println("Total Marks: " + total);
        System.out.printf("Average: %.2f\n", average);
        System.out.println("Grade: " + grade);

        System.out.print("\nDo you want to save this result to a file? (y/n): ");
        String save = sc.nextLine().trim().toLowerCase();
        if (save.equals("y") || save.equals("yes")) {
            try (FileWriter fw = new FileWriter("result_" + roll + ".txt")) {
                fw.write("Name: " + name + "\n");
                fw.write("Roll No: " + roll + "\n");
                fw.write("Total: " + total + "\n");
                fw.write(String.format("Average: %.2f\n", average));
                fw.write("Grade: " + grade + "\n");
                System.out.println("Saved to file: result_" + roll + ".txt");
            } catch (IOException e) {
                System.out.println("Error saving file: " + e.getMessage());
            }
        }

        System.out.println("\nThank you for using the Student Result Management System!");
        sc.close();
    }

    private static int readMark(Scanner sc) {
        while (true) {
            try {
                int m = Integer.parseInt(sc.nextLine().trim());
                if (m < 0 || m > 100) {
                    System.out.print("Invalid mark. Enter a value between 0 and 100: ");
                } else return m;
            } catch (NumberFormatException e) {
                System.out.print("Please enter a numeric value: ");
            }
        }
    }

    private static String gradeFromAverage(double avg) {
        if (avg >= 90) return "A+";
        else if (avg >= 80) return "A";
        else if (avg >= 70) return "B";
        else if (avg >= 60) return "C";
        else if (avg >= 50) return "D";
        else return "Fail";
    }
}
