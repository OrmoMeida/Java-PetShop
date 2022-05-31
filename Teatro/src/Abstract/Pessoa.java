package Abstract;

public abstract class Pessoa {
    private String nome;
    private int idade;
    private String cpf;
    private String email;

    protected Pessoa() {
        this.nome = "";
        this.idade = 0;
        this.cpf = "";
        this.email = "";
    }

    protected Pessoa(int idade, String nome, String cpf, String email) {
        setNome(nome);
        setIdade(idade);
        setCpf(cpf);
        setEmail(email);
    }

    public int getIdade() {
        return this.idade;
    }

    public void setIdade(int idade) {
        if (idade <= 0) {
            throw new IllegalArgumentException();
        } else {
            this.idade = idade;
        }
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // TODO Tem que ser cinco atributos
}
