package model;

public enum Arma {
    NENHUMA("Nenhuma", 0, 0, Atributo.FORCA),

    // Físicas
    ADAGA("Adaga", 18, 40, Atributo.DESTREZA),
    ESPADA_CURTA("Espada Curta", 25, 65, Atributo.FORCA),
    ESPADA_LONGA("Espada Longa", 32, 95, Atributo.FORCA),
    ESPADA_COLOSSAL("Espada Colossal", 55, 280, Atributo.FORCA),
    KATANA("Katana", 29, 130, Atributo.DESTREZA),
    MACHADO("Machado", 30, 85, Atributo.FORCA),
    MACHADO_GRANDE("Machado Grande", 48, 195, Atributo.FORCA),
    MARTELO("Martelo", 35, 110, Atributo.FORCA),
    LANCA("Lança", 27, 75, Atributo.DESTREZA),
    ALABARDA("Alabarda", 42, 170, Atributo.FORCA),
    CHICOTE("Chicote", 23, 100, Atributo.DESTREZA),
    GARRAS("Garras", 24, 115, Atributo.DESTREZA),
    ARCO("Arco", 21, 90, Atributo.DESTREZA),
    CROSSBOW("Crossbow", 29, 140, Atributo.DESTREZA),

    // Mágicas
    CAJADO("Cajado Arcano", 16, 160, Atributo.INTELIGENCIA),
    SELO("Selo Sagrado", 14, 155, Atributo.FE);

    private final String nome;
    private final int danoBase;
    private final int preco;
    private final Atributo scaling;

    Arma(String nome, int danoBase, int preco, Atributo scaling) {
        this.nome = nome;
        this.danoBase = danoBase;
        this.preco = preco;
        this.scaling = scaling;
    }

    public String getNome() { return nome; }
    public int getDanoBase() { return danoBase; }
    public int getPreco() { return preco; }
    public Atributo getScaling() { return scaling; }
}
