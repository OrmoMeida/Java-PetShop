package Controllers;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.concurrent.CancellationException;

import Classes.Apresentacao;
import Enums.ClassificacaoIndicativa;
import Enums.Genero;
import Main.Menu;

public class ListApresentacao {
    private ArrayList<Apresentacao> lstApresentacao;
    private int lastAlterado;

    public ListApresentacao() {
        lstApresentacao = new ArrayList<Apresentacao>();
    }

    public void geraApresentacoes() {
        add("Servamp", "Vampiros que fazem contratos com humanos e tem problemas familiares", 35.50f, 120f, "21/07/2020", "drama", "+16");
        add("Moriarty", "Acompanhe a história de Sherlock pela visão de seu inimigo", 45.99f, 102f, "25/07/2020", "suspense", ClassificacaoIndicativa.MIN16.getDescricao());
        add("SpyxFamily", "Um espião que adota uma criança telepata para sua missão", 25.99f, 105f, "28/07/2022", "Comédia", "Livre");
        add("O retorno de Mariana", "Mariana retorna.", 20.5f, 40.37f, "20/12/2022", "Suspense", "+12");
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

    public void exibirLastAlterado() {
        checkEmpty();

        System.out.println("Informações do cliente alterado:  \n");
        lstApresentacao.get(lastAlterado).exibir();

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
            throw new IllegalArgumentException("Nome da apresentação não encontrado.");
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
                nome = Scanner2.scanner.nextLine();
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
            }
        } while (!validInput);

