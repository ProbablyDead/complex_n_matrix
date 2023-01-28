/**
 * Класс комплексных чисел 
 * @author the_best_earth_spirit_player
 * */
public class Complex {
  /** Поле действительной части комплексного числа */
  private double real;
  /** Поле мнимой части комплексного числа */
  private double imag;

  /** Получение действительной части */
  public double getReal () { return this.real; }

  /** Получение мнимой части */
  public double getImag () { return this.imag; }

  /** Конструктор, создающий нулевое комплексное число 
   * @see Complex#Complex(double, double)
   * */
  Complex () { 
    real = 0;
    imag = 0;
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
  public Complex add (Complex dig) { 
    return new Complex(this.real + dig.real, this.imag + dig.imag); 
  }
  
  /** Вычитание из комплексного числа другого
   * @param dig вычитаемое
   * */
  public Complex subtraction (Complex dig) { 
    return new Complex(this.real - dig.real, this.imag - dig.imag);
  }

  /** Умножение комплексного числа на другое
   * @param dig множитель
   * */
  public Complex multiplication (Complex dig) {
    return new Complex(this.real*dig.real - this.imag*dig.imag,
        this.real*dig.imag + dig.real*this.imag);
  }

  /** Деление комплексного числа на другое
   * @param dig делитель
   * */
  public Complex division (Complex dig) {
    double tmp_real = (this.real*dig.real + this.imag*dig.imag)/(dig.real*dig.real + dig.imag*dig.imag);
    double tmp_img = (this.imag*dig.real - this.real*dig.imag)/(dig.real*dig.real + dig.imag*dig.imag);
    return new Complex(tmp_real, tmp_img);
  }
  
  /** Получение сопряженного комплексному */
  public Complex conjugate () {
    return new Complex(real, -imag);
  }

  /** Вывод тригонометрической формы комплексного числа */
  public String trigonometricForm () {
    double r = Math.sqrt(real*real + imag*imag);
    double cos = real/r;
    double sin = imag/r;
    return (r + "*(" + cos + (sin >= 0 ? "+" : "") + sin + "*i)");
  }

  /** Вывод комплексного числа  */
  @Override
  public String toString () {
    return real + (imag >= 0 ? "+" : "") + imag + "*i";
  }
}
