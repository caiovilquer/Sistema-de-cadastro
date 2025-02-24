package services;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class QuestionManagement {
    public static void addQuestion(File file, Scanner sc){
        try(BufferedWriter bw4 = new BufferedWriter(new FileWriter(file + "/formulario.txt", true))) {
            System.out.println("Escreva a nova pergunta: ");
            bw4.append(((int) Files.lines(Paths.get(file + "/formulario.txt")).count()+1)+ " - " +sc.nextLine());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void deleteQuestion(File file, Scanner sc) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file + "/formulario.txt"));
        List<String> questions = new ArrayList<String>();
        while (br.ready()) {
            br.lines().skip(4).forEach(System.out::println);
        }
        br.close();

        BufferedReader br2 = new BufferedReader(new FileReader(file + "/formulario.txt"));
        while (br2.ready()) {
            String line = br2.readLine();
            line = line.substring(4);
            questions.add(line);
        }
        br2.close();

        System.out.println(questions);
        System.out.print("Digite o numero da pergunta que deseja deletar: ");
        int choose = sc.nextInt();
        choose--;
        questions.remove(choose);
        System.out.println(questions);

        BufferedWriter bw = new BufferedWriter(new FileWriter(file + "/formulario.txt"));
        for (int i = 0; i < questions.size(); i++) {
            bw.write(i+1 + " - " +questions.get(i) + "\n");
        }
        bw.close();
    }
}
