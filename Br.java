import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Br {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("WELCOME TO KRMK BUS RESERVATION PLATFORM:");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("CHOOSE YOUR BUS TYPE:");
        System.out.println("1. AC SLEEPER\n\t2. AC SEATER\n\t3. SLEEPER\n\t4. SEATER\n\t");

        int busType = scanner.nextInt();
        Bus bus = new Bus(busType, scanner);
        bus.switcher();
        scanner.close();
    }

    public static class Bus {
        private int ch;
        private int capacity;
        private Scanner scanner;

        public Bus(int ch, Scanner scanner) {
            this.ch = ch;
            this.scanner = scanner;
        }

        public void switcher() {
            String[] names = {"1. kdnl\n\t2. chennai\n\t3. tenkasi"};
            switch (ch) {
                case 1: {
                    System.out.println("Choose your bus:\n\t1. kingover\n\t2. love error");
                    int n = scanner.nextInt();
                    switch (n) {
                        case 1: {
                            System.out.println("Total seats: 64\n\tFilled seats: 54");
                            System.out.println("1. Pay 1000rs\n\t2. Back\n\t");
                            int k = scanner.nextInt();
                            switch (k) {
                                case 1: {
                                    System.out.println("Your ticket is confirmed.");
                                    System.out.println("Thank you! Have a nice day.");
                                }
                                break;
                            }
                            break;
                        }
                        case 2: {
                            // Implement option 2 if needed
                            break;
                        }
                    }
                    break;
                }
                case 2: {
                    System.out.println("Choose your bus:\n\t1. kingover - 1.00pm\n\t2. love error - 7.00pm");
                    int n = scanner.nextInt();
                    switch (n) {
                        case 1: {
                            System.out.println("Total seats: 64\n\tFilled seats: 54");
                            System.out.println("1. Pay 1000rs\n\t2. Back\n\t");
                            int k = scanner.nextInt();
                            switch (k) {
                                case 1: {
                                    System.out.println("Your ticket is confirmed.");
                                    System.out.println("Thank you! Have a nice day.");
                                }
                                break;
                            }
                            break;
                        }
                        case 2: {
                            // Implement option 2 if needed
                            break;
                        }
                    }
                    break;
                }
                case 3: {
                    System.out.println("Choose your bus:\n\t1. kingover\n\t2. love error");
                    int n = scanner.nextInt();
                    switch (n) {
                        case 1: {
                            // Implement option 1 if needed
                            break;
                        }
                        case 2: {
                            // Implement option 2 if needed
                            break;
                        }
                    }
                    break;
                }
                case 4: {
                    System.out.println("Choose your bus:\n\t1. kingover\n\t2. love error");
                    int n = scanner.nextInt();
                    switch (n) {
                        case 1: {
                            select(scanner);
                            break;
                        }
                        case 2: {
                            select(scanner);
                            break;
                        }
                    }
                    break;
                }
            }
        }

        public static void select(Scanner scanner) {
            System.out.println("Choose your bus:");
            int no = scanner.nextInt();
            switch (no) {
                case 1:
                    System.out.println("Bus name: kingover");
                    break;
                case 2:
                    System.out.println("Bus name: love error");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
