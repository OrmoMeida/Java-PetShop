package Classes;
import Abstract.Pessoa;
import Interfaces.IPessoa;

public class Cliente extends Pessoa implements IPessoa {
    private int codCliente;
    private static int qntClientes = 0;


    public Cliente() {
        codCliente = ++qntClientes;
    }

    @Override
    public void exibir() {
        // TODO Auto-generated method stub
        
    }

    public int getCodCliente() {
        return codCliente;
    }
}
