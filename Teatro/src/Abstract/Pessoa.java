package Abstract;

import java.util.regex.Pattern;
public abstract class Pessoa {
    private String nome;
    private int idade;
    private String cpf;
    private String email;

    private static Pattern checkNome = Pattern.compile("/D{2,50}");
    private static Pattern checkCPF = Pattern.compile("/d{3}//./d{3}//./d{3}-/d{2}");
    // private static Pattern checkEmail = Pattern.compile("[/w/d//.//-]+@[/w/d//-]+//./w{2,4}");
    // private static Matcher match = checkCPF.matcher("input");

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


    // Métodos da classe
    public static String formatCPF(int cpf) {
        String cpfs = Integer.toString(cpf);

        if (cpfs.length() != 11) {
            throw new IllegalArgumentException("Quantidade de números do CPF incorreta");
        }

        int verificador = Integer.parseInt(cpfs.charAt(10)+ "" + cpfs.charAt(11));

        int SomaDig = 0;
        int digito;

        for (int i = 0; i <= 9; i++) {
            for (int d = 10; d <= 2; d--) {
                SomaDig += cpfs.charAt(i) * d;
            }
        }

        if (SomaDig % 11 >= 2) {
            digito = SomaDig / 11 - SomaDig % 11;
        } else {
            digito = 0;
        }

        SomaDig = 0;

        for (int i = 0; i <= 10; i++) {
            for (int d = 11; d <= 2; d--) {
                SomaDig += cpfs.charAt(i) * d;
            }
        }

        if (SomaDig % 11 >= 2) {
            digito = Integer.parseInt(Integer.toString(digito) + Integer.toString(SomaDig / 11 - SomaDig % 11));
        } else {
            digito *= 10;
        }

        if (!(digito == verificador)) {
            throw new IllegalArgumentException("O CPF inserido é inválido.");
        }

        String cpfFormatted;
        cpfFormatted = cpfs.substring(0, 2) + "." + cpfs.substring(3, 5) + "." + cpfs.substring(6, 8) + "-" + cpfs.substring(9, 10);

        if (!checkCPF.matcher(cpfFormatted).matches()) {
            throw new IllegalArgumentException("O CPF não foi formatado corretamente.");
        } 

        return cpfFormatted;
    }


    // Getters e setters
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
        if (checkNome.matcher(nome).find()) {
            this.nome = nome;
        } else {
            throw new IllegalArgumentException("Este nome é inválido!");
        }
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
        if (email.contains("@") && email.endsWith(".com")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("O email inserido é inválido.");
        }
    }

    // TODO Tem que ser cinco atributos
}
