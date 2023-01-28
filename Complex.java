public class Complex {
  double real;
  double imag;

  Complex () { 
    this.real = 0;
    this.imag = 0;
  }

  Complex (double real, double imag) { 
    this.real = real;
    this.imag = imag;
  }

  public void add (Complex dig) { 
    this.real += dig.real;
    this.imag += dig.imag;
  }
  
  public void subtraction (Complex dig) { 
    this.real -= dig.real;
    this.imag -= dig.imag;
  }

  public void multiplication (Complex dig) {
    Complex tmp = new Complex(this.real*dig.real - this.imag*dig.imag,
        this.real*dig.imag + dig.real*this.imag);
    this.copy(tmp);
  }

  public void division (Complex dig) {
    double tmp_real = (this.real*dig.real + this.imag*dig.imag)/(dig.real*dig.real + dig.imag*dig.imag);
    double tmp_img = (this.imag*dig.real - this.real*dig.imag)/(dig.real*dig.real + dig.imag*dig.imag);
    Complex tmp = new Complex(tmp_real, tmp_img);
    this.copy(tmp);
  }
  
  public void conjugate () {
    imag = -imag;
  }

  public void copy (Complex dig) {
    this.real = dig.real;
    this.imag = dig.imag;
  }

  public void trigonometric_form () {
    double r = Math.sqrt(real*real + imag*imag);
    double cos = real/r;
    double sin = imag/r;
    System.out.println(r + "*(" + cos + (sin >= 0 ? "+" : "") + sin + "*i)");
  }

  public void print () {
    System.out.println(real + (imag >= 0 ? "+" : "") + imag + "*i");
  }
}
