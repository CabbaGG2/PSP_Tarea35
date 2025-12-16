import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

import static java.util.Locale.filter;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre de la criptomoneda o simbolo:");
        String nombre = sc.nextLine().trim().toLowerCase();

        try {
            obtenerMonedas(nombre);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    private static final String API_URL = "https://api.coinlore.net/api/tickers/";

    private static void obtenerMonedas(String nombre) throws IOException, InterruptedException {

        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        if(respuesta.statusCode() == 200) {
            Gson gson = new Gson();
            MonedaResponse monedaResponse = gson.fromJson(respuesta.body(), MonedaResponse.class);

            Optional<Moneda> monedaFiltrada = Arrays.stream(monedaResponse.getData())
                    .filter(m -> m.getNombre().toUpperCase().contains(nombre) || m.getSimbolo().toUpperCase().contains(nombre))
                    .findFirst();
            if (monedaFiltrada.isPresent()) {
                mostrarMonedaFiltrada(monedaFiltrada.get());
            }else{
                System.out.println("No se encontró la criptomoneda especificada.");
            }
        } else {
            System.out.println("Error al obtener los datos de la API. Código de estado: " + respuesta.statusCode());
        }
    }

    private static void mostrarMonedaFiltrada(Moneda moneda) {
        System.out.println("Nombre: " + moneda.getNombre());
        System.out.println("Simbolo: " + moneda.getSimbolo());
        System.out.println("Valor en Dólares: " + moneda.getValorEnDolares());
        System.out.println("Rank: " + moneda.getRank());
        System.out.println("Cambio en 24h: " + moneda.getChange24h());
    }

}

