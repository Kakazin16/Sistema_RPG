package model;

import view.Menu;

public class Inimigo {
    private final String nome;
    private int vida;
    private final int danoBase;
    private int venenoPorTurno = 0;
    private int venenoTurnosRestantes = 0;
    private int congeladoTurnos = 0;

    public Inimigo(String nome, int vida, int dano) {
        this.nome = nome;
        this.vida = vida;
        this.danoBase = dano;
    }

    public void receberDano(int valor) {
        vida -= valor;
    }

    public void aplicarVeneno(int danoPorTurno, int turnos) {
        venenoPorTurno = Math.max(venenoPorTurno, danoPorTurno);
        venenoTurnosRestantes = Math.max(venenoTurnosRestantes, turnos);
    }

    public void aplicarCongelamento(int turnos) {
        congeladoTurnos = Math.max(congeladoTurnos, turnos);
    }

    public void tickEfeitos() {
        if (venenoTurnosRestantes > 0) {
            vida -= venenoPorTurno;
            venenoTurnosRestantes--;
            Menu.mostrarMensagem("☠️ Veneno causa " + venenoPorTurno + " de dano!");
        }
        if (congeladoTurnos > 0) {
            congeladoTurnos--;
        }
    }

    public int atacar() {
        int dano = danoBase;
        if (congeladoTurnos > 0) dano = (int) (dano * 0.6);
        return dano;
    }

    public boolean estaVivo() { return vida > 0; }
    public String getNome() { return nome; }
    public int getVida() { return vida; }
}