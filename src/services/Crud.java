package services;

import entities.People;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public final class Crud {
    public static void register(List<People> registeredPeople, File file, String[] data, int numberOfQuestions) throws IOException {


        createUser(registeredPeople, numberOfQuestions, data);
        File userFile = new File(file + "/users/" + registeredPeople.size() + "." + data[0].replace(" ", "").toUpperCase() + ".txt");

        BufferedWriter bw = new BufferedWriter(new FileWriter(userFile));
        StringBuilder sb = new StringBuilder();

        for (String d : data) sb.append(d).append("\n");

        bw.write(sb.toString());

        bw.close();
    }

    public static void viewQuestions(File file) throws IOException {
        File formFile = new File(file + "/formulario.txt");
        BufferedReader br2 = new BufferedReader(new FileReader(formFile));
        while (br2.ready()) {
            String line = br2.readLine();
            System.out.println(line);
        }
        br2.close();
    }

    private static void createUser(List<People> registeredPeople, int numberOfQuestions, String[] data) {
        registeredPeople.add(new People(data[0], data[1], Integer.parseInt(data[2]), Double.parseDouble(data[3])));
        int fixedQuestions = 4;
        for (int i = 0; i < numberOfQuestions - fixedQuestions; i++) {
            registeredPeople.getLast().setExtraAttributes(i, data[4 + i]);
        }
    }

    public static void initialRegister(List<People> registeredPeople, File file) throws IOException {
        Files.list(Paths.get(file.getAbsolutePath() + "/users")).filter(Files::isRegularFile).forEach(fileUsers -> {
            try (BufferedReader br3 = new BufferedReader(new FileReader(file + "/users/" + fileUsers.getFileName().toString()))) {
                int numberOfQuestions = (int) Files.lines(Paths.get(file + "/formulario.txt")).count();
                String[] data = new String[numberOfQuestions];
                for (int i = 0; i < data.length; i++) data[i] = br3.readLine();
                createUser(registeredPeople, numberOfQuestions, data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void selectAllUsers(List<People> registeredPeople) {
        for (int i = 0; i < registeredPeople.size(); i++) {
            System.out.println(i + 1 + " - " + registeredPeople.get(i).getName());
        }
    }

    public static void selectByAttribute(List<People> registeredPeople, String search) {
        registeredPeople.stream().forEach(registeredPeople1 -> {
            if (registeredPeople1.getName().toUpperCase().startsWith(search.toUpperCase()))
                System.out.println(registeredPeople1.getName());
        });
    }
}

