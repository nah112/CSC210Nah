import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LogisticMapGenerator {

    public static void main(String[] args) {
        
        if (args.length < 3) {
            System.err.println("Usage: java LogisticMapGenerator <growthRate> <initialPopulation> <iterations> [outputFile]");
            System.exit(1);
        }
        
        try {
            
            double growthRate = Double.parseDouble(args[0]);
            double initialPopulation = Double.parseDouble(args[1]);
            int iterations = Integer.parseInt(args[2]);
            String outputFile = (args.length >= 4) ? args[3] : "logistic_map_data.csv";
            
            
            try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
                
                writer.println("Iteration,Population");
                
                
                double x = initialPopulation;
                
                writer.println("0," + x);
                
                
                for (int i = 1; i <= iterations; i++) {
                    x = growthRate * x * (1 - x);
                    writer.println(i + "," + x);
                }
            }
            System.out.println("Data written to " + outputFile);
            
        } catch (NumberFormatException e) {
            System.err.println("Error: growth rate, initial population, and iterations must be numeric values.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            System.exit(1);
        }
    }
}

