package com.school;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Generation {
    ArrayList<Solution> solutions = new ArrayList<>();
    ArrayList<Item> bag;
    int max;

    public Generation(ArrayList<Item> bag, int max) {
        this.bag = bag;
        this.max = max;
    }

    void addSolution(Solution toAdd) {
        //add new solution to the generation
        solutions.add(toAdd);
    }
    void generateSolution() {
        //Create random solution and add to the generation
        Boolean[] bagRepresentation = new Boolean[bag.size()];
        Random random = new Random();

        for(int i = 0; i < bag.size(); i++) {
            bagRepresentation[i] = random.nextBoolean();
        }
        Solution newSolution = new Solution(bagRepresentation, bag, max);
        if(newSolution.getWeight() < max) {
            solutions.add(newSolution);
        }
    }
    Solution getSolution() {
        //select random solution through rotation wheel method
        float sum = this.fitnessSum();
        Random random = new Random();
        double r = random.nextDouble() * sum;
        float tempSum = 0;
        for(Solution sol : this.solutions) {
            tempSum += sol.getFitness();
            if(tempSum >= r) {
                return sol;
            }
        }
        return this.solutions.get(0);
    }
    float fitnessSum() {
        float fitness = 0;
        for(Solution sol : solutions) {
            fitness += sol.getFitness();
        }
        return fitness;
    }
    float getFitness() {
        return this.getBest().getFitness();
    }
    Solution getBest() {
        return Collections.min(solutions, new compSolution());
    }

    void print() {
        for(Solution sol : solutions) {
            System.out.println(sol.getString());
        }
    }

}
