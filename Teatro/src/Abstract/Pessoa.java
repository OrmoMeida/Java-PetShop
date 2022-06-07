package Abstract;

import java.util.regex.Pattern;
import java.util.InputMismatchException;
import java.util.Scanner;

import Classes.CPF;

public abstract class Pessoa {
    private static Scanner input = new Scanner(System.in);
    private String nome;
    private int idade;
    private String email;
    private CPF cpf;
    
    private static Pattern checkNome = Pattern.compile("^[a-záàâãéèêíïóôõöúçñ]{3,}(//s[a-záàâãéèêíïóôõöúçñ]{2,})+$");
    // private static Pattern checkEmail = Pattern.compile("[/w/d//.//-]+@[/w/d//-]+//./w{2,4}");
    // private static Matcher match = checkCPF.matcher("input");

    // TODO regex para o Email
    // TODO setEmail usando o regex

    protected Pessoa() {
        this.cpf = new CPF();
        System.out.println("Cadastro:  ");
        setNome();
        setIdade();
        setCpf();
        setEmail();
    }

    protected Pessoa(int idade, String nome, String cpf, String email) {
        this.cpf = new CPF();
        setNome(nome);
        setIdade(idade);
        setCpf(cpf);
        setEmail(email);
    }


    // Métodos da classe


    // Getters e setters
    public int getIdade() {
        return this.idade;
    }

    public void setIdade(int idade) {
        if (idade <= 0) {
            throw new IllegalArgumentException("A idade deve ser maior que 0.");
        } else {
            this.idade = idade;
        }
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        if (nome.isEmpty())
            throw new IllegalArgumentException("O nome não deve estar vazio.");

        if (!checkNome.matcher(nome).matches()) {
            this.nome = nome;
        } else {
            throw new IllegalArgumentException("O nome deve conter ao menos 3 letras, e ao menos um sobrenome é obrigatório.");
        }
    }

    public String getCpf() {
        return this.cpf.get();
    }

    public void setCpf(String cpf) {
        this.cpf.set(cpf);
    }

    public String getEmail() {
        return this.email;
    }

    protected Scanner getInput() {
        return input;
    }

    public void setEmail(String email) {
        if (email.isEmpty())
            throw new IllegalArgumentException("O email não deve estar vazio.");

        if (!email.startsWith("@") && email.contains("@") && email.endsWith(".com")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("O email inserido é inválido.");
        }
    }

    // Sets personalizados
    public void setNome() {
        String nome;
        boolean validInput = true;
    
        do {
            validInput = true;
            System.out.print("\tNome:  ");
    
            try {
                nome = input.nextLine();
                setNome(nome);
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido.\n");
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                validInput = false;
            }
        } while (!validInput);
    }
    
    public void setCpf() {
        String cpf;
        boolean validInput = true;

        do {
            validInput = true;
            System.out.print("\tCPF:  ");

            try {
                cpf = input.next();
                setCpf(cpf);
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido.\n");
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                validInput = false;
            }
        } while (!validInput);
    }

    public void setIdade() {
        int idade;
        boolean validInput = true;

        do {
            validInput = true;
            System.out.print("\tIdade:  ");

            try {
                idade = input.nextInt();
                setIdade(idade);
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite apenas números.\n");
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                validInput = false;
            }
        } while (!validInput);
    }

    public void setEmail() {
        String email;
        boolean validInput = true;

        do {
            validInput = true;
            System.out.print("\tEmail:  ");

            try {
                email = input.nextLine();
                setEmail(email);
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
