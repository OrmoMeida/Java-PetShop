import java.io.IOException;
import java.util.Scanner;


public final class Menu {
    public void newMenu() {
        clrscr();
        System.out.println("----------------------------");
    }

    public int getOption(int qntOptions) {
        int selectedOption;
        boolean validOption = true;

        do {
            Scanner input = new Scanner(System.in);
            System.out.print("\t\tOpção:  ");
            selectedOption = input.nextInt();
            input.close();

            try {
                if (!(selectedOption > 0 && selectedOption <= qntOptions)) {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                validOption = false;
            }

        } while (!validOption);      

        return selectedOption;
    }

    public static void clrscr(){
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }

    public int menuPrincipal() {
        newMenu();
        System.out.println("Menu Principal\n\n");
        System.out.println("Selecione uma opção:  \n");
        System.out.println("[1] Cadastrar um novo cliente;");
        System.out.println("[2] Cadastrar um novo funcionário;");
        System.out.println("[3] Cadastrar uma nova peça de teatro.");

        return getOption(3);
    }

    // public int 
}
