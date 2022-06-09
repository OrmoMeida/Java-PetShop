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
            System.out.println(" | " + Integer.toString(i) + " | " + apresentacao.getNome() + " | " + apresentacao.getDesc() + " | " + apresentacao.getPreco() + " | " + apresentacao.getData());
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


    

    public void remover(Apresentacao apresentacao) {
        checkEmpty();
        lstApresentacao.remove(apresentacao);
    }

    public void remover(int index) {
        checkEmpty();
        lstApresentacao.remove(index);
    }
    
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
