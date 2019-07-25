import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Atm {
    private static Atm status;
    private Double balance;
    private BankCard currentBankCard;

    private Atm()
    {
        currentBankCard = null;
        balance = getBalance("BankCardBalance.txt");
    }

    public Double getBankCardBalance()
    {
        return balance;
    }

    public static Atm getObject()
    {
        if(status == null)
        {
            status = new Atm();
        }
        return status;
    }

    public void enterBankCard(BankCard bankCard) throws Exception
    {
        if(bankCard.blockStatus())
        {
            throw new Exception("Your card is blocked.");
        }

        currentBankCard = bankCard;
        Scanner in = new Scanner(System.in);
        Integer inputPin = 0;

        while (inputPin < 3)
        {
            System.out.print("Please enter you PIN code: ");
            String pin = in.nextLine();
            if (bankCard.login(pin))
            {
                System.out.println("Success.");
                return;
            }
            else
            {
                System.out.println("Invalid PIN code.");
                inputPin++;
                continue;
            }
        }
        currentBankCard.block();
        throw new Exception("Your card is blocked. You have used allowed number of attemps. ");
    }

    public Double checkCurrentBanalce()
    {
        return currentBankCard.getBankCardBalance();
    }

    public void TopUpTheBalance(Double amount)
    {
        try
        {
            currentBankCard.topUpTheBanalce(amount);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void withdrawMoney(Double amount)
    {
        try
        {
            currentBankCard.withdrawMoney(amount);
            balanceChanges(amount);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void balanceChanges(Double amount) throws Exception
    {
        if(this.balance - amount < 0)
        {
            throw new Exception("Insufficient funds to complete the transaction");
        }
        else
        {
            this.balance -= amount;
        }
    }

    private Double getBalance(String fileName)
    {
        Double balance = 0.0;
        try(FileReader fr = new FileReader(fileName))
        {
            Scanner scan = new Scanner(fr);
            while(scan.hasNextDouble())
            {
                balance = scan.nextDouble();
            }
        }
        catch (IOException e)
        {

        }
        return balance;
    }
}