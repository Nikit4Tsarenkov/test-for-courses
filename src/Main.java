import javax.swing.plaf.IconUIResource;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        Atm atm = Atm.getObject();
        BankCardOwner owner = null;
        String fileName = "BankCardOwner.txt";

        Scanner in = new Scanner(System.in);
        System.out.println("Belsarusbank");
        while(true)
        {
            System.out.print("Enter your card number: ");
            String individualCardNumber = in.nextLine();
            owner = getOwner(fileName);


        try
        {
            owner.enterBankCard(atm, individualCardNumber);
            break;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            saveOwnerData(fileName, owner, atm);
            return;
            }
        }

        mainMenu: while(true)
        {
            System.out.println("1. Check current balance.");
            System.out.println("2. Top up the balance.");
            System.out.println("3. Withdraw.");
            System.out.println("4. Exit.");

            String choice = in.nextLine();

            switch (choice)
            {
                case "1":
                    System.out.println("Balance: " + owner.currentBalance());
                    break;

                case "2":
                    while(true)
                    {
                        System.out.println("Enter amount to top up the balance: ");
                        Double Amount = Double.parseDouble(in.nextLine());
                        if(Amount == 0)
                        {
                            System.out.println("Check the input please.");
                            continue;
                        }
                        else
                        {
                            owner.topUpTheBalance(Amount);
                            System.out.println("operation completed successfully, current balance is: " + owner.currentBalance());
                            break;
                        }
                    }
                case "3":
                    while(true)
                    {
                        System.out.println("Enter amount to withdraw: ");
                        Double Amount = Double.parseDouble(in.nextLine());
                        if(Amount == 0)
                        {
                            System.out.println("Check the input please");
                            continue;
                        }
                        else
                        {
                            owner.withdrawMoney(Amount);
                            System.out.println("operation completed successfully, current balance is: " + owner.currentBalance());
                            break;
                        }
                    }
                case "4":
                    break mainMenu;
                default:
                    System.out.println("Invalid operation.");
                    break;
            }
        }
        saveOwnerData(fileName, owner, atm);
    }

    public static BankCardOwner getOwner(String fileName)
    {
        BankCard bankCard = null;
        String OwnerName = null;
        try(FileReader fr = new FileReader(fileName))
        {
            Scanner fs = new Scanner(fr);
            var value = fs.nextLine().split(" ");
            OwnerName = value[0];
            bankCard = new BankCard
                    (
                            value[1], //Individual Card Number
                            value[2], //PIN code
                            Double.parseDouble(value[3]), // Current balance
                            Boolean.parseBoolean(value[4]) // Card status
                    );

        }
        catch (IOException ioe)
        {
            System.out.println(ioe.getMessage());
        }
        catch (Exception e)
        {
            System.out.println((e.getMessage()));
        }

        return new BankCardOwner(OwnerName, bankCard);
    }

    public static void saveOwnerData(String fileName, BankCardOwner owner, Atm atm)
    {
        try(FileWriter fw = new FileWriter(fileName, false))
        {
            fw.write(owner.toString());
        }
        catch (IOException ioe)
        {
            System.out.println(ioe.getMessage());
        }

        try(FileWriter fw = new FileWriter("BankCardBalance.txt"))
        {
            fw.write(atm.getBankCardBalance().toString());
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
}
