import java.util.*;

class Client {
    private String name;
    private int age;

    public Client(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Client client = (Client) obj;
        return age == client.age && Objects.equals(name, client.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}

class Account {
    private int accountId;
    private List<Integer> coins;

    public Account(int accountId) {
        this.accountId = accountId;
        this.coins = new ArrayList<>();
    }

    public int getAccountId() {
        return accountId;
    }

    public void addCoins(int amount) {
        coins.add(amount);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Account account = (Account) obj;
        return accountId == account.accountId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId);
    }
}

class Bank {
    private Map<Client, List<Account>> clientAccounts;
    private Map<Account, Client> accountClientMapping;

    public Bank() {
        clientAccounts = new HashMap<>();
        accountClientMapping = new HashMap<>();
    }

    public void addClient(Client client) {
        clientAccounts.put(client, new ArrayList<>());
    }

    public void addAccount(Client client, Account account) {
        List<Account> accounts = clientAccounts.get(client);
        accounts.add(account);
        accountClientMapping.put(account, client);
    }

    public List<Account> getAccounts(Client client) {
        return clientAccounts.getOrDefault(client, new ArrayList<>());
    }

    public Client findClient(Account account) {
        return accountClientMapping.get(account);
    }
}

public class equalsandhashCoderealisMapandSet {
    public static void main(String[] args) {
        Client client1 = new Client("John", 30);
        Client client2 = new Client("Alice", 25);

        Account account1 = new Account(1);
        Account account2 = new Account(2);
        Account account3 = new Account(3);

        // Добавление клиентов и счетов в банк
        Bank bank = new Bank();
        bank.addClient(client1);
        bank.addClient(client2);

        bank.addAccount(client1, account1);
        bank.addAccount(client1, account2);
        bank.addAccount(client2, account3);

        // Получение всех счетов по клиенту
        List<Account> client1Accounts = bank.getAccounts(client1);
        System.out.println("Счета клиента 1:");
        for (Account account : client1Accounts) {
            System.out.println(account.getAccountId());
        }

        // Поиск клиента по счету
        Client client = bank.findClient(account2);
        if (client != null) {
            System.out.println("Клиент для счета 2: " + client.getName());
        } else {
            System.out.println("Счет не найден");
        }
    }
}
