package doituong;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Quanli {
    private NhanVien nv;
    private KhachHang kh;
    private SanPham sp;


    public Quanli(){
        
    }
    public Quanli(NhanVien nv) {
        this.nv = nv;
    }
    public Quanli(KhachHang kh){
        this.kh = kh;
    }
    public Quanli(SanPham sp){
        this.sp = sp;
    }

    public NhanVien getNv() {
        return this.nv;
    }

    public void setNv(NhanVien nv) {
        this.nv = nv;
    }

    public KhachHang getKh() {
        return this.kh;
    }

    public void setKh(KhachHang kh) {
        this.kh = kh;
    }

    public SanPham getSp() {
        return this.sp;
    }

    public void setSp(SanPham sp) {
        this.sp = sp;
    }

    //======more method======//

}