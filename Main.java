import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
  public static void main (String[] args) throws FileNotFoundException {
    Scanner in = new Scanner(new File("test.txt"));
    Matrix first = new Matrix(in.nextInt(), in.nextInt());
    first.fill_matrix(in);
    System.out.println(first + "\n--------------\n");

    System.out.println(first.det());
  }
}
