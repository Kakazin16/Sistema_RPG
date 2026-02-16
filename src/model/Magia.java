package model;

public enum Magia {
    CHAMA_SAGRADA("Chama Sagrada", 12, 28, Atributo.FE, "Dano de fogo"),
    RAIO_DIVINO("Raio Divino", 16, 42, Atributo.FE, "Dano de raio"),
    CURA_DIVINA("Cura Divina", 10, 0, Atributo.FE, "Restaura vida"),
    BEAM_AZUL("Beam Azul", 9, 22, Atributo.INTELIGENCIA, "Projétil mágico"),
    LAMINA_MAGICA("Lâmina Mágica", 13, 0, Atributo.INTELIGENCIA, "Buff no próximo ataque"),
    VENENO("Nuvem Venenosa", 11, 0, Atributo.ARCANO, "Veneno por 3 turnos"),
    CONGELAMENTO("Congelamento Arcano", 14, 0, Atributo.ARCANO, "Reduz dano do inimigo");

    private final String nome;
    private final int custoMana;
    private final int baseDano;
    private final Atributo atributo;
    private final String descricao;

    Magia(String nome, int custo, int dano, Atributo atributo, String descricao) {
        this.nome = nome;
        this.custoMana = custo;
        this.baseDano = dano;
        this.atributo = atributo;
        this.descricao = descricao;
    }

    public String getNome() { return nome; }
    public int getCustoMana() { return custoMana; }
    public int getBaseDano() { return baseDano; }
    public Atributo getAtributo() { return atributo; }
    public String getDescricao() { return descricao; }
}