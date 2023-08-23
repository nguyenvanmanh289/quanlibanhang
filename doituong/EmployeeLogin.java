package doituong;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeLogin {
    public EmployeeLogin(){};

    public ArrayList<String> Input(){
        Scanner sc = new Scanner(System.in);
        System.out.print("De tiep tuc dang nhap tai khoan nhan vien de su dung!\n");
        System.out.print("ma nhan vien: ");
        String username = sc.nextLine();
        System.out.print("passwork: ");
        String passwork = Md5.encryptToMD5(sc.nextLine());
        ArrayList<String> payload = new ArrayList<String>();
        payload.add(username);
        payload.add(passwork);
        return payload;
    }

    public void CheckPayload(ArrayList<String> payload){
        QuanLiNhanVien manager = new QuanLiNhanVien();
        ArrayList<NhanVien> employees = new ArrayList<NhanVien>();
        employees = manager.GetListEmployee();

        boolean isFound = false;
        for (NhanVien employee : employees) {
            boolean found = false;
            if(payload.get(0).equals(employee.getEmployeeCode()) &&
                payload.get(1).equals(employee.getPasswork())
            ){
                found = !found;
                isFound = !isFound;
                System.out.print(
                    "\nDang nhap thanh cong!\n"+
                    "   nhan vien: "+employee.getName()+"\n"+
                    "   ma nhan vien: "+employee.getEmployeeCode()+"\n"
                );
            }
            if(found){
                break;
            }
        }
        if(!isFound){
            System.out.print("sai ten dang nhap hoac mat khau: ");
            EmployeeLogin Login =new EmployeeLogin();
            Login.CheckPayload(Login.Input());
        }
        
    }
}
