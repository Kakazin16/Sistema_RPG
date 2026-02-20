package model;

import view.Menu;

public class Personagem {
    private final String nome;
    private final Classe classe;
    private final Status status;

    private int level;
    private int xp = 0;
    private int pontos = 0;
    private int vidaAtual;
    private int manaMax;
    private int manaAtual;
    private int ouro = 50;                    // ouro inicial
    private Arma armaEquipada;

    public Personagem(String nome, Classe classe) {
        this.nome = nome;
        this.classe = classe;
        this.status = new Status(classe.getAtributosIniciais());
        this.level = classe.getLevelInicial();
        this.vidaAtual = getVidaMaxima();
        this.manaMax = status.get(Atributo.MENTE) * 10;
        this.manaAtual = manaMax;
        this.armaEquipada = Arma.NENHUMA;
    }

    private Arma definirArmaInicial(Classe c) {
        return switch (c) {
            case GUERREIRO, PALADINO, HEROI -> Arma.ESPADA_LONGA;
            case ESPADACHIM -> Arma.KATANA;
            case BANDIDO, PRISIONEIRO -> Arma.ADAGA;
            case ERUDITO, OCULTISTA -> Arma.CAJADO;
            case ARAUTO -> Arma.SELO;
            default -> Arma.ESPADA_CURTA;
        };
    }

    public void ganharXp(int valor) {
        xp += valor;
        while (xp >= level * 100) {
            xp -= level * 100;
            level++;
            pontos += 3;
            Menu.mostrarMensagem("🎉 Subiu para o nível " + level + "!");
        }
    }

    public void evoluir(Atributo atributo) {
        status.aumentar(atributo);
        pontos--;

        if (atributo == Atributo.MENTE) {
            manaMax = status.get(Atributo.MENTE) * 10;
            manaAtual = Math.min(manaAtual, manaMax);
        }
    }

    public int getVidaMaxima() {
        return status.get(Atributo.VITALIDADE) * 10;
    }

    public void receberDano(int dano) {
        vidaAtual = Math.max(0, vidaAtual - dano);
    }

    public boolean estaVivo() {
        return vidaAtual > 0;
    }

    public int calcularDanoAtaque() {
        Arma arma = getArmaEquipada();

        int base = arma.getDanoBase();
        int atributo = status.get(arma.getScaling());

        int dano = base + atributo * 2;

        // crítico por destreza
        if (Math.random() < status.get(Atributo.DESTREZA) / 50.0) {
            Menu.mostrarMensagem("💥 CRÍTICO!");
            dano *= 2;
        }

        return dano;
    }

    public int calcularDefesa() {
        return status.get(Atributo.FORTITUDE) * 2;
    }

    public void regenerarManaTurno() {
        manaAtual = Math.min(manaMax, manaAtual + status.get(Atributo.MENTE) / 4);
    }

    public void gastarMana(int custo) {
        manaAtual = Math.max(0, manaAtual - custo);
    }

    public void restaurarManaCompleta() {
        manaAtual = manaMax;
    }

    public void restaurarAposBatalha() {
        int cura = getVidaMaxima() * 50 / 100; // 50%
        vidaAtual = Math.min(getVidaMaxima(), vidaAtual + cura);
        restaurarManaCompleta();
    }

    public void ganharOuro(int qtd) { ouro += qtd; }
    public void gastarOuro(int qtd) { ouro -= qtd; }
    public void equiparArma(Arma arma) { this.armaEquipada = arma; }



    // Getters
    public Arma getArmaEquipada() { return armaEquipada; }
    public int getOuro() { return ouro; }
    public int getLevel() { return level; }
    public int getXp() { return xp; }
    public Status getStatus() { return status; }
    public int getPontos() { return pontos; }
    public int getVidaAtual() { return vidaAtual; }
    public String getNome() { return nome; }
    public int getManaAtual() { return manaAtual; }
    public int getManaMax() { return manaMax; }
    public void curar(int valor) {
        vidaAtual = Math.min(getVidaMaxima(), vidaAtual + valor);
    }
}
