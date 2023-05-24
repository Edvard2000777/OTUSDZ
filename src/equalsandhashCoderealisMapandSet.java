import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Client{
private String name;
private  int age;

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
}
class Account{
private int accountId;
private List<Integer> coins;

    public Account(int accountId) {
        this.accountId = accountId;
        this.coins = new ArrayList<>();
    }

    public int getAccountId() {
        return accountId;
    }
    public void addCoins(int amount){
        coins.add(amount);
    }
}
class Bank{
    private Map<Client,List<Account>> clientAccounts;
    public  Bank(){
        clientAccounts= new HashMap<>();
    }

    public void addClient(Client client){
        clientAccounts.put(client, new ArrayList<>());
    }
    public void  addAccount(Client client, Account account){
        List<Account> accounts= clientAccounts.get(client);
        accounts.add(account);
    }
    
    
    public List<Account> getAccounts(Client client) {
        return clientAccounts.getOrDefault(client, new ArrayList<>());
    }

    public Client findClient(Account account) {
        for (Map.Entry<Client, List<Account>> entry : clientAccounts.entrySet()) {
            if (entry.getValue().contains(account)) {
                return entry.getKey();
            }
        }
        return null;
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
