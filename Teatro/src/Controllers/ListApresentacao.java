package Controllers;

import java.util.ArrayList;

import Classes.Apresentacao;
import Main.Menu;

public class ListApresentacao {
    private ArrayList<Apresentacao> lstApresentacao;

    public ListApresentacao() {
        lstApresentacao = new ArrayList<Apresentacao>();
    }

    public boolean isNotEmpty() {
        return lstApresentacao.size() > 0;
    }

    public void checkEmpty() {
        if (!isNotEmpty())
            throw new ArrayIndexOutOfBoundsException("Não há apresentações cadastradas no sistema.");
    }

    public static void exibir(ArrayList<Apresentacao> lstApresentacao) {
        int i = 1;
        for (Apresentacao apresentacao : lstApresentacao) {
            System.out.println(" | " + Integer.toString(i) + " | " + apresentacao.getNome() + " | "
                    + apresentacao.getDesc() + " | " + apresentacao.getPreco() + " | " + apresentacao.getData());
            i++;
        }
    }

    public void exibir() {
        exibir(this.lstApresentacao);
    }

    public static void exibir(Apresentacao apresentacao) {
        System.out.println(" | 1 | " + apresentacao.getNome() + " | " + apresentacao.getDesc() + " | "
                + apresentacao.getPreco() + " | " + apresentacao.getData());
    }

    public void add(Apresentacao apresentacao) {
        lstApresentacao.add(apresentacao);
    }

    public void add(String nome, String desc, float preco, float duracao, String data, String genero,
            String classificacao) {
        lstApresentacao.add(new Apresentacao(nome, desc, preco, duracao, data, genero, classificacao));
    }

    public void add() {
        lstApresentacao.add(new Apresentacao());
    }

    public int size() {
        return lstApresentacao.size();
    }

    public int lastIndex() {
        checkEmpty();
        return lstApresentacao.size() - 1;
    }

    public Apresentacao last() {
        checkEmpty();
        return lstApresentacao.get(lastIndex());
    }

    public void lastExibir() {
        checkEmpty();

        System.out.println("Informações da apresentação cadastrada:  \n");
        last().exibir();
        Menu.waiter();
    }

    public ArrayList<Apresentacao> buscaNome(String nome) {
        checkEmpty();
        nome = nome.trim().toLowerCase();
        ArrayList<Apresentacao> list = new ArrayList<Apresentacao>();

        for (Apresentacao apresentacao : lstApresentacao) {
            if (apresentacao.getNome().toLowerCase().startsWith(nome))
                list.add(apresentacao);
        }

        if (list.size() <= 0)
            throw new IllegalArgumentException("Nome de produto não encontrado.");
        return list;
    }

    public ArrayList<Apresentacao> buscaGenero(String genero) {
        checkEmpty();
        genero = genero.trim().toLowerCase();
        ArrayList<Apresentacao> list = new ArrayList<Apresentacao>();

        for (Apresentacao apresentacao : lstApresentacao) {
            if (apresentacao.getGenero().toLowerCase().startsWith(genero))
                list.add(apresentacao);
        }

        if (list.size() <= 0)
            throw new IllegalArgumentException("Nenhuma apresentação deste gênero foi encontrada.");
        return list;
    }

    public ArrayList<Apresentacao> buscaClassificacao(String classificacao) {
        checkEmpty();
        classificacao = classificacao.trim().toLowerCase();
        ArrayList<Apresentacao> list = new ArrayList<Apresentacao>();

        for (Apresentacao apresentacao : lstApresentacao) {
            if (apresentacao.getClassificacao().toLowerCase().startsWith(classificacao))
                list.add(apresentacao);
        }

        if (list.size() <= 0)
            throw new IllegalArgumentException("Nenhuma apresentação desta classificação indicativa foi encontrada.");
        return list;
    }

    public ArrayList<Apresentacao> buscaPreco(float minPrice, float maxPrice) {
        checkEmpty();
        ArrayList<Apresentacao> list = new ArrayList<Apresentacao>();

        for (Apresentacao apresentacao : lstApresentacao) {
            if (apresentacao.getPreco() >= minPrice && apresentacao.getPreco() <= maxPrice)
                list.add(apresentacao);
        }

        if (list.size() <= 0)
            throw new IllegalArgumentException("Nenhuma apresentação nesta faixa de preços foi encontrada.");
        return list;
    }

    // TODO public void buscaMenu() {}

    /* 
     * Estou pensando em algum jeito de fazer justamente a chave pesquisada aparecer
     * na mostra dos resultados da pesquisa. 
     * Por exemplo, uma pesquisa por Gênero mostra os resultados com seus respectivos gêneros.
     * É chatinho, mas nada demais, também.
     */
    public void remover(Apresentacao apresentacao) {
        checkEmpty();
        lstApresentacao.remove(apresentacao);
    }

    public void remover(int index) {
        checkEmpty();
        lstApresentacao.remove(index);
    }

    // TODO public void remover();
    // O que significa que estou genuinamente perto de terminar.

    public void alterar() {
        checkEmpty();
        Apresentacao apresentacao = last();

        Menu.voider();
        System.out.println("Menu de alteração de dados de apresentação.");
        System.out.println("Y para alterar e N para manter.\n");

        System.out.println("Nome:  " + apresentacao.getNome());
        if (Menu.getOptionBool())
            apresentacao.setNome();

        System.out.println("Descrição:  " + apresentacao.getDesc());
        if (Menu.getOptionBool())
            apresentacao.setDesc();

        System.out.println("Data:  " + apresentacao.getData());
        if (Menu.getOptionBool())
            apresentacao.setData();

        System.out.println("Preço:  " + apresentacao.getPreco());
        if (Menu.getOptionBool())
            apresentacao.setPreco();

        System.out.println("Duração:  " + apresentacao.getDuracao());
        if (Menu.getOptionBool())
            apresentacao.setDuracao();

        System.out.println("Classificação indicativa:  " + apresentacao.getClassificacao());
        if (Menu.getOptionBool())
            apresentacao.setClassificacao();

        System.out.println("Gênero:  " + apresentacao.getGenero());
        if (Menu.getOptionBool())
            apresentacao.getGenero();

        lstApresentacao.set(lastIndex(), apresentacao);

        System.out.println("\nApresentação alterada com sucesso!");
        Menu.waiter();
    }

    public Apresentacao maisCara() {
        checkEmpty();
        float preco = 0;
        Apresentacao maisCara = lstApresentacao.get(0);

        for (Apresentacao apresentacao : lstApresentacao) {
            if (apresentacao.getPreco() > preco)
                maisCara = apresentacao;
        }

        return maisCara;
    }

    public float mediaPrecos() {
        checkEmpty();
        float preco = 0;

        for (Apresentacao apresentacao : lstApresentacao) {
            preco += apresentacao.getPreco();
        }

        return preco / size();
    }

    public int qntAcima() {
        checkEmpty();
        int qntAcima = 0;
        float media = mediaPrecos();

        for (Apresentacao apresentacao : lstApresentacao) {
            if (apresentacao.getPreco() > media)
                qntAcima++;
        }

        return qntAcima;
    }
}
