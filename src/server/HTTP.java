package server;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;


public class HTTP  {

    public static void main(String[] args) throws IOException {
        int port = 1109;

        HttpServer httpServer = HttpServer.create(new InetSocketAddress(port), 0);
        HttpContext httpContext = httpServer.createContext("/");
        httpContext.setHandler(HTTP::manageRequest);

        System.out.println(" hola ");
        httpServer.start();

    }

    private static void manageRequest(HttpExchange httpExchange) throws IOException {
        final int RESPONSE_CODE = 200;
        FileInputStream fip = new FileInputStream("data/index.html");

        System.out.println( "Available " + fip.available() );
        byte[] contentResponse = fip.readAllBytes();

        httpExchange.sendResponseHeaders(RESPONSE_CODE, contentResponse.length);

        OutputStream os = httpExchange.getResponseBody();

        os.write(contentResponse);

        os.close();
    }

}
