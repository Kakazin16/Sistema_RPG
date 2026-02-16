package model;

import java.util.EnumMap;
import java.util.Map;

public enum Classe {
    GUERREIRO(Map.of(
            Atributo.VITALIDADE, 14, Atributo.MENTE, 12, Atributo.FORTITUDE, 13,
            Atributo.FORCA, 14, Atributo.DESTREZA, 12, Atributo.INTELIGENCIA, 10,
            Atributo.FE, 9, Atributo.ARCANO, 10), 1),

    PALADINO(Map.of(
            Atributo.VITALIDADE, 14, Atributo.MENTE, 12, Atributo.FORTITUDE, 14,
            Atributo.FORCA, 13, Atributo.DESTREZA, 13, Atributo.INTELIGENCIA, 10,
            Atributo.FE, 10, Atributo.ARCANO, 8), 1),

    BANDIDO(Map.of(
            Atributo.VITALIDADE, 12, Atributo.MENTE, 14, Atributo.FORTITUDE, 11,
            Atributo.FORCA, 11, Atributo.DESTREZA, 14, Atributo.INTELIGENCIA, 8,
            Atributo.FE, 8, Atributo.ARCANO, 12), 1),

    ESPADACHIM(Map.of(
            Atributo.VITALIDADE, 13, Atributo.MENTE, 13, Atributo.FORTITUDE, 12,
            Atributo.FORCA, 12, Atributo.DESTREZA, 16, Atributo.INTELIGENCIA, 9,
            Atributo.FE, 9, Atributo.ARCANO, 10), 1),

    PRISIONEIRO(Map.of(
            Atributo.VITALIDADE, 11, Atributo.MENTE, 14, Atributo.FORTITUDE, 11,
            Atributo.FORCA, 11, Atributo.DESTREZA, 11, Atributo.INTELIGENCIA, 10,
            Atributo.FE, 12, Atributo.ARCANO, 9), 1),

    HEROI(Map.of(
            Atributo.VITALIDADE, 14, Atributo.MENTE, 11, Atributo.FORTITUDE, 15,
            Atributo.FORCA, 15, Atributo.DESTREZA, 11, Atributo.INTELIGENCIA, 11,
            Atributo.FE, 11, Atributo.ARCANO, 8), 1),

    ERUDITO(Map.of(  // Heurudito → Erudito (tava feio demais)
            Atributo.VITALIDADE, 12, Atributo.MENTE, 15, Atributo.FORTITUDE, 11,
            Atributo.FORCA, 11, Atributo.DESTREZA, 12, Atributo.INTELIGENCIA, 15,
            Atributo.FE, 9, Atributo.ARCANO, 8), 1),

    ARAUTO(Map.of(
            Atributo.VITALIDADE, 12, Atributo.MENTE, 15, Atributo.FORTITUDE, 11,
            Atributo.FORCA, 11, Atributo.DESTREZA, 12, Atributo.INTELIGENCIA, 10,
            Atributo.FE, 15, Atributo.ARCANO, 7), 1),

    OCULTISTA(Map.of(
            Atributo.VITALIDADE, 12, Atributo.MENTE, 12, Atributo.FORTITUDE, 12,
            Atributo.FORCA, 11, Atributo.DESTREZA, 12, Atributo.INTELIGENCIA, 9,
            Atributo.FE, 11, Atributo.ARCANO, 15), 1),

    MISERAVEL(Map.of(
            Atributo.VITALIDADE, 10, Atributo.MENTE, 10, Atributo.FORTITUDE, 10,
            Atributo.FORCA, 10, Atributo.DESTREZA, 10, Atributo.INTELIGENCIA, 10,
            Atributo.FE, 10, Atributo.ARCANO, 10), 1);

    private final Map<Atributo, Integer> atributosIniciais;
    private final int levelInicial;

    Classe(Map<Atributo, Integer> atributosIniciais, int levelInicial) {
        this.atributosIniciais = new EnumMap<>(atributosIniciais);
        this.levelInicial = levelInicial;
    }

    public Map<Atributo, Integer> getAtributosIniciais() {
        return new EnumMap<>(atributosIniciais);
    }

    public int getLevelInicial() {
        return levelInicial;
    }
}