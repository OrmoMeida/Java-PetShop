import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Classes.Cliente;
import Classes.Funcionario;
import Classes.PecaDeTeatro;

public class Controller {
    private static Scanner input = new Scanner(System.in);
    private ArrayList<Cliente> lstCliente;
    private ArrayList<Funcionario> lstFuncionario;
    private ArrayList<PecaDeTeatro> lstPeca;

    public Controller() {
        lstCliente = new ArrayList<Cliente>();
        lstFuncionario = new ArrayList<Funcionario>();
        lstPeca = new ArrayList<PecaDeTeatro>();
    }

    public Cliente buscaCliente(String nome) {
        for (Cliente cliente : lstCliente) {
            if (cliente.getNome().equals(nome))
                return cliente;
        }
        throw new IllegalArgumentException("Nome de cliente não encontrado.");
    }

    public Cliente buscaCliente() {
        Cliente cliente = null;
        String nome;
        boolean validInput = true;

        do {
            validInput = true;
            System.out.print("\tNome do cliente:  ");

            try {
                nome = input.nextLine();
                cliente = buscaCliente(nome);
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido.\n");
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                validInput = false;
            }
        } while (!validInput);

        if (cliente.equals(null)) {
            throw new RuntimeException("Cliente não encontrado. (?)");}
        return cliente;
    }

    /*
     * Cada uma das classes de controle deve possuir três metodos para adicionar. 
     * Um recebendo os mesmo parâmetros;
     * Um recebendo um objeto;
     * E um sem parâmetros.
     * 
     * Os dois anteriores devem simplesmente adicionar as informações recebidas no ArrayList
     * O primeiro, no caso, chamando o construtor de seu método.
     * O sem parâmetros deve chamar o construtor também sem parâmetros, assim invocando 
     * a chamada de  métodos que esperam pelo input do usuário, já filtrados via setters.
     */

    // ADDs para cliente
    public void addCliente(int idade, String nome, String cpf, String email, String telefone) {
        lstCliente.add(new Cliente(idade, nome, cpf, email, telefone));
    }

    public void addCliente(Cliente cliente) {
        lstCliente.add(cliente);
    }

    public void addCliente() {
        System.out.println("Insira as informações do novo cliente:  \n");
        lstCliente.add(new Cliente());
        System.out.println("\nCliente adicionado com sucesso.");
    }


    // ADDs para Funcionário
    public void addFuncionario(int idade, String nome, String cpf, String email, String cargo) {
        lstFuncionario.add(new Funcionario(idade, nome, cpf, email, cargo));
    }
    
    public void addFuncionario(Funcionario funcionario) {
        lstFuncionario.add(funcionario);
    }
    
    public void addFuncionario() {
        System.out.println("Insira as informações do novo funcionário:  \n");
        lstFuncionario.add(new Funcionario());
        System.out.println("\nFuncionário adicionado com sucesso.");
    }


    // Adds para peças de teatro
    public void addPeca(String nome, String desc, float preco, float duracao, String data, String genero,
            String classificacao) {
        lstPeca.add(new PecaDeTeatro(nome, desc, preco, duracao, data, genero, classificacao));
    }

    public void addPeca(PecaDeTeatro peca) {
        lstPeca.add(peca);
    }

    public void addPeca() {
        System.out.println("Insira as informações da nova peça de teatro:  \n");
        lstPeca.add(new PecaDeTeatro());
        System.out.println("\nPeça de teatro adicionada com sucesso.");
    }
 
    // TODO ver o que mais precisa fazer no controller

    // TODO Sistema de pesquisa de produto (pelo menos 3)
    // TODO Sistema de busca de cliente por CPF
    // TODO Sistema de busca para os três retornando uma lista com todas as semelhanças encontradas

    /* 
     * TODO Retornar lista com todos os nomes que bateram da pesquisa
     * * [ ] Cliente
     * * [ ] Funcionário
     * * [ ] Peca
     * 
     * TODO Sistema de pesquisa de produto
     * * [ ] Nome
     * * [ ] Descrição
     * * [ ] Tema
     * * [ ] Classificação Indicativa
     * * [ ] Gênero
     * * [ ] Faixa de preço
     *
     */

