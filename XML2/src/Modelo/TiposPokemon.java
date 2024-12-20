package Modelo;

public class TiposPokemon {
    private int id;
    private String name;
    private String weaknesses;
    private String strengths;
    private String resistances;

    public TiposPokemon(int id, String name, String weaknesses, String strengths, String resistances) {
        this.id = id;
        this.name = name;
        this.weaknesses = weaknesses;
        this.strengths = strengths;
        this.resistances = resistances;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(String weaknesses) {
        this.weaknesses = weaknesses;
    }

    public String getStrengths() {
        return strengths;
    }

    public void setStrengths(String strengths) {
        this.strengths = strengths;
    }

    public String getResistances() {
        return resistances;
    }

    public void setResistances(String resistances) {
        this.resistances = resistances;
    }

    @Override
    public String toString() {
        return "PokemonType [id=" + id + ", name=" + name + ", weaknesses=" + weaknesses +
                ", strengths=" + strengths + ", resistances=" + resistances + "]";
    }
}