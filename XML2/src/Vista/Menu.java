package Vista;

import Modelo.TiposPokemon;
import Servicio.TiposServicio;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private final TiposServicio service;

    public Menu(TiposServicio service) {
        this.service = service;
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Mostrar todos los tipos Pokémon");
            System.out.println("2. Buscar por nombre del tipo");
            System.out.println("3. Buscar por debilidad");
            System.out.println("4. Buscar por resistencia");
            System.out.println("5. Salir");
            System.out.print("Opción: ");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    mostrarTodosLosTipos();
                    break;
                case "2":
                    buscarPorAtributo("name");
                    break;
                case "3":
                    buscarPorAtributo("weaknesses");
                    break;
                case "4":
                    buscarPorAtributo("resistances");
                    break;
                case "5":
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, elija una opción del 1 al 5.");
                    break;
            }
        }
    }

    private void mostrarTodosLosTipos() {
        System.out.println("Lista de Tipos Pokémon:");
        service.getAllTypes().forEach(System.out::println);
    }

    private void buscarPorAtributo(String attributeType) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el valor a buscar: ");
        String attributeValue = scanner.nextLine().toLowerCase();

        List<TiposPokemon> results = service.searchByAttribute(attributeType, attributeValue);

        if (results.isEmpty()) {
            System.out.println("No se encontraron resultados para \"" + attributeValue + "\" en " + attributeType + ".");
        } else {
            System.out.println("Resultados de búsqueda:");
            results.forEach(type -> {
                System.out.println("ID: " + type.getId());
                System.out.println("Nombre: " + type.getName());
                System.out.println("Debilidades: " + type.getWeaknesses());
                System.out.println("Resistencias: " + type.getResistances());
                System.out.println("---");
            });
        }
    }
}