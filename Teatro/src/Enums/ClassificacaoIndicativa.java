package Enums;

public enum ClassificacaoIndicativa {
    LIVRE(1, "Livre"),
    MIN10(2, "+10"),
    MIN12(3, "+12"),
    MIN16(4, "+16"),
    MIN18(5, "+18"),
    MIN20(5, "+20");

    private int value;
    private String descricao;

    public static ClassificacaoIndicativa setClassificacao(String descricao) {
        descricao = descricao.trim().toLowerCase();

        for (ClassificacaoIndicativa cIndicativa : ClassificacaoIndicativa.values()) {
            if (cIndicativa.getDescricao().toLowerCase().equals(descricao)) {
                return cIndicativa;
            }
        }
        throw new IllegalArgumentException("Classificação Indicativa não encontrada.");
    }

    ClassificacaoIndicativa(int value, String descricao) {
        this.value = value;
        this.descricao = descricao;
    }

    public static String getClassificacoes() {
        String classificacoes = new String(); 
        for (ClassificacaoIndicativa cIndicativa : ClassificacaoIndicativa.values()) {
            classificacoes.concat(cIndicativa.descricao + " | ");
        }

        return " | " + classificacoes;
    }
    
    public int getValue() {return value;}
    public String getDescricao() {return descricao;}
}
