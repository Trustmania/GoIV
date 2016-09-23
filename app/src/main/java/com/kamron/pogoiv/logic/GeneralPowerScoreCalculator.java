package com.kamron.pogoiv.logic;


/**
 * Created by Johan on 2016-09-18.
 */
public class GeneralPowerScoreCalculator {


    /**
     * Get how perfect the pokemon is % wise compared to if the pokemon had perfect ivs.
     * Basically, returns pokemon estimate cp / max species cp
     * @param cpAverage the cp at level 40 of the pokemon with potentially imperfect ivs.
     * @param pokeInfoCalculator used to calculate the cp
     * @param selectedPokemon Which pokemon to apply the iv and max cp calculations to
     * @return a number between 0 up to 1.
     */
    public double getPercentOfPerfect(double cpAverage, PokeInfoCalculator pokeInfoCalculator, Pokemon
            selectedPokemon) {

        //the cp for this pokemon with max ivs on level 40
        double cpMax = pokeInfoCalculator.getCpRangeAtLevel(selectedPokemon, 15, 15, 15, 15, 15, 15, 40).high;
        return cpAverage / cpMax;

    }


    /**
     * Get a string representation of a pokemon rating, for example "A" or "B+".
     * @param combatPower the general combatPower to translate to a tier string.
     * @return A string S,A,B,C,D which might have a plus or minus after.
     */
    public String getRating(double combatPower){
        int ap = 3100; //
        int a = 2650;
        int am = 2550;
        int bp = 2200;
        int b = 2100;
        int bm = 2000;
        int cp = 1900;
        int c = 1800;
        int cm = 1700;
        int dp = 1600;
        int d = 1500;
        int dm = 1400;
        int ep = 1300;
        int e = 1200;
        int em = 1100;
        int fp = 1000;
        int f = 800;
        int fm = 0;

        if (combatPower > ap) return "A+";
        if (combatPower > a) return "A";
        if (combatPower > am) return "A-";
        if (combatPower > bp) return "B+";
        if (combatPower > b) return "B";
        if (combatPower > bm) return "B-";
        if (combatPower > cp) return "C+";
        if (combatPower > c) return "C";
        if (combatPower > cm) return "C-";
        if (combatPower > dp) return "D+";
        if (combatPower > d) return "D";
        if (combatPower > dm) return "D-";
        if (combatPower > ep) return "E+";
        if (combatPower > e) return "E";
        if (combatPower > em) return "E-";
        if (combatPower > fp) return "F+";
        if (combatPower > f) return "F";
        if (combatPower > fm) return "F-";

        return "??";
    }
}
