package groceryListJava;

public class Grocery {
    protected int id;
    protected String item;
    protected String amount;
    protected String isle;
    protected boolean hasCoupon;
    protected boolean onSale;
    protected String couponExpiration;
    protected String saleEnd;

    public Grocery() {

    }

    public Grocery(int id) {
        this.id = id;
    }

    public Grocery(int id, String item, String amount, String isle, boolean hasCoupon, boolean onSale) {
        this.item = item;
        this.id = id;
        this.amount = amount;
        this.isle = isle;
        this.hasCoupon = hasCoupon;
        this.onSale = onSale;
    }

    public Grocery(String item, String amount, String isle, boolean hasCoupon, boolean onSale) {
        this.item = item;
        this.amount = amount;
        this.isle = isle;
        this.hasCoupon = hasCoupon;
        this.onSale = onSale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getIsle() {
        return isle;
    }

    public void setIsle(String isle) {
        this.isle = isle;
    }

    public boolean getHasCoupon() {
        return hasCoupon;
    }

    public void setHasCoupon(boolean hasCoupon) {
        this.hasCoupon = hasCoupon;
    }

    public boolean getOnSale() {
        return onSale;
    }

    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
    }
}
