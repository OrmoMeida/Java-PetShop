package Abstract;

import java.util.InputMismatchException;

import Controllers.Scanner2;

public abstract class Produto {
    // Atributos da classe
    private String nome;
    private String desc;
    private float preco;


    // Construtores da classe
    public Produto() {
        System.out.println("Cadastro de um novo produto.\n\n");
        setNome();
        setDesc();
        setPreco();
    }

    public Produto(String nome, String desc, float preco) {
        setNome(nome);
        setDesc(desc);
        setPreco(preco);
    }

    
    // Getters e setters
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        nome = nome.trim();
        if (nome.isEmpty())
            throw new IllegalArgumentException("O nome do produto não deve estar vazio.");
        if (nome.length() < 3)
            throw new IllegalArgumentException("O nome do produto deve possuir ao menos 3 caracteres.");
        this.nome = nome;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        desc = desc.trim();

        if (desc.isEmpty())
            throw new IllegalArgumentException("A descrição do produto não deve estar vazia.");
        
        if (desc.length() < 5)
            throw new IllegalArgumentException("A descrição do produto deve conter ao menos 5 caracteres.");
        
        this.desc = desc;
    }

    public float getPreco() {
        return this.preco;
    }

    public void setPreco(float preco) {
        if (preco <= 0)
            throw new IllegalArgumentException("O preço do produto deve ser maior que 0.");
        else
            this.preco = preco;
    }

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
    
    public void setDesc() {
        String desc;
        boolean validInput = true;

        do {
            validInput = true;
            System.out.print("\tDescrição:  ");

            try {
                desc = Scanner2.scanner.nextLine();
                setDesc(desc);
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido.\n");
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                validInput = false;
            }
        } while (!validInput);
    }

    public void setPreco() {
        float preco;
        boolean validInput = true;

        do {
            validInput = true;
            System.out.print("\tPreço:  ");

            try {
                preco = Scanner2.scanner.nextUnsignedFloat();
                setPreco(preco);
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
