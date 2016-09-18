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
}
