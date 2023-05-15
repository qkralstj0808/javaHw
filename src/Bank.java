public class Bank {
    private String accountNumber; // 계좌번호
    private String name; // 이름
    private int balance; // 잔액

    // 디폴트 생성자 메소드
    public Bank() {

    }

    // 매개변수 생성자 메소드
    public Bank(String accountNumber, String name, int balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    public void printAccountInfo() {
        System.out.println("계좌번호: " + accountNumber);
        System.out.println("이름: " + name);
        System.out.println("잔액: " + balance + "원");
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public boolean withdraw(int amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public void printAccounts() {
        System.out.printf("계좌번호: %s, 이름: %s, 잔액: %d원%n", accountNumber, name, balance);
    }
}
