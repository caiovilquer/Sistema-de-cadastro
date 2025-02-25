package application;

import entities.People;
import exceptions.NameIsToShortException;
import services.Crud;
import services.QuestionManagement;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            File file = new File("/home/caiovilquer/Documents/Repositório/ws-java/sistema_de_cadastros/Data");
            List<People> registeredPeople = new ArrayList<People>();
            Crud.initialRegister(registeredPeople, file);

            while (true) {
                BufferedReader br = new BufferedReader(new FileReader(file + "/menu.txt"));
                while (br.ready()) {
                    String line = br.readLine();
                    System.out.println(line);
                }
                br.close();
                int choose = sc.nextInt();
                sc.nextLine();
                int numberOfQuestions = (int) Files.lines(Paths.get(file + "/formulario.txt")).count();
                switch (choose) {
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
