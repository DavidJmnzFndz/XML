package Vista;

import Servicio.TiposServicio;

public class Main {
    public static void main(String[] args) {
        String filePath = "XML_Document/TiposPokemon.xml";
        TiposServicio service = new TiposServicio(filePath);

        Menu menu = new Menu(service);
        menu.mostrarMenu();
    }
}
