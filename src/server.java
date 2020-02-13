import java.net.*;
import java.io.*;

class server
{
    public static void main(String args[]) throws IOException
    {
        ServerSocket server = new ServerSocket (3359);
        System.out.println("Server waiting for client connection on port 3359");

        Socket client = server.accept();
        System.out.println("Connection Established");

        PrintStream ps = new PrintStream(client.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));

        ps.println("Hello!");

        while(true) {
            
            String fromClient, toClient;

            while((fromClient = br.readLine()) != null) {
                String[] words = fromClient.split("\\W+");

                if (!(words[0].equals("add") || words[0].equals("subtract") || words[0].equals("multiply"))) {
                    ps.println("ERROR -1: incorrect operation command.");
                } 
                else if (words.length <= 1) {
                    ps.println("ERROR -2: number of inputs is less than two.");
                }
                else if (words.length > 4) {
                    ps.println("ERROR -3: number of inputs is more than four.");
                }
                else {
                    int answer = Integer.parseInt(words[1]);

                    if (words[0].equals("add")) {
                        for(int i = 2; i < words.length; i++) {
                            answer = answer + Integer.parseInt(words[i]);
                        }
                    }
                    else if (words[0].equals("subtract")) {
                        for(int i = 2; i < words.length; i++) {
                            answer = answer - Integer.parseInt(words[i]);
                        }
                    }
                    else if (words[0].equals("multiply")) {
                        for(int i = 2; i < words.length; i++) {
                            answer = answer*Integer.parseInt(words[i]);
                        }
                    }

                    ps.println("Answer: " + answer);

                    }

            }

            // close connection 
            ps.close(); 
            br.close();  
            server.close(); 
            client.close(); 
  
            // terminate application 
            //System.exit(0); 
                
        }
    }
}