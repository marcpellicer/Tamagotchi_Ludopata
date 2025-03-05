import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private Tamagochi tamagochi;

    public Main() {
        this.tamagochi = null;
    }

    private void mostrarMensajeBienvenida() {
        System.out.println(" ");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------- ");
        System.out.println("¡BIENVENIDO AL TAMAGOCHI L-U-D-O-P-A-T-A!");
        System.out.println("- En este juego, tu objetivo es convertirte en el hombre más rico del mundo.");
        System.out.println("- Para ello deberás gestionar tu dinero, sueño y hambre para alcanzar a la meta.");
        System.out.println("- ¡Ten cuidado, cada día tendrás que pagar una deuda para que no te coja la policía!");
        System.out.println("- Para pagar las deudas y convertirte en el hombre más rico podrás ganar dinero jugando a varios juegos: La Ruleta, Al 26, Gemelos o CaBoom.");
        System.out.println("¡BUENA SUERTE!");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------\n ");
    }

    private void iniciarJuego() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        System.out.print("¿Cómo quieres que se llame tu Tamagochi?: ");
        String nombre = scanner.nextLine();
        this.tamagochi = new Tamagochi(nombre);
    }

    private void mostrarMenu() {
        System.out.println("\n¿QUÉ QUIERES HACER AHORA?");
        System.out.println("1) Ir a dormir");
        System.out.println("2) Dar de comer");
        System.out.println("3) Jugar --> La Ruleta");
        System.out.println("4) Jugar --> Al 26");
        System.out.println("5) Jugar --> Gemelos");
        System.out.println("6) Jugar --> CaBoom");
        System.out.println("7) Pagar deuda");
        System.out.println("8) Fin del juego\n");
    }

    private boolean ejecutarOpcion(int opcion) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        switch (opcion) {
            case 1:
                int horas = 0;
                while (horas < 1 || horas > 3) {
                    System.out.println("");
                    System.out.println("¿Cuántas horas quieres que duerma tu Tamagochi? Selecciona una opción.");
                    System.out.println("1) 3 horas (-25 de sueño)");
                    System.out.println("2) 6 horas (-50 de sueño)");
                    System.out.println("3) 9 horas (-80 de sueño)");
                    System.out.println("");
                    horas = scanner.nextInt();
                }
                tamagochi.dormir(horas == 1 ? 3 : horas == 2 ? 6 : 9);
                break;
            case 2:
                int opcionComida = 0;
                while (opcionComida < 1 || opcionComida > 3) {
                    System.out.println("");
                    System.out.println("¿Qué quieres que coma tu Tamagochi? Selecciona una opción.");
                    System.out.println("1) Sopa       (-10 de hambre: " + (int)(tamagochi.getDinero() * 0.15) + " $)");
                    System.out.println("2) Macarrones (-30 de hambre: " + (int)(tamagochi.getDinero() * 0.35) + " $)");
                    System.out.println("3) Bistec     (-60 de hambre: " + (int)(tamagochi.getDinero() * 0.50) + " $)");
                    System.out.println("");
                    opcionComida = scanner.nextInt();
                }
                tamagochi.comer(opcionComida);
                break;
            case 3:
                System.out.println("");
                System.out.print("¿Cuanto dinero quieres apostar?: ");
                int apuestaRuleta = scanner.nextInt();
                Set<Integer> numerosSeleccionadosSet = new HashSet<>();
                System.out.println("");
                System.out.println("Ahora tienes que seleccionar los numeros con los que quieres jugar:");
                for (int i = 0; i < 5; i++) {
                    int numero;
                    do {
                        System.out.print("Número " + (i + 1) + " (1-20): ");
                        numero = scanner.nextInt();
                    } while (numerosSeleccionadosSet.contains(numero) || numero < 1 || numero > 20);
                    numerosSeleccionadosSet.add(numero);
                }
                int[] numerosSeleccionados = numerosSeleccionadosSet.stream().mapToInt(Integer::intValue).toArray();
                tamagochi.jugarRuleta(apuestaRuleta, numerosSeleccionados);
                break;
            case 4:
                System.out.println("");
                System.out.print("¿Cuanto dinero quieres apostar?: ");
                int apuesta26 = scanner.nextInt();
                tamagochi.jugar26(apuesta26);
                break;
            case 5:
                System.out.println("");
                System.out.print("¿Cuanto dinero quieres apostar?: ");
                int apuestaDados = scanner.nextInt();
                System.out.println("");
                System.out.print("¿Con cuántos dados quieres jugar (1 o 2)?: ");
                int dados = scanner.nextInt();
                int[] numerosSeleccionadosDados = new int[dados];
                for (int i = 0; i < dados; i++) {
                    System.out.println("");
                    System.out.print("Número seleccionado para el dado " + (i + 1) + " (1-6): ");
                    numerosSeleccionadosDados[i] = scanner.nextInt();
                }
                tamagochi.jugarDados(apuestaDados, dados, numerosSeleccionadosDados);
                break;
            case 6:
                System.out.println("");
                System.out.print("¿Cuanto dinero quieres apostar?: ");
                int apuestaMapa = scanner.nextInt();
                tamagochi.jugarMapa(apuestaMapa);
                break;
            case 7:
                tamagochi.pagarDeuda();
                break;
            case 8:
                System.out.println("");
                System.out.println("¡GRACIAS POR JUGAR! Hasta la proxima.");
                System.out.println("");
                return false;
            default:
                System.out.println("");
                System.out.println("Opción no válida. Inténtalo de nuevo.");
        }
        return true;
    }

    public void run() {
        mostrarMensajeBienvenida();
        iniciarJuego();

        boolean jugando = true;
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        while (jugando) {
            tamagochi.mostrarEstado();
            mostrarMenu();
            System.out.print("Introduce el número de tu opción: ");
            int opcion = scanner.nextInt();
            jugando = ejecutarOpcion(opcion);
        }
    }

    public static void main(String[] args) {
        Main juego = new Main();
        juego.run();
    }
}
