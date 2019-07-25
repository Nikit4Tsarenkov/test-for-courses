public class BankCardOwner {
    private String ownerName;
    private BankCard bankCard;
    private Atm currentAtm;

    public BankCardOwner(String ownerName, BankCard bankCard)
    {
        this.ownerName = ownerName;
        this.bankCard = bankCard;
    }

    public void enterBankCard(Atm atm, String individualCardNumber) throws Exception
    {
        if(individualCardNumber.equals(this.bankCard.getIndividualNumber()))
        {
            currentAtm = atm;
            currentAtm.enterBankCard(this.bankCard);
        }
        else
        {
            throw new Exception("Invalid individual card number");
        }
    }

    public Double currentBalance()
    {
        return currentAtm.checkCurrentBanalce();
    }

    public void topUpTheBalance(Double amount)
    {
        currentAtm.TopUpTheBalance(amount);
    }
    public void withdrawMoney(Double amount)
    {
        currentAtm.withdrawMoney(amount);
    }

    @Override public String toString()
    {
        return ownerName + " " + bankCard.toString();
    }
}
