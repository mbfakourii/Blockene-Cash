package Base;

import Scenario.Simulator;

// connect citizen and Politician
public class Connection {
    public static void run() {
        for (int d = 0; d < Simulator.citizens.size(); d++) {
            Politician p = SimuRandom.getRandomPolitician(Simulator.politicians);

            p.numberCitizen++;
            Simulator.citizens.get(d).setPolitician(p);
        }
    }
}
