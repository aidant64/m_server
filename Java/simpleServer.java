//HTTP/1.1 200 OK\nContent-Type: text/css\nContent-Length: 
//char *hello = "HTTP/1.1 200 OK\nContent-Type: text/plain\nContent-Length: 11\n\nHello, Ally";
import java.net.*;
import java.io.*;

public class simpleServer {


    public simpleServer(int port) {

        // starts server and waits for a connection
        try {
            try (ServerSocket server = new ServerSocket(port)) {
                System.out.println("Server waiting for a client ...");

                while (true) {

                    final Socket socket = server.accept();
                    System.out.println("Client accepted");

                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    // print client request
                    String request = ResponseHelper.readRequest(in);
                    String response = ResponseHelper.respond(request);
                    if(response != null){ 
                        out.write(response);
                    } else {
                        System.out.println("Responded null to : \n");
                    }
                    
                    // close connection
                    out.close();
                    in.close();
                    socket.close();

                }

            }
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    
    public static void main(String args[]) {
        new simpleServer(8080);
    }

}
