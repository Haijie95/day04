package SendFileInSocket;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerOne {

    public static void main(String[] args) {
        int PORT=12000;
        System.out.println("Server Initialised");

        try{
            ServerSocket server =  new ServerSocket(PORT);
            Socket sc = server.accept();

            DataInputStream dis = new DataInputStream(new BufferedInputStream(sc.getInputStream()));

            String line=dis.readUTF();
            while(!line.equalsIgnoreCase("EOF") && line!=null){
                System.out.println("Got: ->"+line);
                try{
                    //read the next line
                    line=dis.readUTF();
                } catch(EOFException e){
                    System.out.println("Reached End Of File");
                    break;
                }
            }
            sc.close();
            server.close();
        }
        catch(IOException e){
            System.out.println("IO Error!");
        }
        
    }
    
}
