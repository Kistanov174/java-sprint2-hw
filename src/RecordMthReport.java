public class RecordMthReport {
    String itemName;
    boolean isExpense;
    int month, quantity, sumOfOne;

    public RecordMthReport(int month, String itemName, boolean isExpense, int quantity, int sumOfOne) {
        this.month = month;
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }
}
