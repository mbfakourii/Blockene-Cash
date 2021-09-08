package Base;

public class Info {
    boolean successesTransaction = false;
    boolean CanRequest;

    public Info() {}

    public Info(boolean successesTransaction, boolean CanRequest) {
        this.successesTransaction = successesTransaction;
        this.CanRequest = CanRequest;
    }

    public Info(boolean successesTransaction) {
        this.successesTransaction = successesTransaction;
    }

    public boolean isCanRequest() {
        return CanRequest;
    }

    public void setCanRequest(boolean canRequest) {
        CanRequest = canRequest;
    }

    public void setSuccessesTransaction(boolean successesTransaction) {
        this.successesTransaction = successesTransaction;
    }

    public boolean isSuccessesTransaction() {
        return successesTransaction;
    }
}
