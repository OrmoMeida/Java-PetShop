public class App {
    private static Menu menu = new Menu();
    private static Controller control = new Controller();

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
                control.addCliente();
                menu.menuCadastroCliente();
                break;
            case "0-1-1-1":
                Menu.voider();
                System.out.println("Informações do cliente cadastrado:  \n");
                control.lastAddCliente().exibir();
                Menu.waiter();
                break;
            case "0-1-1-2":
                Menu.voider();
                control.alterarCliente();
                Menu.waiter();
                break;
            case "0-1-1-3":
                menu.back();
                break;
        
            case "0-1-2":
                control.addFuncionario();
                menu.menuCadastroFuncionario();
                break;
            case "0-1-2-1":
                Menu.voider();
                System.out.println("Informações do funcionário cadastrado:  \n");
                control.lastAddFuncionario().exibir();
                Menu.waiter();
                break;
            case "0-1-2-2":
                Menu.voider();
                control.alterarFuncionario();
                Menu.waiter();
            case "0-1-2-3":
                menu.back();
                break;

            case "0-1-3":
                control.addPeca();
                // TODO menu.menuCadastroPeca();
                break;
            case "0-1-3-1":
                Menu.voider();
                System.out.println("Informações da peça de teatro cadastrada:  ");
                control.lastAddPeca().exibir();
                Menu.waiter();
                break;                

            case "0-2":
                menu.menuConsulta();
                break;
            case "0-2-1":
                if (control.qntClientes() <= 0) {
                    System.out.println("Não há clientes cadastrados no sistema.");
                    Menu.waiter();
                } else {control.buscaCliente();}
                menu.back();
                break;
            case "0-2-2":
                if (control.qntFuncionarios() <= 0) {
                    System.out.println("Não há funcionários cadastrados no sistema.");
                    Menu.waiter();
                } else {control.buscaFuncionarioNome();}
                menu.back();
                break;
            case "0-2-3":
                control.buscaPeca();
                menu.back();
                break;

            case "0-3":
                menu.menuRemocao();
                break;
            case "0-3-1":
                


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
