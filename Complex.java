/**
 * Класс комплексных чисел 
 * @author the_best_earth_spirit_player
 * */
public class Complex {
  /** Поле действительной части комплексного числа */
  public double real;
  /** Поле мнимой части комплексного числа */
  public double imag;

  /** Конструктор, создающий нулевое комплексное число 
   * @see Complex#Complex(double, double)
   * */
  Complex () { 
    this.real = 0;
    this.imag = 0;
  }
  
  /** Конструктор, создающий комплексное число с определенным значением
   * @param real действительная часть
   * @param imag мнимая часть
   * @see Complex#Complex()
   * */
  Complex (double real, double imag) { 
    this.real = real;
    this.imag = imag;
  }

  /** Прибавление к комплексному числа другого
   * @param dig слагаемое
   * */
  public void add (Complex dig) { 
    this.real += dig.real;
    this.imag += dig.imag;
  }
  
  /** Вычитание из комплексного числа другого
   * @param dig вычитаемое
   * */
  public void subtraction (Complex dig) { 
    this.real -= dig.real;
    this.imag -= dig.imag;
  }

  /** Умножение комплексного числа на другое
   * @param dig множитель
   * */
  public void multiplication (Complex dig) {
    Complex tmp = new Complex(this.real*dig.real - this.imag*dig.imag,
        this.real*dig.imag + dig.real*this.imag);
    this.copy(tmp);
  }

  /** Деление комплексного числа на другое
   * @param dig делитель
   * */
  public void division (Complex dig) {
    double tmp_real = (this.real*dig.real + this.imag*dig.imag)/(dig.real*dig.real + dig.imag*dig.imag);
    double tmp_img = (this.imag*dig.real - this.real*dig.imag)/(dig.real*dig.real + dig.imag*dig.imag);
    Complex tmp = new Complex(tmp_real, tmp_img);
    this.copy(tmp);
  }
  
  /** Копирование комплексного числа 
   * @param dig копируемое
   * */
  public void copy (Complex dig) {
    this.real = dig.real;
    this.imag = dig.imag;
  }
  
  /** Получение сопряженного комплексному */
  public void conjugate () {
    imag = -imag;
  }

  /** Вывод тригонометрической формы комплексного числа */
  public void trigonometric_form () {
    double r = Math.sqrt(real*real + imag*imag);
    double cos = real/r;
    double sin = imag/r;
    System.out.println(r + "*(" + cos + (sin >= 0 ? "+" : "") + sin + "*i)");
  }

  /** Вывод комплексного числа  */
  public void print () {
    System.out.println(real + (imag >= 0 ? "+" : "") + imag + "*i");
  }
}
