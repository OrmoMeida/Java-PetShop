package Controllers;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.concurrent.CancellationException;

import Classes.Funcionario;
import Main.Menu;

public class ListFuncionario {   
    private ArrayList<Funcionario> lstFuncionario;
    
    public ListFuncionario() {
        lstFuncionario = new ArrayList<Funcionario>();
    }

    public static boolean isNotEmpty(ArrayList<Funcionario> list) {
        return list.size() > 0;
    }

    public boolean isNotEmpty() {
        return isNotEmpty(lstFuncionario);
    }

    public static void checkEmpty(ArrayList<Funcionario> list) {
        if (!isNotEmpty(list))
        throw new ArrayIndexOutOfBoundsException("Não há funcionários cadastrados no sistema.");
    }
    
    public void checkEmpty() {
        checkEmpty(lstFuncionario);
    }

    public static void exibir(ArrayList<Funcionario> lstFuncionario) {
        int i = 1;
        for (Funcionario funcionario : lstFuncionario) {
            System.out.println(" | " + Integer.toString(i) + " | " + funcionario.getNome() + " | " + funcionario.getCpf());
            i++;
        }
    }

    public static void exibir(Funcionario funcionario) {
        System.out.println(" | 1 | " + funcionario.getNome() + " | " + funcionario.getCpf());
    }

    public void exibir() {
        exibir(this.lstFuncionario);
    }

    public void add(int idade, String nome, String cpf, String email, String cargo) {
        lstFuncionario.add(new Funcionario(idade, nome, cpf, email, cargo));
    }

    public void add(Funcionario funcionario) {
        lstFuncionario.add(funcionario);
    }

    public void add() {
        lstFuncionario.add(new Funcionario());
    }

    public int size() {
        return lstFuncionario.size();
    }

    public Funcionario last() {
        checkEmpty();
        return lstFuncionario.get(lstFuncionario.size() - 1);
    }

    public int lastIndex() {
        checkEmpty();
        return lstFuncionario.size() - 1;
    }

    public void lastExibir() {
        checkEmpty();

        System.out.println("Informações do funcionário cadastrado:  \n");
        last().exibir();
        Menu.waiter();
    }

    public ArrayList<Funcionario> buscaNome(String nome) {
        checkEmpty();
        nome = nome.toLowerCase().trim();

        ArrayList<Funcionario> list = new ArrayList<Funcionario>();

        for (Funcionario funcionario : lstFuncionario) {
            if (funcionario.getNome().toLowerCase().startsWith(nome))
                list.add(funcionario);
        }

        checkEmpty(list);
        return list;
    }

    public Funcionario buscaNomeObj(String nome) {
        checkEmpty();
        nome = nome.toLowerCase().trim();

        for (Funcionario funcionario : lstFuncionario) {
            if (funcionario.getNome().toLowerCase().startsWith(nome))
                return funcionario;

        }
        throw new IllegalArgumentException("Nome de funcionário não encontrado.");
    }

    public ArrayList<Funcionario> buscaNome() {
        checkEmpty();
        ArrayList<Funcionario> list = new ArrayList<Funcionario>();
        String nome;
        boolean validInput = true;

        do {
            validInput = true;
            System.out.print("\tNome do funcionário:  ");

            try {
                nome = Main.Menu.input().next();
                list = buscaNome(nome);
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido.\n");
                System.out.println("Deseja pesquisar novamente?");
                if (!Menu.getOptionBool())
                    throw new CancellationException("Busca de funcionários cancelada.");
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                validInput = false;
            }
        } while (!validInput);

        return list;
    }

    public Funcionario buscaCpf(String cpf) {
        cpf = Classes.CPF.trimCPF(cpf);
        
        for (Funcionario funcionario : lstFuncionario) {
            if (funcionario.getNome().replaceAll("\\D", "").equals(cpf))
                return funcionario;
        }
     
        throw new IllegalArgumentException("CPF de funcionário não encontrado.");
    }

