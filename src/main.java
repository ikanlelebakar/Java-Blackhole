import java.util.Scanner;

public class main {
    public static double Lingkaran(double jariJari) {
        final double Pi = 3.14159265358979323846f;

        return Pi * Math.pow(jariJari, 2);
    }

    public static void main(String[] args) {
        //deklarasi Variabel, scanner dan kelas
        double jariJari;

        Scanner input = new Scanner(System.in);

        System.out.print("Jari Jari : ");
        jariJari = input.nextDouble();
        System.out.print("Luas lingkaran ini = " + Lingkaran(jariJari));
    }
}
