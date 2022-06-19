package com.teatro.Enums;

public enum Cargos {
    DIRETOR(1, "Diretor"),
    REITOR(2, "Reitor"),
    SUPERVISOR(3, "Supervisor"),
    FAXINEIRO(4, "Faxineiro"),
    SEGURANCA(5, "Segurança"),
    BALCONISTA(6, "Balconista"),
    BILHETEIRO(7, "Bilheteiro"),
    ASSISTENTE(8, "Assistente"),
    FIGURINISTA(9, "Figurinista");

    private int valor;
    private String descricao;
    
    private Cargos(int valor, String descricao) {
        setValor(valor);
        setDescricao(descricao);
    }

    public static Cargos setCargo(String descricao) {
        descricao = descricao.trim().toLowerCase();
        for (Cargos cargo : Cargos.values()) {
            if (cargo.getDescricao().toLowerCase().equals(descricao)) {
                return cargo;
            }
        }
        throw new IllegalArgumentException("Esse cargo não existe.");
    }

    public static String getCargos() {
        String sCargo= " | "; 
        for (Cargos cargo : Cargos.values())
            sCargo = sCargo + " " + cargo.getDescricao() + " |";
        return sCargo.trim();
    }


    public int getValor() {
        return this.valor;
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

}
