package controller;

import model.*;
import view.Menu;

import java.util.Random;

public class JogoController {
    private Personagem personagem;
    private final Random random = new Random();
    private boolean jogando = true;

    public void iniciar() {
        criarPersonagem();
        loopJogo();
        encerrarJogo();
    }

    private void criarPersonagem() {
        String nome = Menu.lerNome();
        if (nome.isEmpty()) nome = "Sem Nome";

        Classe classe = Menu.escolherClasse();
        personagem = new Personagem(nome, classe);

        Menu.mostrarMensagem("\nPersonagem criado com sucesso!");
        Menu.mostrarMensagem("Classe: " + classe);
        Menu.mostrarMensagem("Nível: " + personagem.getLevel());
        Menu.mostrarMensagem(personagem.getStatus().toString());
    }

    private void loopJogo() {
        while (jogando) {
            int opcao = Menu.menuJogo();

            switch (opcao) {
                case 1 -> treinar();
                case 2 -> lutar();
                case 3 -> distribuirPontos();
                case 4 -> visitarLoja();
                case 0 -> jogando = false;
                default -> Menu.mostrarMensagem("Opção inválida.");
            }

            if (jogando) {
                Menu.mostrarMensagem("\n" + personagem.getStatus().toString());
                Menu.mostrarMensagem("Arma: " + personagem.getArmaEquipada().getNome() +
                        " | Ouro: " + personagem.getOuro());
                Menu.mostrarMensagem("Nível: " + personagem.getLevel() + " | XP: " + personagem.getXp() + "/" + (personagem.getLevel() * 100));
            }
        }
    }

    private void visitarLoja() {
        Menu.mostrarMensagem("\n=== LOJA DO VELHO MERCADOR ===");
        Menu.mostrarMensagem("Seu ouro: " + personagem.getOuro() + "\n");

        Arma[] armas = Arma.values();
        for (int i = 1; i < armas.length; i++) {  // pula NENHUMA
            Arma a = armas[i];
            System.out.printf("%d - %s | Dano base: %d | Preço: %d ouro\n",
                    i, a.getNome(), a.getDanoBase(), a.getPreco());
        }

        System.out.print("\nEscolha o número da arma (0 para sair): ");
        int escolha = Menu.lerIntValido(0, armas.length - 1);  // reuse o método

        if (escolha == 0) return;

        Arma escolhida = armas[escolha];
        if (escolhida == Arma.NENHUMA) return;
        if (personagem.getOuro() >= escolhida.getPreco()) {
            personagem.gastarOuro(escolhida.getPreco());
            personagem.equiparArma(escolhida);
            Menu.mostrarMensagem("✅ " + escolhida.getNome() + " equipada com sucesso!");
        } else {
            Menu.mostrarMensagem("❌ Ouro insuficiente.");
        }
    }

    private void treinar() {
        Menu.mostrarMensagem("Você treinou duro por horas...");
        personagem.ganharXp(50);
    }

