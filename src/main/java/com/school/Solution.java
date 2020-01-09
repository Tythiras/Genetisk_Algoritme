package com.school;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Solution {
    Boolean[] bagRepresentation;
    ArrayList<Item> bag;
    int max;
    public Solution(Boolean[] bagRepresentation, ArrayList<Item> bag, int max) {
        this.bagRepresentation = bagRepresentation;
        this.bag = bag;
        this.max = max;
    }

    float getValue() {
        //loop through items and sum them
        float currValue = 0;
        for(int i = 0; i < bag.size(); i++) {
            if(bagRepresentation[i]) {
                currValue += bag.get(i).value;
            }
        }
        return currValue;
    }
    float getWeight() {
        //loop through items and sum them
        float currWeight = 0;
        for(int i = 0; i < bag.size(); i++) {
            if(bagRepresentation[i]) {
                currWeight += bag.get(i).weight;
            }
        }
        return currWeight;
    }
    float getFitness() {
        //return the value if under the maximum weight
        return this.getWeight() < max ? this.getValue() : 0;
    }

    void mutate(double mutateChance) {
        //mutate a single item
        Random random = new Random();
        double r = random.nextDouble();
        if(r > mutateChance) {
            int randomIndex = (int) (Math.random() * this.bagRepresentation.length);
            this.bagRepresentation[randomIndex] = !this.bagRepresentation[randomIndex];
        }
    }
    static ArrayList<Solution> pair(Solution a, Solution b) {
        //cross over
        int splitPoint = (int) Math.floor(a.bagRepresentation.length / 2f);
        Boolean[] aClone = a.bagRepresentation;
        Boolean[] bClone = b.bagRepresentation;

        Boolean[] aPartOne = Arrays.copyOfRange(aClone, 0, splitPoint);
        Boolean[] aPartTwo = Arrays.copyOfRange(aClone, splitPoint, aClone.length);

        Boolean[] bPartOne = Arrays.copyOfRange(bClone, 0, splitPoint);
        Boolean[] bPartTwo = Arrays.copyOfRange(bClone, splitPoint, bClone.length);

        Boolean[] newA = combine(aPartOne, bPartTwo);
        Boolean[] newB = combine(bPartOne, aPartTwo);
        return new ArrayList<Solution>() {
            {
                add(new Solution(newA, a.bag, a.max));
                add(new Solution(newB, b.bag, b.max));
            }
        };
    }

    //combine function used for pairing
    public static Boolean[] combine(Boolean[] a, Boolean[] b){
        int length = a.length + b.length;
        Boolean[] result = new Boolean[length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }


    String getString() {
        StringBuilder str = new StringBuilder();;
        for(Boolean active : this.bagRepresentation) {
            if(active) {
                str.append("1 ");
            } else {
                str.append("0 ");
            }
        }
        str.append("Vægt: ").append(this.getWeight()).append(", Værdi: ").append(this.getValue()).append(" Fitness: ").append(this.getFitness());
        return str.toString();
    }

}


