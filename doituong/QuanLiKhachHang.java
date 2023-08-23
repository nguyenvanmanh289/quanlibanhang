package doituong;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuanLiKhachHang extends Quanli{
    private KhachHang buyer;
    
    public QuanLiKhachHang(){}

    public ArrayList<KhachHang> GetListBuyer(){
        try {
            FileReader fr = new FileReader("./doituong/database/khachhang.txt");
            BufferedReader bf = new BufferedReader(fr);

            //luu danh sach tung chuoi trong database
            ArrayList<String> list = new ArrayList<String>();
            String text ="";
            while ((text = bf.readLine()) != null) {
                list.add(text);
            }

            //luu danh sach cac mang chua tung thong tin cac khac hang
             ArrayList<String[]> listArrayBuyer = new ArrayList<String[]>();
            for (String string : list) {
                listArrayBuyer.add(string.split("-"));
            }

            //tao danh sach cac doi tuong khac hang va tra lai 
            ArrayList<KhachHang> listBuyer = new ArrayList<KhachHang>();
            for (String[] buyer : listArrayBuyer) {
                listBuyer.add(new KhachHang(buyer[0],buyer[1],buyer[2],buyer[3],buyer[4]));
            }
            return listBuyer;

        } catch (IOException e) {
            System.out.print("co loi khi get danh sach nhan vien!\n");
            return null;
        }
    }

    public String BuyerToString(KhachHang a){
        String string = a.getBuyerCode()+"-"+a.getName()+"-"+a.getNumberPhone()+"-"+a.getEmail()+"-"+a.getAddress();
        return string;
    }

    //tim khach hang
    public KhachHang SearchBuyer() {
        QuanLiKhachHang manager = new QuanLiKhachHang();
        ArrayList<KhachHang> buyers = new ArrayList<KhachHang>();
        buyers = manager.GetListBuyer();
        Scanner sc = new Scanner(System.in);
        KhachHang x = null;
        do {
            System.out.print("nhap vao ma Khach Hang || sdt || email de tim kiem :\n");
            String a = sc.nextLine();

            boolean isFound = false;
                for(KhachHang buyer : buyers) {
                    if(a.equals(buyer.getBuyerCode()) ||
                            a.equals(buyer.getNumberPhone()) ||
                            a.equals(buyer.getEmail())
                    ){
                        System.out.print(
                                "Ket qua tim kiem theo: " + a + "\n\n" +
                                        "   Khach hang: " + buyer.getName() + "\n" +
                                        "   ma Khach hang: " + buyer.getBuyerCode() + "\n");
                        x = buyer;
                        isFound = true;
                        break;
                    }
                }
                if (!isFound) {
                System.out.print("Khong tim thay Khach Hang theo: " + a + "\n");
        }
        } while (x == null);  
        return x;
    }

    public KhachHang InputBuyer(){
        Scanner sc = new Scanner(System.in);
        System.out.print("nhap vao Ma Khach Hang: ");
        String buyerCode = sc.nextLine();
        System.out.print("  ho va ten: ");
        String name = sc.nextLine();
        System.out.print("  sdt: ");
        String numberPhone = sc.nextLine();
        System.out.print("  email: ");
        String email = sc.nextLine();
        System.out.print("  dia chi: ");
        String address = sc.nextLine();
        return new KhachHang(buyerCode, name, numberPhone, email, address);
    }

    public void CheckAndAddBuyer(KhachHang a){
        QuanLiKhachHang manager = new QuanLiKhachHang();
        ArrayList<KhachHang> buyers = new ArrayList<KhachHang>();
        buyers = manager.GetListBuyer();

        boolean isValidNumberPhone = false;
        boolean isValidEmail = false;
        boolean isFound0 = false;

        //check valid name
        boolean checkNumberInName = false;
        String name = a.getName();

        for (char c : name.toCharArray()) {
            if (Character.isDigit(c)) {
                checkNumberInName = true;
            }
        }

        // check valid email
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(a.getEmail());
        if (matcher.matches()) {
            isValidEmail = true;
        }
        // check valid numberphone
        if ((a.getNumberPhone().length() == 10 ) && a.getNumberPhone().split("")[0].equals("0")){
            isValidNumberPhone = true;
        }

        for (KhachHang aBuyer : buyers) {
            boolean isFound = false;
            if(aBuyer.getBuyerCode().equals(a.getBuyerCode())){
                isFound = true; //thay trung ma khach hang thoat vong lap
                isFound0 = true; // dieu khien goi lai pt nhap vao lai
                
            };
            if(isFound){
                break;
            }
        }

        if(isFound0 || checkNumberInName || !isValidEmail || !isValidNumberPhone){
            System.out.print("\n\n");
            if(checkNumberInName){
                System.out.print("  ten khong the chua so!\n");
            }
            if(isFound0){
                System.out.print("  ma nhan vien ["+a.getBuyerCode()+"] da ton tai!\n");
            }
            if(!isValidEmail){
                System.out.print("  email khong hop le!\n");
            }
            if(!isValidNumberPhone){
                System.out.print("  sdt khong hop le\n\n");
            }
            System.out.print("Vui long nhap lai thong tin!\n");
            manager.CheckAndAddBuyer(manager.InputBuyer());
        }
        else{
            String string = manager.BuyerToString(a);
            
            try {
                FileWriter fw = new FileWriter("./doituong/database/khachhang.txt",true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(string);
                bw.newLine();
                bw.close();
                System.out.print("\nda them thanh cong 1 Khach hang\n"+string);
                
                
            } catch (IOException e) {
                System.out.print("co loi say ra trong qua trinh them Khach hang!");
                e.printStackTrace();
            }
        }
    }

    public void CheckAndEditBuyer(KhachHang a){

        QuanLiKhachHang manager = new QuanLiKhachHang();
        System.out.print("BAT DAU SUA THONG TIN , LUU Y! sua thong tin nao ghi thong tin do , khong sua thi bo trong\n\n");
        KhachHang x = manager.InputBuyer();
        if(x.getBuyerCode().equals("")) x.setBuyerCode(a.getBuyerCode());
        if(x.getName().equals("")) x.setName(a.getName());
        if(x.getNumberPhone().equals("")) x.setNumberPhone(a.getNumberPhone());
        if(x.getEmail().equals("")) x.setEmail(a.getEmail());
        if(x.getAddress().equals("")) x.setAddress(a.getAddress());
        
        boolean resetInput = true;
        boolean isValidNumberPhone = false;
        boolean isValidEmail = false;
        boolean isEqualsBuyerCode = false;

        
        if(x.getBuyerCode().equals(a.getBuyerCode())){
                isEqualsBuyerCode = true;
                resetInput = true;
        };
        //check name
        boolean checkNumberInName = false;
        String name = x.getName();
        
        for (char c : name.toCharArray()) {
                if (Character.isDigit(c)) {
                    checkNumberInName = true;
                }
        }
        
        // check valid email
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(x.getEmail());
        if (matcher.matches()) {
            isValidEmail = true;
        }
        // check valid numberphone
        if ((x.getNumberPhone().length() == 10 ) && x.getNumberPhone().split("")[0].equals("0")) {
            isValidNumberPhone = true;
            resetInput = false;
        }

        if(resetInput || !isEqualsBuyerCode || checkNumberInName){
            System.out.print("\n\n");
            if(checkNumberInName){
                System.out.print("  ten khong the chua so!\n");
            }
            if(!isEqualsBuyerCode){
                System.out.print("  Khong the sua ma nhan vien !\n");
            }
            if(!isValidEmail){
                System.out.print("  email khong hop le!\n");
            }
            if(!isValidNumberPhone){
                System.out.print("  sdt khong hop le\n\n");
            }
            System.out.print("Vui long nhap lai thong tin!\n");
            manager.CheckAndEditBuyer(manager.InputBuyer());
        }
        else{
            String string = manager.BuyerToString(x);
            
            try {
                FileWriter fw = new FileWriter("./doituong/database/khachhang.txt",true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(string);
                bw.newLine();
                bw.close();
                System.out.print("\nda sua thanh cong 1 Khach Hang\n");
                System.out.print("  before: "+manager.BuyerToString(a)+"\n");
                System.out.print("  after: "+manager.BuyerToString(x)+"\n");
                                
            } catch (IOException e) {
                System.out.print("co loi say ra trong qua trinh sua khac hang!");
                e.printStackTrace();
            }   
        }
    }

    public void EditBuyerInfo(){
        QuanLiKhachHang manager = new QuanLiKhachHang();
        System.out.print("===========================================\nDe sua thong tin nhan vien \n");
        ArrayList<KhachHang> buyers = new ArrayList<KhachHang>();
        buyers = manager.GetListBuyer();

        //tim nhan vien can sua
        KhachHang x = manager.SearchBuyer();
       
        int index=0;
        for (KhachHang buyer : buyers) {
            if(x.getBuyerCode().equals(buyer.getBuyerCode()) || 
                x.getNumberPhone().equals(buyer.getNumberPhone()) ||
                x.getEmail().equals(buyer.getEmail())
            ){
               break;
            }
            index++;
        }
        if (buyers.remove(buyers.get(index))) {
            // xoa data base de chuan bi ghi lai du lieu
            try {
                FileWriter fw = new FileWriter("./doituong/database/khachhang.txt");
                BufferedWriter bf = new BufferedWriter(fw);
                bf.write("");
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        for (KhachHang buyer : buyers) {
            System.out.print(manager.BuyerToString(buyer)+"\n");
            try {
                FileWriter fw = new FileWriter("./doituong/database/khachhang.txt",true);
                BufferedWriter bf = new BufferedWriter(fw);
                bf.write(manager.BuyerToString(buyer));
                bf.newLine();
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
         manager.CheckAndEditBuyer(x);
    }
    
    public void DeleteBuyer(){
        QuanLiKhachHang manager = new QuanLiKhachHang();
        ArrayList<KhachHang> buyers = new ArrayList<KhachHang>();
        buyers = manager.GetListBuyer();
        KhachHang x = manager.SearchBuyer();
        if(x==null) System.exit(0);
        int index = 0;
        for (KhachHang buyer : buyers) {
            if(buyer.getBuyerCode().equals(x.getBuyerCode()) || 
                buyer.getNumberPhone().equals(x.getNumberPhone()) ||
                buyer.getEmail().equals(x.getEmail())    
            ){
                break;
            }
            index++;
        }
        //xoa khach hang tim dc
        buyers.remove(buyers.get(index));
        try {
            FileWriter fw = new FileWriter("./doituong/database/khachhang.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("");
            bw.close();
        } catch (IOException e) {
            System.out.print(" loi xay ra khi xoa danh sach!");
        }

        //ghi lai danh sach moi
        System.out.print("  Xoa thanh cong 1 khach hang co ma khach hang: "+x.getBuyerCode()+"\n");
        for (KhachHang buyer : buyers) {
            try{
                FileWriter fw = new FileWriter("./doituong/database/khachhang.txt",true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(manager.BuyerToString(buyer));
                bw.newLine();
                bw.close();
            }catch(IOException e){
                System.out.print(" loi xay ra khi xoa Khach hang!");
            }
        }

    }
}

