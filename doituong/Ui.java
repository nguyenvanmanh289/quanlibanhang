package doituong;

import java.util.ArrayList;
import java.util.Scanner;
public class Ui {
    public static void UI(){
        System.out.print("========HE THONG QUAN LI BAN HANG=========\n");
        Scanner sc = new Scanner(System.in);
        int n;
        do{
            System.out.print("nhap 1||2||3||4||5 de chon cac chuc nang: \n\n");
            System.out.print("1: Quan li khach Hang\n");
            System.out.print("2: Quan li Nhan Vien\n");
            System.out.print("3: Quan li San Pham\n");
            System.out.print("4: Quan li Ban Hang\n");
            System.out.print("5: Exit\n");
            n=sc.nextInt();
        } while(n<1 || n>5);

        switch (n) {
            case 1:
                    Ui.Qlkh();
                break;
            case 2:
                    Ui.Qlnv();
                break;

            case 3:
                    Ui.Qlsp();
                break;

            case 4:
                    Ui2.Qlbh();
                break;
            case 5:
                System.out.print("da thoat khoi chuong trinh!");
                break;
            default:
                break;
        }

    }

    public static void Qlnv(){
        QuanLiNhanVien manager = new QuanLiNhanVien();
        System.out.print("========= Quan li nhan vien ==========\n");
        
        Scanner sc = new Scanner(System.in);
        int n =-1; 
        do{
            System.out.print("Chon cac chuc nang: \n");
            System.out.print("  1: xem danh sach nhan vien\n");
            System.out.print("  2: tim nhan vien\n");
            System.out.print("  3: them moi nhan vien\n");
            System.out.print("  4: sua thong tin nhan vien\n");
            System.out.print("  5: xoa nhan vien\n");
            System.out.print("0 : QUAY LAI MENU TRUOC\n");
            n = sc.nextInt();
        }while(n<0 || n>5);

        switch(n){
            case 0:
                Ui.UI();
                break;

            case 1:
                 ArrayList<NhanVien> employees = new ArrayList<NhanVien>();
                 employees = manager.GetListEmployee();
                 System.out.print("________________________________________________________________________________________________________________________________________________\n");
                 System.out.print("||  Mnv  ||    Ho ten    ||    Sdt    ||     Email     ||            Passwork            ||   Dia chi   ||  Gioi tinh  ||  Group  || Trang thai ||\n");
                 System.out.print("||_______||______________||___________||_______________||________________________________||_____________||_____________||_________||____________||\n");
                 for (NhanVien employee : employees) {
                    String x = manager.EmployeeToString(employee).replace("-", " || ");
                    System.out.print("\n||  "+x+"  \n");
                 }
                 System.out.print("\n");
                 System.out.print(" Enter de quay lai !\n");
                 sc.nextLine();
                 sc.nextLine();
                 Ui.Qlnv();
                break;

            case 2:
                 System.out.print("================ TIM NHAN VIEN ========================\n");
                 NhanVien employee = manager.SearchEmployee();
                 String x = manager.EmployeeToString(employee).replace("-", " || ");
                 System.out.print(" thong tin chi tiet: \n");
                 System.out.print("________________________________________________________________________________________________________________________________________________\n");
                 System.out.print("||  Mnv  ||    Ho ten    ||    Sdt    ||     Email     ||            Passwork            ||   Dia chi   ||  Gioi tinh  ||  Group  || Trang thai ||\n");
                 System.out.print("||_______||______________||___________||_______________||________________________________||_____________||_____________||_________||____________||\n");
                 System.out.print("\n||  "+x+"  \n");
                 System.out.print("\n");
                 System.out.print(" Enter de quay lai !\n");
                 sc.nextLine();
                 sc.nextLine();
                 Ui.Qlnv();
                break;

            case 3:
                 System.out.print("================ THEM NHAN VIEN ========================\n");
                 manager.CheckAndAddEmployee(manager.InputEmployee());
                 System.out.print("\n");
                 System.out.print(" Enter de quay lai !\n");
                 sc.nextLine();
                 sc.nextLine();
                 Ui.Qlnv();
                break;

            case 4:
                 System.out.print("================ SUA NHAN VIEN ========================\n");
                 manager.EditEmployeeInfo();
                 System.out.print("\n");
                 System.out.print(" Enter de quay lai !\n");
                 sc.nextLine();
                 sc.nextLine();
                 Ui.Qlnv();
                break;

            case 5:
                 System.out.print("================ XOA NHAN VIEN ========================\n");
                 manager.DeleteEmployee();
                 System.out.print("\n");
                 System.out.print(" Enter de quay lai !\n");
                 sc.nextLine();
                 sc.nextLine();
                 Ui.Qlnv();
            break;

            default:
            break;
        }


    }

