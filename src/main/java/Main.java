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

            //System.out.println(respuesta.body());

            Optional<Moneda> monedaFiltrada = Arrays.stream(monedaResponse.getData())
                    .filter(m -> (m.getNombre() != null && m.getNombre().toLowerCase().contains(nombre)) ||  (m.getNombre() != null && m.getSimbolo().toLowerCase().contains(nombre)))
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

        //soy el rey del codigo limpio

        double varianza = Double.parseDouble(moneda.getChange24h());

        String signo = varianza > 0 ? "+" : (varianza < 0 ? "-" : "");

        //codigos para colorines chachis

        final String ANSI_REINICIO = "\u001B[0m";
        final String ANSI_VERDE = "\u001B[32m";
        final String ANSI_ROJO = "\u001B[31m";

        //lógica para elegir el color según la variación de la cripto

        String color = (varianza == 0.0) ?  ANSI_REINICIO : (varianza >= 0 ? ANSI_VERDE : ANSI_ROJO);

        //Formato de salida por consola

        System.out.println("================================================");
        System.out.println("Moneda: " + moneda.getNombre() + " - " + moneda.getSimbolo());
        System.out.println("Precio (USD): $ " + moneda.getValorEnDolares());
        System.out.println("Ranking: # " + moneda.getRank());
        System.out.print("Variación en 24h: " + color);
        if (varianza != 0.0) {
            System.out.print(signo + Math.abs(varianza) + " %");
        } else {
            System.out.println("N/D");
        }
        System.out.println(ANSI_REINICIO);
        System.out.println("================================================");
    }

}

