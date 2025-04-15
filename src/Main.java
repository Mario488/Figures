//org.junit.jupiter:junit-jupiter:5.10.0
import Shapes.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Figure> figures = new ArrayList<>();
    private static final String figuresDbFilePath = "./figures.txt";
    private static void listFigures(){
        for(int i = 0; i < figures.size(); i++){
            Figure currFig = figures.get(i);
            System.out.println((i + 1) + "-" + currFig.toString());
        }
    }
    private static void cloneFigure(Scanner sc){
        System.out.print("Enter figure number you want to clone: ");
        int figureNumber = sc.nextInt();
        sc.nextLine();
        try{
            Figure FigToBeCloaned = figures.get(figureNumber - 1);
            figures.add(FigToBeCloaned.clone());
        }
        catch(IndexOutOfBoundsException e) {
            throw new RuntimeException(e);
        }
    }
    private static void deleteFigure(Scanner sc){
        System.out.print("Enter figure number you want to delete: ");
        int figureNumber = sc.nextInt();
        sc.nextLine();
        try{
            figures.remove(figureNumber - 1);
        }
        catch(IndexOutOfBoundsException e){
            throw new RuntimeException(e);
        }
    }
    private static void handleRandomInput(Scanner sc){
        FigureFactory ff = AbstractFigureFactory.getFactory("random");
        System.out.println("How many figures do you want to read: ");
        int N = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < N; i++) {
            Figure f = ff.create();
            if (f != null) figures.add(f);
        }
    }
    private static void handleUserCommands(Scanner sc){
        String command;
        while(!(command = sc.nextLine()).equalsIgnoreCase("exit")){
            switch(command){
                case "list":
                    listFigures();
                    break;
                case "clone":
                    cloneFigure(sc);
                    break;
                case "delete":
                    deleteFigure(sc);
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }
    }
    private static void handleStreamInput(Scanner sc){
        System.out.println("Enter the input method (file or stdin): ");
        String inputMethod = sc.nextLine().trim().toLowerCase();

        if (inputMethod.equals("stdin")) {
            System.out.println("How many figures do you want to read: ");
            int N = sc.nextInt();
            sc.nextLine();

            System.out.println("Enter the data:");
            FigureFactory ff = AbstractFigureFactory.getFactory("stream", System.in);

            for (int i = 0; i < N; i++) {
                Figure f = ff.create();
                if (f != null) figures.add(f);
            }
        }
        else if (inputMethod.equals("file")) {
            System.out.println("Enter the path to the file: ");
            String filePath = sc.nextLine();

            System.out.println("How many figures do you want to read: ");
            int N = sc.nextInt();
            sc.nextLine();

            try (InputStream fileStream = new FileInputStream(filePath)) {
                FigureFactory ff = AbstractFigureFactory.getFactory("stream", fileStream);
                for (int i = 0; i < N; i++) {
                    Figure f = ff.create();
                    if (f != null) figures.add(f);
                }
            } catch (IOException e) {
                System.err.println("Failed to read from file: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid input method");
        }
    }
    public static void loadFiguresFromFile(){
        try(InputStream fileStream = new FileInputStream(figuresDbFilePath)){
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream));
            String line;
            while((line = reader.readLine())  != null){
                if(!line.isBlank()){
                    try{
                        Figure fig = StringToFigure.createFrom(line);
                        figures.add(fig);
                    } catch (Exception e) {
                        System.err.println("Error loading figure from file: " + e.getMessage());
                    }
                }
            }
            System.out.println("Loaded " + figures.size() + " figures from file");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void saveFiguresToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(figuresDbFilePath))) {
            for (Figure fig : figures) {
                writer.println(fig.toString());
            }
            System.out.println("Figures saved to file.");
        } catch (IOException e) {
            System.err.println("Error saving figures: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Load figures from file
        loadFiguresFromFile();
        Scanner sc = new Scanner(System.in);

        System.out.print("Choose the input method you want to create figures (random | stream): ");
        String inputType = sc.nextLine();

        switch (inputType.toLowerCase()) {
            case "random" -> handleRandomInput(sc);
            case "stream" -> handleStreamInput(sc);
            default -> System.out.println("Invalid input type");
        }

        System.out.println("List of available commands:\n" +
                "list - displays all figures\nclone - clones a figure\n" +
                "delete - deletes a figure");

        handleUserCommands(sc);
        // Save the figures to the file
        saveFiguresToFile();
    }
}