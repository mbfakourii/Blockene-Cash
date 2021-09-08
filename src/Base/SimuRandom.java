package Base;

import Scenario.Config;
import Scenario.Simulator;

import java.util.List;
import java.util.SplittableRandom;

public class SimuRandom {
    static private Config config;
    static private final SplittableRandom random = new SplittableRandom();

    // Random Select Politician for Citizen
    public static Politician getRandomPolitician(List<Politician> temp) {
        int i = 0;
        while (true) {
            //if number Citizen in politicians > size citizens / politicians
            if (i >= temp.size()) return temp.get((int) (0 + Math.random() * (temp.size())));

            Politician politician = temp.get((int) (0 + Math.random() * (temp.size())));
            i++;

            if (politician.numberCitizen != (Simulator.citizens.size() / Simulator.politicians.size())) {
                return politician;
            }
        }
    }

    // Random Can Request To Internet
    public static boolean randomNotInternet() {
        return random.nextInt(0, (int) ((config.numberCitizens)
                * config.problesticNoInternet)) < config.problesticNoInternet;
    }

    // Random Can Transaction
    public static boolean randomCanTransaction() {
        return random.nextInt(0, (int) ((config.numberCitizens * config.numberPoliticians)
                * config.problesticTransaction)) < config.problesticTransaction;
    }

    public static void setConfig(Config inConfig) {
        config = inConfig;
    }

    public static Config getConfig() {
        return config;
    }
}
