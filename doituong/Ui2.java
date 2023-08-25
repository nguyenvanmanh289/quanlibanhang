package doituong;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui2 {
    public static void Qlbh(){
        QuanLiBanHang managerBh = new QuanLiBanHang();
        System.out.print("=================== QUAN LI BAN HANG =========================\n\n");

        Scanner sc = new Scanner(System.in);
        int n=-1;
        do{
            System.out.print("chon cac chuc nang 1 || 2 :\n");
            System.out.print("  1: them hoa don\n");
            System.out.print("  2: Bao cao thong ke\n");
            System.out.print("0 Quay Lai Menu Truoc\n");
            n=sc.nextInt();
            if(n<0 || n>2) System.out.print("\nnhap sai lua chon , nhap lai!\n");
        }while(n<0 || n>2);
        
        if(n==1){
           System.out.print("======chuc nang them hoa don ban hang===============\n");
           HoaDon a = managerBh.AddBill();
           a.partHoaDonToStringToDataBase();
           System.out.print("\n\nTHEM HOA DON THANH CONG !\n");

           System.out.print("\n");
           System.out.print(" Enter de quay lai !\n");
           sc.nextLine();
           sc.nextLine();
           Ui2.Qlbh();
        }
        else if (n == 2) {
            HoaDon biller = new HoaDon();
            System.out.print("======chuc nang bao cao thong ke===============\n");
            int n2 = -1;
            do {
                System.out.print("Chon cac chuc nang: \n");
                System.out.print("  1: xem danh sach hoa don trong 1 ngay\n");
                System.out.print("  2: xem danh sach hoa don trong 1 thang\n");
                System.out.print("  3: xem doanh thu theo nhan vien\n");
                System.out.print("  4: xem danh sach san pham co so luong nho hon 100\n");
                System.out.print("0 : QUAY LAI MENU TRUOC\n");
                n = sc.nextInt();
            } while (n < 0 || n > 5);

            switch (n) {
                case 0:
                    Ui2.Qlbh();
                    break;

                case 1:
                    ArrayList<HoaDon> list0 = new ArrayList<HoaDon>();
                    list0 = biller.ViewListBillInDay();
                    System.out.print(
                            "___________________________________________________________________________________________________________________\n");
                    System.out.print(
                            "||  stt  || ten san pham  || ma san pham || so luong mua ||  don gia  ||  thanh tien  ||  Tong tien  ||  ngay ban  ||\n");
                    System.out.print(
                            "||_______||_______________||_____________||______________||___________||______________||_____________||____________||\n");
                    for (HoaDon bill : list0) {
                        System.out.print(bill.partHoaDonToStringToScreen() + "\n\n");
                    }

                    System.out.print("\n");
                    System.out.print(" Enter de quay lai !\n");
                    sc.nextLine();
                    sc.nextLine();
                    Ui2.Qlbh();
                    break;

                case 2:
                    ArrayList<HoaDon> list1 = new ArrayList<HoaDon>();
                    list1 = biller.ViewListBillInMounth();
                    System.out.print(
                            "___________________________________________________________________________________________________________________\n");
                    System.out.print(
                            "||  stt  || ten san pham  || ma san pham || so luong mua ||  don gia  ||  thanh tien  ||  Tong tien  ||  ngay ban  ||\n");
                    System.out.print(
                            "||_______||_______________||_____________||______________||___________||______________||_____________||____________||\n");
                    for (HoaDon bill : list1) {
                        System.out.print(bill.partHoaDonToStringToScreen() + "\n\n");
                    }

                    System.out.print("\n");
                    System.out.print(" Enter de quay lai !\n");
                    sc.nextLine();
                    sc.nextLine();
                    Ui2.Qlbh();
                    break;

                case 3:
                    QuanLiNhanVien managernv = new QuanLiNhanVien();
                    NhanVien a = managernv.SearchEmployee();
                    a.GetIncomeTrackbyBill();

                    System.out.print("\n");
                    System.out.print(" Enter de quay lai !\n");
                    sc.nextLine();
                    sc.nextLine();
                    Ui2.Qlbh();
                    break;

                case 4:
                    QuanLiSanPham managerSp = new QuanLiSanPham();
                    ArrayList<SanPham> list2 = new ArrayList<SanPham>();
                    list2 = managerSp.ViewProductsIsLowQuantity();
                    System.out.print("_______________________________________________________\n");
                    System.out.print("||  ma san pham || ten san pham || so luong || don gia ||\n");
                    System.out.print("||______________||______________||__________||_________||\n");
                    for (SanPham product : list2) {
                        System.out.print("    " + managerSp.ProductToString(product).replace("-", "   ||  ") + "\n");
                    }

                    System.out.print("\n");
                    System.out.print(" Enter de quay lai !\n");
                    sc.nextLine();
                    sc.nextLine();
                    Ui2.Qlbh();
                    break;
                default:
                    break;
            }

        } else {
            Ui.UI();
        }
    }
}
