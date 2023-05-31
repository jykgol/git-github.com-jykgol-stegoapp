
package HACSfolder;

/**
 *
 * @author jyk22
 */
public class SimpleRand {

    private double U0 = 3.14159;
    private double U1 = 0.542102;
    private double T;

    public double NewRand() {
        T = U0 + U1;
        U0 = U1;
        if (T >= 4)
            T = T - 4;
        U1 = T;

        return T / 4;
    }

    public double getU0() {
        return U0;
    }

    public double getU1() {
        return U1;
    }

    public void setU0(double newU0) {
        U0 = newU0;
    }

    public void setU1(double newU1) {
        U1 = newU1;
    }
}
