import java.io.*;


public class ResponseHelper {


    public static String readRequest(BufferedReader in) throws IOException {
        String header = "";

        String line = null;
        while ((line = in.readLine()) != null) {
            header = header + line + "\n";

            if (line.length() == 0) {
                break;
            }
        }

        String body = "";
        while (in.ready()) {
            char c = (char) in.read();
            body = body + c;
        }

        System.out.println(header + "\n\n" + body);
        return header + "----" + body;
    }


    public static String respond(String request) throws IOException {
        if(request == null || request.length() == 0) return null;

        String firstLine = request.split("\n")[0];
        String bodyString = "";
        if(request.split("----").length == 2){
            bodyString = request.split("----")[1];
        }

        String request_type = firstLine.split(" ")[0];
        String file_type = firstLine.split(" ")[1];

        if (request_type.equals("POST")) {

            FileHelper.writeFile(bodyString.trim() + "::: \n");

        } else if (request_type.equals("GET")) {

            if (file_type.equals("/index.html")) {

                String serverResponse = "HTTP/1.1 200 OK";
                String body = FileHelper.readFile("/index.html");

                return serverResponse + "\n\n" + body;

            } else if (file_type.equals("/style.css")) {

                String serverResponse = "HTTP/1.1 200 OK\nContent-Type: text/css";
                String body = FileHelper.readFile("/style.css");

                return serverResponse + "\n\n" + body;

            } else if (file_type.equals("/script.js")) {
                String serverResponse = "HTTP/1.1 200 OK\nContent-Type: text/javascript";
                String body = FileHelper.readFile("/script.js");

                return serverResponse + "\n\n" + body;

            } else if (file_type.equals("/data.txt")) {

                String serverResponse = "HTTP/1.1 200 OK\nContent-Type: text";
                String body = FileHelper.readFile("/data.txt");

                return serverResponse + "\n\n" + body;
            }



        }

        return null;
    }

}