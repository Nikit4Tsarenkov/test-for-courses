public class BankCard {
    private String individualNumber;
    private String pin;
    private Double balance;
    private boolean blockStatus;

    public BankCard(String individualNumber, String pin, Double balance, boolean blockStatus) throws Exception
    {
        setIndividualNumber(individualNumber);
        setPin(pin);
        this.balance = balance;
        this.blockStatus = blockStatus;
    }

    public String getIndividualNumber() //NUBMER
    {
        return individualNumber;
    }

    private void setIndividualNumber(String individualCardNumber) throws Exception
    {
        if(isValidNum(individualCardNumber))
        {
            this.individualNumber = individualCardNumber;
        }
        else
        {
            throw new Exception("Invalid individual card number");
        }
    }

    private boolean isValidNum(String individualCardNumber)
    {
        return BankCardVerification.isValidNum(individualCardNumber);
    }

    private void setPin(String pin) throws Exception //PIN
    {
        if(isValidPin(pin))
        {
            this.pin = pin;
        }
        else
        {
            throw new Exception("Invalid PIN");
        }
    }

    private boolean isValidPin(String pin)
    {
        return BankCardVerification.isValidPin(pin);
    }

    public Double getBankCardBalance() //BALANCE
    {
        return this.balance;
    }

    public void topUpTheBanalce(Double amount) throws Exception
    {
        if(this.balance + amount > 1000000)
        {
            throw new Exception("You have reached the limit of balance. (1000000)");
        }
        else
        {
            balance += amount;
        }
    }

    public void withdrawMoney(Double amount) throws Exception
    {
        if(this.balance - amount < 0)
        {
            throw new Exception("Insufficient funds to complete the transaction.");
        }
        else
        {
            balance -= amount;
        }
    }

    public boolean login(String pin)
    {
        return this.pin.equals(pin);
    }

    public void block()
    {
        this.blockStatus = true;
    }

    public boolean blockStatus()
    {
        return blockStatus;
    }

    @Override public String toString()
    {
        return individualNumber + " " + pin + " " + balance + " " + blockStatus;
    }
}
