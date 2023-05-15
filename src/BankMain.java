import java.util.Scanner;

public class BankMain {
    public static void main(String[] args) {
        boolean exit = false;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("=====Bank Menu=====");
            System.out.println("1.계좌개설");
            System.out.println("2.입금하기");
            System.out.println("3.출금하기");
            System.out.println("4.전체조회");
            System.out.println("5.계좌이체");
            System.out.println("6.프로그램 종료");
            System.out.println("===================");

            System.out.println("1~6 숫자를 입력하세요.");

            int menu = Integer.parseInt(sc.next());
            switch (menu) {
                case 1: createAccount(); break; // 계좌개설
                case 2: deposit(); break; // 입금하기
                case 3: withdraw();break; // 출금하기
                case 4: retrieveAccounts();break; // 전체조회
                case 5: accountTransfer();break; // 계좌이체
                case 6: exit = true;
                System.out.println("##프로그램을 종료합니다##"); break;
            }
        } while (!exit);
    }

    private static void accountTransfer() {
        // 뭘 하라는 건지...?
    }
    static Bank[] accounts = new Bank[100]; // 전역
    static int index = 0; // ㅈ

    // 계좌 개설
    public static int createAccount() {

        Scanner sc = new Scanner(System.in);

        System.out.println("=======계좌개설======");
        System.out.println("계좌번호:");
        String accountNumber = sc.nextLine();
        System.out.println("이름:");
        String name = sc.nextLine();
        System.out.println("예금:");
        int deposit;
        try {
            deposit = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("올바른 숫자 형식으로 예금액을 입력해주세요.");
            return 0;
        }
        System.out.println("##계좌개설을 완료하였습니다##");
        System.out.println("==================");

        Bank account = new Bank(accountNumber, name, deposit);
        accounts[index++] = account;

        System.out.printf("예금:%d%n", deposit);
        return deposit;
    }

    // 계좌 찾기
    public static Bank findAccount(String accountNumber, String name) {
        for (int i = 0; i < index; i++) {
            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                return accounts[i];
            }
        }
        return null;
    }

    // 입금
    public static void deposit() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=====입금하기=====");

        System.out.println("입금하실 계좌번호를 입력해주세요:");
        String accountNumber = sc.nextLine();

        System.out.println("계좌이름:");
        String accountName = sc.nextLine();

        Bank account = findAccount(accountNumber, accountName);
        if (account == null) {
            System.out.println("해당 계좌가 존재하지 않습니다.");
            return;
        }

        while (true) {
            try {
                System.out.println("입금하실 금액을 입력해주세요:");
                int money = Integer.parseInt(sc.nextLine());

                if (money <= 0) {
                    System.out.println("올바른 금액을 입력해주세요.");
                } else {
                    int balance = account.getBalance() + money;
                    account.setBalance(balance);
                    System.out.printf("##계좌잔고:%d원%n", balance);
                    System.out.println("##입금이 완료되었습니다##");
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("올바른 숫자 형식으로 입력해주세요.");
            }
        }
    }

    // 출금
    public static void withdraw() {
        Scanner sc = new Scanner(System.in);
        System.out.println("===== 출금하기 =====");

        System.out.println("출금하실 계좌번호를 입력해주세요:");
        String accountNumber = sc.nextLine();

        System.out.println("계좌이름:");
        String accountName = sc.nextLine();

        Bank account = findAccount(accountNumber, accountName);
        if (account == null) {
            System.out.println("해당 계좌가 존재하지 않습니다.");
            return;
        }

        System.out.printf("걔좌잔고:%d",account.getBalance());

        System.out.println("출금하실 금액을 입력해주세요:");
        int amount = Integer.parseInt(sc.nextLine());

        boolean success = account.withdraw(amount);
        if (success) {
            System.out.printf("## 출금액 %d원이 출금되었습니다. 현재 잔액: %d원%n", amount, account.getBalance());
        } else {
            System.out.println("잔액이 부족하여 출금할 수 없습니다.");
        }
        System.out.println("=====================");
    }

    // 모든 계좌 정보 조회
    public static void retrieveAccounts() {
        if (index == 0) {
            System.out.println("등록된 계좌가 없습니다.");
            return;
        }
        System.out.println("======전체조회======");
        for (int i = 0; i < index; i++) {
            accounts[i].printAccounts();
        }
        System.out.println("====================");
    }
}
