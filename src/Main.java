
//org.junit.jupiter:junit-jupiter:5.10.0
import Shapes.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String figuresDbFilePath = "./figures.txt";

    public static void loadFiguresFromFile(List<Figure> figures){
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

    public static void saveFiguresToFile(List<Figure> figures) {
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
        // Load previous figures
        List<Figure> figures = new ArrayList<>();
        // Load figures from file
        loadFiguresFromFile(figures);

        System.out.print("Choose the input method you want to create figures (random | stream): ");
        Scanner sc;
        sc = new Scanner(System.in);
        String inputType = sc.nextLine();
        int N;
        FigureFactory ff;

        switch (inputType.toLowerCase()) {
            case "random" -> {
                ff = AbstractFigureFactory.getFactory("random");
                System.out.println("How many figures do you want to read: ");
                N = sc.nextInt();
                sc.nextLine();

                for (int i = 0; i < N; i++) {
                    Figure f = ff.create();
                    if (f != null) figures.add(f);
                }
            }
            case "stream" -> {
                System.out.println("Enter the input method (file or stdin): ");
                String inputMethod = sc.nextLine().trim().toLowerCase();

                if (inputMethod.equals("stdin")) {
                    System.out.println("How many figures do you want to read: ");
                    N = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter the data:");
                    ff = AbstractFigureFactory.getFactory("stream", System.in);

                    for (int i = 0; i < N; i++) {
                        Figure f = ff.create();
                        if (f != null) figures.add(f);
                    }
                } else if (inputMethod.equals("file")) {
                    System.out.println("Enter the path to the file: ");
                    String filePath = sc.nextLine();

                    System.out.println("How many figures do you want to read: ");
                    N = sc.nextInt();
                    sc.nextLine();

                    try (InputStream fileStream = new FileInputStream(filePath)) {
                        ff = AbstractFigureFactory.getFactory("stream", fileStream);
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
            default -> System.out.println("Invalid input type");
        }

//• To list them you use the "to-string" operation and then present the output to the user.
//• To clone them you use the "clone" method.
//• Deleting a figure in C++ will require you to consider a few details, such as adding virtual destructors. Using smart pointers will also help a lot (see the next step)
//• Storing the figures in a file should also rely on the "to-string" operation.
        System.out.println("List of available commands:\n" +
                "list - displays all figures\nclone - clones a figure\n" +
                "delete - deletes a figure");
        String command;
        int figureNumber;
        while((command = sc.nextLine()).equalsIgnoreCase("exit")){
            switch(command){
                case "list":
                    for(int i = 0; i < figures.size(); i++){
                        Figure currFig = figures.get(i);
                        System.out.println((i + 1) + "-" + currFig.toString());
                    }
                    break;
                case "clone":
                    System.out.print("Enter figure number you want to clone: ");
                    figureNumber = sc.nextInt();
                    sc.nextLine();
                    try{
                        Figure FigToBeCloaned = figures.get(figureNumber - 1);
                        figures.add(FigToBeCloaned.clone());
                    }
                    catch(IndexOutOfBoundsException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "delete":
                    System.out.print("Enter figure number you want to delete: ");
                    figureNumber = sc.nextInt();
                    sc.nextLine();
                    try{
                        figures.remove(figureNumber - 1);
                    }
                    catch(IndexOutOfBoundsException e){
                        throw new RuntimeException(e);
                    }
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }

        // Save the figures to the file
        saveFiguresToFile(figures);



    }
}