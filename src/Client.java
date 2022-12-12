import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    
    public static void main(String[] args) {
        
        int PORT=12345;
        try{
            Socket cs = new Socket("localhost",PORT); //open socket with local host

            //get the input output streams
            OutputStream os = cs.getOutputStream(); //set up output stream
            BufferedOutputStream bos = new BufferedOutputStream(os);//buffer the output
            DataOutputStream dos = new DataOutputStream(bos); //output stream into data

            // dos.writeUTF("Hello World from Client"); //write the message
            // dos.flush();
            // System.out.println("Message sent to server");//inform that mesage have been sent
            // cs.close();

            //start part 2 here for continous reading
            Scanner inputSc=new Scanner(System.in);
            String line;
            while((line=inputSc.nextLine())!=null){ //as long as line is not null
                if (line.equalsIgnoreCase("close")){ //if line = "close"
                    System.out.println("Exit from shell");
                    dos.writeUTF("close");
                    dos.flush();
                    break;
                }
                dos.writeUTF(line);
                dos.flush();
                System.out.println("Message Sent to Server: "+line);
            }
            cs.close();
            inputSc.close();

        }
        catch(UnknownHostException e){
            System.out.println("Unable to reach the host");
        }
        catch(IOException e){
            System.out.println("IO error");
        }
    }
}
