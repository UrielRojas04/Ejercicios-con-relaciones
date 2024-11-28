import java.util.Scanner;

public class Facturacion {
    private static final String[][] articulos = {
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Factura factura = new Factura();

        System.out.print("Ingrese la fecha de la factura: ");
        factura.setFechaFactura(scanner.nextLine());

        long numeroFactura;
        do {
            System.out.print("Ingrese el número de factura (debe ser mayor a cero): ");
            numeroFactura = scanner.nextLong();
            scanner.nextLine();
        } while (numeroFactura <= 0);
        factura.setNumeroFactura(numeroFactura);

        String cliente;
        do {
            System.out.print("Ingrese el nombre del cliente (no puede estar vacío): ");
            cliente = scanner.nextLine();
        } while (cliente.isEmpty());
        factura.setCliente(cliente);

        while (true) {
            String codigoArticulo;
            while (true) {
                System.out.print("Ingrese el código del artículo a facturar: ");
                codigoArticulo = scanner.nextLine();
                boolean encontrado = false;
                for (String[] articulo : articulos) {
                    if (articulo[0].equals(codigoArticulo)) {
                        encontrado = true;
                        break;
                    }
                }
                if (encontrado) {
                    break;
                } else {
                    System.out.println("El código ingresado no existe, intente nuevamente.");
                }
            }

            System.out.print("Ingrese la cantidad a facturar: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine();

            String nombreArticulo = "";
            double precioUnitario = 0.0;
            for (String[] articulo : articulos) {
                if (articulo[0].equals(codigoArticulo)) {
                    nombreArticulo = articulo[1];
                    precioUnitario = Double.parseDouble(articulo[2]);
                    break;
                }
            }

            double descuentoItem = 0.0;
            if (cantidad > 5) {
                descuentoItem = precioUnitario * 0.1;
            }
            double subtotal = (precioUnitario * cantidad) - (descuentoItem * cantidad);

            DetalleFactura detalleFactura = new DetalleFactura(codigoArticulo, nombreArticulo, cantidad, precioUnitario, descuentoItem, subtotal);
            factura.agregarDetalleFactura(detalleFactura);

            System.out.print("¿Desea continuar cargando artículos? (si/no): ");
            String continuar = scanner.nextLine();
            if (!continuar.equalsIgnoreCase("si")) {
                break;
            }
        }

        factura.calcularMontoTotal();

        System.out.println("Fecha: " + factura.getFechaFactura());
        System.out.println("Número: " + factura.getNumeroFactura());
        System.out.println("Cliente: " + factura.getCliente());
        System.out.println("Código Artículo\tNombre Artículo\tCantidad\tPrecio Unitario\tDescuento\tSubtotal");
        for (DetalleFactura detalle : factura.getDetallesFactura()) {
            System.out.printf("%-15s %-15s %-10d %-15.2f %-10.2f %-10.2f%n",
                    detalle.getCodigoArticulo(),
                    detalle.getNombreArticulo(),
                    detalle.getCantidad(),
                    detalle.getPrecioUnitario(),
                    detalle.getDescuentoItem(),
                    detalle.getSubtotal());
        }
        System.out.println("Total: " + factura.getTotalCalculadoFactura());

        scanner.close();
    }
}
