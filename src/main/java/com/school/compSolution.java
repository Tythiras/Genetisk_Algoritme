package com.school;

import java.util.Comparator;

public class compSolution implements Comparator<Solution> {
    public int compare(Solution a, Solution b) {
        if (a.getFitness() > b.getFitness())
            return -1; // highest value first
        if (a.getFitness() == b.getFitness())
            return 0;
        return 1;
    }
}
