import java.util.Scanner;

import Enums.Tema;

public class App {
    public static void main(String[] args) throws Exception {
        Tema novoTema = Tema.COMEDIA;
        novoTema = Tema.ACAO;

        novoTema.setTema("ação");
    }
}
