package Main;

import Controllers.ListApresentacao;
import Controllers.ListCliente;
import Controllers.ListFuncionario;

public class App {
    private static Menu menu = new Menu();
    private static ListCliente lstCliente = new ListCliente();
    private static ListFuncionario lstFuncionario = new ListFuncionario();
    private static ListApresentacao lstApresentacao = new ListApresentacao();

    public static void next() {
        switch (menu.getNextMenu()) {
            case "!":
                menu.menuInicial();
                break;

            case "1":
                menu.menuErro();
                break;

            case "0":
                menu.menuPrincipal();
                break;

            case "0-1":
                menu.menuCadastro();
                break;

            case "0-1-1":
                menu.newMenu();
                lstCliente.add();
                menu.nextMenu("1");
                break;

            case "0-1-1-1":
                menu.menuCadastroCliente();
                break;

            case "0-1-1-1-1":
                menu.newMenu();
                try {
                    lstCliente.lastExibir();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-1-1-1-2":
                menu.newMenu();
                try {
                    lstCliente.alterar();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-1-1-1-3":
                menu.back();
                break;

            case "0-1-1-1-4":
                menu.newMenu();
                try {
                    lstCliente.removerLast();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-1-2":
                menu.newMenu();
                lstFuncionario.add();
                menu.nextMenu("1");
                break;

            case "0-1-2-1":
                menu.menuCadastroFuncionario();
                break;

            case "0-1-2-1-1":
                menu.newMenu();
                try {
                    lstFuncionario.lastExibir();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-1-2-1-2":
                menu.newMenu();
                try {
                    lstFuncionario.alterar();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-1-2-1-3":
                menu.back();
                break;
            
            case "0-1-2-1-4":
                menu.newMenu();
                try {
                    lstFuncionario.removerLast();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-1-3":
                menu.newMenu();
                lstApresentacao.add();
                menu.nextMenu("1");
                break;

            case "0-1-3-1":
                menu.menuCadastroApresentacao();
                break;

            case "0-1-3-1-1":
                menu.newMenu();
                try {
                    lstApresentacao.lastExibir();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-1-3-1-2":
                menu.newMenu();
                try {
                    lstApresentacao.alterar();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-1-3-1-3":
                menu.back();
                break;

            case "0-1-3-1-4":
                menu.newMenu();
                try {
                    lstApresentacao.removerLast();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-2":
                menu.menuConsulta();
                break;

            case "0-2-1":
                menu.newMenu();
                try {
                    lstCliente.buscaMenu();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-2-2":
                menu.newMenu();
                try {
                    lstFuncionario.buscaMenu();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-2-3":
                menu.newMenu();
                try {
                    lstApresentacao.buscaMenu();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-2-4":
                menu.menuConsultaEspecial();
                break;

            case "0-2-4-1":
                menu.menuConsultaEspecialCliente();
                break;

            case "0-2-4-1-1":
                menu.newMenu();
                try {
                    lstCliente.clienteMaisVelhoMenu();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-2-4-1-2":
                menu.newMenu();
                try {
                    lstCliente.clienteMaisNovoMenu();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-2-4-1-3":
                menu.newMenu();
                try {
                    lstCliente.mediaIdadeMenu();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-2-4-2":
                menu.menuConsultaEspecialProduto();
                break;

            case "0-2-4-2-1":
                menu.newMenu();
                try {
                    lstApresentacao.maisCaraMenu();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-2-4-2-2":
                menu.newMenu();
                try {
                    lstApresentacao.mediaPrecosMenu();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-2-4-2-3":
                menu.newMenu();
                try {
                    lstApresentacao.qntAcimaMenu();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-3":
                menu.menuAlteracao();
                break;

            case "0-3-1":
                menu.newMenu();
                try {
                    lstCliente.alterarMenu();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-3-2":
                menu.newMenu();
                try {
                    lstFuncionario.alterarMenu();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-3-3":
                menu.newMenu();
                try {
                    lstApresentacao.alterarMenu();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-4":
                menu.menuRemocao();
                break;

            case "0-4-1":
                menu.newMenu();
                try {
                    lstCliente.remover();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-4-2":
                menu.newMenu();
                try {
                    lstFuncionario.remover();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-4-3":
                menu.newMenu();
                try {
                    lstApresentacao.remover();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            default:
                menu.menuErro();
                break;
        }
    }

    public static void main(String[] args) throws Exception {
        // TODO integrar o controller e o menu pela main e fazer tudo rodar
        // TODO testar tuuuudo

        while (!menu.shouldStop()) {
            next();
        }
    }
}
