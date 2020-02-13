import java.net.*;
import java.io.*;

class client
{
    public static void main(String args[]) throws IOException
    {
        String message;
        Socket client = new Socket("localhost", 3359);
        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        message = br.readLine();
        System.out.println(message);

        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));

        String toServer, fromServer;

        while (!(toServer = kb.readLine()).equals("exit")) {
            dos.writeBytes(toServer + "\n");

            fromServer = br.readLine();
            System.out.println(fromServer);
        }

        // close connection. 
        dos.close(); 
        br.close(); 
        kb.close(); 
        client.close(); 

    }
}