    public static void Qlkh(){
        QuanLiKhachHang manager = new QuanLiKhachHang();
        System.out.print("========= Quan li Khach Hang ==========\n");
        
        Scanner sc = new Scanner(System.in);
        int n =-1; 
        do{
            System.out.print("Chon cac chuc nang: \n");
            System.out.print("  1: xem danh sach Khach Hang\n");
            System.out.print("  2: tim Khach Hang\n");
            System.out.print("  3: them moi Khach Hang\n");
            System.out.print("  4: sua thong tin Khach Hang\n");
            System.out.print("  5: xoa Khach Hang\n");
            System.out.print("0 : QUAY LAI MENU TRUOC\n");
            n = sc.nextInt();
        }while(n<0 || n>5);

        switch(n){
            case 0:
                Ui.UI();
                break;

            case 1:
                 ArrayList<KhachHang> buyers = new ArrayList<KhachHang>();
                 buyers = manager.GetListBuyer();
                 System.out.print("_____________________________________________________________________________\n");
                 System.out.print("||  Mkh   ||    Ho ten    ||    Sdt    ||     Email     ||      Dia chi      ||\n");
                 System.out.print("||________||______________||___________||_______________||___________________||\n");
                 for (KhachHang buyer : buyers) {
                    String x = manager.BuyerToString(buyer).replace("-", " || ");
                    System.out.print("\n||  "+x+"  \n");
                 }
                 System.out.print("\n");
                 System.out.print(" Enter de quay lai !\n");
                 sc.nextLine();
                 sc.nextLine();
                 Ui.Qlkh();
                break;

            case 2:
                 System.out.print("================ TIM KHACH HANG ========================\n");
                 KhachHang buyer = manager.SearchBuyer();
                 String x = manager.BuyerToString(buyer).replace("-", " || ");
                 System.out.print(" thong tin chi tiet: \n");
                 System.out.print("____________________________________________________________________________\n");
                 System.out.print("||  Mkh  ||    Ho ten    ||    Sdt    ||     Email     ||       Dia chi     ||\n");
                 System.out.print("||_______||______________||___________||_______________||___________________||\n");
                 System.out.print("\n||  "+x+"  \n");
                 System.out.print("\n");
                 System.out.print(" Enter de quay lai !\n");
                 sc.nextLine();
                 sc.nextLine();
                 Ui.Qlkh();
                break;

            case 3:
                 System.out.print("================ THEM KHACH HANG ========================\n");
                 manager.CheckAndAddBuyer(manager.InputBuyer());
                 System.out.print("\n");
                 System.out.print(" Enter de quay lai !\n");
                 sc.nextLine();
                 sc.nextLine();
                 Ui.Qlkh();
                break;

            case 4:
                 System.out.print("================ SUA KHACH HANG ========================\n");
                 manager.EditBuyerInfo();
                 System.out.print("\n");
                 System.out.print(" Enter de quay lai !\n");
                 sc.nextLine();
                 sc.nextLine();
                 Ui.Qlkh();
                break;

            case 5:
                 System.out.print("================ XOA KHACH HANG ========================\n");
                 manager.DeleteBuyer();
                 System.out.print("\n");
                 System.out.print(" Enter de quay lai !\n");
                 sc.nextLine();
                 sc.nextLine();
                 Ui.Qlkh();
            break;

            default:
            break;
        }
    }

    public static void Qlsp(){
        QuanLiSanPham manager = new QuanLiSanPham();
        System.out.print("========= Quan li San Pham ==========\n");
        
        Scanner sc = new Scanner(System.in);
        int n =-1; 
        do{
            System.out.print("Chon cac chuc nang: \n");
            System.out.print("  1: xem danh sach San Pham\n");
            System.out.print("  2: tim San Pham\n");
            System.out.print("  3: them moi San Pham\n");
            System.out.print("  4: sua thong tin San Pham\n");
            System.out.print("  5: xoa San Pham\n");
            System.out.print("0 : QUAY LAI MENU TRUOC\n");
            n = sc.nextInt();
        }while(n<0 || n>5);

        switch(n){
            case 0:
                Ui.UI();
                break;

            case 1:
                 ArrayList<SanPham> products = new ArrayList<SanPham>();
                 products = manager.GetListProduct();
                 System.out.print("___________________________________________________________\n");
                 System.out.print("||  Msp   ||   ten san pham  || so luong  ||   don gia     ||\n");
                 System.out.print("||________||_________________||___________||_______________||\n");
                 for (SanPham product : products) {
                    String x = manager.ProductToString(product).replace("-", " || ");
                    System.out.print("\n||  "+x+"  \n");
                 }
                 System.out.print("\n");
                 System.out.print(" Enter de quay lai !\n");
                 sc.nextLine();
                 sc.nextLine();
                 Ui.Qlsp();
                break;

            case 2:
                 System.out.print("================ TIM SAN PHAM ========================\n");
                 SanPham product = manager.SearchProduct();
                 String x = manager.ProductToString(product).replace("-", " || ");
                 System.out.print(" thong tin chi tiet: \n");
                 System.out.print("__________________________________________________________\n");
                 System.out.print("||  Msp  || ten san pham   ||  so luong ||  don gia      ||\n");
                 System.out.print("||_______||________________||___________||_______________||\n");
                 System.out.print("\n||  "+x+"  \n");
                 System.out.print("\n");
                 System.out.print(" Enter de quay lai !\n");
                 sc.nextLine();
                 sc.nextLine();
                 Ui.Qlsp();
                break;

            case 3:
                 System.out.print("================ THEM SAN PHAM ========================\n");
                 try {
                    manager.CheckAndAddProduct(manager.InputProduct());
                 } catch (Exception e) {
                    // TODO: handle exception
                 }
                 System.out.print("\n");
                 System.out.print(" Enter de quay lai !\n");
                 sc.nextLine();
                 sc.nextLine();
                 Ui.Qlsp();
                break;

            case 4:
                 System.out.print("================ SUA SAN PHAM ========================\n");
                 manager.EditProductInfo();
                 System.out.print("\n");
                 System.out.print(" Enter de quay lai !\n");
                 sc.nextLine();
                 sc.nextLine();
                 Ui.Qlsp();
                break;

            case 5:
                 System.out.print("================ XOA SAN PHAM ========================\n");
                 manager.DeleteProduct();
                 System.out.print("\n");
                 System.out.print(" Enter de quay lai !\n");
                 sc.nextLine();
                 sc.nextLine();
                 Ui.Qlsp();
            break;

            default:
            break;
        }
    }
}
