package com.kamron.pogoiv.logic;

import java.util.ArrayList;

/**
 * Created by Johan on 2016-09-18.
 * <p/>
 * A class which calculates and manages "General power" score.
 * <p/>
 * The general power is defined as att * def * sta / 10000
 * <p/>
 * It gives a rough understanding of how strong a pokemon is, disregarding moveset.
 * <p/>
 * Comparing this score between two pokemon can be more accurate than comparing their IVs. For example, a chansey has
 * base stats 40 60 500. Adding 15 stamina ivs is less worth than 10 attack ivs, so a 10/0/0 chansey is more valuable
 * than a 0/0/15 chansey, even though the first iv combination (22%) is less than the second iv combination (33%)
 */
public class GeneralPowerScoreCalculator {


    /**
     * Get the average general score for all possible iv combinations for a pokemon
     *
     * @param ivCombinations The iv combinations to check all power combinations and average out
     * @param pokemon        the pokemon whose base stats to use as a baseline to calulate the general score
     * @return A double representing the power of the pokemon
     */
    public double getAverageGeneralScore(ArrayList<IVCombination> ivCombinations, Pokemon pokemon) {
        double total = 0;
        for (IVCombination ivComb : ivCombinations) {
            total += getGeneralScore(ivComb, pokemon);
        }
        return total / ivCombinations.size();
    }

    /**
     * Get the average general score for all possible iv combinations for a pokemon
     *
     * @param powerScore the PowerScore of the pokemon
     * @param pokemon    the pokemon to get the % close to perfection from
     * @return A double that gets closer to 100 when the pokemon has perfect ivs
     */
    public double getPercentOfPerfect(double powerScore, Pokemon pokemon) {
        double maxPowerScore = getPokemonMaxGeneralScore(pokemon);
        return (powerScore / maxPowerScore) * 100;
    }

    /**
     * Gets the general score a pokemon would have if it had the ivs 8,7,7 (48.888% ivs).
     *
     * @param p the pokemon
     * @return The average general score of pokemon p.
     */
    public double getPokemonAverageGeneralScore(Pokemon p) {
        return getGeneralScore(new IVCombination(8, 7, 7), p);
    }

    /**
     * Gets the general score a pokemon would have if it had the ivs 15 15 15.
     *
     * @param p the pokemon
     * @return The max general score for pokemon p
     */
    public double getPokemonMaxGeneralScore(Pokemon p) {
        return getGeneralScore(new IVCombination(15, 15, 15), p);
    }

    /**
     * Gets the general score a pokemon would have if it had the ivs 0,0,0.
     *
     * @param p the pokemon
     * @return The min general score for pokemon p
     */
    public double getPokemonMinGeneralScore(Pokemon p) {
        return getGeneralScore(new IVCombination(0, 0, 0), p);
    }

    /**
     * Get the general power score of a pokemon with a specific IV combination
     *
     * @param ivC The iv combination for the pokemon
     * @param p   The pokemon (used to get the poke base stats)
     * @return A double representing the general score of the pokemon
     */
    private double getGeneralScore(IVCombination ivC, Pokemon p) {
        return (p.baseAttack + ivC.att) * (p.baseDefense + ivC.def) * (p.baseStamina + ivC.sta) / 10000;
    }

    /**
     * Get a string representation of a pokemon rating, for example "A" or "B+".
     * @param score the general score to translate to a string.
     * @return A string S,A,B,C,D which might have a plus or minus after.
     */
    public String getRating(double score){
        int ap = 1050;
        int a = 950;
        int am = 900;
        int bp = 800;
        int b = 750;
        int bm = 700;
        int cp = 650;
        int c = 600;
        int cm = 550;
        int dp = 500;
        int d = 450;
        int dm = 350;
        int ep = 300;
        int e = 200;
        int em = 150;
        int fp = 100;
        int f = 50;
        int fm = 0;

        if (score > ap) return "A+";
        if (score > a) return "A";
        if (score > am) return "A-";
        if (score > bp) return "B+";
        if (score > b) return "B";
        if (score > bm) return "B-";
        if (score > cp) return "C+";
        if (score > c) return "C";
        if (score > cm) return "C-";
        if (score > dp) return "D+";
        if (score > d) return "D";
        if (score > dm) return "D-";
        if (score > ep) return "E+";
        if (score > e) return "E";
        if (score > em) return "E-";
        if (score > fp) return "F+";
        if (score > f) return "F";
        if (score > fm) return "F-";

        return "??";
    }
}
