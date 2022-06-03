package Enums;

public enum Tema {
    TERROR(1, "terror"),
    DRAMA(2, "drama"),
    COMEDIA(3, "comédia"),
    INFANTIL(4, "infantil"),
    SUSPENSE(5, "suspense"),
    ACAO(6, "ação");

    
    // Construtor
    private Tema(int valor, String descricao) {
        setValor(valor);
        setDescricao(descricao);
    }

    // Atributos do Enum
    private int valor;
    private String descricao;


    // Getters e Setters
    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Tema setTema(String newTema) {
        for (Tema tema : Tema.values()) {
            if (tema.getDescricao().toLowerCase().equals(newTema.toLowerCase())) {
                return tema;
            }
        }

        return null;
    }
}
