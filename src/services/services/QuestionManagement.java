package services.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
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
}
