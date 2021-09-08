package Scenario;

import Base.SimBase;

public class Config {
    public int totalSeconds;

    public double problesticTransaction;
    public double problesticNoInternet;

    public int percentMaliciousPoliticians;
    public int percentMaliciousCitizens;

    public int numberPoliticians;
    public int numberCitizens;

    public boolean canCaching;

    // Set Number Entitiy in Simulation Base on Main Paper
    public void setBaseSimConfig(SimBase value) {
        if (value == SimBase.FullyHonest) {
            percentMaliciousPoliticians = 0;
            percentMaliciousCitizens = 0;
        } else if (value == SimBase.Malicious_50_10) {
            percentMaliciousPoliticians = 50;
            percentMaliciousCitizens = 10;
        } else {
            percentMaliciousPoliticians = 80;
            percentMaliciousCitizens = 25;
        }
    }
}
