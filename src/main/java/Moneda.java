public class Moneda {

    private String nombre;
    private String simbolo;
    private double valorEnDolares;

    private int rank;
    private String change24h;

    public Moneda(String nombre, String simbolo, double valorEnDolares, int rank, String change24h) {
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.valorEnDolares = valorEnDolares;
        this.rank = rank;
        this.change24h = change24h;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public double getValorEnDolares() {
        return valorEnDolares;
    }

    public void setValorEnDolares(double valorEnDolares) {
        this.valorEnDolares = valorEnDolares;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getChange24h() {
        return change24h;
    }

    public void setChange24h(String change24h) {
        this.change24h = change24h;
    }
}

