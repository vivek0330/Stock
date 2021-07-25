package com.bridgelabz;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StockManagement {
    public static Scanner sc = new Scanner(System.in);
    public static JSONArray stockList = new JSONArray();

    public static void main(String[] args) {
        System.out.println("********* Stock Management *********");
        getInputFromUser();

    }
    private static void getInputFromUser(){
        System.out.println("Which operation do you want to perform ?\n1.Add Stock \n2.Print stock report \n3.Exit");
        int ch = sc.nextInt();
        switch (ch) {
            case 1 -> addStock();
            case 2 -> printStock();
            case 3 -> System.exit(1);
            default -> System.out.println("Invalid choice");
        }
    }

    public static void printStock() {
        System.out.println("***** print stock *******");
        JSONParser jsonParser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("Data/Stock.json"));
            for (int i =0; i < jsonArray.size();i++){
                System.out.printf("******** Stock %s ********\n",i+1);
                JSONObject obj = (JSONObject) jsonArray.get(i);
                String name = (String) obj.get("name");
                long shares = (long) obj.get("no_of_shares");
                Double price = (Double) obj.get("price");
                System.out.println("Stock Name : " +name);
                System.out.println("Number of Shares : " +shares);
                System.out.println("Stock price : " +price);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        getInputFromUser();
    }

    public static void addStock() {
        System.out.println("******* Add stock *******");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Stock Name : ");
        String stockName = sc.nextLine();
        System.out.println("Enter number of shares: ");
        int noOfShares = sc.nextInt();
        System.out.println("Enter share price: ");
        double sharePrice = sc.nextDouble();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", stockName);// Assigning the accepted value to the JSON Object
        jsonObject.put("no_of_shares", noOfShares);
        jsonObject.put("price", sharePrice);
        stockList.add(jsonObject); // Adding the JSON Object into the JSON Array..
        try {
            FileWriter file = new FileWriter("Data/stock.json");
            file.write(stockList.toJSONString());
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("added: "+jsonObject);
        getInputFromUser();
    }
}
