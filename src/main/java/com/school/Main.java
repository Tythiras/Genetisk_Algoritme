package com.school;



import processing.core.PApplet;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends PApplet {
    ArrayList<Item> items = new ArrayList<>();
    ArrayList<Generation> generations = new ArrayList<>();

    double mutationChance = 0.85f;
    int populationSize = 200;
    int bagMaxSize = 5000;


    public static void main(String[] args) {
        PApplet.main("com.school.Main");
    }
    public void load() throws IOException {
        LoadFile reader = new LoadFile();
        items = reader.getResult();
    }
    public void settings(){

        size(1000,600);
    }

    public void setup(){
        try {
            this.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //create initial generation
        initialGeneration(populationSize);

        frameRate(50);
    }

    public void draw(){
        Generation lastGeneration = generations.get(generations.size() - 1);
        Solution best = lastGeneration.getBest();
        if(best.getFitness() < 1130) {
            //create new generation with parents from last
            newGeneration(lastGeneration);
            lastGeneration = generations.get(generations.size() - 1);
        }

        //processing drawing
        clear();
        stroke(255);
        color(255);
        strokeWeight(2);
        textSize(32);
        text("Generation: " + generations.size() + "; Bedste løsning: "+lastGeneration.getFitness(), 50, 40);
        textSize(20);
        text("Mutation: " + round((float) (mutationChance * 100)) + "%" + ", Population: "+populationSize, 50, 80);
        textSize(15);
        text(lastGeneration.getBest().getString(), 50, 110);
        float yOffset = 200;
        float xDist = (float) width / generations.size();
        line(0, yOffset - 50, width, yOffset - 50);
        float currX = 0;
        float currY = 0;
        float yScaling = 0.5f;


        for(Generation gen : generations) {
            float newY = height + yOffset - (yScaling * gen.getBest().getFitness());
            float newX = currX + xDist;
            ellipse(newX, newY, 3, 3);
            if(currY != 0) {
                line(currX, currY, newX, newY);
            }
            currY = newY;
            currX = newX;
        }
    }
    public void initialGeneration(int length) {
        //create an working generation with length populationsize
        Generation initialGeneration = new Generation(items, bagMaxSize);
        while(initialGeneration.solutions.size() < length) {
            initialGeneration.generateSolution();
        }
        generations.add(initialGeneration);
    }
    public void newGeneration(Generation oldGeneration) {
        //generate new generation from oldgenerations parents
        Generation newGeneration = new Generation(items, bagMaxSize);
        //make oldSize / 2 pairings
        for(int i = 0; i < oldGeneration.solutions.size() / 2; i++) {
            //Create 2 children from 2 parents selected through Generation.getSolution()
            ArrayList<Solution> children = Solution.pair(oldGeneration.getSolution(), oldGeneration.getSolution());
            for(Solution child : children) {
                //Make mutations to children
                child.mutate(mutationChance);
                //Add them to the generation
                newGeneration.addSolution(child);
            }

        }

        generations.add(newGeneration);
    }
}
