package Main;
import java.util.ArrayList;
import java.util.InputMismatchException;

import Classes.Cliente;
import Classes.Funcionario;
import Classes.Apresentacao;

public class Controller {
    private ArrayList<Cliente> lstCliente;
    private ArrayList<Funcionario> lstFuncionario;
    private ArrayList<Apresentacao> lstApresentacao;

    public Controller() {
        lstCliente = new ArrayList<Cliente>();
        lstFuncionario = new ArrayList<Funcionario>();
        lstApresentacao = new ArrayList<Apresentacao>();
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
                nome = Main.Menu.input().nextLine();
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


    // Adds para apresentaçãos de teatro
    public void addApresentacao(String nome, String desc, float preco, float duracao, String data, String genero,
            String classificacao) {
        lstApresentacao.add(new Apresentacao(nome, desc, preco, duracao, data, genero, classificacao));
    }

    public void addApresentacao(Apresentacao apresentacao) {
        lstApresentacao.add(apresentacao);
    }

    public void addApresentacao() {
        System.out.println("Insira as informações da nova apresentação de teatro:  \n");
        lstApresentacao.add(new Apresentacao());
        System.out.println("\nApresentação de teatro adicionada com sucesso.");
    }
 
    // TODO ver o que mais precisa fazer no controller

    // TODO Sistema de pesquisa de produto (pelo menos 3)
    // TODO Sistema de busca de cliente por CPF
    // TODO Sistema de busca para os três retornando uma lista com todas as semelhanças encontradas

    /* 
     * TODO Retornar lista com todos os nomes que bateram da pesquisa
     * * [ ] Cliente
     * * [ ] Funcionário
     * * [ ] Apresentacao
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
                nome = Main.Menu.input().nextLine();
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
                cpf = Main.Menu.input().nextLine();
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

    public Apresentacao buscaApresentacao(String nome) {
        for (Apresentacao apresentacao : lstApresentacao) {
            if (apresentacao.getNome().equals(nome))
                return apresentacao;
        }
        throw new IllegalArgumentException("Nome da apresentação não encontrado.");
    }

    public Apresentacao buscaApresentacao() {
        Apresentacao apresentacao = null;
        String nome;
        boolean validInput = true;

        do {
            validInput = true;
            System.out.print("\tNome do cliente:  ");

            try {
                nome = Main.Menu.input().nextLine();
                apresentacao = buscaApresentacao(nome);
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido.\n");
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                validInput = false;
            }
        } while (!validInput);

        if (apresentacao.equals(null)) {
            throw new RuntimeException("Apresentação de teatro não encontrada. (??)");
        }
        return apresentacao;
    }

    public Cliente lastAddCliente() {
        return lstCliente.get(lstCliente.size() - 1);
    }

    public Funcionario lastAddFuncionario() {
        return lstFuncionario.get(lstFuncionario.size() - 1);
    }

    public Apresentacao lastAddApresentacao() {
        return lstApresentacao.get(lstApresentacao.size() - 1);
    }

    public Apresentacao produtoMaisCaro() {
        Apresentacao apresentacao;
        
        if (qntApresentacaos() > 0)
            apresentacao = lstApresentacao.get(0);
        else
            throw new RuntimeException("Não há produtos cadastrados no sistema.");

        for (Apresentacao apresentacaoDeTeatro : lstApresentacao) {
            if (apresentacaoDeTeatro.getPreco() > apresentacao.getPreco())
                apresentacao = apresentacaoDeTeatro;
        }

        return apresentacao;
    }

    public Apresentacao produtoMaisBarato() {
        Apresentacao apresentacao;

        if (qntApresentacaos() > 0)
            apresentacao = lstApresentacao.get(0);
        else
            throw new RuntimeException("Não há produtos cadastrados no sistema.");

        for (Apresentacao apresentacaoDeTeatro : lstApresentacao) {
            if (apresentacaoDeTeatro.getPreco() < apresentacao.getPreco())
                apresentacao = apresentacaoDeTeatro;
        }

        return apresentacao;
    }

    public float mediaPreco() {
        float preco = 0;

        for (Apresentacao apresentacaoDeTeatro : lstApresentacao) {
            preco += apresentacaoDeTeatro.getPreco();
        }

        return preco / lstApresentacao.size();
    }

    public int qntPrecosAcima() {
        float media = mediaPreco();
        int qntAcima = 0;

        for (Apresentacao apresentacaoDeTeatro : lstApresentacao) {
            if (apresentacaoDeTeatro.getPreco() > media)
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

    public int qntApresentacaos() {
        return lstApresentacao.size();
    }
}
