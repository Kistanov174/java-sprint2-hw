public class RecordMthReport {
    public String itemName;
    public boolean isExpense;
    public int month, quantity, sumOfOne;

    public RecordMthReport(int month, String itemName, boolean isExpense, int quantity, int sumOfOne) {
        this.month = month;
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }
}
