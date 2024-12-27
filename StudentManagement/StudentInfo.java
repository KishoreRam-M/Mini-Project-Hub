import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class StudentInfo {
    private final Scanner scanner;
    private int studentId;
    private String studentName;
    private Date studentDateOfBirth;
    private String studentPhoneNo;
    private String studentAddress;
    private String studentDepartment; // New department field
    private final LinkedHashSet<Integer> Id;
    private final LinkedHashSet<String> PhNo;
    private final LinkedHashSet<String> Addr;
    private final LinkedHashSet<String> Departments; // New set for departments

    public StudentInfo() {
        this.scanner = new Scanner(System.in);
        this.Id = new LinkedHashSet<>();
        this.PhNo = new LinkedHashSet<>();
        this.Addr = new LinkedHashSet<>();
        this.Departments = new LinkedHashSet<>(); // Initialize the department set
    }

    public static void main(String[] args) {
        StudentInfo student1 = new StudentInfo();
        String response;

        // Repeatedly collect student details until the user chooses "no"
        do {
            student1.collectStudentDetails();
            System.out.println("Do you want to enter details for another student? (yes/no):");
            response = student1.scanner.nextLine();
        } while (!response.equalsIgnoreCase("no"));
    }

    public void collectStudentDetails() {
        studentId = getValidInput("Enter Your ID (5 digits):", Id, true, Integer.class);
        studentName = getStringInput("Enter Your Name:");
        studentDateOfBirth = getDateInput("Enter your Date of Birth (yyyy-MM-dd):");
        studentPhoneNo = getValidInput("Enter your Phone No (10 digits):", PhNo, false, String.class);
        studentAddress = getUniqueInput("Enter the Student Address:", Addr);
        studentDepartment = getDepartmentInput("Enter your Department:"); // Collect department
    }

    // Method to get a valid input (ID or Phone Number) with a custom validation check
    public <T> T getValidInput(String prompt, LinkedHashSet<T> set, boolean isNumeric, Class<T> type) {
        T value;
        while (true) {
            System.out.println(prompt);
            if (isNumeric) {
                value = type.cast(scanner.nextInt());
                scanner.nextLine(); // Consume the newline
            } else {
                value = type.cast(scanner.nextLine());
            }

            if (isUnique(set, value) && isVerify(value)) {
                break;
            } else {
                System.out.println("Invalid input or duplicate value. Try again.");
            }
        }
        return value;
    }

    // Method to get a unique string input (like address)
    public String getUniqueInput(String prompt, LinkedHashSet<String> set) {
        String value;
        while (true) {
            System.out.println(prompt);
            value = scanner.nextLine();

            if (isUnique(set, value)) {
                break;
            } else {
                System.out.println("Address already exists. Enter a unique address:");
            }
        }
        return value;
    }

    // Method to get the department input with validation
    public String getDepartmentInput(String prompt) {
        String value;
        while (true) {
            System.out.println(prompt);
            value = scanner.nextLine();

            if (isUnique(Departments, value)) {
                break; // If the department is unique, break the loop
            } else {
                System.out.println("Department already exists. Enter a unique department:");
            }
        }
        return value;
    }

    // Generic method to check uniqueness in a LinkedHashSet
    public static <T> boolean isUnique(LinkedHashSet<T> set, T value) {
        if (set.contains(value)) {
            return false;
        } else {
            set.add(value);
            return true;
        }
    }

    // Method to verify input validity based on type (length checks for phone numbers and IDs)
    public static <T> boolean isVerify(T value) {
        if (value instanceof Integer) {
            return value.toString().length() == 5; // ID length check
        } else if (value instanceof String) {
            return ((String) value).length() == 10; // Phone number length check
        }
        return false;
    }

    // Method to get a valid date input from the user
    public Date getDateInput(String prompt) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false); // Strict parsing
        Date date;

        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine();

            try {
                date = sdf.parse(input);
                break;
            } catch (ParseException e) {
                System.out.println("Invalid date format! Please use yyyy-MM-dd.");
            }
        }
        return date;
    }

    // Utility method to read a string input
    public String getStringInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }
}
