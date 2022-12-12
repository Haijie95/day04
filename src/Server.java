import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{

    public static void main(String[] args) {
        System.out.println("Socket Server");
        int PORT = 12345;
        //create server socket
        try{
            ServerSocket server = new ServerSocket(PORT); //open server

            //get the socket object
            Socket socket = server.accept(); //server to accept the socket 

            //new connection from a client(we just accepted it)
            InputStream is = socket.getInputStream(); //get the input stream
            BufferedInputStream bis = new BufferedInputStream(is);  //get the buffered input stream
            DataInputStream dis = new DataInputStream(bis); //process the input stream

            // String msg = dis.readUTF(); //if there are any msg from client, it willl be available here
            // System.out.println(msg); //print out the message from the client
            // socket.close(); //close the socket
        
            //part 2 start here
            String fromClient=dis.readUTF();
            while(!fromClient.equalsIgnoreCase("close") && fromClient!=null){

                //process the msg
                System.out.println("Received msg from client: "+fromClient);
                //read the next line from the input stream
                fromClient=dis.readUTF();
            }
            socket.close();
            server.close();
        }
        catch(IOException e){
            System.out.println("IO error");
        }
    }

}