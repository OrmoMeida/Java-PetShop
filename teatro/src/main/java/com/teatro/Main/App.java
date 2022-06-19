package com.teatro.Main;

import java.util.concurrent.CancellationException;

import com.teatro.Controllers.ListApresentacao;
import com.teatro.Controllers.ListCliente;
import com.teatro.Controllers.ListFuncionario;

public class App {
    private static Menu menu = new Menu();
    private static ListCliente lstCliente = new ListCliente();
    private static ListFuncionario lstFuncionario = new ListFuncionario();
    private static ListApresentacao lstApresentacao = new ListApresentacao();

    public static void cadastros() {
        System.out.println("Quantidade de cadastros do sistema:\n\n");

        System.out.println("Clientes        :  " + lstCliente.size());
        System.out.println("Funcionários    :  " + lstFuncionario.size());
        System.out.println("Peças de teatro :  " + lstApresentacao.size());

        Menu.waiter();
    }
    
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
                    lstCliente.exibirLast();
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
                    if (lstCliente.removerLast()) {
                        menu.back();
                        menu.back();
                    }
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
                    lstFuncionario.exibirLast();
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
                    if (lstFuncionario.removerLast()) {
                        menu.back();
                        menu.back();
                    }
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
                    if (lstApresentacao.removerLast()) {
                        menu.back();
                        menu.back();       
                    }
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
                    lstCliente.qntClientesMaioresDe60Menu();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-2-4-1-4":
                menu.newMenu();
                try {
                    lstCliente.qntClientesMenoresDeIdadeMenu();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-2-4-1-5":
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

            case "0-2-5":
                menu.newMenu();
                cadastros();
                menu.back();
                break;

            case "0-3":
                menu.menuAlteracao();
                break;

            case "0-3-1":
                menu.newMenu();
                try {
                    lstCliente.alterarMenu();
                    menu.nextMenu("1");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                    menu.back();
                } catch (CancellationException e) {
                    menu.back();
                }
                break;

            case "0-3-1-1":
                menu.menuAlteracaoCliente();
                break;

            case "0-3-1-1-1":
                menu.newMenu();
                try {
                    lstCliente.exibirLastAlterado();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-3-1-1-2":
                menu.newMenu();
                try {
                    lstCliente.alterarNovamente();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-3-1-1-3":
                menu.newMenu();
                try {
                    if (lstCliente.removerLastAlterado()) {
                        menu.back();
                        menu.back();       
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-3-1-1-4":
                menu.back();
                break;
                
            

            case "0-3-2":
                menu.newMenu();
                try {
                    lstFuncionario.alterarMenu();
                    menu.nextMenu("1");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                    menu.back();
                } catch (CancellationException e) {
                    menu.back();
                }
                break;

            case "0-3-2-1":
                menu.menuAlteracaoFuncionario();
                break;

            case "0-3-2-1-1":
                menu.newMenu();
                try {
                    lstFuncionario.exibirLastAlterado();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-3-2-1-2":
                menu.newMenu();
                try {
                    lstFuncionario.alterarNovamente();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-3-2-1-3":
                menu.newMenu();
                try {
                    if (lstFuncionario.removerLastAlterado()) {
                        menu.back();
                        menu.back();       
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-3-2-1-4":
                menu.back();
                break;
                

            case "0-3-3":
                menu.newMenu();
                try {
                    lstApresentacao.alterarMenu();
                    menu.nextMenu("1");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                    menu.back();
                } catch (CancellationException e) {
                    menu.back();
                }
                break;

            case "0-3-3-1":
                menu.menuAlteracaoApresentacao();
                break;

            case "0-3-3-1-1:":
                menu.newMenu();
                try {
                    lstApresentacao.exibirLastAlterado();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;
            
            case "0-3-3-1-2":
                menu.newMenu();
                try {
                    lstApresentacao.alterarNovamente();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-3-3-1-3":
                menu.newMenu();
                try {
                    if (lstApresentacao.removerLastAlterado()) {
                        menu.back();
                        menu.back();
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Impossível realizar ação.\n\n");
                    System.out.println("\t" + e.getMessage());
                    Menu.waiter();
                }
                menu.back();
                break;

            case "0-3-3-1-4":
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
        lstCliente.geraCliente();
        lstFuncionario.geraFuncionarios();
        lstApresentacao.geraApresentacoes();

        while (!menu.shouldStop())
            next();
    }
}
