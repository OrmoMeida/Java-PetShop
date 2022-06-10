package Enums;

public enum Genero {
    TERROR(1, "Terror"),
    DRAMA(2, "Drama"),
    COMEDIA(3, "Comédia"),
    INFANTIL(4, "Infantil"),
    SUSPENSE(5, "Suspense"),
    ACAO(6, "Ação");

    
    // Construtor
    private Genero(int valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    // Atributos do Enum
    private int valor;
    private String descricao;


    // Getters e Setters
    public int getValor() {
        return valor;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public static Genero setGenero(String descricao) {
        descricao = descricao.trim().toLowerCase();

        for (Genero tema : Genero.values()) {
            if (tema.getDescricao().toLowerCase().equals(descricao)) {
                return tema;
            }
        }
        throw new IllegalArgumentException("Gênero não encontrado.");
    }

    public static String getGenero() {
        String generos = new String(""); 
        for (Genero genero : Genero.values()) {
            generos.concat(genero.descricao + " | ");
        }

        return " | " + generos;
    }
}
