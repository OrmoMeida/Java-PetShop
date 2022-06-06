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
        throw new IllegalArgumentException("Nome não encontrado.");
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

        return cliente;
    }

    // TODO buscaFuncionario sem argumentos
    // TODO buscaPeca sem argumentos
    // TODO retornar o cliente mais velho do sistema
    // TODO retornar o cliente mais novo do sistema
    // TODO retornar o produto mais caro do sistema
    // TODO ver o que mais precisa fazer no controller

    public Funcionario buscaFuncionario(String nome) {
        for (Funcionario funcionario : lstFuncionario) {
            if (funcionario.getNome().equals(nome))
                return funcionario;
        }
        throw new IllegalArgumentException("Nome não encontrado.");
    }

    public PecaDeTeatro buscaPeca(String nome) {
        for (PecaDeTeatro peca : lstPeca) {
            if (peca.getNome().equals(nome))
                return peca;
        }
        throw new IllegalArgumentException("Nome não encontrado.");
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
