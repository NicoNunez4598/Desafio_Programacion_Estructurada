import java.util.*;

public class CodeChadsAcademy {

    static int calcNota4(int nota2) {
        return (nota2 < 60) ? 100 : nota2;
    }

    static int calcNota5(int nota1, int nota3) {
        return (nota1 + nota3 > 150) ? 95 : 70;
    }

    static String aprobacionStatus(int[] notas) {
        int aprobadas = 0;
        for (int n : notas) if (n >= 60) aprobadas++;
        if (aprobadas == notas.length) return "Resultado: Aprobaste todas. ¡Backend Sensei!";
        if (aprobadas == 0)            return "Resultado: No aprobaste ninguna. ¡Sos un clon de frontend!";
        return "Resultado: Algunas aprobadas. Sos un refactor en progreso.";
    }

    static class Salto {
        int desde;
        int hasta;
        int magnitud;
    }

    static Salto mayorSaltoConsecutivo(int[] notas) {
        Salto s = new Salto();
        s.magnitud = -1;
        for (int i = 0; i < notas.length - 1; i++) {
            int diff = Math.abs(notas[i+1] - notas[i]);
            if (diff > s.magnitud) {
                s.magnitud = diff;
                s.desde = i;
                s.hasta = i + 1;
            }
        }
        return s;
    }

    static boolean esProgresivo(int[] notas) {
        for (int i = 1; i < notas.length; i++) {
            if (!(notas[i] > notas[i-1])) return false;
        }
        return true;
    }

    static int[] ordenadoDesc(int[] notas) {
        int[] a = Arrays.copyOf(notas, notas.length);
        for (int i = 0; i < a.length - 1; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] > a[maxIdx]) maxIdx = j;
            }
            int tmp = a[i]; a[i] = a[maxIdx]; a[maxIdx] = tmp;
        }
        return a;
    }

    static String nivelFinal(int total) {
        if (total < 250) return "Normie total 😢";
        if (total <= 349) return "Soft Chad";
        if (total <= 449) return "Chad";
        return "Stone Chad definitivo 💪";
    }

    static int suma(int[] notas) {
        int s = 0; for (int n : notas) s += n; return s;
    }

    static double promedio(int[] notas) {
        return suma(notas) / (double) notas.length;
    }

    static double desviacionEstandar(int[] notas) {
        double mean = promedio(notas);
        double acum = 0;
        for (int n : notas) {
            double d = n - mean;
            acum += d * d;
        }
        return Math.sqrt(acum / notas.length);
    }

    static void demoAlumnoUnico() {
        int n1 = 85, n2 = 58, n3 = 92;

        int n4 = calcNota4(n2);
        int n5 = calcNota5(n1, n3);

        int[] notas = new int[] { n1, n2, n3, n4, n5 };

        System.out.println("Notas (1..5): " + Arrays.toString(notas));

        // 1) Aprobaciones
        System.out.println(aprobacionStatus(notas));

        // 2) Mayor salto consecutivo
        Salto s = mayorSaltoConsecutivo(notas);
        System.out.printf("Mayor salto fue de %d puntos entre la prueba %d y la prueba %d.%n",
                s.magnitud, s.desde + 1, s.hasta + 1);

        // 3) Bonus por progreso
        if (esProgresivo(notas)) {
            System.out.println("¡Nivel PROGRESIVO! Sos un Stone Chad en crecimiento 📈");
        }

        // 4) Mostrar notas ordenadas (sin sort)
        int[] ord = ordenadoDesc(notas);
        System.out.println("Notas de mayor a menor: " + Arrays.toString(ord));

        // 5) Evaluación final por nivel
        int total = suma(notas);
        System.out.printf("Total = %d → %s%n", total, nivelFinal(total));
    }

    static void demoRankingClase() {
        String[] alumnos = { "Ada", "Linus", "Grace", "Ken" };
        int[][] notas = {
                { 90, 78, 88, 78, 95 },
                { 70, 72, 60, 72, 70 },
                { 95, 92, 98, 92, 95 },
                { 65, 60, 58, 100, 70 }
        };

        // a) Promedio más alto
        int idxPromMax = -1;
        double promMax = -1;
        for (int i = 0; i < alumnos.length; i++) {
            double p = promedio(notas[i]);
            if (p > promMax) { promMax = p; idxPromMax = i; }
        }

        // b) Más regular (menor desviación)
        int idxReg = -1;
        double desvMin = Double.POSITIVE_INFINITY;
        for (int i = 0; i < alumnos.length; i++) {
            double d = desviacionEstandar(notas[i]);
            if (d < desvMin) { desvMin = d; idxReg = i; }
        }

        // c) Peor rendimiento en la tercera prueba (nota 3 más baja)
        int idxPeorPrueba3 = -1;
        int minNota3 = Integer.MAX_VALUE;
        for (int i = 0; i < alumnos.length; i++) {
            int n3 = notas[i][2]; // índice 2 = tercera prueba
            if (n3 < minNota3) { minNota3 = n3; idxPeorPrueba3 = i; }
        }

        System.out.printf("→ Promedio más alto: %s (%.2f)%n", alumnos[idxPromMax], promMax);
        System.out.printf("→ Más regular (menor desviación): %s (σ = %.2f)%n", alumnos[idxReg], desvMin);
        System.out.printf("→ Peor en la 3ra prueba: %s (nota = %d)%n", alumnos[idxPeorPrueba3], minNota3);
    }

    public static void main(String[] args) {
        System.out.println("=== Alumno único: 1ra + 2da parte ===");
        demoAlumnoUnico();

        System.out.println("\n=== Desafío final: Ranking en clase ===");
        demoRankingClase();
    }
}
