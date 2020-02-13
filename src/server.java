import java.net.*;
import java.io.*;

class server
{

    // Method completes operations from client input and returns
    public static int operations(String[] words) {
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

        return answer;
    }

    // Method to check if array only contains ints
    public static boolean areAllInts(String[] words) {
        for (int i = 1; i < words.length; i++) {
            try {
                Integer.parseInt(words[i]);
            }
            catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    // Method will handle errors or send back correct answer to client
    public static void runWithErrorHandler(PrintStream ps, String[] words) throws IOException {
        if (!(words[0].equals("add") || words[0].equals("subtract") || words[0].equals("multiply") 
        || words[0].equals("bye") || words[0].equals("terminate"))) {
            ps.println(-1);
        } 
        else if (words[0].equals("bye")) {
            ps.println(-5);
        }
        else if (words[0].equals("terminate")) {
            ps.println(-5);
        }
        else if (words.length <= 2) {
            ps.println(-2);
        }
        else if (words.length > 5) {
            ps.println(-3);
        }
        else if (!areAllInts(words)) {
            ps.println(-4);
        }
        else {
            int answer = operations(words);
            ps.println(answer);
        }
    }

    public static void main(String args[]) throws IOException
    {
        // Create server socket and establish connection
        ServerSocket server = new ServerSocket (Integer.parseInt(args[0]));
        System.out.println("Server waiting for client connection on port " + args[0]);


        // While loop to restart client socket
        restart: while (!server.isClosed()) {

            Socket client = server.accept();
            System.out.println("Connection Established...");

            // Create PrintStream ps and BufferedReader br
            PrintStream ps = new PrintStream(client.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));

            // Send connection message to client
            ps.println("Hello!");

            // Declare string
            String fromClient;
            
            // While connection is established
            while(!server.isClosed()) {
                while((fromClient = br.readLine()) != null) {
                    // Creating string array to parse
                    String[] words = fromClient.split("\\W+");

                    // Running code
                    runWithErrorHandler(ps, words);

                    if (words[0].equals("terminate")) {
                        server.close();
                    }

                }   
                // Close connection to client socket
                ps.close(); 
                br.close();   
                client.close();
                System.out.println("Client Disconnected. Server still running on port " + Integer.parseInt(args[0]));
                continue restart;
            } 

        }

        // Terminate application 
        System.out.println("Server shutting down");
        System.exit(0); 
    }
}