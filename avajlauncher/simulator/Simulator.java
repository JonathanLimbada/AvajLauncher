import java.io.*;
import weather.*;
import vehicles.*;
import java.util.*;

public class Simulator {

    private static List<Flyable> flyables = new ArrayList<>();

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide a scenario file");
            System.exit(1);
        }
        WeatherTower weatherTower = new WeatherTower();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            Coordinates.writer = new PrintWriter(new FileWriter("simulation.txt"));
            String line = reader.readLine();
            int simulations = Integer.parseInt(line.split(" ")[0]);
            if (simulations < 0) {
                System.out.println("Invalid simulations count: " + simulations);
                System.exit(1);
            }
            int c = 0;
            while ((line = reader.readLine()) != null) {
                Flyable flyable = AircraftFactory.newAircraft(
                        line.split(" ")[0], line.split(" ")[1],
                        Integer.parseInt(line.split(" ")[2]),
                        Integer.parseInt(line.split(" ")[3]),
                        Integer.parseInt(line.split(" ")[4]));
                if (flyable == null) {
                    System.err.println("Error : invalid aircraft type");
                    System.exit(1);
                }
                if (Integer.parseInt(line.split(" ")[4]) > 100 || Integer.parseInt(line.split(" ")[4]) < 0) {
                    System.err.println("Error : height must be between 0 and 100");
                    System.exit(1);
                }
                if (Integer.parseInt(line.split(" ")[3]) < 0) {
                    System.err.println("Error : latitude must be greater than 0");
                    System.exit(1);
                }
                if (Integer.parseInt(line.split(" ")[2]) < 0) {
                    System.err.println("Error : longitude must be greater than 0");
                    System.exit(1);
                }
                flyables.add(flyable);
                c = 1;
            }
            for (Flyable flyable : flyables) {
                flyable.registerTower(weatherTower);
            }
            for (int i = 0; i < simulations; i++) {
                weatherTower.changeWeather();
            }
            if (c == 0) {
                Coordinates.writer.println("No Aircraft are in the skies");
                    
            } 

        } catch (FileNotFoundException e) {
			System.out.println("Couldn't find file " + args[0]);
		} catch (IOException e) {
			System.out.println("There was an error while reading the file " + args[0]);
		} catch (ArrayIndexOutOfBoundsException e) {
		} catch (NullPointerException e) {
			System.out.println("value is null");
		} catch (NumberFormatException e) {
			System.out.println("not a valid number entered in file");
        } finally {
            if (Coordinates.writer != null)
            Coordinates.writer.close();
        }
    }
}