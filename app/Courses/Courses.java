package app.Courses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.w3c.dom.ranges.RangeException;

public final class Courses {
   public final HashMap<String, ArrayList<String>> registeredCourses = new HashMap<>();
   public final HashMap<String, String> courseFaculties = new HashMap<>();

   public void addStudent(String studentName) {
      if (registeredCourses.containsKey(studentName))
         throw new IndexOutOfBoundsException("Student already registered with the Name");
      registeredCourses.put(studentName, new ArrayList<>());
   }

   public void addFaculty(String courseName, String facultyName) {
      if (courseFaculties.containsKey(courseName))
         throw new IndexOutOfBoundsException("Course is already taught by some Faculty");
      courseFaculties.put(courseName, facultyName);
   }

   public void registerCourse(String studentName, String courseName) {
      if (!registeredCourses.containsKey(studentName))
         throw new IndexOutOfBoundsException("Student Record not found");
      if (!courseFaculties.containsKey(courseName))
         throw new IndexOutOfBoundsException("Course Record not found");

      ArrayList<String> studentCourses = registeredCourses.get(studentName);
      if (studentCourses.size() >= 3)
         throw new RangeException((short) 3, "A Student can register a Maximum of 3 courses only");
      studentCourses.add(courseName);
   }

   public List<String> fetchFaculties(String studentName) {
      if (!registeredCourses.containsKey(studentName))
         throw new IndexOutOfBoundsException("Student Record not found");

      return registeredCourses
         .get(studentName)
         .stream()
         .map(courseFaculties::get)
         .collect(Collectors.toList());
   }
}
