/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package racesports.classes;

import java.util.Arrays;
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

    public static Campeonato[] criarCampeonatos() {
        Scanner ler = new Scanner(System.in);
        System.out.print("Digite a quantidade de campionatos: ");
        int qtd_campeonatos = ler.nextInt();

        while (qtd_campeonatos < 1) {
            System.out.print("A quantidade de campeonatos é invalida.\nDigite uma nova quantidade: ");
            qtd_campeonatos = ler.nextInt();
        }

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

            for (int j = 0; j < qtd_pilotos; j++) {

                Piloto piloto = Piloto.criarPiloto(j);

                //Verificar existencia do piloto
                while (Campeonato.validarPiloto(campeonato.pilotos, piloto, j)) {
                    System.out.println("O piloto escolhido já existe, insira um novo piloto.");
                    piloto = Piloto.criarPiloto(j);
                }

                campeonato.pilotos[j] = piloto;
            }

            System.out.print("Insira quantidade de pontos do campeonato: ");
            int qtd_pontos = ler.nextInt();
            campeonato.pontuacoes = new int[qtd_pontos];

            ler.nextLine();

            for (int j = 0; j < qtd_pontos; j++) {
                System.out.print("Insira o " + (j + 1) + "º ponto: ");
                int n = ler.nextInt();
                while (n <= 0) {
                    System.out.println("Insira um número maior que 0.\nInsira o " + (j + 1) + "º ponto: ");
                    n = ler.nextInt();
                }
                campeonato.pontuacoes[j] = n;
            }

            ler.nextLine();

            campeonato.pontuacoes = Campeonato.ordenar(campeonato.pontuacoes);

            System.out.println("Digite o numero de provas: ");
            int qtd_provas = ler.nextInt();
            while (qtd_provas < 1 || qtd_provas > 50) {
                System.out.println("A quantidade de provas deve estar entre 1 e 50.");
                System.out.println("Digite o numero de provas: ");
                qtd_provas = ler.nextInt();
            }

            for (int j = 0; j < qtd_provas; j++) {
                campeonato.provas[j] = Prova.criarProva(campeonato);
            }

            campeonatos[i] = campeonato;
        }

        return campeonatos;

    }

    public static boolean validarPiloto(Piloto pilotos[], Piloto piloto, int pos) {

        for (int i = 0; i < pos; i++) {
            if (pilotos[i].nome.equalsIgnoreCase(piloto.nome) && pilotos[i].equipa.equalsIgnoreCase(piloto.equipa)) {
                return true;
            }
        }

        return false;
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

}