    private void lutar() {
        Menu.mostrarMensagem("\nUm inimigo apareceu!");

        String[] nomes = {"Goblin", "Lobo Raivoso", "Bandido", "Esqueleto", "Orc", "Slime Gigante", "Cavaleiro", "Mercenário"};
        Inimigo inimigo = new Inimigo(nomes[random.nextInt(nomes.length)], 80 + random.nextInt(60), 12 + random.nextInt(10));

        Menu.mostrarMensagem("Você encontrou um " + inimigo.getNome() + " (Vida: " + inimigo.getVida() + ")");

        int proximoDanoExtra = 0;
        boolean defendendo = false;

        while (personagem.estaVivo() && inimigo.estaVivo()) {
            Menu.mostrarMensagem("\nVida: " + personagem.getVidaAtual() + "/" + personagem.getVidaMaxima() +
                    " | Mana: " + personagem.getManaAtual() + "/" + personagem.getManaMax() +
                    " | Arma: " + personagem.getArmaEquipada().getNome());

            int escolha = Menu.menuCombate();

            switch (escolha) {
                case 1 -> { // ATACAR
                    int dano = personagem.calcularDanoAtaque() + proximoDanoExtra;
                    inimigo.receberDano(dano);
                    Menu.mostrarMensagem("Você acertou com " + personagem.getArmaEquipada().getNome() +
                            " por " + dano + " de dano!");
                    proximoDanoExtra = 0;
                }
                case 2 -> { // DEFENDER
                    defendendo = true;
                    Menu.mostrarMensagem("🛡️ Você se defendeu!");
                }
                case 3 -> { // MAGIA
                    int magiaEscolha = Menu.escolherMagia();
                    Magia magia = Magia.values()[magiaEscolha - 1];

                    if (personagem.getManaAtual() < magia.getCustoMana()) {
                        Menu.mostrarMensagem("Mana insuficiente!");
                        continue;
                    }

                    int buffGerado = executarMagia(magia, inimigo);
                    if (buffGerado > 0) {
                        proximoDanoExtra = buffGerado;
                    }
                }
                case 4 -> { // FUGIR
                    if (Math.random() < 0.55) {
                        Menu.mostrarMensagem("🏃 Fugiu com sucesso!");
                        return;
                    } else {
                        Menu.mostrarMensagem("Não conseguiu fugir!");
                    }
                }
            }

            personagem.regenerarManaTurno(); // regenera todo turno

            if (!inimigo.estaVivo()) break;

            // === TURNO DO INIMIGO ===
            inimigo.tickEfeitos();
            if (!inimigo.estaVivo()) break;
            int danoInimigo = inimigo.atacar();
            if (defendendo) {
                danoInimigo = Math.max(1, danoInimigo - personagem.calcularDefesa());
                defendendo = false;
            }
            personagem.receberDano(danoInimigo);
            Menu.mostrarMensagem("O " + inimigo.getNome() + " te acertou por " + danoInimigo + "!");
        }

        if (personagem.estaVivo()) {
            Menu.mostrarMensagem("✅ VITÓRIA! +150 XP");
            personagem.ganharOuro(70 + random.nextInt(60));
            personagem.ganharXp(150);
            personagem.restaurarAposBatalha();
        } else {
            Menu.mostrarMensagem("💀 Derrotado...");
            jogando = false;
        }
    }

    private int executarMagia(Magia magia, Inimigo inimigo) {
        int poder = personagem.getStatus().get(magia.getAtributo()) * 1;
        int dano = magia.getBaseDano() + poder;

        switch (magia) {
            case CHAMA_SAGRADA, RAIO_DIVINO, BEAM_AZUL -> {
                inimigo.receberDano(dano);
                Menu.mostrarMensagem("✨ " + magia.getNome() + " causa " + dano + " de dano!");
            }
            case CURA_DIVINA -> {
                int cura = 35 + poder;
                personagem.curar(cura);
                Menu.mostrarMensagem("✨ Cura restaurou " + cura + " de vida!");
            }
            case LAMINA_MAGICA -> {
                int buff = 20 + poder;
                Menu.mostrarMensagem("✨ Lâmina Mágica ativada! Próximo ataque físico ganha +" + buff);
                return buff;   // ← retorna o buff pro próximo ataque
            }
            case VENENO -> {
                inimigo.aplicarVeneno(12 + poder / 2, 3);
                Menu.mostrarMensagem("☠️ Veneno aplicado por 3 turnos!");
            }
            case CONGELAMENTO -> {
                inimigo.aplicarCongelamento(2);
                Menu.mostrarMensagem("❄️ Inimigo congelado por 2 turnos!");
            }
        }
        personagem.gastarMana(magia.getCustoMana());
        return 0;
    }

    private void distribuirPontos() {
        if (personagem.getPontos() <= 0) {
            Menu.mostrarMensagem("Você não tem pontos para distribuir. Suba de nível primeiro!");
            return;
        }

        while (personagem.getPontos() > 0) {
            Menu.mostrarMensagem("\nPontos disponíveis: " + personagem.getPontos());
            Menu.mostrarMensagem(personagem.getStatus().toString());

            Atributo atributo = Menu.escolherAtributo();
            personagem.evoluir(atributo);
            Menu.mostrarMensagem("+" + atributo + " aumentado!");
        }

        Menu.mostrarMensagem("Todos os pontos foram distribuídos!");
    }

    private void encerrarJogo() {
        Menu.mostrarMensagem("\n=== JOGO ENCERRADO ===");
        Menu.mostrarMensagem("Build final de " + personagem.getNome() + ":");
        Menu.mostrarMensagem(personagem.getStatus().toString());
        Menu.mostrarMensagem("Nível final: " + personagem.getLevel());
    }
}