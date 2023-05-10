import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Multithreading {
    public static int month(Scanner read){
        int choice = 0;
        while(choice <= 0 || choice > 12) {
            System.out.println("Please Choose One of the Following Months:");
            System.out.println("1: January");
            System.out.println("2: February");
            System.out.println("3: March");
            System.out.println("4: April");
            System.out.println("5: May");
            System.out.println("6: June");
            System.out.println("7: July");
            System.out.println("8: August");
            System.out.println("9: September");
            System.out.println("10: October");
            System.out.println("11: November");
            System.out.println("12: December\n");
            choice = read.nextInt();
            if (choice == 2) {
                while (choice != 1 && choice != 2) {
                    System.out.println("Is it a leap year? (1 for yes, 2 for no)");
                    choice = read.nextInt();
                    if (choice == 1) {
                        return 29;
                    } else if (choice == 2) {
                        return 28;
                    } else {
                        System.out.println("Incorrect input, please answer the prompt with a valid selection");
                    }
                }
            } else if ((choice <= 7 && (choice % 2 == 1)) || (choice > 7 && (choice % 2 == 0))) {
                return 31;
            } else if (((choice <= 7 && (choice % 2 == 0)) || (choice > 7 && (choice % 2 == 1))) && (choice != 7)) {
                return 30;
            } else {
                System.out.println("Incorrect input, please answer the prompt with a valid selection");
            }
        }
        return 31;
    }

    public static void print(double[] array1, Double[] array2, int month, Scanner read){
        System.out.println("\nTemperature data collected from array 1:");
        for (int x = 0; x < month; x++) {
            System.out.printf("Temperature reading #%d: %.2f\n", x+1, array1[x]);
        }
        System.out.println("\nType 1 When you Wish to Proceed");
        read.nextInt();
        System.out.println("\nTemperature data collected from array 2:");
        for (int x = 0; x < month; x++) {
            System.out.printf("Temperature reading #%d: %.2f\n", x+1, array2[x]);
        }
    }

    public static double[] input(int month, Scanner read){
        double[] array1 = new double[month];
        for (int x = 0; x < month; x++) {
            System.out.printf("Temperature reading #%d: ", x + 1);
            array1[x] = read.nextDouble();
        }
        return array1;
    }


    public static void main(String[] args) throws InterruptedException {
        double[] array1;
        Double[] array2;
        Scanner read = new Scanner(System.in);
        int month = month(read);

        System.out.println("Enter temperature readings for the Month:");
        array1= input(month, read);
        array2 = Arrays.stream(array1).boxed().toArray(Double[]::new);

        Thread t1 = new Thread(() -> Arrays.sort(array1));
        Thread t2 = new Thread(() -> Arrays.sort(array2, Collections.reverseOrder()));

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        print(array1, array2, month, read);
    }
}
