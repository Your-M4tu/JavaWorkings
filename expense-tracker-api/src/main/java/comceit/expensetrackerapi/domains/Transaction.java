package comceit.expensetrackerapi.domains;

public class Transaction {
    private Integer categoryId;
    private Integer transactionId;
    private Integer userId;
    private Double amount;
    private String note;
    private Long transactionDate;

    public Transaction(Integer categoryId, Integer transactionId, Integer userId, Double amount, String note, Long transactionDate) {
        this.categoryId = categoryId;
        this.transactionId = transactionId;
        this.userId = userId;
        this.amount = amount;
        this.note = note;
        this.transactionDate = transactionDate;
    }



    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Double getAmount() {
        return amount;
    }

    public String getNote() {
        return note;
    }

    public Long getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setTransactionDate(Long transactionDate) {
        this.transactionDate = transactionDate;
    }
}
