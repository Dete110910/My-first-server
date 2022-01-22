package server;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;


public class HTTP  {

    public static void main(String[] args) throws IOException {
        int port = 1109;

        HttpServer httpServer = HttpServer.create(new InetSocketAddress(port), 0);
        HttpContext httpContext = httpServer.createContext("/");
        httpContext.setHandler(HTTP::manageRequest);

        httpServer.start();
    }

    private static void manageRequest(HttpExchange httpExchange) throws  IOException{
        final int RESPONSE_CODE = 200;
        String contentResponse = "Response from server HTTP";

        httpExchange.sendResponseHeaders(RESPONSE_CODE, contentResponse.getBytes().length);

        OutputStream os = httpExchange.getResponseBody();

        os.write(contentResponse.getBytes());

        os.close();
    }

}
