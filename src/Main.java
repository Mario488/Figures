//org.junit.jupiter:junit-jupiter:5.10.0
import Shapes.*;
import java.io.*;
import java.util.*;

public class Main {
    private static final List<Figure> figures = new ArrayList<>();
    private static final String figuresDbFilePath = "./figures.txt";
    private static void listFigures(){
        for(int i = 0; i < figures.size(); i++){
            Figure currFig = figures.get(i);
            System.out.println((i + 1) + "-" + currFig.toString());
        }
    }
    private static int readFigureCount(Scanner sc){
        while(true){
            try{
                int N = sc.nextInt();
                sc.nextLine();
                return N;
            }
            catch(InputMismatchException e){
                System.out.println("You must enter a number!");
                sc.nextLine();
            }
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
    private static void generateFigures(FigureFactory ff, int N){
        for (int i = 0; i < N; i++) {
            Figure f = ff.create();
            if (f != null) figures.add(f);
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
        int N = readFigureCount(sc);
        generateFigures(ff, N);
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
        while(true){
            System.out.println("Enter the input method (file or stdin): ");
            String inputMethod = sc.nextLine().trim().toLowerCase();

            if (inputMethod.equals("stdin")) {
                System.out.println("How many figures do you want to read: ");
                int N = readFigureCount(sc);
                System.out.println("Enter the data:");
                FigureFactory ff = AbstractFigureFactory.getFactory("stream", System.in);
                generateFigures(ff, N);
                return;
            }
            else if (inputMethod.equals("file")) {
                System.out.println("Enter the path to the file: ");
                String filePath = sc.nextLine();

                System.out.println("How many figures do you want to read: ");
                int N = readFigureCount(sc);

                try (InputStream fileStream = new FileInputStream(filePath)) {
                    FigureFactory ff = AbstractFigureFactory.getFactory("stream", fileStream);
                    generateFigures(ff, N);
                    return;
                } catch (IOException e) {
                    System.err.println("Failed to read from file: " + e.getMessage());
                }
            } else {
                System.out.println("Invalid input method");
            }
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
    public static void handleInputMethod(Scanner sc) {
        while (true) {
            System.out.print("Choose the input method to create figures (random | stream): ");
            String inputType = sc.nextLine().trim().toLowerCase();

            switch (inputType) {
                case "random" -> {
                    handleRandomInput(sc);
                    return;
                }
                case "stream" -> {
                    handleStreamInput(sc);
                    return;
                }
                default -> System.out.println("Invalid input type. Available are: 'random' and 'stream'.");
            }
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
        handleInputMethod(sc);
        System.out.println("List of available commands:\n" +
                "list - displays all figures\nclone - clones a figure\n" +
                "delete - deletes a figure");
        handleUserCommands(sc);
        // Save the figures to the file
        saveFiguresToFile();
    }
}