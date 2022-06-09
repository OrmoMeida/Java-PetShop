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
                menu.menuCadastroCliente();
                break;
            case "0-1-1-1":
                menu.newMenu();
                lstCliente.lastExibir();
                menu.back();
                break;
            case "0-1-1-2":
                menu.newMenu();
                lstCliente.alterar();
                menu.back();
                break;
            case "0-1-1-3":
                menu.back();
                break;
        
            case "0-1-2":
                menu.newMenu();
                lstFuncionario.add();
                menu.menuCadastroFuncionario();
                break;
            case "0-1-2-1":
                menu.newMenu();
                lstFuncionario.lastExibir();
                menu.back();
                break;
            case "0-1-2-2":
                menu.newMenu();
                lstFuncionario.alterar();
                menu.back();
                break;
            case "0-1-2-3":
                menu.back();
                break;

            case "0-1-3":
                menu.newMenu();
                lstApresentacao.add();
                menu.menuCadastroApresentacao();
                break;
            case "0-1-3-1":
                menu.newMenu();
                lstApresentacao.lastExibir();
                menu.back();
                break;
            case "0-1-3-2":
                menu.newMenu();
                lstApresentacao.alterar();
                menu.back();
                break;
            case "0-1-3-3":
                menu.back();
                break;

            case "0-2":
                menu.menuConsulta();
                break;
            case "0-2-1":
                menu.newMenu();
                lstCliente.buscaMenu();
                menu.back();
                break;
            case "0-2-2":
                menu.newMenu();
                lstFuncionario.buscaMenu();
                menu.back();
                break;
            case "0-2-3":
                menu.newMenu();
                // TODO lstApresentacao.buscaMenu();
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
                lstCliente.clienteMaisVelhoMenu();
                menu.back();
                break;
            case "0-2-4-1-2":
                menu.newMenu();
                lstCliente.clienteMaisNovoMenu();
                menu.back();
                break;
            case "0-2-4-1-3":
                menu.newMenu();
                lstCliente.mediaIdadeMenu();
                menu.back();
                break;

            case "0-2-4-2":
                menu.menuConsultaEspecialProduto();
                break;
            case "0-2-4-2-1":
                menu.newMenu();
                // TODO lstApresentacao.maisCaraMenu();
                menu.back();
                break;
            case "0-2-4-2-2":
                menu.newMenu();
                // TODO lstApresentacao.mediaPrecos();
                menu.back();
                break;
            case "0-2-4-2-3":
                menu.newMenu();
                // TODO lstApresentacao.qntMaisCaras();
                menu.back();
                break;

            case "0-3":
                menu.menuRemocao();
                break;
            case "0-3-1":
                menu.newMenu();
                lstCliente.remover();
                menu.back();
                break;
            case "0-3-2":
                menu.newMenu();
                lstFuncionario.remover();
                menu.back();
                break;
            case "0-3-3":
                menu.newMenu();
                // TODO lstApresentacao.remover();
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
