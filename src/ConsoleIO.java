import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleIO {
	// Instance variables
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;

    // Constructor
    public static String readInput()
    {
        // Give return string a temp value so it 
        // resembles an error when nothing was read
        String returnString = "Error";
        try
        {
            // Try to read a line of text:
            returnString = br.readLine();
        }
        catch(Exception e)        
        {
            // If the read didn't work, put something in the console:
            writeOutput("Something went wrong: " + e.getMessage());
        }
        
        return returnString;
    }
    
    // Display output
    public static void writeOutput(String message)
    {
        System.out.println(message);
    }
}
