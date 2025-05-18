package application;

import entities.People;
import services.Crud;
import services.QuestionManagement;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            File currentDirectory = new File(".");
            File file = new File(currentDirectory + "/Data");
            Path formPath = Paths.get(file.getAbsolutePath(), "formulario.txt");
            List<People> registeredPeople = new ArrayList<>();
            Crud.initialRegister(registeredPeople, file);
            while (true) {
                Path menuPath = Paths.get(file.getAbsolutePath(), "menu.txt");
                try (BufferedReader br = Files.newBufferedReader(menuPath)) {
                    br.lines().forEach(System.out::println);
                }
                System.out.println("0 - Para sair do sistema");
                int choose = sc.nextInt();
                sc.nextLine();
                int numberOfQuestions;
                try (Stream<String> lines = Files.lines(formPath)) {
                    numberOfQuestions = (int) lines.count();
                }
                switch (choose) {
                    case 0:
                        System.out.println("Saindo...");
                        return;

                    case 1:
                        Crud.viewQuestions(file);
                        String[] data = new String[numberOfQuestions];
                        for (int i = 0; i < data.length; i++) {
                            System.out.print(i + 1 + ": ");
                            data[i] = sc.nextLine();
                        }
                        Crud.register(registeredPeople, file, data, numberOfQuestions);
                        break;
                    case 2:
                        System.out.println("Todos os usuários cadastrados no formulário:");
                        Crud.selectAllUsers(registeredPeople);
                        break;
                    case 3:
                        QuestionManagement.addQuestion(file, sc);
                        break;
                    case 4:
                        QuestionManagement.deleteQuestion(file, sc);
                        break;
                    case 5:
                        System.out.print("Digite o termo a ser buscado: ");
                        Crud.selectByAttribute(registeredPeople, sc.nextLine(), numberOfQuestions);
                        break;
                    default:
                        System.out.println("Error, invalid option");

                }
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }

    }
}
