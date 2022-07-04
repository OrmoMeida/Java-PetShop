package Controllers;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.concurrent.CancellationException;

import Classes.CPF;
import Classes.Cliente;
import Main.Menu;

public class ListCliente {
    private static final Controllers.Scanner2 input = Controllers.Scanner2.getInstanceOf();

    private ArrayList<Cliente> lstCliente;
    private int iLastAlterado;

    public ListCliente() {
        lstCliente = new ArrayList<Cliente>();
    }

    public void geraCliente() {
        add(20, "Jeremy", "66821645051", "jeremy@gmail.com", "(12)345678901");
        add(22, "Michael Afton", "49422281075", "michael@gmail.com", "(09)876543211");
        add(56, "William Afton","38754193087", "purpleguy@gmail.com", "(12)345678999");
    }

    public static boolean isNotEmpty(ArrayList<Cliente> list) {
        return list.size() > 0;
    }

    public boolean isNotEmpty() {
        return isNotEmpty(lstCliente);
    }

    public static void checkEmpty(ArrayList<Cliente> list) {
        if (!isNotEmpty(list))
        throw new ArrayIndexOutOfBoundsException("Não há clientes cadastrados no sistema.");
    }
    
    public void checkEmpty() {
        checkEmpty(lstCliente);
    }

    public static void exibir(ArrayList<Cliente> lstCliente) {
        int i = 1;
        for (Cliente cliente : lstCliente) {
            System.out.println(" | " + Integer.toString(i) + " | " + cliente.getNome() + " | " + cliente.getCpf());
            i++;
        }
    }

    public void exibir() {
        exibir(this.lstCliente);
    }


    public static void exibir(Cliente cliente) {
        System.out.println(" | 1 | " + cliente.getNome() + " | " + cliente.getCpf());
    }

    public void add(Cliente cliente) {
        lstCliente.add(cliente);
    }

    public void add(int idade, String nome, String cpf, String email, String telefone) {
        lstCliente.add(new Cliente(idade, nome, cpf, email, telefone));
    }

    public void add() {
        lstCliente.add(new Cliente());
    }

    public int size() {
        return lstCliente.size();
    }

    public int lastIndex() {
        checkEmpty();
        return lstCliente.size() - 1;
    }

    public Cliente last() {
        checkEmpty();
        return lstCliente.get(lastIndex());
    }

    public Cliente lastAlterado() {
        checkEmpty();
        return lstCliente.get(iLastAlterado);
    }

    public void exibirLast() {
        checkEmpty();

        System.out.println("Informações do cliente cadastrado:  \n");
        last().exibir();
        Menu.waiter();
    }

    public void exibirLastAlterado() {
        checkEmpty();

        System.out.println("Informações do cliente alterado:  \n");
        lastAlterado().exibir();
        Menu.waiter();
    }




    public ArrayList<Cliente> buscaNome(String nome) {
        checkEmpty();
        nome = nome.toLowerCase().trim();
        ArrayList<Cliente> list = new ArrayList<Cliente>();

        for (Cliente cliente : lstCliente) {
            if (cliente.getNome().toLowerCase().startsWith(nome))
                list.add(cliente);
        }

        if (list.size() <= 0)
            throw new IllegalArgumentException("Nome de cliente não encontrado.");
        return list;
    }

    public ArrayList<Cliente> buscaNome() {
        checkEmpty();
        ArrayList<Cliente> list = new ArrayList<Cliente>();
        String nome;
        boolean validInput = true;

        System.out.println("Menu de busca por clientes via nome.\n\n");

        do {
            validInput = true;
            System.out.print("\tNome:  ");

            try {
                nome = input.nextLine();
                list = buscaNome(nome);
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido.\n");
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                System.out.println("Deseja tentar novamente?");
                if (!Menu.getOptionBool())
                    throw new CancellationException("Busca de cliente por nome cancelada pelo usuário.");
                validInput = false;
            }
        } while (!validInput);

        return list;
    }

    public Cliente buscaCpf(String cpf) {
        cpf = CPF.trimCPF(cpf);

        for (Cliente cliente : lstCliente) {
            if (cliente.getCpf().replaceAll("\\D", "").equals(cpf))
                return cliente;
        }

        throw new IllegalArgumentException("CPF de cliente não encontrado.");
    }

