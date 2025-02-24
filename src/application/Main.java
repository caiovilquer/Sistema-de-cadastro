package application;

import entities.People;
import services.Crud;
import services.services.QuestionManagement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

            File file = new File("/home/caiovilquer/Documents/Repositório/ws-java/sistema_de_cadastros/Data");
            List<People> registeredPeople = new ArrayList<People>();
            Crud.initialRegister(registeredPeople, file);
            Crud.register(registeredPeople, file, sc);
            while (true) {
                BufferedReader br = new BufferedReader(new FileReader(file + "/menu.txt"));
                while (br.ready()) {
                    String line = br.readLine();
                    System.out.println(line);
                }
                br.close();
                int choose = sc.nextInt();
                sc.nextLine();
                switch (choose) {
                    case 1:
                        Crud.register(registeredPeople, file, sc);
                        break;
                    case 2:
                        Crud.selectAllUsers(registeredPeople);
                        break;
                    case 3:
                        QuestionManagement.addQuestion(file, sc);
                        break;
                    case 4:
//                        deleteQuestion();
                        break;
                    case 5:
//                        selectByAttribute();
                        break;
                    default:
                        System.out.println("Error, invalid option");

                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }


}
