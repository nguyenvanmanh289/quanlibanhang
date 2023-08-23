import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import doituong.EmployeeLogin;
import doituong.KhachHang;
import doituong.Md5;
import doituong.NhanVien;
import doituong.QuanLiKhachHang;
import doituong.QuanLiNhanVien;
import doituong.QuanLiSanPham;
import doituong.Quanli;
import doituong.SanPham;
import doituong.Ui;

public class main {
    public static void main(String args[]){
        System.out.print("========HE THONG QUAN LI BAN HANG=========\n");
    
        EmployeeLogin loginer = new EmployeeLogin();
        loginer.CheckPayload(loginer.Input());
        Ui.UI();

    }
}
