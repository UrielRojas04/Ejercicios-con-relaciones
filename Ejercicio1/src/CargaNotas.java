import java.util.ArrayList;
import java.util.Scanner;

public class CargaNotas {
    public static void main(String[] args) {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de alumnos: ");
        int cantidadAlumnos = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < cantidadAlumnos; i++) {
            System.out.print("Ingrese el nombre completo del alumno " + (i + 1) + ": ");
            String nombreCompleto = scanner.nextLine();
            System.out.print("Ingrese el legajo del alumno " + (i + 1) + ": ");
            long legajo = scanner.nextLong();
            scanner.nextLine();

            Alumno alumno = new Alumno(nombreCompleto, legajo);

            System.out.print("Ingrese la cantidad de notas para el alumno " + (i + 1) + ": ");
            int cantidadNotas = scanner.nextInt();
            scanner.nextLine();

            if (cantidadNotas < 1) {
                System.out.println("Debe ingresar al menos una nota.");
                i--;
                continue;
            }

            for (int j = 0; j < cantidadNotas; j++) {
                System.out.print("Ingrese la cátedra de la nota " + (j + 1) + ": ");
                String catedra = scanner.nextLine();
                System.out.print("Ingrese la nota del examen " + (j + 1) + ": ");
                double notaExamen = scanner.nextDouble();
                scanner.nextLine();

                Nota nota = new Nota(catedra, notaExamen);
                alumno.agregarNota(nota);
            }

            alumnos.add(alumno);
        }

        for (Alumno alumno : alumnos) {
            System.out.println("Nombre completo: " + alumno.getNombreCompleto());
            System.out.println("Legajo: " + alumno.getLegajo());
            System.out.println("Notas:");
            for (Nota nota : alumno.getNotas()) {
                System.out.println("  Cátedra: " + nota.getCatedra() + ", Nota: " + nota.getNotaExamen());
            }
            System.out.println("Promedio de notas: " + alumno.calcularPromedio());
            System.out.println();
        }

        scanner.close();
    }
}

