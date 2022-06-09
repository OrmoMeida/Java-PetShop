package Controllers;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.concurrent.CancellationException;

import Classes.CPF;
import Classes.Cliente;
import Main.Menu;

public class ListCliente {
    private ArrayList<Cliente> lstCliente;

    public ListCliente() {
        lstCliente = new ArrayList<Cliente>();
    }

    public boolean isNotEmpty() {
        return lstCliente.size() > 0;
    }

    public void checkEmpty() {
        if (!isNotEmpty())
            throw new ArrayIndexOutOfBoundsException("Não há clientes cadastrados no sistema.");
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

    public void lastExibir() {
        checkEmpty();

        System.out.println("Informações do cliente cadastrado:  \n");
        last().exibir();
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

    public Cliente buscaNomeObj(String nome) {
        checkEmpty();
        nome = nome.toLowerCase().trim();

        for (Cliente cliente : lstCliente) {
            if (cliente.getNome().toLowerCase().startsWith(nome))
                return cliente;
        }
        throw new IllegalArgumentException("Nome de cliente não encontrado.");
    }

    public ArrayList<Cliente> buscaNome() {
        checkEmpty();
        ArrayList<Cliente> list = new ArrayList<Cliente>();
        String nome;
        boolean validInput = true;

        Menu.voider();
        System.out.println("Menu de busca por clientes via nome.\n\n");

        do {
            validInput = true;
            System.out.print("\tNome:  ");

            try {
                nome = Main.Menu.input().next();
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
            } finally {
                Main.Menu.input().nextLine();
            }
        } while (!validInput);

        return list;
    }

    public Cliente buscaNomeObj() {
        String nome;
        boolean validInput;

        Menu.voider();
        System.out.println("Menu de busca por clientes via nome.\n\n");

        do {
            validInput = true;
            System.out.print("\tNome:  ");

            try {
                nome = Main.Menu.input().next().trim();
                return buscaNomeObj(nome);
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido.\n");
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                System.out.println("Deseja tentar novamente?");
                if (!Menu.getOptionBool())
                    throw new CancellationException("Busca de cliente por nome cancelada pelo usuário.");
                validInput = false;
            } finally {
                Main.Menu.input().nextLine();
            }
        } while (!validInput);

        throw new IllegalArgumentException("Nome de cliente não encontrado.");
    }

    public Cliente buscaCpf(String cpf) {
        cpf = CPF.trimCPF(cpf);

        for (Cliente cliente : lstCliente) {
            if (cliente.getCpf().replaceAll("//D", "").equals(cpf))
                return cliente;
        }

        throw new IllegalArgumentException("CPF de cliente não encontrado.");
    }

    public Cliente buscaCpf() {
        String cpf;
        boolean validInput;

        Menu.voider();
        System.out.println("Menu de busca de clientes via CPF\n\n");

        do {
            validInput = true;
            System.out.print("\tCPF:  ");

            try {
                cpf = Main.Menu.input().next().trim();
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
            } finally {
                Main.Menu.input().nextLine();
            }
        } while (!validInput);

        throw new IllegalArgumentException("Nome de cliente não encontrado.");
    }

    public void buscaMenu() {
        checkEmpty();
        ArrayList<Cliente> busca = new ArrayList<Cliente>();

        System.out.println("Menu de busca de cliente.\n\n");

        System.out.println("Deseja procurar o cliente por:  ");
        System.out.println("[0] Cancelar;");
        System.out.println("[1] Nome;");
        System.out.println("[2] CPF.");

        switch (Menu.getOption(2)) {
            case 0:
                return;
            case 1:
                try {
                    busca = buscaNome();
                } catch (CancellationException e) {
                    return;
                }
                break;
            case 2:
                try {
                    busca.add(buscaCpf());
                } catch (CancellationException e) {
                    return;
                }
                break;
        }

        Menu.voider();
        
        if (busca.size() > 1)
            System.out.println("Clientes encontrados:  \n");
        else
            System.out.println("Cliente encontrado:  \n");

        exibir(busca);

        if (busca.size() == 1) {
            System.out.println("\nDeseja ver as informações desse cliente?  ");
            if (Menu.getOptionBool()) {
                busca.get(0).exibir();
                Menu.waiter();
            }
            return;
        }

        System.out.println("\nDeseja consultar as informações de algum desses clientes?");
        if (!Menu.getOptionBool())
            return;
        
        System.out.println("\nDigite o número do cliente.");
        busca.get(Menu.getOption(1, busca.size() - 1)).exibir();
        Menu.waiter();
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
        ArrayList<Cliente> busca = new ArrayList<Cliente>();

        System.out.println("Menu de remoção de cliente.\n\n");

        System.out.println("Deseja procurar o cliente por:  ");
        System.out.println("[0] Cancelar");
        System.out.println("[1] Nome");
        System.out.println("[2] CPF");

        switch (Menu.getOption(2)) {
            case 0:
                return;
            case 1:
                try {
                    busca = buscaNome();
                } catch (Exception e) {
                    return;
                }
                break;
            case 2:
                try {
                    busca.add(buscaCpf());
                } catch (Exception e) {
                    return;
                }
                break;
        }

        Menu.voider();
        
        if (busca.size() > 1)
            System.out.println("Clientes encontrados:  \n");
        else
            System.out.println("Cliente encontrado:  \n");

        int i = 0;
        
        for (Cliente cliente : busca) {
            System.out.println(" | " + i + " | " + cliente.getNome() + " | " + cliente.getCpf());
            i++;
        }

        if (busca.size() == 1) {
            System.out.println("\nDeseja remover esse cliente?  ");
            if (Menu.getOptionBool()) {
                remover(busca.get(0));
                Menu.waiter();
            }
            return;
        }

        System.out.println("\nDeseja remover algum desses clientes?");
        if (!Menu.getOptionBool())
            return;
        
        remover(busca.get(Menu.getOption(1, busca.size() - 1)));
        Menu.waiter();
    }



    public void alterar() {
        checkEmpty();
        Cliente cliente = last();

        Menu.voider();
        System.out.println("Menu de alteração de dados do cliente.");
        System.out.println("Y para alterar e N para manter.\n");

        System.out.println("Nome:  " + cliente.getNome());
        if (Menu.getOptionBool())
            cliente.setNome();

        System.out.println("Idade:  " + cliente.getIdade());
        if (Menu.getOptionBool())
            cliente.setIdade();

        System.out.println("CPF:  " + cliente.getCpf());
        if (Menu.getOptionBool())
            cliente.setCpf();

        System.out.println("Email:  " + cliente.getEmail());
        if (Menu.getOptionBool())
            cliente.setEmail();

        System.out.println("Telefone:  " + cliente.getTelefone());
        if (Menu.getOptionBool())
            cliente.setTelefone();

        lstCliente.set(lastIndex(), cliente);

        System.out.println("\nCliente alterado com sucesso!");
        Menu.waiter();
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

        System.out.println("Média de idades do sistema:  " + mediaIdade());
        Menu.waiter();
    }
}
