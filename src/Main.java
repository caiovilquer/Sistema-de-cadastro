import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

            File file = new File("/home/caiovilquer/Documents/Repositório/ws-java/sistema_de_cadastros/Data/formulario.txt");
            List<People> registeredPeople = new ArrayList<People>();
            register(registeredPeople, file, sc);
            while (true) {
                BufferedReader br = new BufferedReader(new FileReader(file.getParent() + "/menu.txt"));
                while (br.ready()) {
                    String line = br.readLine();
                    System.out.println(line);
                }
                br.close();
                int choose = sc.nextInt();
                sc.nextLine();
                switch (choose) {
                    case 1:
                        register(registeredPeople, file, sc);
                        break;
                    default:
                        System.out.println("Error, invalid option");

                }
            }
        } catch (Exception e) {
            System.out.println(e.getCause() + " " + e.getMessage() + " " + e.getStackTrace());
        }


    }

    public static void register(List<People> registeredPeople, File file, Scanner sc) throws FileNotFoundException, IOException {
        BufferedReader br2 = new BufferedReader(new FileReader(file));
        while (br2.ready()) {
            String line = br2.readLine();
            System.out.println(line);
        }
        br2.close();
        String[] data = new String[4];
        for (int i = 0; i < data.length; i++) data[i] = sc.nextLine();
        registeredPeople.add(new People(data[0], data[1], Integer.parseInt(data[2]), Double.parseDouble(data[3])));
        BufferedWriter bw2 = new BufferedWriter(new FileWriter(file.getParent() + "/" + registeredPeople.size() + "." + data[0].toUpperCase() + ".txt"));
        bw2.write(registeredPeople.getLast().toString());
        bw2.close();
    }
}
