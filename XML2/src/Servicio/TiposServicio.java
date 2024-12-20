package Servicio;

import DAO.TiposControlador;
import Modelo.TiposPokemon;

import java.util.ArrayList;
import java.util.List;

public class TiposServicio {
    private TiposControlador dao;

    public TiposServicio(String filePath) {
        this.dao = new TiposControlador(filePath);
    }

    public List<TiposPokemon> getAllTypes() {
        return dao.readAll();
    }

    public void addType(TiposPokemon type) {
        dao.addType(type);
    }

    // Operación de ejemplo: Sumar IDs de todos los tipos
    public int sumAllIDs() {
        return dao.readAll().stream().mapToInt(TiposPokemon::getId).sum();
    }

    public ArrayList<TiposPokemon> searchByAttribute(String attributeType, String attributeValue) {
        return dao.searchByAttribute(attributeType, attributeValue);
    }
}
