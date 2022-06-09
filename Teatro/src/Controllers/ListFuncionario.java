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

    public boolean isNotEmpty() {
        return lstFuncionario.size() > 0;
    }

    public void checkEmpty() {
        if (!isNotEmpty())
            throw new ArrayIndexOutOfBoundsException("Não há funcionários cadastrados no sistema.");
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

        System.out.println("Informações do cliente cadastrado:  \n");
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

        if (list.size() <= 0)
            throw new IllegalArgumentException("Nome de funcionário não encontrado.");
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
                nome = Main.Menu.input().nextLine();
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
                cpf = Main.Menu.input().nextLine();
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

    public void buscaMenu() {
        checkEmpty();
        ArrayList<Funcionario> busca = new ArrayList<Funcionario>();

        System.out.println("Menu de busca de funcionário.\n\n");
        System.out.println("[0] Cancelar;");
        System.out.println("[1] Nome;");
        System.out.println("[2] CPF.");

        switch (Menu.getOption(2)) {
            case 0:
                return;
            case 1:
                try {
                    busca = buscaNome();
                } catch (CancellationException e) {
                    return;
                }
                break;
            case 2:
                try {
                    busca.add(buscaCpf());
                } catch (CancellationException e) {
                    return;
                }
                break;
        }

        Menu.voider();

        if (busca.size() > 1)
            System.out.println("Funcionários encontrados:  \n");
        else
            System.out.println("Funcionário encontrado:  \n");

        exibir(busca);

        if (busca.size() == 1) {
            System.out.println("\nDeseja consultar as informações desse funcionário?");
            if (Menu.getOptionBool()) {
                busca.get(0).exibir();
                Menu.waiter();
            }
            return;
        }

        System.out.println("\nDeseja consultar as informações de algum desses funcionários?");
        if (!Menu.getOptionBool())
            return;

        System.out.println("\nDigite o número do funcionário.");
        busca.get(Menu.getOption(1, busca.size() - 1)).exibir();
        Menu.waiter();
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
        ArrayList<Funcionario> busca = new ArrayList<Funcionario>();

        System.out.println("Menu de busca de funcionário.\n\n");
        System.out.println("[0] Cancelar;");
        System.out.println("[1] Nome;");
        System.out.println("[2] CPF.");

        switch (Menu.getOption(2)) {
            case 0:
                return;
            case 1:
                try {
                    busca = buscaNome();
                } catch (CancellationException e) {
                    return;
                }
                break;
            case 2:
                try {
                    busca.add(buscaCpf());
                } catch (CancellationException e) {
                    return;
                }
                break;
        }

        Menu.voider();

        if (busca.size() > 1)
            System.out.println("Funcionários encontrados:  \n");
        else
            System.out.println("Funcionário encontrado:  \n");

        exibir(busca);

        if (busca.size() == 1) {
            System.out.println("\nDeseja remover esse funcionário?");
            if (Menu.getOptionBool()) {
                remover(busca.get(0));;
                Menu.waiter();
            }
            return;
        }

        System.out.println("\nDeseja remover algum desses funcionários?");
        if (!Menu.getOptionBool())
            return;

        System.out.println("\nDigite o número do funcionário.");
        remover(busca.get(Menu.getOption(1, busca.size() - 1)));
        Menu.waiter();
    }

    

    public void alterar() {
        checkEmpty();
        Funcionario funcionario = last();

        Menu.voider();
        System.out.println("Menu de alteração de dados do funcionario.");
        System.out.println("Y para alterar e N para manter.\n");
        
        System.out.println("Nome:  " + funcionario.getNome());
        if (Menu.getOptionBool())
            funcionario.setNome();
        
        System.out.println("Idade:  " + funcionario.getIdade());
        if (Menu.getOptionBool())
            funcionario.setIdade();
        
        System.out.println("CPF:  " + funcionario.getCpf());
        if (Menu.getOptionBool())
            funcionario.setCpf();

        System.out.println("Email:  " + funcionario.getEmail());
        if (Menu.getOptionBool())
            funcionario.setEmail();

        System.out.println("Telefone:  " + funcionario.getCargo());
        if (Menu.getOptionBool())
            funcionario.setCargo();

        lstFuncionario.set(lastIndex(), funcionario);

        System.out.println("\nFuncionário alterado com sucesso!");
        Menu.waiter();
    }
}
