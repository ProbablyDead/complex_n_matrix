import java.util.Scanner;

public class Matrix {
  Complex[][] matrix;
  private int rows;
  private int columns;

  Matrix (int ROWS_AND_COLUMNS_NUMBER) {
    this.matrix = new Complex[ROWS_AND_COLUMNS_NUMBER][ROWS_AND_COLUMNS_NUMBER];
    this.rows = ROWS_AND_COLUMNS_NUMBER;
    this.columns = ROWS_AND_COLUMNS_NUMBER;
    this.fill_matrix();
  }

  Matrix (int ROWS_NUMBER, int COLUMNS_NUMBER) {
    this.matrix = new Complex[ROWS_NUMBER][COLUMNS_NUMBER];
    this.rows = ROWS_NUMBER;
    this.columns = COLUMNS_NUMBER;
    this.fill_matrix();
  }

  public void fill_matrix () {
    Scanner in = new Scanner(System.in);
    for (int i = 0; i < this.rows; ++i) {
      for (int j = 0; j < this.columns; ++j) {
        matrix[i][j] = Complex.input(in);
      }
    }
    in.close();
  }

}
