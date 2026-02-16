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

    public Personagem(String nome, Classe classe) {
        this.nome = nome;
        this.classe = classe;
        this.status = new Status(classe.getAtributosIniciais());
        this.level = classe.getLevelInicial();
        this.vidaAtual = getVidaMaxima();
        this.manaMax = status.get(Atributo.MENTE) * 10;
        this.manaAtual = manaMax;
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

    public int calcularDanoFisico() {
        int base = (int) (status.get(Atributo.FORCA) * 1.8 + status.get(Atributo.DESTREZA) * 0.9);
        if (Math.random() < status.get(Atributo.DESTREZA) / 40.0) {
            Menu.mostrarMensagem("CRÍTICO FÍSICO!");
            return base * 2;
        }
        return base;
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
        int cura = getVidaMaxima() * 100 / 100; // 100%
        vidaAtual = Math.min(getVidaMaxima(), vidaAtual + cura);
        restaurarManaCompleta();
    }



    // Getters
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
