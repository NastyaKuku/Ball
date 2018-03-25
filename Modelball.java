
import java.io.*;

class main {

    public static void main(String[] args) throws IOException {
        double x = 0, //Начальная координата x
                y = 15, //Начальная координата y
                dx = 5, //Начальная скорость по x
                dy = 15, //Начальная скорость по y
                t = 1, //Время
                r = 1.5, //Радиус r
                k = 0.5; //Коэффициент упругости к
        new Modelball(new double[]{x, y, dx, dy, t, r, k});

    }
}

class Modelball {

    private final static double g = 9.8;
    private final static int n = 300;
    static double t, r, k;
    private Body body;
    static int i;

    private void calculate() throws IOException {
        double x, y, dx, dy, V0x, V0y, dV0x, dV0y, dt;

        x = body.x;
        y = body.y;
        V0x = body.V0x;
        V0y = body.V0y;
        dt = t / n;

        for (i = 1; i <= n; i++) {
            dx = V0x * dt;
            dy = V0y * dt;
            dV0x = V0x * dt;
            dV0y = (V0y - g) * dt;
            x += dx;
            V0x += dV0x;
            if (y + dy > r) {
                y += dy;
                V0y += dV0y;
            } else {
                y = Math.abs(r - (y - r));
                V0y = Math.abs(V0y + dV0y) * k;
            }
            body.set(x, y, V0x, V0y);
            body.get();
        }
    }

    Modelball(double[] params) throws IOException {
        double x = params[0];
        double y = params[1];
        double V0x = params[2];
        double V0y = params[3];
        body = new Body(x, y, V0x, V0y);
        this.t = params[4];
        this.r = params[5];
        this.k = params[6];
        calculate();
    }

}

class Body {

    double x, y, V0x, V0y;

    double V() {
        return Math.sqrt(Math.pow(V0x, 2) + Math.pow(V0y, 2));
    }

    Body(double x, double y, double V0x, double V0y) throws IOException {
        set(x, y, V0x, V0y);
        
    }

    void set(double x, double y, double V0x, double V0y) {
        this.x = x;
        this.y = y;
        this.V0x = V0x;
        this.V0y = V0y;
    }

    void get() throws IOException {
    	System.out.println(String.format( "t%d: (%5.2f ; %5.2f) , Скорость: %5.2f\n", Modelball.i, x, y, V()));
    }
}


