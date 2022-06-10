package Classes;
import java.util.regex.Pattern;

public class CPF {
    // Atributos da classe
    private String CPF;
    private static Pattern checkCPF = Pattern.compile("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");


    public void set(String cpf) {
        cpf = formatCPF(cpf);

        if (verificaCPF(cpf))
            this.CPF = formatCPF(cpf);
        else 
            throw new IllegalArgumentException("O CPF inserido é inválido.");
    }

    public String get() {
        return this.CPF;
    }

    public String getRaw() {
        return trimCPF(this.CPF);
    }

    public static String trimCPF(String cpf) {
        cpf = cpf.trim().replaceAll("\\D", "");

        if (cpf.length() != 11)
            throw new IllegalArgumentException("O CPF deve possuir exatamente 11 dígitos.");

        return cpf;
    }

    public static boolean isFormatted(String cpf) {
        return checkCPF.matcher(cpf).matches();
    }

    public static String formatCPF(String cpf) {
        cpf = trimCPF(cpf);

        String cpfFormatted;
        cpfFormatted = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);

        if (!isFormatted(cpfFormatted))
            throw new IllegalArgumentException("O CPF não foi formatado corretamente.");

        return cpfFormatted;
    }
    
    public static boolean verificaCPF(String cpf) {
        
        
        cpf = trimCPF(cpf);
        int verificador = Integer.parseInt(cpf.charAt(9)+ "" + cpf.charAt(10));

        int SomaDig = 0;
        int digito;

        int peso = 10;
        for (int i = 0; i < 9; i++) {
            SomaDig += Integer.parseInt("" + cpf.charAt(i)) * peso;
            peso--;
        }

        if (SomaDig % 11 >= 2) {
            digito = 11 - SomaDig % 11;
        } else {
            digito = 0;
        }

        SomaDig = 0;
        peso = 11;

        for (int i = 0; i < 10; i++) {
            SomaDig += Integer.parseInt("" + cpf.charAt(i)) * peso;
            peso--;
        }

        if (SomaDig % 11 >= 2) {
            digito = Integer.parseInt(Integer.toString(digito) + Integer.toString(11 - SomaDig % 11));
        } else {
            digito *= 10;
        }

        return digito == verificador;
    }
}
