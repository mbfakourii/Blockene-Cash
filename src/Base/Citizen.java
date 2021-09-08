package Base;

import java.util.ArrayList;
import java.util.List;

public class Citizen {
    boolean isMalicious;
    private Politician politician;
    int numberRequestCash = 0;

    public Citizen(boolean isMalicious) {
        this.isMalicious = isMalicious;
    }

    public List<Info> communicationInSec() {
        List<Info> infos = new ArrayList<>();
        Info info = new Info();

        // check citizen have internet
        if (!SimuRandom.randomNotInternet()) {
            //check enable caching and if true run Previous Cachs
            if (SimuRandom.getConfig().canCaching) {
                infos = checkPreviousRequestInCach();
            }

            info.setCanRequest(true);

            // can transaction and transaction !
            boolean transaction = SimuRandom.randomCanTransaction();
            if (transaction) {
                info.setSuccessesTransaction(politician.transaction());
            }

            infos.add(info);
        } else {
            // Cach request
            if (SimuRandom.getConfig().canCaching) {
                numberRequestCash++;
            }

            info.setCanRequest(false);
            infos.add(info);

        }


        return infos;
    }

    private List<Info> checkPreviousRequestInCach() {
        List<Info> previousInfos = new ArrayList<>();

        // check previous requests
        for (int i = 0; i < numberRequestCash; i++) {
            boolean successesTransaction = sendCachContent();
            previousInfos.add(new Info(successesTransaction, true));
        }

        numberRequestCash = 0;

        return previousInfos;
    }

    private boolean sendCachContent() {
        boolean transaction = SimuRandom.randomCanTransaction();
        if (transaction) {
            return politician.transaction();
        }
        return false;
    }

    public boolean isMalicious() {
        return isMalicious;
    }

    public void setPolitician(Politician politician) {
        this.politician = politician;
    }

    public Politician getPolitician() {
        return politician;
    }
}
