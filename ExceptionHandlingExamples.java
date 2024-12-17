package EXCEPTIONS;
import java.io.*;

import java.sql.*; // For SQLException example

public class ExceptionHandlingExamples {

    public static void main(String[] args) {
        System.out.println("Checked Exceptions:------------------");
        simulateIOException();
        simulateFileNotFoundException();
        simulateEOFException();
        simulateSQLException();
        simulateClassNotFoundException();

        System.out.println("Unchecked Exceptions:------------------");
        simulateArithmeticException();
        simulateNullPointerException();
        simulateArrayIndexOutOfBoundsException();
        simulateClassCastException();
        simulateIllegalArgumentException();
        simulateNumberFormatException();
    }

    // Checked Exceptions
    // 1. IOException Example
    public static void simulateIOException() {
        System.out.println("1. IOException Example:");
        try {
            // Attempt to read a non-existent file
            FileReader file = new FileReader("nonexistent_file.txt");
            BufferedReader reader = new BufferedReader(file);
            System.out.println(reader.readLine());
        } catch (IOException e) {
            System.out.println("IOException caught: " + e.getMessage());
        }
    }

    // 2. FileNotFoundException Example
    public static void simulateFileNotFoundException() {
        System.out.println("\n2. FileNotFoundException Example:");
        try {
            // FileNotFoundException occurs here
            FileInputStream fis = new FileInputStream("file_does_not_exist.txt");
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException caught: " + e.getMessage());
        }
    }

    // 3. EOFException Example
    public static void simulateEOFException() {
        System.out.println("3. EOFException Example:");
        try {
            // Simulate EOFException
            ByteArrayInputStream input = new ByteArrayInputStream(new byte[]{1, 2, 3});
            ObjectInputStream ois = new ObjectInputStream(input);
            ois.readInt(); // Read beyond content
        } catch (EOFException e) {
            System.out.println("EOFException caught: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException caught: " + e.getMessage());
        }
    }

    // 4. SQLException Example
    public static void simulateSQLException() {
        System.out.println("4. SQLException Example:");
        try {
            // Simulate a database connection failure
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nonexistent_db", "user", "password");
        } catch (SQLException e) {
            System.out.println("SQLException caught: " + e.getMessage());
        }
    }

    // 5. ClassNotFoundException Example
    public static void simulateClassNotFoundException() {
        System.out.println("5. ClassNotFoundException Example:");
        try {
            // Attempt to load a non-existent class
            Class.forName("NonExistentClass");
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException caught: " + e.getMessage());
        }
    }

    // Unchecked Exceptions
    // 6. ArithmeticException Example
    public static void simulateArithmeticException() {
        System.out.println("6. ArithmeticException Example:");
        try {
            int result = 10 / 0; // Division by zero
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException caught: " + e.getMessage());
        }
    }

    // 7. NullPointerException Example
    public static void simulateNullPointerException() {
        System.out.println("7. NullPointerException Example:");
        try {
            String str = null;
            System.out.println(str.length()); // NullPointerException
        } catch (NullPointerException e) {
            System.out.println("NullPointerException caught: " + e.getMessage());
        }
    }

    // 8. ArrayIndexOutOfBoundsException Example
    public static void simulateArrayIndexOutOfBoundsException() {
        System.out.println("8. ArrayIndexOutOfBoundsException Example:");
        try {
            int[] arr = new int[5];
            System.out.println(arr[10]); // Accessing invalid index
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException caught: " + e.getMessage());
        }
    }

    // 9. ClassCastException Example
    public static void simulateClassCastException() {
        System.out.println("9. ClassCastException Example:");
        try {
            Object obj = new Integer(100);
            String str = (String) obj; // Invalid type casting
        } catch (ClassCastException e) {
            System.out.println("ClassCastException caught: " + e.getMessage());
        }
    }

    // 10. IllegalArgumentException Example
    public static void simulateIllegalArgumentException() {
        System.out.println("10. IllegalArgumentException Example:");
        try {
            setAge(-5); // Invalid argument passed
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException caught: " + e.getMessage());
        }
    }

    public static void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative.");
        }
    }

    // 11. NumberFormatException Example
    public static void simulateNumberFormatException() {
        System.out.println("11. NumberFormatException Example:");
        try {
            String invalidNumber = "abc";
            int number = Integer.parseInt(invalidNumber); // Invalid number format
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException caught: " + e.getMessage());
        }
    }
}


