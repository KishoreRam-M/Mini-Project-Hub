
import java.util.LinkedHashSet;
import java.util.Scanner;

public class StudentInformation {
   Scanner scanner;
   private int studentId;
   private String studentName;
   private String studentDateOfBirth;
   private String studentPhoneNo;
   private String studentAddress;
   LinkedHashSet<Integer> Id;
   LinkedHashSet<String> PhNo;
   LinkedHashSet<String> Addr;

   public StudentInformation() {
      this.scanner = new Scanner(System.in);
      this.Id = new LinkedHashSet();
      this.PhNo = new LinkedHashSet();
      this.Addr = new LinkedHashSet();
   }

   public static void main(String[] args) {
      StudentInformation student1 = new StudentInformation();

      String response;
      do {
         student1.toGetcollectStudentDetails();
         System.out.println("Do you want to enter details for another student? (yes/no):");
         response = student1.scanner.nextLine();
      } while(!response.equalsIgnoreCase("no"));

   }

   public void toGetcollectStudentDetails() {
      System.out.println("Enter Your Id:");
      this.studentId = this.scanner.nextInt();
      this.scanner.nextLine();

      while(!isUnique(this.Id, this.studentId) || !isVerify(this.Id, this.studentId)) {
         if (!isUnique(this.Id, this.studentId)) {
            System.out.println("Enter a new Id.");
            this.studentId = this.scanner.nextInt();
            this.scanner.nextLine();
         }

         if (!isVerify(this.Id, this.studentId)) {
            System.out.println("Enter Your Id !:");
            this.studentId = this.scanner.nextInt();
            this.scanner.nextLine();
         }
      }

      System.out.println("Enter Your Name:");
      this.studentName = this.scanner.nextLine();
      System.out.println("Enter your DateOfBirth (year-month-day):");
      this.studentDateOfBirth = this.scanner.nextLine();
      System.out.println("Enter your Phone No:");
      this.studentPhoneNo = this.scanner.nextLine();

      while(!isUnique(this.PhNo, this.studentPhoneNo) || !isVerify(this.PhNo, this.studentPhoneNo)) {
         if (!isUnique(this.PhNo, this.studentPhoneNo)) {
            System.out.println("Enter your Phone No:");
            this.studentPhoneNo = this.scanner.nextLine();
         }

         if (!isVerify(this.PhNo, this.studentPhoneNo)) {
            System.out.println("Enter your Phone No: ");
            this.studentPhoneNo = this.scanner.nextLine();
         }
      }

      System.out.println("Enter the Student Address:");

      for(this.studentAddress = this.scanner.nextLine(); !isUnique(this.Addr, this.studentAddress); this.studentAddress = this.scanner.nextLine()) {
         System.out.println("Address already exists. Enter a unique address:");
      }

   }

   public static <T> boolean isUnique(LinkedHashSet<T> obj, T value) {
      if (obj.contains(value)) {
         return false;
      } else {
         obj.add(value);
         return true;
      }
   }

   public static <T> boolean isVerify(LinkedHashSet<T> obj, T value) {
      if (value instanceof Integer) {
         return value.toString().length() == 5;
      } else if (value instanceof String) {
         return ((String)value).length() == 10;
      } else {
         return false;
      }
   }
}