    public Funcionario buscaCpf() {
        checkEmpty();
        String cpf;
        boolean validInput = true;

        do {
            validInput = true;
            System.out.print("\tCPF do funcionário:  ");

            try {
                cpf = Main.Menu.input().next();
                return buscaCpf(cpf);
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido.\n");
                System.out.println("Deseja pesquisar novamente?");
                if (!Menu.getOptionBool())
                    throw new CancellationException("Busca de funcionários cancelada.");
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                validInput = false;
            }
        } while (!validInput);

        throw new IllegalArgumentException("CPF de funcionário não encontrado.");
    }

    public ArrayList<Funcionario> busca() {
        checkEmpty();
        ArrayList<Funcionario> busca = new ArrayList<Funcionario>();

        System.out.println("Menu de busca de funcionário.\n\n");
        System.out.println("[0] Cancelar;");
        System.out.println("[1] Ver todos os funcionários;");
        System.out.println("[2] Consultar por nome;");
        System.out.println("[3] Consultar por CPF.");

        switch (Menu.getOption(3)) {
            case 0:
                throw new CancellationException("Operação de busca de funcionário cancelada pelo usuário.");
            case 1:
                busca = lstFuncionario;
                break;

            case 2:
                busca = buscaNome();
                break;

            case 3:
                busca.add(buscaCpf());
                break;

        }

        return busca;
    }
    
    public Funcionario buscaArray(ArrayList<Funcionario> busca) {
        checkEmpty(busca);

        if (busca.size() > 1)
            System.out.println("Funcionários encontrados:  \n");
        else
            System.out.println("Funcionário encontrado:  \n");

        exibir(busca);

        if (busca.size() == 1) {
            System.out.println("\nDeseja selecionar esse funcionário?");
            if (Menu.getOptionBool())
                return busca.get(0);
            else
                throw new CancellationException("Operação de seleção de um único funcionário cancelada pelo usuário.");
        }

        System.out.println("\nDeseja selecionar algum desses funcionários?");
        if (!Menu.getOptionBool())
            throw new CancellationException("Operação de seleção dentre múltiplos funcionários cancelada.");

        System.out.println("\nDigite o número do funcionário.");
        return busca.get(Menu.getOption(1, busca.size()) - 1);
    } 

    public void buscaMenu() {
        checkEmpty();
        
        try {
            buscaArray(busca()).exibir();;    
            Menu.waiter();
        } catch (CancellationException e) {
            return;
        }
    }
    


    public void remover(Funcionario funcionario) {
        checkEmpty();
        lstFuncionario.remove(funcionario);
    }

    public void remover(int index) {
        checkEmpty();
        lstFuncionario.remove(index);
    }

    public void remover() {
        checkEmpty();

        try {
            remover(buscaArray(busca()));
            System.out.println("Tem certeza que deseja remover o último funcionário adicionado?");
            Menu.waiter();
        } catch (CancellationException e) {
            return;
        }
    }

    public void removerLast() {
        checkEmpty();

        System.out.println("Tem certeza que deseja remover o último funcionário adicionado?");
        if (Menu.getOptionBool()) {
            remover(last());
            System.out.println("\nFuncionário removido com sucesso.\n");
            Menu.waiter();
        }
    }    

    public void alterar(Funcionario funcionario) {
        checkEmpty();

        Menu.voider();
        System.out.println("\n\nMenu de alteração de dados do funcionario.");
        System.out.println("Y para alterar e N para manter.\n");

        System.out.println("\nNome:  " + funcionario.getNome());
        if (Menu.getOptionBool())
            funcionario.setNome();

        System.out.println("\nIdade:  " + funcionario.getIdade());
        if (Menu.getOptionBool())
            funcionario.setIdade();

        System.out.println("\nCPF:  " + funcionario.getCpf());
        if (Menu.getOptionBool())
            funcionario.setCpf();

        System.out.println("\nEmail:  " + funcionario.getEmail());
        if (Menu.getOptionBool())
            funcionario.setEmail();

        System.out.println("\nTelefone:  " + funcionario.getCargo());
        if (Menu.getOptionBool())
            funcionario.setCargo();

        lstFuncionario.set(lastIndex(), funcionario);

        System.out.println("\n\nFuncionário alterado com sucesso!");
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
}