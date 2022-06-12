package Controllers;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.concurrent.CancellationException;

import Classes.CPF;
import Classes.Funcionario;
import Enums.Cargos;
import Main.Menu;

public class ListFuncionario {   
    private ArrayList<Funcionario> lstFuncionario;
    private int lastAlterado;
    
    public ListFuncionario() {
        lstFuncionario = new ArrayList<Funcionario>();
    }

    public void geraFuncionarios() {
        add(28, "Diluc", "57191011017", "diluctavern@gmail.com", "Reitor");
        add(26, "Kaeya", "kjgwssag97534479061", "kaeyamito@gmail.com", "Balconista");
        add(25, "Rosaria", "80458987042", "notrosaria@hotmail.com", "Faxineiro");
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

    public void exibirLastAlterado() {
        checkEmpty();

        System.out.println("Informações do cliente alterado:  \n");
        lstFuncionario.get(lastAlterado).exibir();

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
            throw new IllegalArgumentException("Nenhum funcionário com este nome foi encontrado.");
        return list;
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
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                System.out.println("\nDeseja pesquisar novamente?");
                if (!Menu.getOptionBool())
                    throw new CancellationException("Busca de funcionários por nome cancelada.");
                validInput = false;
            }
        } while (!validInput);

        return list;
    }

    public Funcionario buscaCpf(String cpf) {
        cpf = CPF.trimCPF(cpf);
        
        for (Funcionario funcionario : lstFuncionario) {
            if (funcionario.getCpf().replaceAll("\\D", "").equals(cpf))
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
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                System.out.println("Deseja pesquisar novamente?");
                if (!Menu.getOptionBool())
                    throw new CancellationException("Busca de funcionários cancelada.");
                validInput = false;
            }
        } while (!validInput);

        throw new IllegalArgumentException("CPF de funcionário não encontrado.");
    }

    public ArrayList<Funcionario> buscaCargo(String cargo) {
        cargo = cargo.trim().toLowerCase();
        ArrayList<Funcionario> busca = new ArrayList<Funcionario>();
        
        for (Funcionario funcionario : lstFuncionario) {
            if (funcionario.getCargo().trim().toLowerCase().startsWith(cargo))
                busca.add(funcionario);
        }
     
        if (busca.size() <= 0)
            throw new IllegalArgumentException("Nenhum funcionário com este cargo foi encontrado.");
        return busca;
    }

    public ArrayList<Funcionario> buscaCargo() {
        checkEmpty();
        ArrayList<Funcionario> list = new ArrayList<Funcionario>();
        String cargo;
        boolean validInput = true;

        do {
            validInput = true;
            System.out.print("\tCargo do funcionário:  ");

            try {
                cargo = Main.Menu.input().next();
                list = buscaCargo(cargo);
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido.\n");
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                System.out.println("Cargos disponíveis:");
                System.out.println(Cargos.getCargos() + "\n");
                System.out.println("\nDeseja pesquisar novamente?");
                if (!Menu.getOptionBool())
                    throw new CancellationException("Busca de funcionários por cargo cancelada.");
                validInput = false;
            }
        } while (!validInput);

        return list;
    }

    public ArrayList<Funcionario> busca() {
        checkEmpty();
        ArrayList<Funcionario> busca = new ArrayList<Funcionario>();

        System.out.println("Menu de busca de funcionário.\n\n");
        System.out.println("[0] Cancelar;");
        System.out.println("[1] Ver todos os funcionários;");
        System.out.println("[2] Consultar por nome;");
        System.out.println("[3] Consultar por CPF;");
        System.out.println("[4] Consultar por cargo.");

        switch (Menu.getOption(4)) {
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

            case 4:
                busca = buscaCargo();
                break;

        }

        if (busca.size() <= 0)
            throw new IllegalArgumentException("Nenhum funcionário foi encontrado na busca.");
        return busca;
    }
    
    public Funcionario buscaArray(ArrayList<Funcionario> busca) {
        if (busca.size() <= 0)
            throw new IllegalArgumentException("Não há funcionários para pesquisar.");

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
        Funcionario funcionario;

        try {
            funcionario = buscaArray(busca());
            System.out.println("Tem certeza que deseja remover o funcionário selecionado?");
            if (Menu.getOptionBool()) {
                remover(funcionario);
                System.out.println("\nFuncionário removido com sucesso.\n");
                Menu.waiter();
            } else {
                throw new CancellationException("Operação de remoção de funcionário via busca cancelada pelo usuário.");
            }
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
        lastAlterado = lstFuncionario.indexOf(funcionario);

        if (lastAlterado == -1)
            throw new IllegalArgumentException("Funcionário não encontrado: Impossível alterar.");

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

        System.out.println("\nCargo:  " + funcionario.getCargo());
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

    public void alterarNovamente() {
        checkEmpty();

        System.out.println("\nMenu de nova alteração\n\n");
        
        try {
            alterar(lstFuncionario.get(lastAlterado));
        } catch (CancellationException e) {
            return;
        }
    }
}
