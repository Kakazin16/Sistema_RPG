# 🧙 Sistema_RPG (Java)

Projeto de RPG em modo texto feito em Java, inspirado em sistemas de atributos estilo soulslike.
O jogador cria um personagem, escolhe uma classe, luta contra inimigos aleatórios, usa magias e evolui atributos.

Tudo no terminal, sem gráficos.

## 🎮 Funcionalidades

✔ Criação de personagem com classes

✔ Sistema de atributos (VIT, FOR, DES, INT, etc.)

✔ Combate por turnos

✔ Sistema de magias (dano, cura, buff, veneno, congelamento)

✔ Loja com compra e troca de armas

✔ Sistema de ouro

✔ Sistema de XP e level up

✔ Distribuição manual de pontos

✔ Armas com scaling por atributo

✔ Recuperação parcial de vida após batalhas

---

## 🧙 Classes Disponiveis

- Guerreiro

- Paladino

- Bandido

- Espadachim

- Prisioneiro

- Herói

- Erudito

- Arauto

- Ocultista

- Miserável

Cada classe começa com atributos diferentes.

---

## ⚔️ Tipos de armas

- Adaga

- Espada Curta

- Espada Longa

- Espada Colossal

- Katana

- Machado

- Machado Grande

- Alabarda

- Chicote

- Garras

- Martelo

- Lança

- Arco

- Crossbow

- Selo

- Cajado

Cada arma tem:

- Dano base

- Preço

- Atributo de scaling (FOR, DES, INT, FE, ARC)

---

## 🔥 Magias

- Chama Sagrada

- Raio Divino

- Beam Azul

- Cura Divina

- Lâmina Mágica (buff)

- Veneno

- Congelamento

---

## 🧠 Sistema de atributos

- Vitalidade

- Mente

- Fortitude

- Força

- Destreza

- Inteligência

- Fé

- Arcano

---



## 🗂 Estrutura do Projeto

Sistema_RPG/

│

├── app/ 

└──  Main.java

│

├── controller/

└── JogoController.java

│

│

├── model/

  └── Atributo.java
  
  └── Classe.java
  
  └── Inimigo.java
  
  └── Magia.java
  
  └── Personagem.java
  
  └── Status.java

│

│

├── view/


  └──  Menu.java


Arquitetura baseada em MVC:

Model → regras do jogo

View → entrada e saída (menu)

Controller → lógica principal

---

## 🛠️ Tecnologias usadas

- Java 17+

- Programação Orientada a Objetos

- Enum para classes, armas e magias

- Scanner para entrada de dados

---

## ▶️ Como Executar

Pré-requisitos:

- Java 17 ou superior

- Terminal ou IDE (IntelliJ, VS Code, Eclipse)

### Rodar pelo terminal:

```

javac app/Main.java
java app.Main

```

Ou rodar a classe Main pela IDE.


## 🎮 Como Jogar

1. Digite o nome do personagem
   
2. Escolha uma classe

3. No menu principal:

   1- Treinar (ganha XP)

   2- Lutar

   3- Distribuir Pontos

   4- Visitar Loja

   0- Sair

4. No combate:

   1- Atacar

   2- Defender

   3- Usar Magia

   4- Fugir

Se morrer: fim de jogo.

Se vencer: XP, level up e volta pro menu.

---

## 📜 Licença

Projeto educacional, feito para estudo de Java e lógica de jogos.

