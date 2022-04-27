import java.net.*;
import java.io.*;
import java.util.*;



public class Server {
    static List<Book> bibliography = new ArrayList<Book>();
    public static void main(String[] args) throws IOException {
        int port = new Integer(args[0]).intValue();
        ServerSocket socket = new ServerSocket(port);
        


        while (true) {
            // Listen for a TCP connection request.
            Socket connection = socket.accept();

            // Construct an object to process the HTTP request message.
            Protocol request;
            try {
                request = new Protocol(connection,bibliography);


                // Create a new thread to process the request.
                Thread thread = new Thread(request);

                thread.start();

            
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

          

        
        }
		
            

           
        }

        


    }



    


    

