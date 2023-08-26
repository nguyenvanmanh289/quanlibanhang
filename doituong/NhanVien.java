package doituong;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NhanVien {
    private String employeeCode , name , email , passwork , address;
    private String sex , state , group;
    private String numberPhone;

    public NhanVien(String employeeCode, String name, String numberPhone, String email, String passwork, String address, String sex, String group, String state) {
        this.employeeCode = employeeCode;
        this.name = name;
        this.email = email;
        this.passwork = passwork;
        this.address = address;
        this.sex = sex;
        this.state = state;
        this.group = group;
        this.numberPhone = numberPhone;
    }

    //=====get/set=====//

    public String getEmployeeCode() {
        return this.employeeCode;
    }
    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswork() {
        return this.passwork;
    }
    public void setPasswork(String passwork) {
        this.passwork = passwork;
    }

    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getState() {
        return this.state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public String getGroup() {
        return this.group;
    }
    public void setGroup(String group) {
        this.group = group;
    }

    public String getNumberPhone() {
        return this.numberPhone;
    }
    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    //=====more method====//
    public void GetIncomeTrackbyBill(){
        HoaDon biller = new HoaDon();
        ArrayList<HoaDon> bills = new ArrayList<HoaDon>();
        bills = biller.GetListHoaDon();

        float sumIncome = 0;
        for (HoaDon bill : bills) {
            if(employeeCode.equals(bill.getEmployeeCode())){
                sumIncome += bill.SumPay();
            }
        }
         System.out.print("Doanh thu nhan vien "+this.name+" la:" +biller.partPrice(Float.toString(sumIncome))+"\n");
    }
 
}
