package com.example.manujayapremathilaka.market;

public class Items {
    private String ID;
    private String price;
    private String itemPic;
    private String name;
    private String qty;
    private String quantity;
    private String NIC;

    public Items() {
    }

    public Items(String itemNo, String price, String id) {
        this.ID = id;
        this.price = price;
        this.itemPic = itemPic;
    }

    public String getID() { return ID; }

    public void setID(String ID) { this.ID = ID; }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getItemPic() {
        return itemPic;
    }

    public void setItemPic(String itemPic) {
        this.itemPic = itemPic;
    }

    public String getNIC() { return NIC; }

    public void setNIC(String NIC) { this.NIC = NIC; }

    public String getQuantity() { return quantity; }

    public void setQuantity(String quantity) { this.quantity = quantity; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
