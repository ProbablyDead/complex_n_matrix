public class Matrix {
  Complex[][] matrix;

  Matrix (int n) {
    this.matrix = new Complex[n][n];
    this.fill_matrix();
  }

  Matrix (int n, int m) {
    this.matrix = new Complex[n][m];
    this.fill_matrix();
  }

  public void fill_matrix () {

  }
}
