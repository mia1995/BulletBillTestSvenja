package sample;


import static java.lang.Math.sqrt;

public class PVector {

    double x;
    double y;

    PVector(double x_, double y_){
        x = x_;
        y = y_;
    }

    public void add(PVector v){
        x = x + v.x;
        y = y + v.y;
    }

    public void sub(PVector v){
        x = x - v.x;
        y = y - v.y;
    }

    public void mult (double n){
        x = x * n;
        y = y * n;
    }

    public void div (double n){
        x = x / n;
        y = y / n;
    }
    // Länge des Vektors (Magnitude)
    public double mag(){
        return sqrt(x*x + y*y);
    }

    public void normalize(){
        double m = mag();
        if (m != 0){
            div(m);
        }
    }

    public double getY() {
        return y;
    }
    public double getX() {
        return x;
    }
}
