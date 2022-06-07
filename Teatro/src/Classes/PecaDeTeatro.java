package Classes;

import java.util.InputMismatchException;
import java.util.regex.Pattern;

import Abstract.Produto;
import Enums.ClassificacaoIndicativa;
import Enums.Genero;

public class PecaDeTeatro extends Produto {
    // Atributos da classe
    private float duracao;
    private String data;
    private Genero genero;
    private ClassificacaoIndicativa classificacao;
    private static Pattern checkData = Pattern.compile("(\\d){2}(\\)(\\d){2}(\\)(\\d){4}");


    // Construtores
    public PecaDeTeatro(String nome, String desc, float preco, float duracao, String data, String genero, String classificacao) {
        super(nome, desc, preco);
        setDuracao(duracao);
        setData(data);
        setGenero(genero);
        setClassificacao(classificacao);
    }

    public PecaDeTeatro() {
        super();
        setDuracao();
        setData();
        setGenero();
        setClassificacao();
    }


    // Métodos da classe
    public void exibir() {
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
        this.genero = Genero.setTema(descricao);
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
                duracao = getInput().nextFloat();
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
                data = getInput().nextLine();
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
                descricao = getInput().nextLine();
                setGenero(descricao);
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido.\n");
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                validInput = false;
            }
        } while (!validInput);
    }

    public void setClassificacao() {
        String descricao;
        boolean validInput = true;

        do {
            validInput = true;
            System.out.print("\tGênero:  ");

            try {
                descricao = getInput().nextLine();
                setClassificacao(descricao);
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