package Classes;

import java.util.InputMismatchException;
import java.util.regex.Pattern;

import Abstract.Produto;
import Enums.ClassificacaoIndicativa;
import Enums.Genero;

public class Apresentacao extends Produto {
    // Atributos da classe
    private static int qntApresentacao = 0;
    private int codApresentacao;
    private float duracao;
    private String data;
    private Genero genero;
    private ClassificacaoIndicativa classificacao;
    private static Pattern checkData = Pattern.compile("(\\d){2}\\/(\\d){2}\\/(\\d){4}");


    // Construtores
    public Apresentacao(String nome, String desc, float preco, float duracao, String data, String genero, String classificacao) {
        super(nome, desc, preco);
        codApresentacao = ++qntApresentacao;
        setDuracao(duracao);
        setData(data);
        setGenero(genero);
        setClassificacao(classificacao);
    }

    public Apresentacao() {
        super();
        setDuracao();
        setData();
        setGenero();
        setClassificacao();
    }


    // Métodos da classe
    public void exibir() {
        System.out.println("\nApresentação nº " + codApresentacao);
        System.out.println("Nome          :  " + getNome());
        System.out.println("Descrição     :  " + getDesc());
        System.out.println("Preço         :  " + getPreco());
        System.out.println("Duração       :  " + getDuracao());
        System.out.println("Data          :  " + getData());
        System.out.println("Classificação :  " + getClassificacao());
        System.out.println("Gênero        :  " + getGenero());
    }


    // Getters e setters
    public float getDuracao() {
        return this.duracao;
    }

    public void setDuracao(float duracao) {
        if (duracao <= 0)
            throw new IllegalArgumentException("A duração deve ser maior que 0.");
        this.duracao = duracao;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        data = data.replaceAll("\\D", "");

        if (data.length() != 8)
            throw new IllegalArgumentException("A data deve conter 8 dígitos.");

        data = data.substring(0, 2) + "/" + data.substring(2, 4) + "/" + data.substring(4, 8);

        if (!checkData.matcher(data).matches())
            throw new IllegalArgumentException("Erro na formatação da data.");

        this.data = data;
    }

    public String getGenero() {
        return this.genero.getDescricao();
    }

    public void setGenero(String descricao) {
        this.genero = Genero.setGenero(descricao);
    }

    public String getClassificacao() {
        return this.classificacao.getDescricao();
    }

    public void setClassificacao(String descricao) {
        this.classificacao = ClassificacaoIndicativa.setClassificacao(descricao);
    }

    public void setDuracao() {
        float duracao;
        boolean validInput = true;

        do {
            validInput = true;
            System.out.print("\tDuração:  ");

            try {
                duracao = Main.Menu.input().nextFloat();
                setDuracao(duracao);
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido.\n");
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                validInput = false;
            }
        } while (!validInput);
    }
    
    public void setData() {
        String data;
        boolean validInput = true;

        do {
            validInput = true;
            System.out.print("\tData:  ");

            try {
                data = Main.Menu.input().next();
                setData(data);
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido.\n");
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                validInput = false;
            }
        } while (!validInput);
    }

    public void setGenero() {
        String descricao;
        boolean validInput = true;

        do {
            validInput = true;
            System.out.print("\tGênero:  ");

            try {
                descricao = Main.Menu.input().next();
                setGenero(descricao);
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido.\n");
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                System.out.println("Gêneros disponíveis:");
                System.out.println(Genero.getGenero() + "\n");
                validInput = false;
            }
        } while (!validInput);
    }

    public void setClassificacao() {
        String descricao;
        boolean validInput = true;

        do {
            validInput = true;
            System.out.print("\tClassificação indicativa:  ");

            try {
                descricao = Main.Menu.input().next();
                setClassificacao(descricao);
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido.\n");
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                System.out.println("Classificações indicativas disponíveis:");
                System.out.println(ClassificacaoIndicativa.getClassificacoes() + "\n");
                validInput = false;
            }
        } while (!validInput);
    }
}