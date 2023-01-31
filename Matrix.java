import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

/** Класс матриц
 * @author the_best_earth_spirit_player
 * */
public class Matrix {
  private static Hashtable<String, Matrix> MatrixTable = new Hashtable<String, Matrix>();
  private Complex[][] matrix;
  private int rows;
  private int columns;

  /** Конструктор квадратной матрицы
   * @param ROWS_AND_COLUMNS_NUMBER количество строк и столбцов
   * */
  Matrix (int ROWS_AND_COLUMNS_NUMBER) {
    this.matrix = new Complex[ROWS_AND_COLUMNS_NUMBER][ROWS_AND_COLUMNS_NUMBER];
    this.rows = ROWS_AND_COLUMNS_NUMBER;
    this.columns = ROWS_AND_COLUMNS_NUMBER;
  }

  /** Конструктор произвольной матрицы
   * @param ROWS_NUMBER количество строк
   * @param COLUMNS_NUMBER количество столбцов
   * */
  Matrix (int ROWS_NUMBER, int COLUMNS_NUMBER) {
    this.matrix = new Complex[ROWS_NUMBER][COLUMNS_NUMBER];
    this.rows = ROWS_NUMBER;
    this.columns = COLUMNS_NUMBER;
  }

  /** Заполнение матрицы 
   * @param in от куда заполняется (файл, консоль)
   * */
  public void fill_matrix (Scanner in) {
    for (int i = 0; i < rows; ++i) {
      for (int j = 0; j < columns; ++j) {
        matrix[i][j] = Complex.input(in);
      }
    }
  }

  public static void addMatrix (String name, String fill) {
    Scanner str = new Scanner(fill);
    Matrix tmp = new Matrix(str.nextInt(), str.nextInt());
    tmp.fill_matrix(str);
    MatrixTable.put(name, tmp);
    str.close();
  }

  public static String getMatricies () {
    String result = "";
    for (String key : MatrixTable.keySet()) {
      result += key + "\n" + MatrixTable.get(key) + "-".repeat(59) + "\n";
    }
    return result;
  }
  
  /** Вывод матрицы */
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

  /** Сложение матриц
   * @param arr слагаемое
   * */
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

  /** Вычитание матриц
   * @param arr вычитаемое
   * */
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

  /** Проверка на возможность сложения/вычитания
   * @param arr проверяемое 
   * */
  private boolean checkForAdd (Matrix arr) {
    if (this.rows != arr.rows || this.columns != arr.columns) {
      System.out.println("Cannot add/sub up matrices");
      return false;
    }
    else return true;
  }

  /** Проверка на возможность умножения
   * @param arr проверяемое 
   * */
  private boolean checkForMult (Matrix arr) {
    if (this.columns != arr.rows) {
      System.out.println("Cannot multiply matrices");
      return false;
    }
    else return true;
  }

  /** Умножение матриц
   * @param arr множитель
   * */
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

  public Matrix multiply (Complex dig) {
    Matrix result = new Matrix(this.rows, this.columns);

    for (int i = 0; i < result.rows; ++i) {
      for (int j = 0; j < result.columns; ++j) {
        result.matrix[i][j] = this.matrix[i][j].multiplication(dig);
      }
    }

    return result;
  }

  /** Транспонирование матрицы */
  public Matrix transposition () {
    Matrix result = new Matrix(this.columns, this.rows);
    for (int i = 0; i < result.rows; ++i) {
      for (int j = 0; j < result.columns; ++j) {
        result.matrix[i][j] = new Complex(this.matrix[j][i].getReal(), this.matrix[j][i].getImag());
      }
    }
    return result;
  }

  /** Получение детерминанта */
  public Complex det () {
    if (this.rows != this.columns) {
      System.out.println("It is impossible to calculate the determinant");
      return null;
    }
    
    if (this.rows == 1 && this.columns == 1) return this.matrix[0][0];
    
    Complex result = new Complex();
    int row = 0; int column = 0;
    for (; column < this.columns; ++column) {
      Complex minor = this.lineDecomposition(row, column).det();
      Complex minOne = new Complex(Math.pow(-1, row + column), 0);
      result = result.add(minOne.multiplication(this.matrix[row][column]).multiplication(minor));
    }
    return result;
  }

  /** Получение минора
   * @param row строка
   * @param column колона */
  private Matrix lineDecomposition (int row, int column) {
    Matrix tmp = new Matrix(this.rows - 1, this.columns - 1);
    String str = "";
    for (int i = 0; i < this.rows; ++i) {
      for (int j = 0; j < this.columns; ++j) {
        if (i != row && j != column) {
          str += this.matrix[i][j].getReal() + "," + this.matrix[i][j].getImag() + " ";
        }
      }
    }
    tmp.fill_matrix(new Scanner(str));
    return tmp;
  }
}
