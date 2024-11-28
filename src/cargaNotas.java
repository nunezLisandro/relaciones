import java.util.ArrayList;
import java.util.Scanner;

public class CargaNotas {

    // Clase Nota
    public static class Nota {
        private String catedra;
        private double notaExamen;

        // Constructor
        public Nota(String catedra, double notaExamen) {
            this.catedra = catedra;
            this.notaExamen = notaExamen;
        }

        // Getters
        public String getCatedra() {
            return catedra;
        }

        public double getNotaExamen() {
            return notaExamen;
        }

        @Override
        public String toString() {
            return "Cátedra: " + catedra + ", Nota: " + notaExamen;
        }
    }

    // Clase Alumno
    public static class Alumno {
        private String nombreCompleto;
        private long legajo;
        private ArrayList<Nota> notas;

        // Constructor
        public Alumno(String nombreCompleto, long legajo) {
            this.nombreCompleto = nombreCompleto;
            this.legajo = legajo;
            this.notas = new ArrayList<>();
        }

        // Método para agregar una nota
        public void agregarNota(Nota nota) {
            this.notas.add(nota);
        }

        // Método para calcular el promedio de las notas
        public double calcularPromedio() {
            if (notas.isEmpty()) {
                return 0.0; // Si no tiene notas, el promedio es 0
            }

            double suma = 0.0;
            for (Nota nota : notas) {
                suma += nota.getNotaExamen();
            }
            return suma / notas.size();
        }

        // Método para mostrar la información del alumno
        public void mostrarInformacion() {
            System.out.println("Nombre Completo: " + nombreCompleto);
            System.out.println("Legajo: " + legajo);
            for (Nota nota : notas) {
                System.out.println(nota);
            }
            System.out.println("Promedio: " + calcularPromedio());
        }
    }

    // Clase principal (CargaNotas) con el método main
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Alumno> alumnos = new ArrayList<>();

        System.out.print("Ingrese la cantidad de alumnos: ");
        int cantidadAlumnos = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea pendiente

        // Cargar los alumnos
        for (int i = 0; i < cantidadAlumnos; i++) {
            System.out.println("\nIngrese los datos del alumno #" + (i + 1));

            System.out.print("Nombre Completo: ");
            String nombreCompleto = scanner.nextLine();

            System.out.print("Legajo: ");
            long legajo = scanner.nextLong();
            scanner.nextLine();  // Consumir el salto de línea pendiente

            Alumno alumno = new Alumno(nombreCompleto, legajo);

            System.out.print("¿Cuántas notas desea ingresar para este alumno? ");
            int cantidadNotas = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea pendiente

            // Validar que ingrese al menos 1 nota
            if (cantidadNotas < 1) {
                System.out.println("Debe ingresar al menos una nota.");
                continue;  // Continuar con el siguiente alumno
            }

            // Cargar las notas
            for (int j = 0; j < cantidadNotas; j++) {
                System.out.print("Ingrese la cátedra para la nota #" + (j + 1) + ": ");
                String catedra = scanner.nextLine();

                System.out.print("Ingrese la nota para la cátedra " + catedra + ": ");
                double notaExamen = scanner.nextDouble();
                scanner.nextLine();  // Consumir el salto de línea pendiente

                Nota nota = new Nota(catedra, notaExamen);
                alumno.agregarNota(nota);
            }

            // Agregar el alumno a la lista de alumnos
            alumnos.add(alumno);
        }

        // Mostrar la información de todos los alumnos
        System.out.println("\n\n--- Información de los Alumnos ---");
        for (Alumno alumno : alumnos) {
            alumno.mostrarInformacion();
            System.out.println("-----------------------------------");
        }

        scanner.close();
    }
}