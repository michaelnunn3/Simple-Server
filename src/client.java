import java.net.*;
import java.io.*;

class client
{

    // Method handles data recieved from server
    public static void displayData(String fromServer) {
        if (Integer.parseInt(fromServer) > 0) {
            System.out.println(fromServer);
        }
        else if (Integer.parseInt(fromServer) == -1) {
            System.out.println("ERROR -1: incorrect operation command.");
        }
        else if (Integer.parseInt(fromServer) == -2) {
            System.out.println("ERROR -2: number of inputs is less than two.");
        }
        else if (Integer.parseInt(fromServer) == -3) {
            System.out.println("ERROR -3: number of inputs is more than four.");
        }
        else if (Integer.parseInt(fromServer) == -4) {
            System.out.println("ERROR -4: one or more of the inputs contain(s) non-number(s).");
        }
        else if (Integer.parseInt(fromServer) == -5) {
            System.out.println("RETURN -5: exit");

        }
    }

    public static void main(String args[]) throws IOException
    {
        // Create client socket
        Socket client = new Socket(args[0], Integer.parseInt(args[1]));

        // Create BufferedReader br, DataOutputStream dos and BufferedReader kb
        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));

        // Reading connection message
        System.out.println(br.readLine());

        // Declaring strings
        String toServer, fromServer;

        // While connection is established
        while (!(toServer = kb.readLine()).equals(null)) {
            // Writing to server
            dos.writeBytes(toServer + "\n");

            // Displaying data from server
            fromServer = br.readLine();
            displayData(fromServer);
            if (Integer.parseInt(fromServer) == -5) {
                break;
            }
        }
        // Close client connection
        dos.close(); 
        br.close(); 
        kb.close(); 
        client.close();
    }
}