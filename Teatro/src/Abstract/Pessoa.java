package Abstract;

import java.util.InputMismatchException;
import Classes.CPF;
import Controllers.Scanner2;

public abstract class Pessoa {
    private String nome;
    private int idade;
    private String email;
    private CPF cpf;
    
    // private static Pattern checkNome = Pattern.compile("\\w{3,50}");
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
        if (idade <= 0)
            throw new IllegalArgumentException("A idade deve ser maior que 0.");
        
        this.idade = idade;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        nome = nome.trim();

        if (nome.isEmpty())
            throw new IllegalArgumentException("O nome não deve estar vazio.");

        if (nome.length() < 3)
            throw new IllegalArgumentException("O nome deve conter ao menos 3 letras.");
                    
        this.nome = nome;
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

    public void setEmail(String email) {
        email = email.trim();

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
                nome = Scanner2.scanner.nextLine();
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
                cpf = Scanner2.scanner.next();
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
                idade = Scanner2.scanner.nextInt(1, 150);
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
                email = Scanner2.scanner.next();
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
