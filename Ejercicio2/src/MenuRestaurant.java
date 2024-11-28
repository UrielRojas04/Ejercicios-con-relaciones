import java.util.ArrayList;
import java.util.Scanner;

public class MenuRestaurant {
    public static void main(String[] args) {
        ArrayList<Plato> platosMenu = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de platos: ");
        int cantidadPlatos = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < cantidadPlatos; i++) {
            System.out.print("Ingrese el nombre del plato " + (i + 1) + ": ");
            String nombreCompleto = scanner.nextLine();
            System.out.print("Ingrese el precio del plato " + (i + 1) + ": ");
            double precio = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("¿Es una bebida? (true/false): ");
            boolean esBebida = scanner.nextBoolean();
            scanner.nextLine();

            Plato plato = new Plato(nombreCompleto, precio, esBebida);

            if (!esBebida) {
                System.out.print("Ingrese la cantidad de ingredientes para el plato " + (i + 1) + ": ");
                int cantidadIngredientes = scanner.nextInt();
                scanner.nextLine();

                if (cantidadIngredientes < 1) {
                    System.out.println("Debe ingresar al menos un ingrediente.");
                    i--;
                    continue;
                }

                for (int j = 0; j < cantidadIngredientes; j++) {
                    System.out.print("Ingrese el nombre del ingrediente " + (j + 1) + ": ");
                    String nombreIngrediente = scanner.nextLine();
                    System.out.print("Ingrese la cantidad del ingrediente " + (j + 1) + ": ");
                    double cantidadIngrediente = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Ingrese la unidad de medida del ingrediente " + (j + 1) + ": ");
                    String unidadDeMedida = scanner.nextLine();

                    Ingrediente ingrediente = new Ingrediente(nombreIngrediente, cantidadIngrediente, unidadDeMedida);
                    plato.agregarIngrediente(ingrediente);
                }
            }

            platosMenu.add(plato);
        }

        System.out.println("MENÚ");
        for (Plato plato : platosMenu) {
            System.out.println(plato.nombreCompleto);
            System.out.println("Precio: $" + plato.precio);
            if (!plato.esBebida) {
                System.out.println("Ingredientes:");
                System.out.println("Nombre");
                System.out.println("NOMBRE        CANTIDAD     UNIDAD DE MEDID");
                for (Ingrediente ingrediente : plato.ingredientes) {
                    System.out.println(ingrediente.nombre+"       "+ingrediente.cantidad+"       "+ingrediente.unidadDeMedida);
                }

            }
            System.out.println();
        }

        scanner.close();
    }
}