        return list;
    }

    public ArrayList<Apresentacao> buscaDesc(String descricao) {
        checkEmpty();
        descricao = descricao.trim().toLowerCase();
        ArrayList<Apresentacao> list = new ArrayList<Apresentacao>();

        for (Apresentacao apresentacao : lstApresentacao) {
            if (apresentacao.getDesc().toLowerCase().contains(descricao))
                list.add(apresentacao);
        }

        if (list.size() <= 0)
            throw new IllegalArgumentException("Descrição de apresentação não encontrada.");
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
                desc = Scanner2.scanner.nextLine();
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
            }
        } while (!validInput);

        return list;
    }

    public ArrayList<Apresentacao> buscaGenero(String genero) {
        checkEmpty();
        genero = genero.trim().toLowerCase();
        ArrayList<Apresentacao> list = new ArrayList<Apresentacao>();

        Genero.setGenero(genero);

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
            System.out.print("\tGênero:  ");

            try {
                genero = Scanner2.scanner.next();
                list = buscaGenero(genero);
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido.\n");
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                System.out.println("Gêneros disponíveis:  ");
                System.out.println(Genero.getGeneros());
                System.out.println("\nDeseja tentar novamente?");
                if (!Menu.getOptionBool())
                    throw new CancellationException("Busca de apresentações via gênero cancelada pelo usuário.");
                validInput = false;
            }
        } while (!validInput);

        return list;
    }

    public ArrayList<Apresentacao> buscaClassificacao(String classificacao) {
        checkEmpty();
        classificacao = classificacao.trim().toLowerCase();
        ArrayList<Apresentacao> list = new ArrayList<Apresentacao>();

        ClassificacaoIndicativa.setClassificacao(classificacao);

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
            System.out.print("\tClassificação indicativa:  ");

            try {
                classificacao = Scanner2.scanner.next();
                list = buscaClassificacao(classificacao);
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido.\n");
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                System.out.println("Classificações indicativas disponíveis: ");
                System.out.println(ClassificacaoIndicativa.getClassificacoes());
                System.out.println("\nDeseja tentar novamente?");
                if (!Menu.getOptionBool())
                    throw new CancellationException("Busca de apresentações via classificação indicativa cancelada pelo usuário.");
                validInput = false;
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
        boolean validInput;

        System.out.println("Menu de busca por apresentações via nome.\n\n");
        
        // list = buscaNome(nome);

        do {
            validInput = true;
            System.out.println("Limite de preços:  \n");
            
            try {
                do {
                    validInput = true;
                    System.out.print("\tPreço mínimo:  ");
    
                    try {
                        minPrice = Scanner2.scanner.nextFloat();
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
                    }
                } while (!validInput);
    
                do {
                    validInput = true;
                    System.out.print("\tPreço máximo:  ");
    
                    try {
                        maxPrice = Scanner2.scanner.nextFloat();
                        if (maxPrice <= 0)
                            throw new IllegalArgumentException("O preço máximo da busca deve ser maior que 0.");
                        if (maxPrice < minPrice)
                            throw new IllegalArgumentException("O preço máximo da busca não deve ser maior que o preço mínimo.");
                    } catch (InputMismatchException e) {
                        System.out.println("Valor inválido.\n");
                        validInput = false;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage() + "\n");
                        System.out.println("Deseja tentar novamente?");
                        if (!Menu.getOptionBool())
                            throw new CancellationException("Busca de apresentações por preço cancelada pelo usuário.");
                        validInput = false;
                    }
                } while (!validInput);

                list = buscaPreco(minPrice, maxPrice);

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
        System.out.println("[1] Ver todas as apresentações;");
        System.out.println("[2] Consultar por nome;");
        System.out.println("[3] Consultar por descrição;");
        System.out.println("[4] Consultar por gênero;");
        System.out.println("[5] Consultar por classificação;");
        System.out.println("[6] Consultar por faixa de preço.");

        switch (Menu.getOption(6)) {
            case 0:
                throw new CancellationException("Operação de busca de apresentação cancelada pelo usuário.");

            case 1:
                busca = lstApresentacao;
                break;

            case 2:
                busca = buscaNome();
                break;

            case 3:
                busca = buscaDesc();
                break;

            case 4:
                busca = buscaGenero();
                break;

            case 5:
                busca = buscaClassificacao();
                break;

            case 6:
                busca = buscaPreco();
                break;

        }

        if (busca.size() <= 0)
            throw new IllegalArgumentException("Nenhuma apresentação foi encontrada na busca.");
        return busca;
    }

    public Apresentacao buscaArray(ArrayList<Apresentacao> busca) {
        if (busca.size() <= 0)
            throw new IllegalArgumentException("Não há apresentações para pesquisar.");

        if (busca.size() > 1)
            System.out.println("\n\nApresentações encontradas:  \n");
        else
            System.out.println("\n\nApresentação encontrada:  \n");

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
            buscaArray(busca()).exibir();
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
        Apresentacao apresentacao;

        try {
            apresentacao = buscaArray(busca());
            System.out.println("Tem certeza que deseja remover a apresentação selecionada?");
            if (Menu.getOptionBool()) {
                remover(apresentacao);
                System.out.println("\nApresentação removida com sucesso.\n");
                Menu.waiter();
            } else {
                throw new CancellationException("Operação de remoção de apresentação via busca cancelada pelo usuário.");
            }
        } catch (CancellationException e) {
            return;
        }
    }

    public void removerLast() {
        checkEmpty();

        System.out.println("Tem certeza que deseja remover a última apresentação adicionada?");
        if (Menu.getOptionBool()) {
            remover(last());
            System.out.println("\nApresentação removida com sucesso.\n");
            Menu.waiter();
        }
    }

    // O que significa que estou genuinamente perto de terminar.

    public void alterar(Apresentacao apresentacao) {
        checkEmpty();
        lastAlterado = lstApresentacao.indexOf(apresentacao);

        if (lastAlterado == -1)
            throw new IllegalArgumentException("Apresentação não encontrada: Impossível alterar.");

        Menu.voider();
        System.out.println("\n\nMenu de alteração de dados de apresentação.");
        System.out.println("Y para alterar e N para manter.\n");

        System.out.println("\nNome:  " + apresentacao.getNome());
        if (Menu.getOptionBool())
            apresentacao.setNome();

        System.out.println("\nDescrição:  " + apresentacao.getDesc());
        if (Menu.getOptionBool())
            apresentacao.setDesc();

        System.out.println("\nData:  " + apresentacao.getData());
        if (Menu.getOptionBool())
            apresentacao.setData();

        System.out.println("\nPreço:  " + apresentacao.getPreco());
        if (Menu.getOptionBool())
            apresentacao.setPreco();

        System.out.println("\nDuração:  " + apresentacao.getDuracao());
        if (Menu.getOptionBool())
            apresentacao.setDuracao();

        System.out.println("\nClassificação indicativa:  " + apresentacao.getClassificacao());
        if (Menu.getOptionBool())
            apresentacao.setClassificacao();

        System.out.println("\nGênero:  " + apresentacao.getGenero());
        if (Menu.getOptionBool())
            apresentacao.getGenero();

        lstApresentacao.set(lastAlterado, apresentacao);

        System.out.println("\n\nApresentação alterada com sucesso!");
        Menu.waiter();
    }

    public void alterar() {
        alterar(last());
    }
    
    public void alterarMenu() {
        checkEmpty();

        System.out.println("\n\nMenu de alteração\n\n");

        try {
            alterar(buscaArray(busca()));
        } catch (CancellationException e) {
            return;
        }
    }

    public void alterarNovamente() {
        checkEmpty();

        System.out.println("\nMenu de nova alteração\n\n");
        
        try {
            alterar(lstApresentacao.get(lastAlterado));
        } catch (CancellationException e) {
            return;
        }
    }

    // public void 

    public Apresentacao maisCara() {
        checkEmpty();
        float preco = 0;
        Apresentacao maisCara = lstApresentacao.get(0);

        for (Apresentacao apresentacao : lstApresentacao) {
            if (apresentacao.getPreco() > preco) {
                preco = apresentacao.getPreco();
                maisCara = apresentacao;
            }
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
