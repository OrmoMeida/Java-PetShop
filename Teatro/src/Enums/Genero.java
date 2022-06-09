package Enums;

public enum Genero {
    TERROR(1, "terror"),
    DRAMA(2, "drama"),
    COMEDIA(3, "comédia"),
    INFANTIL(4, "infantil"),
    SUSPENSE(5, "suspense"),
    ACAO(6, "ação");

    
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
}
