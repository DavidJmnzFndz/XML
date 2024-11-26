import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

//Ajax

public class Main {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            File file = new File("XML_Document/entrenamientos.xml");
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            Document doc = constructor.parse(file);
            Entrenamiento entrenamientos;
            Element elemento;
            String nombre = "";
            String nivel = "";
            int duracion = 0;
            int id = 0;
            Node node;
            NodeList nodeList = doc.getElementsByTagName("entrenamiento");

            for (int i = 0; i < nodeList.getLength(); i++) {
                node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE){
                    elemento = (Element) node;

                    id = Integer.parseInt(elemento.getAttribute("id"));
                    nombre = String.valueOf(elemento.getElementsByTagName("nombre"));
                    duracion = Integer.parseInt(elemento.getAttribute("duracion"));
                    nivel = String.valueOf(elemento.getElementsByTagName("nivel"));
                    entrenamientos = new Entrenamiento(id, nombre, duracion, nivel);
                }

                System.out.println("Entrenamiento " + id +":" +
                        "\n nombre: " + nombre + "\n duracion: " + duracion + "\n nivel :" + nivel);
            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}