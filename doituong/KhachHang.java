package doituong;

import java.util.Scanner;

public class KhachHang{
    private String buyerCode ,name , address , email, numberPhone;

    public KhachHang(String buyerCode, String name, String numberPhone, String email, String address) {
        this.buyerCode = buyerCode;
        this.name = name;
        this.address = address;
        this.email = email;
        this.numberPhone = numberPhone;
    }
    //======get/set========//

    public String getBuyerCode() {
        return this.buyerCode;
    }
    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberPhone() {
        return this.numberPhone;
    }
    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    //=====more method====//


}