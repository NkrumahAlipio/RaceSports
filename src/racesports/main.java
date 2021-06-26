/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package racesports;

import java.util.Scanner;
import racesports.classes.Campeonato;

/**
 *
 * @author nkrumah
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Campeonato campeonatos[] = Campeonato.criarCampeonatos();
    }

    public static boolean verifica(int i[], int n, int pos) {
        for (int j = 0; j < pos; j++) {
            if (i[j] == n) {
                return true;
            }
        }
        return false;
    }

}
