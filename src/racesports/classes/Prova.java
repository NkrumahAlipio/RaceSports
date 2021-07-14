/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package racesports.classes;

import java.util.Scanner;

/**
 *
 * @author nkrumah
 */
public class Prova {

    public String nome;
    public Piloto pilotos[];

    public static Prova criarProva(Campeonato campeonato, int tamanho) {
        Prova prova = new Prova();
        prova.pilotos = new Piloto[campeonato.pilotos.length];

        Scanner ler = new Scanner(System.in);
        System.out.print("Insira o nome da " + (tamanho + 1) + "ª prova: ");
        prova.nome = ler.nextLine();

        while (prova.nome.isEmpty() || prova.nome.length() > 50) {
            System.out.println("O nome digitado é invalido.");
            System.out.print("Insira o nome da " + (tamanho + 1) + "ª prova: ");
            prova.nome = ler.nextLine();
        }

        for (int i = 0; i < campeonato.pilotos.length; i++) {
            prova.pilotos[i] = new Piloto();

            System.out.print("Digite o nome e a equipa do piloto na " + (i + 1) + "ª posição, separados por virgula e espaço ex:(João, BMW): ");
            String temp = ler.nextLine();

            while (!Piloto.validarDados(temp)) {
                System.out.println("O formato de texto inserido é invalido.");
                System.out.print("Digite o nome e a equipa do piloto na " + (i + 1) + "ª posição, separados por virgula e espaço ex:(João, BMW): ");
                temp = ler.nextLine();
            }

            prova.pilotos[i].nome = temp.split(", ")[0];
            prova.pilotos[i].equipa = temp.split(", ")[1].split("\\(")[0];
            prova.pilotos[i].equipa = prova.pilotos[i].equipa.stripLeading();

            while (!(Piloto.verificarExistencia(campeonato.pilotos, prova.pilotos[i], campeonato.pilotos.length) && !Piloto.verificarExistencia(prova.pilotos, prova.pilotos[i], i))) {
                System.out.println("O piloto escolhido já foi classificado ou não participa desta competição.");
                System.out.print("Digite o nome e a equipa do piloto na " + (i + 1) + "ª posição, separados por virgula e espaço ex:(João, BMW): ");
                temp = ler.nextLine();

                while (!Piloto.validarDados(temp)) {
                    System.out.println("O formato de texto inserido é invalido.");
                    System.out.print("Digite o nome e a equipa do piloto na " + (i + 1) + "ª posição, separados por virgula e espaço ex:(João, BMW): ");
                    temp = ler.nextLine();
                }

                prova.pilotos[i].nome = temp.split(", ")[0];
                prova.pilotos[i].equipa = temp.split(", ")[1].split("\\(")[0].stripLeading();

            }

            if (i < campeonato.pontuacoes.length) {
                prova.pilotos[i].pontuacao = campeonato.pontuacoes[i];
                for (int j = 0; j < campeonato.equipas.size(); j++) {
                    if (campeonato.equipas.get(j).nome.equalsIgnoreCase(prova.pilotos[i].equipa)) {
                        campeonato.equipas.get(j).pontos += campeonato.pontuacoes[i];
                    }
                }

            }

        }

        return prova;
    }

}
