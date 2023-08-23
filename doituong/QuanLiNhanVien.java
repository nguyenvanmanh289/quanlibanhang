package doituong;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class QuanLiNhanVien extends Quanli {
    public QuanLiNhanVien(NhanVien nv){
        super(nv);
    }

    public QuanLiNhanVien() {
    }

    public NhanVien SearchEmployee() {
        QuanLiNhanVien manager = new QuanLiNhanVien();
        ArrayList<NhanVien> employees = new ArrayList<NhanVien>();
        employees = manager.GetListEmployee();
        Scanner sc = new Scanner(System.in);
        NhanVien x = null;
        do {
            System.out.print("nhap vao ma nhan vien || sdt || email de tim kiem :\n");
            String a = sc.nextLine();
            boolean isFound = false;
            for (NhanVien employee : employees) {
                if (a.equals(employee.getEmployeeCode()) ||
                        a.equals(employee.getNumberPhone()) ||
                        a.equals(employee.getEmail())) {
    
                    System.out.print(
                            "Ket qua tim kiem theo: " + a + "\n\n" +
                                    "   nhan vien: " + employee.getName() + "\n" +
                                    "   ma nhan vien: " + employee.getEmployeeCode() + "\n");
                    x = employee;
                    isFound=true;
                    break;
                }

            }if(!isFound)System.out.print("Khong tim thay nhan vien theo: "+a+"\n");
        } while (x == null);
        return x;
    }

    
    public ArrayList<NhanVien> GetListEmployee(){
        try {
            FileReader fr = new FileReader("./doituong/database/nhanvien.txt");
            BufferedReader bf = new BufferedReader(fr);

            ArrayList<String> list = new ArrayList<String>();
            String text ="";

            while ((text = bf.readLine()) != null) {
                list.add(text);
            }
             ArrayList<String[]> list1 = new ArrayList<String[]>();
            for (String string : list) {
                list1.add( string.split("-") );
        
            }
            ArrayList<NhanVien> listEmployee = new ArrayList<NhanVien>();
            for (String[] employee : list1) {       
                listEmployee.add(new NhanVien(employee[0], employee[1],employee[2],employee[3], employee[4], employee[5], employee[6], employee[7], employee[8]));
            }
            return listEmployee;

        } catch (IOException e) {
            e.getMessage();
            return null;
        }
    }

    public String EmployeeToString(NhanVien a){
        String string = a.getEmployeeCode()+"-"+a.getName()+"-"+a.getNumberPhone()+
        "-"+a.getEmail()+"-"+a.getPasswork()+"-"+a.getAddress()+"-"+a.getSex()+"-"+
        a.getGroup()+"-"+a.getState();
        return string;
    }


    public NhanVien InputEmployee(){
        Scanner sc = new Scanner(System.in);
        System.out.print("nhap vao Ma nhan vien: ");
        String employeeCode = sc.nextLine();
        System.out.print("  ho va ten: ");
        String name = sc.nextLine();
        System.out.print("  sdt: ");
        String numberPhone = sc.nextLine();
        System.out.print("  email: ");
        String email = sc.nextLine();
        System.out.print("  passwork: ");
        String passwork = Md5.encryptToMD5(sc.nextLine());
        System.out.print("  dia chi: ");
        String address = sc.nextLine();
        System.out.print("  gioi tinh: ");
        String sex = sc.nextLine();
        System.out.print("  group: ");
        String group = sc.nextLine();
        System.out.print("  trang thai: ");
        String state = sc.nextLine();
        return new NhanVien(employeeCode, name, numberPhone, email, passwork, address, sex, group, state);
    }

    //add 1 nhan vien
    public void CheckAndAddEmployee(NhanVien a){
        QuanLiNhanVien manager = new QuanLiNhanVien();
        ArrayList<NhanVien> employees = new ArrayList<NhanVien>();
        employees = manager.GetListEmployee();

        boolean resetInput = true;
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
            resetInput = false;
        }
        // check valid numberphone
        if ((a.getNumberPhone().length() == 10 ) && a.getNumberPhone().split("")[0].equals("0")) {
            isValidNumberPhone = true;
            resetInput = false;
        }

        for (NhanVien aEmployee : employees) {
            boolean isFound = false;
            if(aEmployee.getEmployeeCode().equals(a.getEmployeeCode())){
                isFound = !isFound;
                isFound0 = !isFound0;
                
            };
            if(isFound){
                resetInput = true;
                break;
            }
        }

        if(resetInput || checkNumberInName){
            System.out.print("\n\n");
            if(checkNumberInName){
                System.out.print("  ten khong the chua so!\n");
            }
            if(isFound0){
                System.out.print("  ma nhan vien ["+a.getEmployeeCode()+"] da ton tai!\n");
            }
            if(!isValidEmail){
                System.out.print("  email khong hop le!\n");
            }
            if(!isValidNumberPhone){
                System.out.print("  sdt khong hop le\n\n");
            }
            System.out.print("Vui long nhap lai thong tin!\n");
            manager.CheckAndAddEmployee(manager.InputEmployee());
        }
        else{
            String string = manager.EmployeeToString(a);
            
            try {
                FileWriter fw = new FileWriter("./doituong/database/nhanvien.txt",true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(string);
                bw.newLine();
                bw.close();
                System.out.print("\nda them thanh cong 1 nhan vien\n"+string);
                
            } catch (IOException e) {
                System.out.print("co loi say ra trong qua trinh them nhan vien!");
                e.printStackTrace();
            }
        }
    }

    //kiem tra mot doi tuong nhan vien co hop le khong , ok ghi vao database
    public void CheckAndEditEmployee(NhanVien a){

        QuanLiNhanVien manager = new QuanLiNhanVien();
        System.out.print("BAT DAU SUA THONG TIN , LUU Y! sua thong tin nao ghi thong tin do , khong sua thi bo trong\n\n");
        NhanVien x = manager.InputEmployee();
        if(x.getEmployeeCode().equals("")) x.setEmployeeCode(a.getEmployeeCode());
        if(x.getName().equals("")) x.setName(a.getName());
        if(x.getNumberPhone().equals("")) x.setNumberPhone(a.getNumberPhone());
        if(x.getEmail().equals("")) x.setEmail(a.getEmail());
        if(x.getAddress().equals("")) x.setAddress(a.getAddress());
        if(x.getSex().equals("")) x.setSex(a.getSex());
        if(x.getGroup().equals("")) x.setGroup(a.getGroup());
        if(x.getState().equals("")) x.setState(a.getState());
        
        boolean isValidNumberPhone = false;
        boolean isValidEmail = false;
        boolean isEqualsEmployeeCode = false;

        
        if(x.getEmployeeCode().equals(a.getEmployeeCode())){
                isEqualsEmployeeCode = true;    
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
        }

        if(!isValidEmail || !isEqualsEmployeeCode || checkNumberInName || !isValidNumberPhone){
            System.out.print("\n\n");
            if(checkNumberInName){
                System.out.print("  ten khong the chua so!\n");
            }
            if(!isEqualsEmployeeCode){
                System.out.print("  Khong the sua ma nhan vien !\n");
            }
            if(!isValidEmail){
                System.out.print("  email khong hop le!\n");
            }
            if(!isValidNumberPhone){
                System.out.print("  sdt khong hop le\n\n");
            }
            System.out.print("Vui long nhap lai thong tin!\n");
            manager.CheckAndEditEmployee(manager.InputEmployee());
        }
        else{
            String string = manager.EmployeeToString(x);
            
            try {
                FileWriter fw = new FileWriter("./doituong/database/nhanvien.txt",true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(string);
                bw.newLine();
                bw.close();
                System.out.print("\nda sua thanh cong 1 nhan vien\n");
                System.out.print("  before: "+manager.EmployeeToString(a)+"\n");
                System.out.print("  after: "+manager.EmployeeToString(x)+"\n");
                                
            } catch (IOException e) {
                System.out.print("co loi say ra trong qua trinh them nhan vien!");
                e.printStackTrace();
            }   
        }
    }

    // xoa ptu can sua va ghi lai danh sach moi tru phan tu da xoa do . sau do goi method check o tren them nhan vien da sua
    public void EditEmployeeInfo(){
        QuanLiNhanVien manager = new QuanLiNhanVien();
        System.out.print("===========================================\nDe sua thong tin nhan vien \n");
        ArrayList<NhanVien> employees = new ArrayList<NhanVien>();
        employees = manager.GetListEmployee();

        //tim nhan vien can sua
        NhanVien x = manager.SearchEmployee();
       
        int index=0;
        for (NhanVien employee : employees) {
            System.out.print(manager.EmployeeToString(employee)+"\n");
            if(x.getEmployeeCode().equals(employee.getEmployeeCode()) || 
                x.getNumberPhone().equals(employee.getNumberPhone()) ||
                x.getEmail().equals(employee.getEmail())
            ){
                System.out.print(index);
               break;
            }
            index++;
        }
        employees.remove(employees.get(index)) ;
            // xoa data base cu de chuan bi ghi lai du lieu moi
            try {
                FileWriter fw = new FileWriter("./doituong/database/nhanvien.txt");
                BufferedWriter bf = new BufferedWriter(fw);
                bf.write("");
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        
        
        //ghi vao danh sach da xoa nhan vien can sua
        for (NhanVien employee : employees) {
            System.out.print(manager.EmployeeToString(employee)+"\n");
            try {
                FileWriter fw = new FileWriter("./doituong/database/nhanvien.txt",true);
                BufferedWriter bf = new BufferedWriter(fw);
                bf.write(manager.EmployeeToString(employee));
                bf.newLine();
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
         manager.CheckAndEditEmployee(x);

    }     

    public void DeleteEmployee(){
        QuanLiNhanVien manager = new QuanLiNhanVien();
        ArrayList<NhanVien> employees = new ArrayList<NhanVien>();
        employees = manager.GetListEmployee();
        NhanVien x = manager.SearchEmployee();
        if(x==null) System.exit(0);
        int index = 0;
        for (NhanVien employee : employees) {
            if(employee.getEmployeeCode().equals(x.getEmployeeCode()) || 
                employee.getNumberPhone().equals(x.getNumberPhone()) ||
                employee.getEmail().equals(x.getEmail())    
            ){
                break;
            }
            index++;
        }
        //xoa nhan vien tim dc
        employees.remove(employees.get(index));
        try {
            FileWriter fw = new FileWriter("./doituong/database/nhanvien.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("");
            bw.close();
        } catch (IOException e) {
            System.out.print(" loi xay ra khi xoa danh sach!");
        }

        //ghi lai danh sach moi
        System.out.print("  Xoa thanh cong 1 nhan vien co ma nhan vien: "+x.getEmployeeCode()+"\n");
        for (NhanVien employee : employees) {
            try{
                FileWriter fw = new FileWriter("./doituong/database/nhanvien.txt",true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(manager.EmployeeToString(employee));
                bw.newLine();
                bw.close();
            }catch(IOException e){
                System.out.print(" loi xay ra khi xoa nhan vien!");
            }
        }

    }
}
    

