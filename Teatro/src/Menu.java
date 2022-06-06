import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public final class Menu {
    private Scanner input;
    private String pathMenu;
    private String lastMenu;
    private String currentMenu;
    private String nextMenu;
    private boolean shouldStop;

    public Menu() {
        input = new Scanner(System.in);
        startOver();
    }

    // Getters
    public String getPathMenu() {
        return this.pathMenu;
    }

    public String getLastMenu() {
        return this.lastMenu;
    }

    public String getCurrentMenu() {
        return this.currentMenu;
    }

    public String getNextMenu() {
        return this.nextMenu;
    }

    public void menuInicial() {
        newMenu();
        System.out.println("Menu Inicial\n\n");

        System.out.println("Bem vindo ao sistema de Cadastro.\n");

        // System.out.println("        ___                           ___       _           _    _             \n|   \  ___  _ _   __ _        / __| ___ | | ___  ___| |_ (_) _ _   __ _ \n| |) |/ _ \| ' \ / _` |      | (__ / -_)| |/ -_)(_-/|  _|| || ' \ / _` |\n|___/ \___/|_||_|\__/_|       \___|\___||_|\___|/__/ \__||_||_||_|\__/_|        ");
        System.out.println(" ▀█▀ ██▀ ▄▀▄ ▀█▀ █▀▄ ▄▀▄\n  █  █▄▄ █▀█  █  █▀▄ ▀▄▀");

        System.out.print("\n\nDeseja iniciar o programa?\n\t[y/n]:  ");

        if (input.next().equals("y"))
            nextMenu("0");
        else
            shouldStop = true;
    }

    public void menuPrincipal() {
        newMenu();

        System.out.println("Menu Principal\n\n");
        System.out.println("Selecione uma opção:  \n");
        System.out.println("[1] Cadastro;");
        System.out.println("[2] Consulta;");
        System.out.println("[3] Remoção.");

        nextMenu(1, 3);
    }

    public void menuCadastro() {
        newMenu();

        System.out.println("Menu de Cadastro\n\n");
        System.out.println("[0] Voltar");
        System.out.println("[1] Cadastrar novo cliente;");
        System.out.println("[2] Cadastrar novo funcionário.");
        System.out.println("[3] Cadastrar nova peça de teatro.");

        nextMenu(3);
    }

    public void menuConsulta() {
        newMenu();

        System.out.println("Menu de Consulta\n\n");
        System.out.println("[0] Voltar");
        System.out.println("[1] Consultar um cliente;");
        System.out.println("[2] Consultar um funcionário;");
        System.out.println("[3] Consultar uma peça de teatro;");
        System.out.println("[4] Consultas especiais.");

        nextMenu(4);
    }

    public void menuConsultaCliente() {
        newMenu();
        System.out.println("Menu de consulta de clientes\n\n");

        System.out.println("Insira o nome do cliente que você deseja consultar.");

    }

    public void menuRemocao() {
        newMenu();

        System.out.println("Menu de Remoção\n\n");
        System.out.println("[0] Voltar;");
        System.out.println("[1] Remover um cliente;");
        System.out.println("[2] Remover um funcionário;");
        System.out.println("[3] Remover uma peça de teatro;");

        nextMenu(3);
    }

    // Para iniciar os menus
    public void startMenu() {
        setLastMenu();
        pathMenu = nextMenu;
        currentMenu = nextMenu;
    }

    public void newMenu() {
        // System.out.println("\n\nBefore start:");
        // System.out.println("Path:  " + pathMenu);
        // System.out.println("Last:  " + lastMenu);
        // System.out.println("Curr:  " + currentMenu);
        // System.out.println("Next:  " + nextMenu);
        startMenu();
        clrscr();
        for (int i = 0; i < 50; i++) {
            System.out.println("\n");
        }
        System.out.println(ConvertToVerbose(pathMenu));
        System.out.println("---------------------------------\n");
        // System.out.println("\n\nAfter start:");
        // System.out.println("Path:  " + pathMenu);
        // System.out.println("Last:  " + lastMenu);
        // System.out.println("Curr:  " + currentMenu);
        // System.out.println("Next:  " + nextMenu);
    }

    public void back() {
        String cleanPath = pathMenu.replaceAll("-", "");

        if (cleanPath.length() == 1) {
            pathMenu = "!";
        } else if (cleanPath.length() == 2) {
            pathMenu = "" + pathMenu.charAt(0);
        } else if (cleanPath.length() > 2) {
            pathMenu = pathMenu.substring(0, pathMenu.length() - 3);
        } else {
            startOver();
        }

        if (cleanPath.length() == 1 || cleanPath.length() == 2) {
            lastMenu = "!";
        } else if (cleanPath.length() == 3) {
            lastMenu = "" + pathMenu.charAt(0);
        } else if (cleanPath.length() > 3) {
            lastMenu = pathMenu.substring(0, pathMenu.length() - 5);
        } else {
            startOver();
        }

        currentMenu = lastMenu;
        nextMenu = pathMenu;
    }

    public int getOption(int firstOption, int lastOption) {
        int selectedOption = -1;
        boolean validOption;

        do {
            validOption = true;
            System.out.print("\t\tOpção:  ");

            try {
                selectedOption = input.nextInt();

                if (selectedOption == -1) {
                    shouldStop = true;
                }

                if (!(selectedOption >= firstOption && selectedOption <= lastOption) || selectedOption == -1) {
                    throw new IllegalArgumentException();
                } else {
                    validOption = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("O número inserido é inválido.\n");
                validOption = false;
            } catch (InputMismatchException e) {
                System.out.println("O valor inserido não é um número.\n");
                validOption = false;
                input.next();
            }
        } while (!validOption);

        return selectedOption;
    }

    public int getOption(int qntOptions) {
        return getOption(0, qntOptions);
    }

    public boolean shouldStop() {
        return shouldStop;
    }

    public void startOver() {
        pathMenu = "!";
        currentMenu = "!";
        lastMenu = "";
        nextMenu = "!";
        shouldStop = false;
    }

    public static void clrscr() {
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
        }
    }

    // Getters e setters
    private void setLastMenu() {
        this.lastMenu = currentMenu;
    }

    private void nextMenu(String nextMenu) {
        if (!nextMenu.equals("0") && !nextMenu.equals("!"))
            this.nextMenu = this.currentMenu.concat("-" + nextMenu);
        else
            this.nextMenu = nextMenu;
    }

    private void nextMenu(int firstOption, int lastOption) {
        Integer option = getOption(firstOption, lastOption);
        if (option == 0) {
            back();
        } else {
            nextMenu(option.toString());
        }
    }

    private void nextMenu(int qntOptions) {
        nextMenu(0, qntOptions);
    }

    public static String ConvertToVerbose(String spath) {
        String path[] = spath.split("-");
        int lenght = path.length;
        String verbosePath[] = path;
        String verboseSpath = "";

        switch (lenght) {
            case 3:
                switch (path[2]) {
                    case "1":
                        verbosePath[2] = "Cliente";
                        break;
                    case "2":
                        verbosePath[2] = "Funcionário";
                        break;
                    case "3":
                        verbosePath[2] = "Peça";
                        break;
                    case "4":
                        verbosePath[2] = "Especial";
                        break;
                    default:
                        verbosePath[2] = "ERROR";
                }

            case 2:
                switch (path[1]) {
                    case "1":
                        verbosePath[1] = "Cadastro";
                        break;
                    case "2":
                        verbosePath[1] = "Consulta";
                        break;
                    case "3":
                        verbosePath[1] = "Remoção";
                        break;
                    default:
                        verbosePath[1] = "ERROR";
                }

            case 1:
                switch (path[0]) {
                    case "!":
                        return "Início";
                    case "0":
                        verbosePath[0] = "Menu";
                        break;
                    default:
                        verbosePath[0] = "ERROR";
                }
        }

        for (String string : verbosePath) {
            if (string.equals("ERROR")) {
                throw new RuntimeException("De alguma forma, um token inválido foi parar no path.");
            }

            if (verboseSpath.isEmpty()) {
                verboseSpath = string;
            } else {
                verboseSpath = verboseSpath.concat("/" + string);
            }
        }

        return "Início/" + verboseSpath;
    }
}
