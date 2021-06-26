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
public class Prova {

    public String nome;
    public ArrayList<Piloto> classificados;

    public static Prova criarProva(Campeonato campeonato) {
        Prova prova = new Prova();
        Piloto piloto = new Piloto();
        prova.classificados = new ArrayList<>();
        Scanner ler = new Scanner(System.in);
        System.out.println("Insira o nome da prova: ");
        prova.nome = ler.nextLine();

        while (prova.nome.isEmpty() || prova.nome.length() > 50) {
            System.out.println("O nome digitado é invalido.");
            System.out.println("Insira o nome da prova: ");
            prova.nome = ler.nextLine();
        }
        
        for (int i = 0; i < campeonato.pilotos.length; i++) {
            System.out.println("Digite o nome do piloto na " + (i + 1) + "ª posição.");
            piloto.nome = ler.nextLine();

            System.out.println("Digite a equipa do piloto");
            piloto.equipa = ler.nextLine();

            while (!(validarExistencia(campeonato.pilotos, piloto) && validarAusencia(prova.classificados, piloto))) {
                System.out.println("O piloto inserido é invalido.");
                System.out.println("Digite o nome do piloto na " + (i + 1) + "ª posição.");
                piloto.nome = ler.nextLine();

                System.out.println("Digite a equipa do piloto");
                piloto.equipa = ler.nextLine();
            }

            if (i < campeonato.pontuacoes.length) {
                piloto.pontuacao = piloto.pontuacao + campeonato.pontuacoes[i];
            } else {
                piloto.pontuacao = 0;
            }
            
            prova.classificados.add(piloto);

        }

        return prova;
    }

    public static boolean validarExistencia(Piloto pilotos[], Piloto piloto) {

        for (int i = 0; i < pilotos.length; i++) {
            if (pilotos[i].nome.equalsIgnoreCase(piloto.nome) && pilotos[i].equipa.equalsIgnoreCase(piloto.equipa)) {
                return true;
            }
        }

        return false;
    }

    public static boolean validarAusencia(ArrayList<Piloto> pilotos, Piloto piloto) {

        for (int i = 0; i < pilotos.size(); i++) {
            if (pilotos.get(i).nome.equalsIgnoreCase(piloto.nome) && pilotos.get(i).equipa.equalsIgnoreCase(piloto.equipa)) {
                return false;
            }
        }

        return true;
    }

}
