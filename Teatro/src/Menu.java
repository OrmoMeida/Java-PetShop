import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


public final class Menu {
    public void newMenu() {
        clrscr();
        System.out.println("----------------------------");
    }

    public int getOption(int firstOption, int lastOption) {
        int selectedOption = -1;
        boolean validOption;
        Scanner input = new Scanner(System.in);

        do {
            validOption = true;
            System.out.print("\t\tOpção:  ");
            
            try {
                selectedOption = input.nextInt();
                System.out.println(selectedOption);

                if (!(selectedOption >= firstOption && selectedOption <= lastOption) || selectedOption == -1) {
                    throw new IllegalArgumentException();
                } else {
                    validOption = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("\n O número inserido é inválido.");
                validOption = false;
            } catch (InputMismatchException e) {
                System.out.println("\nO valor inserido não é um número.");
                validOption = false;
                input.next();
            }

        } while (!validOption);      
        
        input.close();
        return selectedOption;
    }

    public int getOption(int qntOptions) {
        return getOption(0, qntOptions);
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
        System.out.println("[1] Cadastro;");
        System.out.println("[2] Consulta;");
        System.out.println("[3] Remoção.");

        return getOption(1, 3);
    }

    public int menuCadastro() {
        newMenu();

        System.out.println("Menu de Cadastro:\n\n");
        System.out.println("[0] Voltar");
        System.out.println("[1] Cadastrar novo cliente;");
        System.out.println("[2] Cadastrar novo funcionário.");
        System.out.println("[3] Cadastrar nova peça de teatro.");

        return getOption(3);
    }

    public int menuConsulta() {
        newMenu();

        System.out.println("Menu de Consulta:\n\n");
        System.out.println("[0] Voltar");
        System.out.println("[1] Consultar um cliente;");
        System.out.println("[2] Consultar um funcionário;");
        System.out.println("[3] Consultar uma peça de teatro;");
        System.out.println("[4] Consultas especiais.");

        return getOption(4);
    }

    public int menuRemocao() {
        newMenu();

        System.out.println("Menu de Remoção:\n\n");
        System.out.println("[0] Voltar;");
        System.out.println("[1] Remover um cliente;");
        System.out.println("[2] Remover um funcionário;");
        System.out.println("[3] Remover uma peça de teatro;");
        System.out.println("[4] Remoções especiais.");

        return getOption(4);
    }

    

    // public int 
}
