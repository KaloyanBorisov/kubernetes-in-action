import java.util.ArrayList;
import java.util.List;

public class Test {

    public void main(String args[]) {
        List<Account> acctList = new ArrayList<>();
        Account acct1 = new Account();
        acct1.setAccountNumber(12345);
        acct1.setAccountType("Checking");
        acctList.add(acct1);
        Account acct2 = new Account();
        acct2.setAccountNumber(54321);
        acct2.setAccountType("Savings");
        acctList.add(acct2);
        //Filter only Checking Accounts from acctList
        acctList.stream().filter(account -> account.accountType.equals("Checking")).forEach(account -> System.out.println(account.accountNumber));
    }

    static class Account {
        private Integer accountNumber;
        private String accountType;

        public Integer getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(Integer accountNumber) {
            this.accountNumber = accountNumber;
        }

        public String getAccountType() {
            return accountType;
        }

        public void setAccountType(String accountType) {
            this.accountType = accountType;
        }
    }
}