package com.example.manujayapremathilaka.market;

public class Items {
    private String itemNo;
    private String price;
    private String itemPic;
    //private boolean permission;

    public Items() {
    }

    public Items(String itemNo, String price, String itemPic) {
        this.itemNo = itemNo;
        this.price = price;
        this.itemPic = itemPic;
        //this.permission = permission;
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

//    public boolean getPermission() {
//        return permission;
//    }
//
//    public void setPermission(boolean permission) {
//        this.permission = permission;
//    }
}
