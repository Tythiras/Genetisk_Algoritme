package com.school;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
//loads items in the backpack

public class LoadFile {
    public final String file = "/data.csv";
    ArrayList<Item> items = new ArrayList<Item>();

    public LoadFile() throws IOException {
        BufferedReader csvReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(file)));
        String row;
        int line = 0;
        while ((row = csvReader.readLine()) != null) {
            if(line != 0) {
                String[] fields = row.split(",");
                String name = fields[0];
                int weight = Integer.parseInt(fields[1]);
                int cost = Integer.parseInt(fields[2]);
                items.add(new Item(name, cost, weight));
            }
            line++;
        }
        csvReader.close();
    }
    public ArrayList<Item> getResult() {
        return items;
    }
}
