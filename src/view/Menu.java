package view;

import model.Atributo;
import model.Classe;
import model.Magia;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private static final Scanner sc = new Scanner(System.in);

    public static String lerNome() {
        System.out.print("Digite o nome do personagem: ");
        String nome = sc.nextLine().trim();
        return nome.isEmpty() ? "Sem Nome" : nome;
    }

    public static Classe escolherClasse() {
        System.out.println("\nEscolha sua classe:");
        Classe[] classes = Classe.values();
        for (int i = 0; i < classes.length; i++) {
            System.out.println((i + 1) + " - " + classes[i].name());
        }
        int escolha = lerIntValido(1, classes.length);
        return classes[escolha - 1];
    }

    public static Atributo escolherAtributo() {
        System.out.println("\nEscolha o atributo para aumentar:");
        Atributo[] atributos = Atributo.values();
        for (int i = 0; i < atributos.length; i++) {
            System.out.println((i + 1) + " - " + atributos[i].name());
        }
        int escolha = lerIntValido(1, atributos.length);
        return atributos[escolha - 1];
    }

    public static int menuJogo() {
        System.out.println("\n=== O QUE DESEJA FAZER? ===");
        System.out.println("1 - Treinar (+50 XP)");
        System.out.println("2 - Lutar");
        System.out.println("3 - Distribuir pontos");
        System.out.println("4 - Visitar Loja");
        System.out.println("0 - Sair");
        return lerIntValido(0, 4);
    }

    public static int lerIntValido(int min, int max) {
        while (true) {
            try {
                int valor = sc.nextInt();
                sc.nextLine(); // consome o newline
                if (valor >= min && valor <= max) {
                    return valor;
                }
                System.out.printf("Opção inválida! Digite um número entre %d e %d: ", min, max);
            } catch (InputMismatchException e) {
                sc.nextLine(); // limpa o buffer
                System.out.print("Isso não é um número! Tente novamente: ");
            }
        }
    }

    public static void mostrarMensagem(String msg) {
        System.out.println(msg);
    }

    public static int menuCombate() {
        System.out.println("\n=== SUA VEZ ===");
        System.out.println("1 - Atacar");
        System.out.println("2 - Defender");
        System.out.println("3 - Usar Magia");
        System.out.println("4 - Fugir");
        return lerIntValido(1, 4);
    }

    public static int escolherMagia() {
        System.out.println("\n=== ESCOLHA SUA MAGIA ===");
        Magia[] magias = Magia.values();
        for (int i = 0; i < magias.length; i++) {
            System.out.printf("%d - %s (Mana: %d) - %s\n",
                    i+1, magias[i].getNome(), magias[i].getCustoMana(), magias[i].getDescricao());
        }
        return lerIntValido(1, magias.length);
    }
}