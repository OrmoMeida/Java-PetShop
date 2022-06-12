package Classes;

import java.util.InputMismatchException;

import Abstract.Pessoa;
import Controllers.Scanner2;
import Enums.Cargos;
import Interfaces.IPessoa;

public class Funcionario extends Pessoa implements IPessoa {
    private static int qntFunc = 0;
    private int codFunc;
    private Cargos cargo;


    // Construtores
    public Funcionario() {
        super();
        setCargo();
        this.codFunc = ++qntFunc;
    }

    public Funcionario(int idade, String nome, String cpf, String email, String cargo) {
        super(idade, nome, cpf, email);
        setCargo(cargo);
    }


    // Getters e setters
    public int getCodFunc() {
        return this.codFunc;
    }

    public static int getQntFunc() {
        return qntFunc;
    }

    public String getCargo() {
        return this.cargo.getDescricao();
    }

    public void setCargo(String cargo) {
        this.cargo = Cargos.setCargo(cargo);
    }

    @Override
    public void setIdade(int idade) {
        if (idade < 16)
            throw new IllegalArgumentException("O funcionário deve ter ao menos 16 anos.");

        super.setIdade(idade);
    }

    public void setCargo() {
        String cargo;
        boolean validInput = true;
    
        do {
            validInput = true;
            System.out.print("\tCargo:  ");
    
            try {
                cargo = Scanner2.scanner.next();
                setCargo(cargo);
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido.\n");
                validInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
                System.out.println("Cargos disponíveis:  ");
                System.out.println(Cargos.getCargos() + "\n");
                validInput = false;
            }
        } while (!validInput);
    }
    
    @Override
    public void exibir() {
        System.out.println("\nFuncionário nº " + this.codFunc);
        System.out.println("Nome do funcionário  :  " + this.getNome());
        System.out.println("Idade do funcionário :  " + this.getIdade());
        System.out.println("Cargo do funcionário :  " + this.getCargo());
        System.out.println("CPF do funcionário   :  " + this.getCpf());
        System.out.println("Email do funcionário :  " + this.getEmail());
    }
}
