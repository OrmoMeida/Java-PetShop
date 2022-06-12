package Classes;
import java.util.InputMismatchException;
import java.util.regex.Pattern;

import Abstract.Pessoa;
import Controllers.Scanner2;
import Interfaces.IPessoa;

public class Cliente extends Pessoa implements IPessoa {
    private String telefone;
    private int codCadastro;
    private static int qntClientes = 0;
    private static Pattern checkTelefone = Pattern.compile("\\(\\d{2}\\)\\s\\d{5}-\\d{4}");


    public Cliente() {
        super();
        setTelefone();
        codCadastro = ++qntClientes;
    }

    public Cliente(int idade, String nome, String cpf, String email, String telefone) {
        super(idade, nome, cpf, email);
        setTelefone(telefone);
        codCadastro = ++qntClientes;
    }

    @Override
    public void exibir() {
        System.out.println("\nCliente nº " + this.getCodCliente());
        System.out.println("Nome do cliente     :  " + this.getNome());
        System.out.println("Idade do cliente    :  " + this.getIdade());
        System.out.println("CPF do cliente      :  " + this.getCpf());
        System.out.println("Email do cliente    :  " + this.getEmail());
        System.out.println("Telefone do cliente :  " + this.getTelefone());
    }

    // Getters e setters
    public static int getQntClientes() {
        return qntClientes;
    }

    public int getCodCliente() {
        return codCadastro;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        telefone = telefone.replaceAll("\\D", "");

        if (telefone.length() != 11)
            throw new IllegalArgumentException("O telefone deve conter exatamente 11 dígitos.");

        String formattedTelefone = "(" + telefone.substring(0, 2) + ") " + telefone.substring(1, 6) + "-" + telefone.substring(6, 10);

        if (!checkTelefone.matcher(formattedTelefone).matches())
            throw new IllegalArgumentException("A formatação do telefone está incorreta.");

        this.telefone = formattedTelefone;
    }
    
    public void setTelefone() {
        String telefone;
        boolean validInput = true;
    
        do {
            validInput = true;
            System.out.print("\tTelefone:  ");
    
            try {
                telefone = Scanner2.scanner.nextLine();
                setTelefone(telefone);
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido.\n");
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                validInput = false;
            }
        } while (!validInput);
    }
}
