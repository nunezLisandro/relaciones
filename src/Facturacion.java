import java.util.ArrayList;
import java.util.Scanner;

public class Facturacion {

    // Array de artículos (código, nombre, precio)
    static String[][] articulos = {
            {"101", "Leche", "25"},
            {"102", "Gaseosa", "30"},
            {"103", "Fideos", "15"},
            {"104", "Arroz", "28"},
            {"105", "Vino", "120"},
            {"106", "Manteca", "20"},
            {"107", "Lavandina", "18"},
            {"108", "Detergente", "46"},
            {"109", "Jabón en Polvo", "96"},
            {"110", "Galletas", "60"}
    };

    // Clase DetalleFactura
    public static class DetalleFactura {
        private String codigoArticulo;
        private String nombreArticulo;
        private int cantidad;
        private double precioUnitario;
        private double descuentoItem;
        private double subtotal;

        // Constructor
        public DetalleFactura(String codigoArticulo, String nombreArticulo, int cantidad, double precioUnitario, double descuentoItem) {
            this.codigoArticulo = codigoArticulo;
            this.nombreArticulo = nombreArticulo;
            this.cantidad = cantidad;
            this.precioUnitario = precioUnitario;
            this.descuentoItem = descuentoItem;
            this.subtotal = calcularSubtotal();
        }

        // Método para calcular el subtotal con descuento
        private double calcularSubtotal() {
            return (precioUnitario * cantidad) - descuentoItem;
        }

        // Getters y Setters
        public String getCodigoArticulo() {
            return codigoArticulo;
        }

        public void setCodigoArticulo(String codigoArticulo) {
            this.codigoArticulo = codigoArticulo;
        }

        public String getNombreArticulo() {
            return nombreArticulo;
        }

        public void setNombreArticulo(String nombreArticulo) {
            this.nombreArticulo = nombreArticulo;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public double getPrecioUnitario() {
            return precioUnitario;
        }

        public void setPrecioUnitario(double precioUnitario) {
            this.precioUnitario = precioUnitario;
        }

        public double getDescuentoItem() {
            return descuentoItem;
        }

        public void setDescuentoItem(double descuentoItem) {
            this.descuentoItem = descuentoItem;
        }

        public double getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(double subtotal) {
            this.subtotal = subtotal;
        }

        @Override
        public String toString() {
            return "Código: " + codigoArticulo + ", Nombre: " + nombreArticulo + ", Cantidad: " + cantidad + ", Precio Unitario: $" + precioUnitario + ", Descuento: $" + descuentoItem + ", Subtotal: $" + subtotal;
        }
    }

    // Clase Factura
    public static class Factura {
        private String fechaFactura;
        private long numeroFactura;
        private String cliente;
        private double totalCalculadoFactura;
        private ArrayList<DetalleFactura> detallesFactura;

        // Constructor
        public Factura(String fechaFactura, long numeroFactura, String cliente) {
            this.fechaFactura = fechaFactura;
            this.numeroFactura = numeroFactura;
            this.cliente = cliente;
            this.detallesFactura = new ArrayList<>();
            this.totalCalculadoFactura = 0;
        }

        // Métodos Getters y Setters
        public String getFechaFactura() {
            return fechaFactura;
        }

        public void setFechaFactura(String fechaFactura) {
            this.fechaFactura = fechaFactura;
        }

        public long getNumeroFactura() {
            return numeroFactura;
        }

        public void setNumeroFactura(long numeroFactura) {
            this.numeroFactura = numeroFactura;
        }

        public String getCliente() {
            return cliente;
        }

        public void setCliente(String cliente) {
            this.cliente = cliente;
        }

        public double getTotalCalculadoFactura() {
            return totalCalculadoFactura;
        }

        public void setTotalCalculadoFactura(double totalCalculadoFactura) {
            this.totalCalculadoFactura = totalCalculadoFactura;
        }

        public ArrayList<DetalleFactura> getDetallesFactura() {
            return detallesFactura;
        }

        public void setDetallesFactura(ArrayList<DetalleFactura> detallesFactura) {
            this.detallesFactura = detallesFactura;
        }

        // Método para agregar un detalle de factura
        public void agregarDetalle(DetalleFactura detalle) {
            this.detallesFactura.add(detalle);
            this.totalCalculadoFactura += detalle.getSubtotal(); // Sumar al total calculado
        }

        // Método para mostrar la información de la factura
        public void mostrarFactura() {
            System.out.println("Fecha de Factura: " + fechaFactura);
            System.out.println("Número de Factura: " + numeroFactura);
            System.out.println("Cliente: " + cliente);
            System.out.println("Detalles de la Factura:");
            for (DetalleFactura detalle : detallesFactura) {
                System.out.println(detalle);
            }
            System.out.println("Total Calculado de la Factura: $" + totalCalculadoFactura);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crear instancia de la factura
        System.out.print("Ingrese la fecha de la factura: ");
        String fechaFactura = scanner.nextLine();

        System.out.print("Ingrese el número de la factura (mayor a 0): ");
        long numeroFactura = scanner.nextLong();
        while (numeroFactura <= 0) {
            System.out.print("Número de factura inválido, debe ser mayor a cero: ");
            numeroFactura = scanner.nextLong();
        }
        scanner.nextLine();  // Limpiar el buffer

        System.out.print("Ingrese el nombre del cliente: ");
        String cliente = scanner.nextLine();
        while (cliente.trim().isEmpty()) {
            System.out.print("El cliente no puede estar vacío. Ingrese el nombre del cliente: ");
            cliente = scanner.nextLine();
        }

        // Crear la factura
        Factura factura = new Factura(fechaFactura, numeroFactura, cliente);

        // Proceso de agregar artículos a la factura
        boolean continuar = true;
        while (continuar) {
            System.out.print("Ingrese el código del artículo a facturar: ");
            String codigoArticulo = scanner.nextLine();

            // Buscar el artículo en el array de artículos
            boolean encontrado = false;
            String nombreArticulo = "";
            double precioUnitario = 0.0;

            for (int i = 0; i < articulos.length; i++) {
                if (articulos[i][0].equals(codigoArticulo)) {
                    nombreArticulo = articulos[i][1];
                    precioUnitario = Double.parseDouble(articulos[i][2]);
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("Código de artículo no encontrado.");
                continue; // Volver a pedir el código de artículo
            }

            System.out.print("Ingrese la cantidad de " + nombreArticulo + ": ");
            int cantidad = scanner.nextInt();

            System.out.print("Ingrese el descuento para este ítem: ");
            double descuentoItem = scanner.nextDouble();
            scanner.nextLine();  // Limpiar el buffer

            // Crear el detalle de factura
            DetalleFactura detalle = new DetalleFactura(codigoArticulo, nombreArticulo, cantidad, precioUnitario, descuentoItem);

            // Agregar el detalle a la factura
            factura.agregarDetalle(detalle);

            System.out.print("¿Desea agregar otro artículo? (sí/no): ");
            String respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("no")) {
                continuar = false;
            }
        }

        // Mostrar la factura final
        factura.mostrarFactura();

        scanner.close();
    }
}
