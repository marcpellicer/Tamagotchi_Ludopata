import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Tamagochi {
    private String nombre;
    private int dinero;
    private int sueno;
    private int hambre;
    private int dia;
    private int deuda;
    private int horaDelDia; 
    private Random random;

    public Tamagochi(String nombre) {
        this.nombre = nombre;
        this.dinero = 50;
        this.sueno = 0;
        this.hambre = 0;
        this.dia = 1;
        this.horaDelDia = 0;
        this.random = new Random();
        this.deuda = generarDeuda();
    }

    public int getDinero() {
        return dinero;
    }

    private int generarDeuda() {
        return random.nextInt(16) + 5;
    }

    private void evaluarEstado() {
        if (dinero <= 0) {
            System.out.println("Oh, Oh!" + nombre + " ha sido arrestado por la policía.");
            System.exit(0);
        } else if (sueno >= 100) {
            System.out.println("Oh, Oh!" + nombre + " se ha quedado dormido en un casino y le han robado todo el dinero.");
            System.exit(0);
        } else if (hambre >= 100) {
            System.out.println("Oh, Oh!" + nombre + " ha muerto por no alimentarse.");
            System.exit(0);
        }
    }

    private void mostrarConPausa(String mensaje) {
        for (char c : mensaje.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    public void mostrarEstado() {
        mostrarConPausa("");
        mostrarConPausa("Aquí tienes la información sobre " + nombre + ": ");
        mostrarConPausa("- Día --> " + dia);
        mostrarConPausa("- Hora del día --> " + (horaDelDia / 60) + ":" + (horaDelDia % 60));
        mostrarConPausa("- Sueño --> " + sueno);
        mostrarConPausa("- Hambre --> " + hambre);
        mostrarConPausa("- Dinero --> " + dinero + " $");
        mostrarConPausa("- Deuda a pagar --> " + deuda + " $");
    }

    public void dormir(int horas) {
        System.out.println("");
        System.out.println(nombre + " se despide. ¡Se va a dormir por " + horas + " horas!.");
        sueno = Math.max(0, sueno - (horas == 9 ? 80 : horas == 6 ? 50 : 25));
        hambre = Math.min(100, hambre + 10);
        horaDelDia += horas * 60;
        if (horaDelDia >= 1440) {
            dia++;
            deuda = generarDeuda();
            horaDelDia -= 1440; 
        }
        evaluarEstado();
    }

    public void comer(int opcion) {
        int costo = 0;
        int hambreReducida = 0;
        switch (opcion) {
            case 1:
                costo = (int) (dinero * 0.15);
                hambreReducida = 10;
                break;
            case 2:
                costo = (int) (dinero * 0.35);
                hambreReducida = 30;
                break;
            case 3:
                costo = (int) (dinero * 0.50);
                hambreReducida = 60;
                break;
        }
        if (dinero >= costo) {
            System.out.println("");
            System.out.println(nombre + " dice que la comida esta ¡BRUTAL!.");
            hambre = Math.max(0, hambre - hambreReducida);
            dinero -= costo;
        } else {
            System.out.println("");
            System.out.println(nombre + " no tiene suficiente dinero para comer.");
        }
        sueno = Math.min(100, sueno + 8);
        horaDelDia += 30;
        if (horaDelDia >= 1440) {
            dia++;
            deuda = generarDeuda();
            horaDelDia -= 1440;
        }
        evaluarEstado();
    }

    public void jugarRuleta(int apuesta, int[] numerosSeleccionados) {
        if (apuesta > dinero) {
            System.out.println("");
            System.out.println(nombre + " no tiene suficiente dinero para apostar.");
            return;
        }

        dinero -= apuesta;

        Set<Integer> bolasResultados = new HashSet<>();
        int ganancia = 0;
        for (int i = 0; i < 5; i++) {
            int bolaResultado;
            do {
                bolaResultado = random.nextInt(20) + 1;
            } while (bolasResultados.contains(bolaResultado));
            bolasResultados.add(bolaResultado);
            System.out.println("");
            System.out.println("La bola " + (i + 1) + " ha caído en el número: " + bolaResultado);
            boolean acertado = true;
            for (int numero : numerosSeleccionados) {
                if (bolaResultado == numero) {
                    acertado = false;
                    break;
                }
            }
            if (acertado) {
                ganancia += apuesta * 2;
            } else {
                ganancia -= apuesta;
            }
        }

        dinero += ganancia;
        if (ganancia > 0) {
            System.out.println("");
            System.out.println(nombre + " ha ganado " + ganancia + " $");
        } else {
            System.out.println("");
            System.out.println(nombre + " ha perdido " + (-ganancia) + " $");
        }

        System.out.println("GANANCIA: " + ganancia + " $");

        sueno = Math.min(100, sueno + 10);
        hambre = Math.min(100, hambre + 6);
        horaDelDia += 30;
        if (horaDelDia >= 1440) {
            dia++;
            deuda = generarDeuda();
            horaDelDia -= 1440; 
        }
        evaluarEstado();
    }

    public void jugarDados(int apuesta, int dados, int[] numerosSeleccionados) {
        if (apuesta > dinero) {
            System.out.println("");
            System.out.println(nombre + " no tiene suficiente dinero para apostar.");
            return;
        }

        dinero -= apuesta;

        int[] resultado = new int[dados];
        for (int i = 0; i < dados; i++) {
            resultado[i] = random.nextInt(6) + 1;
            System.out.println("");
            System.out.println("Dado " + (i + 1) + " ha sacado --> " + resultado[i]);
        }

        int ganancia = 0;
        for (int i = 0; i < dados; i++) {
            int dado = resultado[i];
            int numero = numerosSeleccionados[i];
            if (dado == numero) {
                ganancia += apuesta * 3;
            } else if (dado == numero - 1 || dado == numero + 1) {
                ganancia += apuesta * 2;
            } else {
                ganancia -= apuesta;
            }
        }

        dinero += ganancia;
        if (ganancia > 0) {
            System.out.println("");
            System.out.println(nombre + " ha ganado " + ganancia + " $");
        } else {
            System.out.println("");
            System.out.println(nombre + " ha perdido " + (-ganancia) + " $");
        }

        System.out.println("GANANCIA: " + ganancia + " $");

        sueno = Math.min(100, sueno + 10);
        hambre = Math.min(100, hambre + 6);
        horaDelDia += 30;
        if (horaDelDia >= 1440) {
            dia++;
            deuda = generarDeuda();
            horaDelDia -= 1440; 
        }
        evaluarEstado();
    }

    public void jugar26(int apuesta) {
        if (apuesta > dinero) {
            System.out.println("");
            System.out.println(nombre + " no tiene suficiente dinero para apostar.");
            return;
        }

        dinero -= apuesta;

        int puntosJugador = 0;
        int puntosMaquina = 0;
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("");
            System.out.print("Presiona Enter para lanzar el dado: ");
            scanner.nextLine();
            int dadoJugador = random.nextInt(6) + 1;
            puntosJugador += dadoJugador;
            System.out.println("Jugador 1 (Tamagochi) ha sacado --> " + dadoJugador);
            if (puntosJugador > 26) {
                puntosJugador -= dadoJugador;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            int dadoMaquina = random.nextInt(6) + 1;
            puntosMaquina += dadoMaquina;
            System.out.println("Jugador 2 (Máquina) ha sacado --> " + dadoMaquina);
            if (puntosMaquina > 26) {
                puntosMaquina -= dadoMaquina;
            }
            System.out.println("");
            System.out.println("Jugador 1 (Tamagochi): " + puntosJugador);
            System.out.println("Jugador 2 (Máquina): " + puntosMaquina);

            if (puntosJugador == 26 || puntosMaquina == 26) {
                break;
            }
        }

        int ganancia = 0;
        if (puntosJugador == 26) {
            ganancia = apuesta * 2;
            dinero += ganancia;
            System.out.println("");
            System.out.println(nombre + " ha ganado " + ganancia + " $");
        } else {
            ganancia = -apuesta;
            dinero += ganancia;
            System.out.println("");
            System.out.println(nombre + " ha perdido " + (-ganancia) + " $");
        }

        System.out.println("GANANCIA: " + ganancia + " $");

        sueno = Math.min(100, sueno + 10);
        hambre = Math.min(100, hambre + 6);
        horaDelDia += 30;
        if (horaDelDia >= 1440) {
            dia++;
            deuda = generarDeuda();
            horaDelDia -= 1440; 
        }
        evaluarEstado();
    }

    public void jugarMapa(int apuesta) {
        if (apuesta > dinero) {
            System.out.println("");
            System.out.println(nombre + " no tiene suficiente dinero para apostar.");
            return;
        }

        dinero -= apuesta;

        String[][] mapa = new String[5][5];
        boolean[][] visitado = new boolean[5][5];
        int bombasColocadas = 0;
        while (bombasColocadas < 4) {
            int i = random.nextInt(5);
            int j = random.nextInt(5);
            if (mapa[i][j] == null) {
                mapa[i][j] = "bomba";
                bombasColocadas++;
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (mapa[i][j] == null) {
                    mapa[i][j] = "vacío";
                }
                visitado[i][j] = false;
            }
        }

        int[] posicion = {0, 0};
        double ganancia = 0;

        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        while (true) {
            visitado[posicion[0]][posicion[1]] = true;
            System.out.println("");
            System.out.println("Mapa:");
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (i == posicion[0] && j == posicion[1]) {
                        System.out.print("[X] ");
                    } else if (visitado[i][j]) {
                        System.out.print("[O] ");
                    } else {
                        System.out.print("[ ] ");
                    }
                }
                System.out.println();
            }

            System.out.println("");
            System.out.print("Introduce una dirección (arriba, abajo, izquierda, derecha) o 'parar' para detener: ");
            String direccion = scanner.nextLine();
            if (direccion.equals("parar")) {
                break;
            }

            switch (direccion) {
                case "arriba":
                    if (posicion[0] > 0 && !visitado[posicion[0] - 1][posicion[1]]) {
                        posicion[0]--;
                    } else {
                        System.out.println("Movimiento no válido.");
                    }
                    break;
                case "abajo":
                    if (posicion[0] < 4 && !visitado[posicion[0] + 1][posicion[1]]) {
                        posicion[0]++;
                    } else {
                        System.out.println("Movimiento no válido.");
                    }
                    break;
                case "izquierda":
                    if (posicion[1] > 0 && !visitado[posicion[0]][posicion[1] - 1]) {
                        posicion[1]--;
                    } else {
                        System.out.println("Movimiento no válido.");
                    }
                    break;
                case "derecha":
                    if (posicion[1] < 4 && !visitado[posicion[0]][posicion[1] + 1]) {
                        posicion[1]++;
                    } else {
                        System.out.println("Movimiento no válido.");
                    }
                    break;
                default:
                    System.out.println("");
                    System.out.println("Dirección no válida.");
                    break;
            }

            if (mapa[posicion[0]][posicion[1]].equals("bomba")) {
                System.out.println("");
                System.out.println("¡BOOOM! Has encontrado una bomba, has perdido tu dinero.");
                ganancia = -apuesta;
                break;
            } else {
                ganancia += 1.3 * apuesta;
            }
            System.out.println("");
            System.out.println("Ganancia actual: " + ganancia + " $");
        }

        dinero += ganancia;
        if (ganancia > 0) {
        } else {
        }

        System.out.println("GANANCIA: " + ganancia + " $");

        sueno = Math.min(100, sueno + 10);
        hambre = Math.min(100, hambre + 6);
        horaDelDia += 30;
        if (horaDelDia >= 1440) {
            dia++;
            deuda = generarDeuda();
            horaDelDia -= 1440;
        }
        evaluarEstado();
    }

    public void pagarDeuda() {
        if (dinero >= deuda) {
            dinero -= deuda;
            deuda = 0;
            System.out.println("");
            System.out.println(nombre + " ha pagado su deuda!");
        } else {
            System.out.println("");
            System.out.println(nombre + " no tiene suficiente dinero para pagar su deuda.");
        }
        sueno = Math.min(100, sueno + 5);
        hambre = Math.min(100, hambre + 3);
        horaDelDia += 30;
        if (horaDelDia >= 1440) {
            dia++;
            deuda = generarDeuda();
            horaDelDia -= 1440; 
        }
        evaluarEstado();
    }
}
