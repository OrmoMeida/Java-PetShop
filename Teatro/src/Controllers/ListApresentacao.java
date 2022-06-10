package Controllers;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.concurrent.CancellationException;

import Classes.Apresentacao;
import Main.Menu;

public class ListApresentacao {
    private ArrayList<Apresentacao> lstApresentacao;

    public ListApresentacao() {
        lstApresentacao = new ArrayList<Apresentacao>();
    }

    public static boolean isNotEmpty(ArrayList<Apresentacao> list) {
        return list.size() > 0;
    }

    public boolean isNotEmpty() {
        return isNotEmpty(lstApresentacao);
    }

    public static void checkEmpty(ArrayList<Apresentacao> list) {
        if (!isNotEmpty(list))
        throw new ArrayIndexOutOfBoundsException("Não há apresentações cadastradas no sistema.");
    }
    
    public void checkEmpty() {
        checkEmpty(lstApresentacao);
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

    public ArrayList<Apresentacao> buscaNome() {
        checkEmpty();
        ArrayList<Apresentacao> list = new ArrayList<Apresentacao>();
        String nome;
        boolean validInput = true;

        Menu.voider();
        System.out.println("Menu de busca por apresentações via nome.\n\n");

        do {
            validInput = true;
            System.out.print("\tNome:  ");

            try {
                nome = Main.Menu.input().next();
                list = buscaNome(nome);
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido.\n");
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                System.out.println("Deseja tentar novamente?");
                if (!Menu.getOptionBool())
                    throw new CancellationException("Busca de apresentações via nome cancelada pelo usuário.");
                validInput = false;
            } finally {
                Main.Menu.input().nextLine();
            }
        } while (!validInput);

        return list;
    }

    public ArrayList<Apresentacao> buscaDesc(String descricao) {
        checkEmpty();
        descricao = descricao.trim().toLowerCase();
        ArrayList<Apresentacao> list = new ArrayList<Apresentacao>();

        for (Apresentacao apresentacao : lstApresentacao) {
            if (apresentacao.getNome().toLowerCase().contains(descricao))
                list.add(apresentacao);
        }

        return list;
    }
    
    public ArrayList<Apresentacao> buscaDesc() {
        checkEmpty();
        ArrayList<Apresentacao> list = new ArrayList<Apresentacao>();
        String desc;
        boolean validInput = true;

        Menu.voider();
        System.out.println("Menu de busca por apresentações via descrição.\n\n");

        do {
            validInput = true;
            System.out.print("\tDescrição:  ");

            try {
                desc = Main.Menu.input().next();
                list = buscaDesc(desc);
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido.\n");
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                System.out.println("Deseja tentar novamente?");
                if (!Menu.getOptionBool())
                    throw new CancellationException("Busca de apresentações via descrição cancelada pelo usuário.");
                validInput = false;
            } finally {
                Main.Menu.input().nextLine();
            }
        } while (!validInput);

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

    public ArrayList<Apresentacao> buscaGenero() {
        checkEmpty();
        ArrayList<Apresentacao> list = new ArrayList<Apresentacao>();
        String genero;
        boolean validInput = true;

        Menu.voider();
        System.out.println("Menu de busca por apresentações via gênero.\n\n");

        do {
            validInput = true;
            System.out.print("\tNome:  ");

            try {
                genero = Main.Menu.input().next();
                list = buscaGenero(genero);
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido.\n");
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                System.out.println("Deseja tentar novamente?");
                if (!Menu.getOptionBool())
                    throw new CancellationException("Busca de apresentações via gênero cancelada pelo usuário.");
                validInput = false;
            } finally {
                Main.Menu.input().nextLine();
            }
        } while (!validInput);

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

    public ArrayList<Apresentacao> buscaClassificacao() {
        checkEmpty();
        ArrayList<Apresentacao> list = new ArrayList<Apresentacao>();
        String classificacao;
        boolean validInput = true;

        Menu.voider();
        System.out.println("Menu de busca por apresentações via classificação indicativa.\n\n");

        do {
            validInput = true;
            System.out.print("\tNome:  ");

            try {
                classificacao = Main.Menu.input().next();
                list = buscaClassificacao(classificacao);
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido.\n");
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                System.out.println("Deseja tentar novamente?");
                if (!Menu.getOptionBool())
                    throw new CancellationException("Busca de apresentações via classificação indicativa cancelada pelo usuário.");
                validInput = false;
            } finally {
                Main.Menu.input().nextLine();
            }
        } while (!validInput);

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

    public ArrayList<Apresentacao> buscaPreco() {
        checkEmpty();
        ArrayList<Apresentacao> list = new ArrayList<Apresentacao>();
        float minPrice = 0;
        float maxPrice = 0;
        boolean validInput = true;

        Menu.voider();
        System.out.println("Menu de busca por apresentações via nome.\n\n");
        
        // list = buscaNome(nome);

        do {
            System.out.println("Limite de preços:  \n");
            
            try {
                do {
                    validInput = true;
                    System.out.print("\tPreço mínimo:  ");
    
                    try {
                        minPrice = Main.Menu.input().nextFloat();
                        if (minPrice <= 0)
                            throw new IllegalArgumentException("O preço mínimo da busca não deve ser zero nem negativo");
                    } catch (InputMismatchException e) {
                        System.out.println("Valor inválido.\n");
                        validInput = false;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage() + "\n");
                        System.out.println("Deseja tentar novamente?");
                        if (!Menu.getOptionBool())
                            throw new CancellationException("Busca de apresentações por preço cancelada pelo usuário.");
                        validInput = false;
                    } finally {
                        Main.Menu.input().nextLine();
                    }
                } while (!validInput);
    
                do {
                    validInput = true;
                    System.out.print("\tPreço máximo:  ");
    
                    try {
                        maxPrice = Main.Menu.input().nextFloat();
                        if (maxPrice <= 0 || maxPrice < minPrice)
                            throw new IllegalArgumentException("O preço máximo da busca não deve ser zero, negativo ou menor que o valor mínimo.");
                    } catch (InputMismatchException e) {
                        System.out.println("Valor inválido.\n");
                        validInput = false;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage() + "\n");
                        System.out.println("Deseja tentar novamente?");
                        if (!Menu.getOptionBool())
                            throw new CancellationException("Busca de apresentações por preço cancelada pelo usuário.");
                        validInput = false;
                    } finally {
                        Main.Menu.input().nextLine();
                    }
                } while (!validInput);

                buscaPreco(minPrice, maxPrice);

            } catch (InputMismatchException e) {
                System.out.println("Valores inválidos.\n");
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                System.out.println("Deseja tentar novamente?");
                if (!Menu.getOptionBool())
                    throw new CancellationException("Busca de apresentações por preço cancelada pelo usuário.");
                validInput = false;
            }
        } while (!validInput);

        return list;
    }

    public ArrayList<Apresentacao> busca() {
        checkEmpty();
        ArrayList<Apresentacao> busca = new ArrayList<Apresentacao>();

        System.out.println("Menu de busca de apresentações.\n\n");

        System.out.println("Deseja procurar a apresentação por:  ");
        System.out.println("[0] Cancelar;");
        System.out.println("[1] Nome;");
        System.out.println("[2] Descrição;");
        System.out.println("[3] Gênero;");
        System.out.println("[4] Classificação;");
        System.out.println("[5] Faixa de preço.");

        switch (Menu.getOption(5)) {
            case 0:
                throw new CancellationException("Operação de busca de apresentação cancelada pelo usuário.");

            case 1:
                busca = buscaNome();
                break;

            case 2:
                busca = buscaDesc();
                break;

            case 3:
                busca = buscaGenero();
                break;

            case 4:
                busca = buscaClassificacao();
                break;

            case 5:
                busca = buscaPreco();
                break;
        }

        checkEmpty(busca);
        return busca;
    }

    public Apresentacao buscaArray(ArrayList<Apresentacao> busca) {
        checkEmpty(busca);

        if (busca.size() > 1)
            System.out.println("Apresentações encontradas:  \n");
        else
            System.out.println("Apresentação encontrada:  \n");

        exibir(busca);

        if (busca.size() == 1) {
            System.out.println("\nDeseja selecionar essa apresentação?  ");
            if (Menu.getOptionBool())
                return busca.get(0);
            else
                throw new CancellationException("Operação de seleção de uma única apresentação cancelada pelo usuário.");
        }

        System.out.println("\nDeseja selecionar alguma dessas apresentaçãos?");
        if (!Menu.getOptionBool())
            throw new CancellationException("Operação de seleção dentre múltiplas apresentações cancelada pelo usuário.");
        
        System.out.println("\nDigite o número da apresentação.");
        return busca.get(Menu.getOption(1, busca.size()) - 1);
    }

    public void buscaMenu() {
        checkEmpty();
        Menu.voider();

        try {
            exibir(buscaArray(busca()));
            Menu.waiter();
        } catch (CancellationException e) {
            return;
        }
    }

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

    public void remover() {
        checkEmpty();
        Menu.voider();

        try {
            remover(buscaArray(busca()));
            Menu.waiter();
        } catch (CancellationException e) {
            return;
        }
    }
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

    public void maisCaraMenu() {
        checkEmpty();

        System.out.println("Apresentação mais cara do sistema:");
        exibir(maisCara());

        System.out.println("\nDeseja exibir a apresentação?");
        if (Menu.getOptionBool()) {
            maisCara().exibir();
            Menu.waiter();
        }
    }

    public float mediaPrecos() {
        checkEmpty();
        float preco = 0;

        for (Apresentacao apresentacao : lstApresentacao) {
            preco += apresentacao.getPreco();
        }

        return preco / size();
    }

    public void mediaPrecosMenu() {
        checkEmpty();

        System.out.println("Média de preços das apresentações:  " + mediaPrecos());
        Menu.waiter();
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

    public void qntAcimaMenu() {
        checkEmpty();

        System.out.println("Quantidade de apresentações mais caras que a média:  " + qntAcima());
        Menu.waiter();
    }
}
