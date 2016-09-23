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