    public Cliente buscaCpf() {
        String cpf;
        boolean validInput;

        System.out.println("Menu de busca de clientes via CPF\n\n");

        do {
            validInput = true;
            System.out.print("\tCPF:  ");

            try {
                cpf = input.next();
                return buscaCpf(cpf);
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido.\n");
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                System.out.println("Deseja tentar novamente?");
                if (!Menu.getOptionBool())
                    throw new CancellationException("Busca de cliente por nome cancelada pelo usuário.");
                validInput = false;
            }
        } while (!validInput);

        throw new IllegalArgumentException("Nome de cliente não encontrado.");
    }

    public ArrayList<Cliente> busca() {
        checkEmpty();
        ArrayList<Cliente> busca = new ArrayList<Cliente>();

        System.out.println("Deseja procurar o cliente por:  ");
        System.out.println("[0] Cancelar;");
        System.out.println("[1] Ver todos os clientes;");
        System.out.println("[2] Consultar por nome;");
        System.out.println("[3] Consultar por CPF.");

        switch (Menu.getOption(3)) {
            case 0:
                throw new CancellationException("Operação de busca cancelada pelo usuário.");
            case 1:
                busca = lstCliente;
                break;

            case 2:
                busca = buscaNome();
                break;
                
            case 3:
                busca.add(buscaCpf());
                break;
        
        }

        if (busca.size() <= 0)
            throw new IllegalArgumentException("Nenhum cliente foi encontrado na busca.");
        return busca;
    }

    public Cliente buscaArray(ArrayList<Cliente> busca) {
        if (busca.size() <= 0)
            throw new IllegalArgumentException("Não há clientes para pesquisar.");

        if (busca.size() > 1)
            System.out.println("Clientes encontrados:  \n");
        else
            System.out.println("Cliente encontrado:  \n");

        exibir(busca);

        if (busca.size() == 1) {
            System.out.println("\nDeseja selecionar esse cliente?  ");
            if (Menu.getOptionBool())
                return busca.get(0);
            else
                throw new CancellationException("Operação de seleção de um único cliente cancelada pelo usuário.");
        }

        System.out.println("\nDeseja selecionar algum desses clientes?");
        if (!Menu.getOptionBool())
            throw new CancellationException("Operação de seleção dente múltiplos clientes por nome cancelada pelo usuário.");
        
        System.out.println("\nDigite o número do cliente.");
        return busca.get(Menu.getOption(1, busca.size()) - 1);
    }

    public void buscaMenu() {
        checkEmpty();
        System.out.println("Menu de busca de cliente\n\n");

        try {
            buscaArray(busca()).exibir();
            Menu.waiter();
        } catch (CancellationException e) {
            return;
        }
    }




    public void remover(Cliente cliente) {
        checkEmpty();
        lstCliente.remove(cliente);
    }

    public void remover(int index) {
        checkEmpty();
        lstCliente.remove(index);
    }

    public void remover() {
        checkEmpty();
        Cliente cliente;

        try {
            cliente = buscaArray(busca());
            System.out.println("Tem certeza que deseja remover o cliente selecionado?");
            if (Menu.getOptionBool()) {
                remover(cliente);
                System.out.println("\nCliente removido com sucesso.\n");
                Menu.waiter();
            } else {
                throw new CancellationException("Operação de remoção de cliente via busca cancelada pelo usuário.");
            }
        } catch (CancellationException e) {
            return;
        }
    }

    public boolean removerLast() {
        checkEmpty();

        System.out.println("Tem certeza que deseja remover o último cliente adicionado?");
        boolean option = Menu.getOptionBool();
        if (option) {
            remover(last());
            System.out.println("\nCliente removido com sucesso.\n");
            Menu.waiter();
        }

        return option;
    }
    
    public boolean removerLastAlterado() {
        checkEmpty();

        System.out.println("Tem certeza que deseja remover o último cliente alterado?");
        boolean option = Menu.getOptionBool();
        if (option) {
            remover(lastAlterado());
            System.out.println("\nCliente removido com sucesso.\n");
            Menu.waiter();
        }

        return option;
    }



