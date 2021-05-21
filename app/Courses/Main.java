package app.Courses;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.w3c.dom.ranges.RangeException;

public final class Main {
   private static final Scanner S = new Scanner(System.in);

   private static final Pattern addStudentRegex = Pattern.compile("^ADD\\s\\w+$");
   private static final Pattern addFacultyRegex = Pattern.compile("^ADD\\s\\w+\\s\\w+$");
   private static final Pattern registerCourseRegex = Pattern.compile("^REGISTER\\s\\w+\\s\\w+$");
   private static final Pattern fetchRegex = Pattern.compile("^FETCH\\s\\w+$");

   public static void main(String[] args) {
      Courses courses = new Courses();

      System.out.println("Student Course Registration System :\n");
      System.out.println("\t(1) <ADD #S> :\tRegister a Student and add the record with no Courses");
      System.out.println("\t(5) <ADD #C #F> :\tRegister a Faculty for a given Course");
      System.out.println("\t(5) <REGISTER #S #C> :\tRegister the Course for the Student");
      System.out.println("\t(5) <FETCH #S> :\tFetch all the Faculties that teach the Student");
      System.out.println("\t(5) <DISPLAY> :\tDisplay all the details");
      System.out.println("\t(6) <END> :\tEnd the Simulation");

      while (true) {
         System.out.print("\nEnter a Command ::\t");
         String command = S.nextLine();

         try {
            if (command.equals("END")) {
               System.out.println("Terminating ...\n\n");
               break;
            }

            else if (addStudentRegex.matcher(command).matches()) {
               String studentName = command.substring(4);
               courses.addStudent(studentName);
            }

            else if (addFacultyRegex.matcher(command).matches()) {
               String[] tokens = command.split(" ");
               String courseName = tokens[1], facultyName = tokens[2];
               courses.addFaculty(courseName, facultyName);
            }

            else if (registerCourseRegex.matcher(command).matches()) {
               String[] tokens = command.split(" ");
               String studentName = tokens[1], courseName = tokens[2];
               courses.registerCourse(studentName, courseName);
            }

            else if (fetchRegex.matcher(command).matches()) {
               String studentName = command.substring(6);
               List<String> faculties = courses.fetchFaculties(studentName);
               System.out.println("Number of Courses Registered :\t" + faculties.size() + "\n");
               faculties.forEach(facultyName -> System.out.print("\t" + facultyName));
            }

            else
               throw new Exception("Invalid Command Specified");

         } catch (IndexOutOfBoundsException err) {
            System.out.println("Index Out of Bounds Error :\t" + err.getMessage());
         } catch (RangeException err) {
            System.out.println("Range Error :\t" + err.getMessage());
         } catch (Exception err) {
            System.out.println("Error :\t" + err.getMessage());
         }
      }
   }
}
