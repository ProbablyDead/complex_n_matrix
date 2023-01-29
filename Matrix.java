import java.util.Scanner;

public class Matrix {
  private Complex[][] matrix;
  private int rows;
  private int columns;

  Matrix (int ROWS_AND_COLUMNS_NUMBER) {
    this.matrix = new Complex[ROWS_AND_COLUMNS_NUMBER][ROWS_AND_COLUMNS_NUMBER];
    this.rows = ROWS_AND_COLUMNS_NUMBER;
    this.columns = ROWS_AND_COLUMNS_NUMBER;
  }

  Matrix (int ROWS_NUMBER, int COLUMNS_NUMBER) {
    this.matrix = new Complex[ROWS_NUMBER][COLUMNS_NUMBER];
    this.rows = ROWS_NUMBER;
    this.columns = COLUMNS_NUMBER;
  }

  public void fill_matrix (Scanner in) {
    for (int i = 0; i < rows; ++i) {
      for (int j = 0; j < columns; ++j) {
        matrix[i][j] = Complex.input(in);
      }
    }
  }
  
  @Override
  public String toString () {
    String result = "";
    for (int i = 0; i < this.rows; ++i) {
      for (int j = 0; j < this.columns; ++j) {
        result += matrix[i][j] + " ";
      }
      result += '\n';
    }
    return result;
  }

  public Matrix add (Matrix arr) {
    if (!checkForAdd(arr)) return null;

    Matrix tmp = new Matrix(this.rows, this.columns);
    for (int i = 0; i < this.rows; ++i) {
      for (int j = 0; j < this.columns; ++j) {
        tmp.matrix[i][j] = this.matrix[i][j].add(arr.matrix[i][j]);
      }
    }
    return tmp;
  }

  public Matrix sub (Matrix arr) {
    if (!checkForAdd(arr)) return null;

    Matrix tmp = new Matrix(this.rows, this.columns);
    for (int i = 0; i < this.rows; ++i) {
      for (int j = 0; j < this.columns; ++j) {
        tmp.matrix[i][j] = this.matrix[i][j].subtraction(arr.matrix[i][j]);
      }
    }
    return tmp;
  }

  private boolean checkForAdd (Matrix arr) {
    if (this.rows != arr.rows || this.columns != arr.columns) {
      System.out.println("Cannot add/sub up matrices");
      return false;
    }
    else return true;
  }

  private boolean checkForMult (Matrix arr) {
    if (this.columns != arr.rows) {
      System.out.println("Cannot multiply matrices");
      return false;
    }
    else return true;
  }

  public Matrix multiply (Matrix arr) {
    if (!this.checkForMult(arr)) return null;
    Matrix result = new Matrix(this.rows, arr.columns);

    for (int i = 0; i < result.rows; ++i) {
      for (int j = 0; j < result.columns; ++j) {
        result.matrix[i][j] = new Complex(0, 0);
        for (int k = 0; k < this.columns; ++k) {
          result.matrix[i][j] = result.matrix[i][j].add(this.matrix[i][k].multiplication(arr.matrix[k][j])); 
        }
      }
    }
   return result;
  }
}
