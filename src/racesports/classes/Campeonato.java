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
public class Campeonato {

    public Piloto pilotos[];
    public String nome;
    public int ano;
    public int pontuacoes[];
    public Prova provas[];
    public ArrayList<Equipa> equipas = new ArrayList<Equipa>();

    public static Campeonato[] criarCampeonatos() {
        Scanner ler = new Scanner(System.in);
        System.out.print("Digite a quantidade de campeonatos: ");
        int qtd_campeonatos = ler.nextInt();

        while (qtd_campeonatos < 1) {
            System.out.print("A quantidade de campeonatos é invalida.\nDigite uma nova quantidade: ");
            qtd_campeonatos = ler.nextInt();
        }
        System.out.println("");

        ler.nextLine();

        Campeonato campeonatos[] = new Campeonato[qtd_campeonatos];

        for (int i = 0; i < qtd_campeonatos; i++) {
            Campeonato campeonato = new Campeonato();

            System.out.print("Digite o nome do " + (i + 1) + "º campeonato: ");
            campeonato.nome = ler.nextLine();

            while (campeonato.nome.isEmpty() || campeonato.nome.length() > 50) {
                System.out.print("O nome digitado é invalido.\nDigite um nome entre 1 e 50 caracteres: ");
                campeonato.nome = ler.nextLine();
            }

            System.out.print("Digite o ano do " + (i + 1) + "º campeonato: ");
            campeonato.ano = ler.nextInt();

            while (campeonato.ano < 1990 || campeonato.ano > 2050) {
                System.out.print("O ano digitado é invalido.\nDigite um ano entre 1990 e 2050 : ");
                campeonato.ano = ler.nextInt();
            }

            ler.nextLine();

            System.out.print("Digite o numero de pilotos: ");
            int qtd_pilotos = ler.nextInt();

            ler.nextLine();

            campeonato.pilotos = new Piloto[qtd_pilotos];

            while (qtd_pilotos < 1 || qtd_pilotos > 50) {
                System.out.println("A quantidade de pilotos deve estar entre 1 e 50.");
                System.out.print("Digite o numero de pilotos: ");
                qtd_pilotos = ler.nextInt();
            }

            for (int j = 0; j < qtd_pilotos; j++) {

                Piloto piloto = Piloto.criarPiloto(campeonato, j);

                //Verificar existencia do piloto
                while (Piloto.verificarExistencia(campeonato.pilotos, piloto, j)) {
                    System.out.println("O piloto escolhido já existe, insira um novo piloto.");
                    piloto = Piloto.criarPiloto(campeonato, j);
                }

                campeonato.pilotos[j] = piloto;
            }

            System.out.print("Insira quantidade de pontos do campeonato: ");
            int qtd_pontos = ler.nextInt();
            campeonato.pontuacoes = new int[qtd_pontos];
            ler.nextLine();

            //ScringBuilder para criar dianamicamente a regra de validação dos pontos
            StringBuilder regex = new StringBuilder();
            for (int j = 0; j < qtd_pontos; j++) {
                regex.append(" \\d+");
            }

            regex.deleteCharAt(0);

            System.out.print("Insira os " + qtd_pontos + " pontos separados por espaços ex:(10 5 1): ");
            String pontos = ler.nextLine();

            while (!pontos.matches(regex.toString())) {
                System.out.println("O formato digitado é invalido.");
                System.out.print("Insira os " + qtd_pontos + " pontos separados por espaçoes ex:(10 5 1): ");
                pontos = ler.nextLine();
            }

            for (int j = 0; j < pontos.split(" ").length; j++) {
                campeonato.pontuacoes[j] = Integer.parseInt(pontos.split(" ")[j]);
            }

            campeonato.pontuacoes = Campeonato.ordenar(campeonato.pontuacoes);

            System.out.println("");

            System.out.print("Digite o número de provas: ");
            int qtd_provas = ler.nextInt();

            while (qtd_provas < 1 || qtd_provas > 50) {
                System.out.println("A quantidade de provas deve estar entre 1 e 50.");
                System.out.print("Digite o numero de provas: ");
                qtd_provas = ler.nextInt();
            }

            campeonato.provas = new Prova[qtd_provas];

            for (int j = 0; j < qtd_provas; j++) {
                campeonato.provas[j] = Prova.criarProva(campeonato, j);
                System.out.println("");
            }

            campeonato.equipas = ordenarEquipas(campeonato.equipas);
            campeonato.pilotos = Campeonato.atuaizarPontos(campeonato);
            campeonato.pilotos = Campeonato.ordenarPilotos(campeonato);

            campeonatos[i] = campeonato;
        }

        return campeonatos;

    }

