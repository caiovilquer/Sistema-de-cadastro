import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File file = new File("/home/caiovilquer/Documents/Repositório/ws-java/sistema_de_cadastros/formulario.txt");
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            while (br.ready()) {
                String line = br.readLine();
                System.out.println(line);
            }
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){

        }


        }catch (Exception e){
            System.out.println(e);
        }


        sc.close();

    }

}