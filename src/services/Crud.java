package services;

import entities.People;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public final class Crud {
    public static void register(List<People> registeredPeople, File file, Scanner sc) throws FileNotFoundException, IOException {
        BufferedReader br2 = new BufferedReader(new FileReader(file + "/formulario.txt"));
        while (br2.ready()) {
            String line = br2.readLine();
            System.out.println(line);
        }
        br2.close();
        String[] data = new String[(int) Files.lines(Paths.get(file + "/formulario.txt")).count()];
        for (int i = 0; i < data.length; i++) data[i] = sc.nextLine();
        registeredPeople.add(new People(data[0], data[1], Integer.parseInt(data[2]), Double.parseDouble(data[3])));
        BufferedWriter bw2 = new BufferedWriter(new FileWriter(file + "/users/" + registeredPeople.size() + "." + data[0].toUpperCase() + ".txt"));
        bw2.write(registeredPeople.getLast().toString());
        bw2.close();
    }

    public static void initialRegister(List<People> registeredPeople, File file) throws IOException {
        Files.list(Paths.get(file.getAbsolutePath() + "/users")).filter(Files::isRegularFile).forEach(fileUsers -> {
            try (BufferedReader br3 = new BufferedReader(new FileReader(file + "/users/" + fileUsers.getFileName().toString()))) {
                String[] data = new String[(int) Files.lines(Paths.get(file + "/formulario.txt")).count()];
                for (int i = 0; i < data.length; i++) data[i] = br3.readLine();
                registeredPeople.add(new People(data[0], data[1], Integer.parseInt(data[2]), Double.parseDouble(data[3])));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void selectUsers(List<People> registeredPeople, File file) throws IOException {

    }
}