    public static int[] ordenar(int v[]) {

        for (int i = 0; i < v.length - 1; i++) {
            for (int j = i + 1; j < v.length; j++) {

                if (v[i] < v[j]) {
                    int aux = v[j];
                    v[j] = v[i];
                    v[i] = aux;
                }
            }
        }

        return v;
    }

    public static Piloto[] atuaizarPontos(Campeonato campeonato) {

        for (Piloto piloto : campeonato.pilotos) {
            int aux = 0;
            for (Prova prova : campeonato.provas) {
                for (Piloto piloto1 : prova.pilotos) {
                    if (piloto.nome.equalsIgnoreCase(piloto1.nome) && piloto.equipa.equalsIgnoreCase(piloto1.equipa)) {
                        aux = piloto1.pontuacao + aux;
                        break;
                    }
                }
            }

            piloto.pontuacao = aux;
        }

        return campeonato.pilotos;
    }

    public static ArrayList<Equipa> ordenarEquipas(ArrayList<Equipa> equipa) {
        for (int i = 0; i < equipa.size() - 1; i++) {
            for (int j = i + 1; j < equipa.size(); j++) {

                if (equipa.get(i).pontos < equipa.get(j).pontos) {
                    Equipa aux = new Equipa();
                    aux = equipa.get(j);
                    equipa.set(j, equipa.get(i));
                    equipa.set(i, aux);
                }

            }
        }
        return equipa;
    }

    public static Piloto[] ordenarPilotos(Campeonato campeonato) {
        Piloto pilotos[] = campeonato.pilotos;
        for (int i = 0; i < pilotos.length - 1; i++) {
            for (int j = i + 1; j < pilotos.length; j++) {
                if (pilotos[i].pontuacao < pilotos[j].pontuacao) {
                    Piloto aux = pilotos[j];
                    pilotos[j] = pilotos[i];
                    pilotos[i] = aux;
                } else if (pilotos[i].pontuacao == pilotos[j].pontuacao && Piloto.primeirasPosicoes(campeonato, pilotos[i]) < Piloto.primeirasPosicoes(campeonato, pilotos[j])) {
                    Piloto aux = pilotos[j];
                    pilotos[j] = pilotos[i];
                    pilotos[i] = aux;
                } else if (pilotos[i].pontuacao == pilotos[j].pontuacao && Piloto.segundasPosicoes(campeonato, pilotos[i]) < Piloto.segundasPosicoes(campeonato, pilotos[j])) {
                    Piloto aux = pilotos[j];
                    pilotos[j] = pilotos[i];
                    pilotos[i] = aux;
                } else if (pilotos[i].pontuacao == pilotos[j].pontuacao && Piloto.terceirasPosicoes(campeonato, pilotos[i]) < Piloto.terceirasPosicoes(campeonato, pilotos[j])) {
                    Piloto aux = pilotos[j];
                    pilotos[j] = pilotos[i];
                    pilotos[i] = aux;
                } else if (pilotos[i].nome.charAt(0) < pilotos[j].nome.charAt(0)) {
                    Piloto aux = pilotos[j];
                    pilotos[j] = pilotos[i];
                    pilotos[i] = aux;
                }
            }
        }

        return pilotos;
    }

}
