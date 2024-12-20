package DAO;

import Modelo.TiposPokemon;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.File;
import java.util.ArrayList;

public class TiposControlador {
    private String filePath;

    public TiposControlador(String filePath) {
        this.filePath = filePath;
    }

    // Leer XML y devolver lista de objetos
    public ArrayList<TiposPokemon> readAll() {
        ArrayList<TiposPokemon> types = new ArrayList<>();
        try {
            File xmlFile = new File(filePath);
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("Type");

            for (int i = 0; i < nodeList.getLength(); i++) {
                types.add(getPokemonType(nodeList.item(i)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return types;
    }

    private TiposPokemon getPokemonType(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            int id = Integer.parseInt(element.getAttribute("id"));
            String name = getTagValue("name", element);
            String weaknesses = getTagValue("weaknesses", element);
            String strengths = getTagValue("strengths", element);
            String resistances = getTagValue("resistances", element);

            return new TiposPokemon(id, name, weaknesses, strengths, resistances);
        }
        return null;
    }

    private String getTagValue(String tag, Element element) {
        return element.getElementsByTagName(tag).item(0).getTextContent();
    }

    // Añadir un nuevo tipo al XML
    public void addType(TiposPokemon type) {
        try {
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(new File(filePath));

            Element root = doc.getDocumentElement();

            Element newType = doc.createElement("Type");
            newType.setAttribute("id", String.valueOf(type.getId()));

            newType.appendChild(createElement(doc, "name", type.getName()));
            newType.appendChild(createElement(doc, "weaknesses", type.getWeaknesses()));
            newType.appendChild(createElement(doc, "strengths", type.getStrengths()));
            newType.appendChild(createElement(doc, "resistances", type.getResistances()));

            root.appendChild(newType);
            saveXML(doc);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Element createElement(Document doc, String tag, String value) {
        Element element = doc.createElement(tag);
        element.appendChild(doc.createTextNode(value));
        return element;
    }

    private void saveXML(Document doc) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filePath));
        transformer.transform(source, result);
    }

    public ArrayList<TiposPokemon> searchByAttribute(String attributeType, String attributeValue) {
        ArrayList<TiposPokemon> filteredTypes = new ArrayList<>();
        try {
            File xmlFile = new File(filePath);
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("Type");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String value = getTagValue(attributeType, element).toLowerCase();
                    if (value.contains(attributeValue.toLowerCase())) {
                        filteredTypes.add(getPokemonType(node));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filteredTypes;
    }
}