    public void alterarCliente() {
        Cliente cliente = lastAddCliente();

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

        lstCliente.set(lstCliente.indexOf(lastAddCliente()), cliente);
    }
    
    public void alterarFuncionario() {
        Funcionario funcionario = lastAddFuncionario();

        Menu.voider();
        System.out.println("Menu de alteração de dados do funcionario.");
        System.out.println("Y para alterar e N para manter.\n");
        
        System.out.println("Nome:  " + funcionario.getNome());
        if (Menu.getOptionBool())
            funcionario.setNome();
        
        System.out.println("Idade:  " + funcionario.getIdade());
        if (Menu.getOptionBool())
            funcionario.setIdade();
        
        System.out.println("CPF:  " + funcionario.getCpf());
        if (Menu.getOptionBool())
            funcionario.setCpf();

        System.out.println("Email:  " + funcionario.getEmail());
        if (Menu.getOptionBool())
            funcionario.setEmail();

        System.out.println("Telefone:  " + funcionario.getCargo());
        if (Menu.getOptionBool())
            funcionario.setCargo();

        lstFuncionario.set(lstFuncionario.indexOf(lastAddFuncionario()), funcionario);
    }

    // TODO alteraProduto

    // TODO menuBuscaCliente
    // TODO menuBuscaProduto

    public void menuBuscaFuncionario() {
        try {
            ArrayList<Funcionario> list = new ArrayList<Funcionario>();
            
            System.out.println("Menu de Busca de Funcionário:  \n");
            System.out.println("[0] Voltar;");
            System.out.println("[1] Pesquisar por nome;");
            System.out.println("[2] Pesquisar por CPF.");

            switch(Menu.getOption(2)) {
                case 0:
                    return;
                case 1:
                    list = buscaFuncionarioNome();
                    break;
                case 2:
                    list.add(buscaFuncionarioCPF());
            }

            if (list.size() > 1) {
                int i = 1;
                for (Funcionario funcionario : list) {
                    System.out.println(" " + i + " | " + funcionario.getNome() + ".");
                    i++;
                }
        
                System.out.println("[0] Voltar;");
                System.out.println("[1] Exibir um dos funcionários da lista;");
    
                switch (Menu.getOption(1)) {
                    case 0:
                        return;
                    case 1:
                        System.out.print("\n\tDigite o número do funcionário:  ");
                        list.get(Menu.getOption(1, list.size())).exibir();
                }
            } else if (list.size() == 1) {
                list.get(0).exibir();
            }
        } catch (Exception e) {
            return;
        }
    }

    public ArrayList<Funcionario> buscaFuncionarioNome(String nome) {
        if (qntFuncionarios() <= 0) throw new ArrayIndexOutOfBoundsException("Não há funcionários cadastrados no sistema.");

        ArrayList<Funcionario> list = new ArrayList<Funcionario>();

        for (Funcionario funcionario : lstFuncionario) {
            if (funcionario.getNome().equals(nome))
                list.add(funcionario);
        }

        if (list.size() <= 0) throw new IllegalArgumentException("Não há funcionários com esse nome.");
        return list;
    }

    public Funcionario buscaFuncionarioCPF(String cpf) {
        if (qntFuncionarios() <= 0)
            throw new ArrayIndexOutOfBoundsException("Não há funcionários cadastrados no sistema.");
        
        for (Funcionario funcionario : lstFuncionario) {
            if (funcionario.getCpf().equals(cpf))
                return funcionario;
        }
        throw new IllegalArgumentException("CPF de funcionário não encontrado.");
    }

    public ArrayList<Funcionario> buscaFuncionarioNome() {
        ArrayList<Funcionario> list = new ArrayList<Funcionario>();
        String nome;
        boolean validInput = true;

        do {
            validInput = true;
            System.out.print("\tNome do funcionário:  ");

            try {
                nome = input.nextLine();
                list = buscaFuncionarioNome(nome);
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido.\n");
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                validInput = false;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                System.out.println("Impossível pesquisar.\n");
                throw new ArrayIndexOutOfBoundsException(e.getMessage());
            }
        } while (!validInput);

        return list;
    }

