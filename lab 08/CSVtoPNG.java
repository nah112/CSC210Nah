import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVtoPNG {

    
    static class DataPoint {
        int generation;
        double population;
        DataPoint(int generation, double population) {
            this.generation = generation;
            this.population = population;
        }
    }
    
    
    public static List<DataPoint> readCSV(String fileName) {
        List<DataPoint> dataPoints = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine(); 
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length >= 2) {
                    int generation = Integer.parseInt(tokens[0].trim());
                    double population = Double.parseDouble(tokens[1].trim());
                    dataPoints.add(new DataPoint(generation, population));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
            System.exit(1);
        }
        return dataPoints;
    }
    
    
    public static void drawGraph(List<DataPoint> dataPoints, Graphics2D g, int width, int height) {
       
        int marginLeft = 60;
        int marginRight = 20;
        int marginTop = 20;
        int marginBottom = 60;
        
        
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        
      
        g.setColor(Color.BLACK);
        g.drawLine(marginLeft, height - marginBottom, width - marginRight, height - marginBottom); 
        g.drawLine(marginLeft, height - marginBottom, marginLeft, marginTop); 
        
        if (dataPoints.isEmpty()) {
            g.drawString("No data to display", width / 2 - 50, height / 2);
            return;
        }
        
        
        int minGen = dataPoints.get(0).generation;
        int maxGen = dataPoints.get(dataPoints.size() - 1).generation;
        double minPop = Double.MAX_VALUE;
        double maxPop = Double.MIN_VALUE;
        for (DataPoint dp : dataPoints) {
            if (dp.population < minPop) minPop = dp.population;
            if (dp.population > maxPop) maxPop = dp.population;
        }
        
        minPop = Math.min(0, minPop);
        
        int plotWidth = width - marginLeft - marginRight;
        int plotHeight = height - marginTop - marginBottom;
        
        
        int prevX = -1, prevY = -1;
        g.setColor(Color.BLUE);
        for (DataPoint dp : dataPoints) {
            int x = marginLeft + (int) ((dp.generation - minGen) * (double) plotWidth / (maxGen - minGen));
            int y = height - marginBottom - (int) ((dp.population - minPop) * (double) plotHeight / (maxPop - minPop));
            
            
            g.fillOval(x - 2, y - 2, 4, 4);
            if (prevX != -1) {
                g.drawLine(prevX, prevY, x, y);
            }
            prevX = x;
            prevY = y;
        }
        
        
        g.setColor(Color.BLACK);
        g.drawString("Generation", marginLeft + plotWidth / 2, height - 20);
        g.drawString("Population", 10, marginTop + plotHeight / 2);
    }
    
    public static void main(String[] args) {
        
        if (args.length < 1) {
            System.err.println("Usage: java CSVtoPNG <input_csv> [output_png]");
            System.exit(1);
        }
        
        String inputCsv = args[0];
        String outputPng = (args.length >= 2) ? args[1] : "graph_output.png";
        
        
        List<DataPoint> dataPoints = readCSV(inputCsv);
        
        
        int width = 800;
        int height = 600;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        
        drawGraph(dataPoints, g, width, height);
        g.dispose();
        
        
        try {
            ImageIO.write(image, "png", new File(outputPng));
            System.out.println("PNG graph written to " + outputPng);
        } catch (IOException e) {
            System.err.println("Error writing PNG file: " + e.getMessage());
            System.exit(1);
        }
    }
}

