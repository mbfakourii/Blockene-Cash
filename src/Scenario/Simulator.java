package Scenario;

import Base.*;
import Helper.GraphPanel;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static Helper.Utils.print;

public class Simulator {
    public static List<Citizen> citizens;
    public static List<Politician> politicians;

    public static List<Double> secTotalTransaction;
    public static List<Double> secTotalCanRequest;

    public static void setup(Config config) {

        citizens = new ArrayList<>();
        politicians = new ArrayList<>();

        secTotalTransaction = new ArrayList<>();
        secTotalCanRequest = new ArrayList<>();

        SimuRandom.setConfig(config);

        // add Citizens and Malicious Citizens
        int numMaliciousCitizens = (int) (config.numberCitizens * (config.percentMaliciousCitizens * 0.01));

        for (int j = 0; j < numMaliciousCitizens; j++)
            citizens.add(new Citizen(true));

        for (int t = 0; t < (config.numberCitizens - numMaliciousCitizens); t++)
            citizens.add(new Citizen(false));

        // add Politician and Malicious Politician
        int numMaliciousPoliticians = (int) (config.numberPoliticians * (config.percentMaliciousPoliticians * 0.01));

        for (int j = 0; j < numMaliciousPoliticians; j++)
            politicians.add(new Politician(true));

        for (int t = 0; t < (config.numberPoliticians - numMaliciousPoliticians); t++)
            politicians.add(new Politician(false));


        Connection.run();
    }

    public static void run(int sec) {
        int totalTransaction = 0;
        int totalCanRequest = 0;


        // in Seconds
        for (int secs = 0; secs < sec; secs++) {
            int secCanRequest = 0;

            for (Citizen citizen : citizens) {
                // communication In Seconds
                List<Info> outputs = citizen.communicationInSec();
                for (Info info : outputs) {
                    if (info.isSuccessesTransaction()) {
                        totalTransaction++;
                    }

                    if (info.isCanRequest()) {
                        secCanRequest++;
                        totalCanRequest++;
                    }
                }

            }

            secTotalTransaction.add((double) totalTransaction);
            secTotalCanRequest.add((double) secCanRequest);
        }

        GraphPanel.createAndShowGui(secTotalTransaction, "Total Transaction");
        GraphPanel.createAndShowGui(secTotalCanRequest, "Total Can Request");

        print("total CanRequest = " + totalCanRequest);
        print("total transaction = " + totalTransaction);
    }

    public static void report() {
        // save report in txt file
        try {
            FileWriter fw = new FileWriter("report_transaction.txt");

            for (int i = 0; i < secTotalTransaction.size(); i++)
                fw.write(i + "\t" + secTotalTransaction.get(i) + "\n");

            fw.close();
        } catch (IOException e) {
            System.err.println("Bad file name");
        }

        try {
            FileWriter fw = new FileWriter("report_CanRequest.txt");

            for (int i = 0; i < secTotalCanRequest.size(); i++)
                fw.write(i + "\t" + secTotalCanRequest.get(i) + "\n");

            fw.close();
        } catch (IOException e) {
            System.err.println("Bad file name");
        }
    }

    public static void main(String[] args) {
        System.out.println("In the name of Allah");

        Config config = new Config();
        config.setBaseSimConfig(SimBase.Malicious_80_25);
        // 1.25 h = 75 min  = 4500 sec
        config.totalSeconds = 4500;

        config.numberCitizens = 800;
        config.numberPoliticians = 200;

        config.problesticTransaction = 0.029;
        config.problesticNoInternet = 0.009;

        config.canCaching = true;

        setup(config);
        run(config.totalSeconds);
        report();

        System.out.println("Finished !");
    }
}