    public Funcionario buscaFuncionarioCPF() {
        Funcionario func = null;
        String cpf;
        boolean validInput = true;

        do {
            validInput = true;
            System.out.print("\tCPF do funcionário:  ");

            try {
                cpf = input.nextLine();
                func = buscaFuncionarioCPF(cpf);
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido.\n");
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                validInput = false;
            }
        } while (!validInput);

        if (func.equals(null)) {
            throw new RuntimeException("Funcionário não encontrado. (??)");}
        return func;
    }

    public PecaDeTeatro buscaPeca(String nome) {
        for (PecaDeTeatro peca : lstPeca) {
            if (peca.getNome().equals(nome))
                return peca;
        }
        throw new IllegalArgumentException("Nome da peça não encontrado.");
    }

    public PecaDeTeatro buscaPeca() {
        PecaDeTeatro peca = null;
        String nome;
        boolean validInput = true;

        do {
            validInput = true;
            System.out.print("\tNome do cliente:  ");

            try {
                nome = input.nextLine();
                peca = buscaPeca(nome);
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido.\n");
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                validInput = false;
            }
        } while (!validInput);

        if (peca.equals(null)) {
            throw new RuntimeException("Peça de teatro não encontrada. (??)");
        }
        return peca;
    }

    public Cliente lastAddCliente() {
        return lstCliente.get(lstCliente.size() - 1);
    }

    public Funcionario lastAddFuncionario() {
        return lstFuncionario.get(lstFuncionario.size() - 1);
    }

    public PecaDeTeatro lastAddPeca() {
        return lstPeca.get(lstPeca.size() - 1);
    }
    
    public Cliente clienteMaisVelho() {
        Cliente cli;

        if (qntClientes() > 0)
            cli = lstCliente.get(0);
        else
            throw new RuntimeException("Não há clientes cadastrados no sistema.");

        for (Cliente cliente : lstCliente) {
            if (cliente.getIdade() > cli.getIdade())
                cli = cliente;
        }

        return cli;
    }
    
    public Cliente clienteMaisNovo() {
        Cliente cli;
        
        if (qntClientes() > 0)
            cli = lstCliente.get(0);
        else
            throw new RuntimeException("Não há clientes cadastrados no sistema.");        

        for (Cliente cliente : lstCliente) {
            if (cliente.getIdade() < cli.getIdade())
                cli = cliente;
        }
        return cli;
    }

    public PecaDeTeatro produtoMaisCaro() {
        PecaDeTeatro peca;
        
        if (qntPecas() > 0)
            peca = lstPeca.get(0);
        else
            throw new RuntimeException("Não há produtos cadastrados no sistema.");

        for (PecaDeTeatro pecaDeTeatro : lstPeca) {
            if (pecaDeTeatro.getPreco() > peca.getPreco())
                peca = pecaDeTeatro;
        }

        return peca;
    }

    public PecaDeTeatro produtoMaisBarato() {
        PecaDeTeatro peca;

        if (qntPecas() > 0)
            peca = lstPeca.get(0);
        else
            throw new RuntimeException("Não há produtos cadastrados no sistema.");

        for (PecaDeTeatro pecaDeTeatro : lstPeca) {
            if (pecaDeTeatro.getPreco() < peca.getPreco())
                peca = pecaDeTeatro;
        }

        return peca;
    }

    public float mediaPreco() {
        float preco = 0;

        for (PecaDeTeatro pecaDeTeatro : lstPeca) {
            preco += pecaDeTeatro.getPreco();
        }

        return preco / lstPeca.size();
    }

    public int qntPrecosAcima() {
        float media = mediaPreco();
        int qntAcima = 0;

        for (PecaDeTeatro pecaDeTeatro : lstPeca) {
            if (pecaDeTeatro.getPreco() > media)
                qntAcima++;
        }

        return qntAcima;
    }
 
    public int qntClientes() {
        return lstCliente.size();
    }

    public int qntFuncionarios() {
        return lstFuncionario.size();
    }

    public int qntPecas() {
        return lstPeca.size();
    }
}
