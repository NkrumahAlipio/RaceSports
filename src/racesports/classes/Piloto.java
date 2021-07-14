/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package racesports.classes;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author nkrumah
 */
public class Piloto {

    public String nome;
    public String equipa;
    public int pontuacao = 0;

    public static Piloto criarPiloto(Campeonato campeonato, int j) {
        Scanner ler = new Scanner(System.in);
        Piloto piloto = new Piloto();

        System.out.print("Digite o nome e a equipa do " + (j + 1) + "º piloto, separados por virgula e espaço ex:(João, BMW): ");
        String temp = ler.nextLine();

        while (!validarDados(temp)) {
            System.out.println("O formato de texto inserido é invalido.");
            System.out.print("Digite o nome e a equipa do " + (j + 1) + "º piloto, separados por virgula e espaço ex:(João, BMW): ");
            temp = ler.nextLine();
        }

        piloto.nome = temp.split(", ")[0];
        piloto.equipa = temp.split(", ")[1];

        if (!equipaExiste(campeonato.equipas, piloto.equipa)) {
            Equipa equipa = new Equipa();
            equipa.nome = piloto.equipa;
            campeonato.equipas.add(equipa);
        }

        return piloto;
    }

    public static boolean verificarExistencia(Piloto pilotos[], Piloto piloto, int tamanho) {

        for (int i = 0; i < tamanho; i++) {
            if (pilotos[i].nome.equalsIgnoreCase(piloto.nome) && pilotos[i].equipa.equalsIgnoreCase(piloto.equipa)) {
                return true;
            }
        }

        return false;
    }

    public static boolean validarDados(String dados) {
        String nome;
        String equipa;

        try {
            nome = dados.split(", ")[0];
            equipa = dados.split(", ")[1];
        } catch (Exception e) {
            return false;
        }

        return equipa.length() <= 50
                && nome.length() <= 50;
    }

    public static boolean equipaExiste(ArrayList<Equipa> equipas, String equipa) {
        for (int i = 0; i < equipas.size(); i++) {
            if (equipas.get(i).nome.equalsIgnoreCase(equipa)) {
                return true;
            }
        }
        return false;
    }

    public static int primeirasPosicoes(Campeonato campeonato, Piloto piloto) {
        int primeiras = 0;
        try {
            for (Prova prova : campeonato.provas) {
                for (Piloto piloto1 : prova.pilotos) {
                    if (piloto.nome.equalsIgnoreCase(piloto1.nome) && piloto.equipa.equalsIgnoreCase(piloto1.equipa) && piloto.pontuacao == campeonato.pontuacoes[0]) {
                        primeiras++;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            return 0;
        }

        return primeiras;
    }

    public static int segundasPosicoes(Campeonato campeonato, Piloto piloto) {
        int primeiras = 0;
        try {
            for (Prova prova : campeonato.provas) {
                for (Piloto piloto1 : prova.pilotos) {
                    if (piloto.nome.equalsIgnoreCase(piloto1.nome) && piloto.equipa.equalsIgnoreCase(piloto1.equipa) && piloto.pontuacao == campeonato.pontuacoes[0]) {
                        primeiras++;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            return 0;
        }

        return primeiras;
    }

    public static int terceirasPosicoes(Campeonato campeonato, Piloto piloto) {

        int terceiras = 0;
        try {
            for (Prova prova : campeonato.provas) {
                for (Piloto piloto1 : prova.pilotos) {
                    if (piloto.nome.equalsIgnoreCase(piloto1.nome) && piloto.equipa.equalsIgnoreCase(piloto1.equipa) && piloto.pontuacao == campeonato.pontuacoes[2]) {
                        terceiras++;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            return 0;
        }

        return terceiras;

    }

}
