package com.example.manujayapremathilaka.market;

public class Items {
    private String itemNo;
    private String price;
    private String itemPic;
    private String quantity;
    private String NIC;

    public Items() {
    }

    public Items(String itemNo, String price, String itemPic) {
        this.itemNo = itemNo;
        this.price = price;
        this.itemPic = itemPic;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

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
}