    public void alterar(Cliente cliente) {
        checkEmpty();
        iLastAlterado = lstCliente.indexOf(cliente);

        if (iLastAlterado == -1)
            throw new IllegalArgumentException("Cliente não encontrado: Impossível alterar.");

        System.out.println("\n\nMenu de alteração de dados do cliente.");
        System.out.println("Y para alterar e N para manter.\n");

        System.out.println("\nNome:  " + cliente.getNome());
        if (Menu.getOptionBool())
            cliente.setNome();

        System.out.println("\nIdade:  " + cliente.getIdade());
        if (Menu.getOptionBool())
            cliente.setIdade();

        System.out.println("\nCPF:  " + cliente.getCpf());
        if (Menu.getOptionBool())
            cliente.setCpf();

        System.out.println("\nEmail:  " + cliente.getEmail());
        if (Menu.getOptionBool())
            cliente.setEmail();

        System.out.println("\nTelefone:  " + cliente.getTelefone());
        if (Menu.getOptionBool())
            cliente.setTelefone();

        lstCliente.set(iLastAlterado, cliente);

        System.out.println("\n\nCliente alterado com sucesso!");
        Menu.waiter();
    }

    public void alterar() {
        alterar(last());
    }

    public void alterarMenu() {
        checkEmpty();

        System.out.println("\n\nMenu de alteração\n\n");
        alterar(buscaArray(busca()));
    }

    public void alterarNovamente() {
        checkEmpty();

        System.out.println("\nMenu de nova alteração\n\n");
        
        try {
            alterar(lstCliente.get(iLastAlterado));
        } catch (CancellationException e) {
            return;
        }
    }

    public Cliente clienteMaisVelho() {
        checkEmpty();
        Cliente cli;

        cli = lstCliente.get(0);

        for (Cliente cliente : lstCliente) {
            if (cliente.getIdade() > cli.getIdade())
                cli = cliente;
        }

        return cli;
    }

    public void clienteMaisVelhoMenu() {
        checkEmpty();

        System.out.println("Cliente mais velho do sistema:");
        exibir(clienteMaisVelho());

        System.out.println("\nDeseja exibir o cliente?");
        if (Menu.getOptionBool()) {
            clienteMaisVelho().exibir();
            Menu.waiter();
        }
    }
    
    public Cliente clienteMaisNovo() {
        checkEmpty();
        Cliente cli;

        cli = lstCliente.get(0);

        for (Cliente cliente : lstCliente) {
            if (cliente.getIdade() < cli.getIdade())
                cli = cliente;
        }

        return cli;
    }

    public void clienteMaisNovoMenu() {
        checkEmpty();

        System.out.println("Cliente mais novo do sistema:  ");
        exibir(clienteMaisNovo());

        System.out.println("\nDeseja exibir o cliente?");
        if (Menu.getOptionBool()) {
            clienteMaisNovo().exibir();
            Menu.waiter();
        }
    }

    public int qntClientesMaioresDe60() {
        checkEmpty();
        int qntIdosos = 0;

        for (Cliente cliente : lstCliente) {
            if (cliente.getIdade() > 60)
                qntIdosos++;
        }

        return qntIdosos;
    }

    public void qntClientesMaioresDe60Menu() {
        checkEmpty();

        System.out.println("Quantidade de clientes cadastrados maiores de 60:  " + qntClientesMaioresDe60());
        Menu.waiter();
    }
    
    public int qntClientesMenoresDeIdade() {
        checkEmpty();
        int qntCriancas = 0;

        for (Cliente cliente : lstCliente) {
            if (cliente.getIdade() < 18)
                qntCriancas++;
        }

        return qntCriancas;
    }

    public void qntClientesMenoresDeIdadeMenu() {
        checkEmpty();

        System.out.println("Quantidade de clientes cadastrados menores de idade:  " + qntClientesMenoresDeIdade());
        Menu.waiter();
    }

    public float mediaIdade() {
        checkEmpty();
        float media = 0;

        for (Cliente cliente : lstCliente) {
            media += cliente.getIdade();
        }

        return media / size();
    }

    public void mediaIdadeMenu() {
        checkEmpty();

        System.out.printf("Média de idades do sistema:  %.2f", mediaIdade());
        Menu.waiter();
    }
}
