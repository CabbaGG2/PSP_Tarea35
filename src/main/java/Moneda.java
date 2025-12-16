import com.google.gson.annotations.SerializedName;

public class Moneda {

    @SerializedName("name")
    private String nombre;

    @SerializedName("symbol")
    private String simbolo;

    @SerializedName("price_usd")
    private double valorEnDolares;

    @SerializedName("rank")
    private int rank;

    @SerializedName("percent_change_24h")
    private String change24h;

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

