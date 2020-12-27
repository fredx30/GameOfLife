package core.entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class EntityUtility {

    /**
     * Loads an entity/start-state from a file
     * @param file A file where - represents an unset square and a + represents a set square.
     * @returns a 2d boolean array representing a set of squares that represents a spaceship, still-life, or space-goo.
     */
    public static boolean[][] loadFromFile(File file) throws FileNotFoundException {
        Scanner myReader = new Scanner(file);
        System.out.println("File " +  file.getName() + " found! Printing Contents:");
        boolean[][] loadedEntity = new boolean[EntityUtility.countLinesInFile(file)][];

        for (int i = 0; myReader.hasNextLine(); i++) {
            String data = myReader.nextLine();
            System.out.println(data);
            loadedEntity[i] = EntityUtility.charToBoolean(data.toCharArray(),'+');
        }
        myReader.close();
        return loadedEntity;
    }

    /**
     * Takes an array of characters and returns an array of matches (where haystack[i] == needle)
     * @param haystack Array to find matches in.
     * @param needle Char to match array against.
     * @return the condition for the match `haystack[i] == needle`
     */
    public static boolean[] charToBoolean(char[] haystack, char needle) {
        boolean[] boolArray = new boolean[haystack.length];
        for (int i = 0; i < haystack.length; i++) {
            boolArray[i] = haystack[i] == needle;
        }
        return boolArray;
    }

    public static int countLinesInFile(File file) throws FileNotFoundException{
        Scanner myReader = new Scanner(file);
        int count = 0;
        while (myReader.hasNextLine()) {
            count++;
            myReader.nextLine();
        }
        return count;
    }

    public static void writeConfigurationToFile(String[] configuration, File entityConfigurationFile) throws IOException {
        FileWriter entityConfigurationWriter = new FileWriter(entityConfigurationFile);
        for (String spaceshipConfigurationRow : configuration) {
            entityConfigurationWriter.write(spaceshipConfigurationRow);
        }
        entityConfigurationWriter.close();
        // Read-back test comes later for now lets assume the file has been written.
    }

    /**
     * Load a start configuration form a file.
     * @param entityConfigurationFile sample files are found in `%project_root%/configurations`
     * @return an entity configuration compatible with `loadEntityFromString(...)` from a file.
     * @throws IOException on IO errors.
     */
    public static String[] readConfigurationFileToString(File entityConfigurationFile) throws IOException {
        ArrayList<String> readBack = new ArrayList<>();
        Scanner entityConfigurationReader = new Scanner(entityConfigurationFile);
        for (int i = 0; entityConfigurationReader.hasNext(); i++) {
            readBack.add(entityConfigurationReader.nextLine());
        }
        return (String[]) readBack.toArray();
    }

    /**
     * Loads one entity from a string.
     * @param entity string where rows are denoted by `\n` and active square are denoted by `+`
     * @return A 2d array representing the active status of the squares in the entity.
     */
    public static boolean[][] loadEntityFromString(String entity) {
        String[] entityRows = entity.split("\n");
        return loadEntityFromString(entityRows);
    }

    /**
     * Loads one entity from a string.
     * @param entityRows string where rows each represent a element in the string array and active square are denoted by `+`
     * @return A 2d array representing the active status of the squares in the entity.
     */
    public static boolean[][] loadEntityFromString(String[] entityRows) {
        int colCount = entityRows[0].length();
        boolean[][] outputEntity = new boolean[entityRows.length][colCount];
        for (int i = 0; i<entityRows.length; i++) {
            char[] characterRowToParse = entityRows[i].toCharArray();
            for (int j = 0; j < colCount; j++) {
                outputEntity[i][j] = characterRowToParse[j] == '+';
            }
        }
        return outputEntity;
    }

}
