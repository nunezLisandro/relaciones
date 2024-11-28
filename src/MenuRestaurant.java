import java.util.ArrayList;
import java.util.Scanner;

public class MenuRestaurant {

    // Clase Ingrediente
    public static class Ingrediente {
        private String nombre;
        private double cantidad;
        private String unidadMedida;

        // Constructor
        public Ingrediente(String nombre, double cantidad, String unidadMedida) {
            this.nombre = nombre;
            this.cantidad = cantidad;
            this.unidadMedida = unidadMedida;
        }

        // Getters
        public String getNombre() {
            return nombre;
        }

        public double getCantidad() {
            return cantidad;
        }

        public String getUnidadMedida() {
            return unidadMedida;
        }

        @Override
        public String toString() {
            return nombre + " " + cantidad + " " + unidadMedida;
        }
    }

    // Clase Plato
    public static class Plato {
        private String nombreCompleto;
        private double precio;
        private boolean esBebida;
        private ArrayList<Ingrediente> ingredientes;

        // Constructor
        public Plato(String nombreCompleto, double precio, boolean esBebida) {
            this.nombreCompleto = nombreCompleto;
            this.precio = precio;
            this.esBebida = esBebida;
            this.ingredientes = new ArrayList<>();
        }

        // Método para agregar ingredientes
        public void agregarIngrediente(Ingrediente ingrediente) {
            this.ingredientes.add(ingrediente);
        }

        // Método para mostrar la información del plato
        public void mostrarInformacion() {
            System.out.println(nombreCompleto);
            System.out.println("Precio: $ " + precio);
            if (!esBebida) {
                System.out.println("Ingredientes:");
                System.out.println("Nombre Cantidad Unidad de Medida");
                for (Ingrediente ingrediente : ingredientes) {
                    System.out.println(ingrediente);
                }
            }
        }
    }

    // Método principal que ejecuta el código
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Plato> platosMenu = new ArrayList<>();

        System.out.print("Ingrese la cantidad de platos en el menú: ");
        int cantidadPlatos = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea pendiente

        // Cargar los platos
        for (int i = 0; i < cantidadPlatos; i++) {
            System.out.println("\nIngrese los datos del plato #" + (i + 1));

            System.out.print("Nombre del plato: ");
            String nombrePlato = scanner.nextLine();

            System.out.print("Precio del plato: ");
            double precio = scanner.nextDouble();

            System.out.print("¿Es una bebida? (true/false): ");
            boolean esBebida = scanner.nextBoolean();
            scanner.nextLine();  // Consumir el salto de línea pendiente

            // Crear el objeto Plato
            Plato plato = new Plato(nombrePlato, precio, esBebida);

            if (!esBebida) {
                // Si no es una bebida, cargar los ingredientes
                System.out.print("¿Cuántos ingredientes tiene este plato? ");
                int cantidadIngredientes = scanner.nextInt();
                scanner.nextLine();  // Consumir el salto de línea pendiente

                // Validar que haya al menos un ingrediente
                if (cantidadIngredientes < 1) {
                    System.out.println("El plato debe tener al menos 1 ingrediente.");
                    continue; // Saltamos al siguiente plato
                }

                // Cargar los ingredientes
                for (int j = 0; j < cantidadIngredientes; j++) {
                    System.out.print("Ingrese el nombre del ingrediente #" + (j + 1) + ": ");
                    String nombreIngrediente = scanner.nextLine();

                    System.out.print("Ingrese la cantidad de " + nombreIngrediente + ": ");
                    double cantidadIngrediente = scanner.nextDouble();

                    System.out.print("Ingrese la unidad de medida de " + nombreIngrediente + ": ");
                    scanner.nextLine();  // Consumir el salto de línea pendiente
                    String unidadMedida = scanner.nextLine();

                    // Crear el ingrediente y agregarlo al plato
                    Ingrediente ingrediente = new Ingrediente(nombreIngrediente, cantidadIngrediente, unidadMedida);
                    plato.agregarIngrediente(ingrediente);
                }
            }

            // Agregar el plato al menú
            platosMenu.add(plato);
        }

        // Mostrar el menú completo
        System.out.println("\n\n--- MENÚ DEL RESTAURANTE ---");
        for (Plato plato : platosMenu) {
            plato.mostrarInformacion();
            System.out.println("-----------------------------------");
        }

        scanner.close();
    }
}

