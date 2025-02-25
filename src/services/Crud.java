package services;

import entities.People;
import exceptions.EmailAlreadyExistsException;
import exceptions.EmailDoesNotContainsAtException;
import exceptions.NameIsToShortException;
import exceptions.UnderAgeException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

public final class Crud {

    public static void register(List<People> registeredPeople, File file, String[] data, int numberOfQuestions) {

        try {
            if (data[0].length() < 10) throw new NameIsToShortException("Nome curto demais (10 caracteres)");
            if (!data[1].contains("@")) throw new EmailDoesNotContainsAtException("O email deve conter um @");
            if (Integer.parseInt(data[2]) < 18) throw new UnderAgeException("Idade deve ser maior que 18");
            if (registeredPeople.stream().anyMatch(person -> person.getEmail().equals(data[1])))
                throw new EmailAlreadyExistsException("Esse email já está registrado");

            createUser(registeredPeople, numberOfQuestions, data);

            File userFile = new File(file + "/users/" + registeredPeople.size() + "." + data[0].replace(" ", "").toUpperCase() + ".txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(userFile));

            StringBuilder sb = new StringBuilder();

            for (String d : data) sb.append(d).append("\n");

            bw.write(sb.toString());
            bw.close();
            System.out.println("Usuário cadastrado com sucesso!");

        } catch (NameIsToShortException | IOException | EmailDoesNotContainsAtException | UnderAgeException |
                 NumberFormatException | EmailAlreadyExistsException e) {
            System.out.println("Erro: " + e.getMessage());
        }
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
        for (int i = 0; i < numberOfQuestions - People.fixedQuestions; i++) {
            registeredPeople.getLast().setExtraAttributes(i, data[People.fixedQuestions + i]);
        }
    }

    public static void initialRegister(List<People> registeredPeople, File file) throws IOException {
        Files.list(Paths.get(file.getAbsolutePath() + "/users")).filter(Files::isRegularFile).
                sorted(Comparator.comparingInt(path -> {
                    String filename = path.getFileName().toString();
                    return Integer.parseInt(filename.split("\\.")[0]);
                })).forEach(fileUsers -> {
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

    public static void selectByAttribute(List<People> registeredPeople, String search, int numberOfQuestions) {
        System.out.println("Retorno da busca:");
        registeredPeople.stream().forEach(registeredPeople1 -> {
            if (registeredPeople1.getName().toUpperCase().startsWith(search.toUpperCase())) {
                System.out.println(registeredPeople1.toString());
                for (int i = 0; i < numberOfQuestions - People.fixedQuestions; i++) {
                    if (registeredPeople1.getExtraAttributes(i) == null)
                        System.out.print("Pergunta não respondida pelo usuário");
                    else System.out.print(registeredPeople1.getExtraAttributes(i));
                }
                System.out.println("\n");
            }
        });
    }
}

