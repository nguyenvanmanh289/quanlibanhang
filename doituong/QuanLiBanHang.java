package doituong;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class QuanLiBanHang {
    public QuanLiBanHang(){};

    public HoaDon AddBill(){
        //nhap vao ma khack hang de tim , neu ko co tao moi
        ArrayList<SanPham> listBuy = new ArrayList<SanPham>();
        QuanLiKhachHang managerkh = new QuanLiKhachHang();
        KhachHang kh = managerkh.SearchBuyerNoLoop();
        if(kh==null){
            System.out.print("tao moi mot khach hang !\n");
           kh = managerkh.CheckAndAddBuyer(managerkh.InputBuyer());   
        }
        QuanLiBanHang managerbh = new QuanLiBanHang();
    
        Scanner sc =new Scanner(System.in);
        System.out.print(kh.getName()+" ban muon mua gi?\n");
        boolean addToCart = false;
        do{
            SanPham x = managerbh.PurchasedProduct();
            int n =0;
            do{
                System.out.print("Ban co tiep tuc mua them khong? ");
                System.out.print("chon 1 || 2 :\n   1: mua tiep\n2: thanh toan luon\n");
                n = sc.nextInt();
            }while(n<1 || n>2);

            
            if(n==1){
                addToCart = true;
            }
            else{
                addToCart = false;
            }
            listBuy.add(x);

        }while(addToCart);

        QuanLiSanPham M = new QuanLiSanPham();
        for (SanPham sanPham : listBuy) {
            System.out.print(M.ProductToString(sanPham)+"\n");
        }
        
        QuanLiNhanVien managerNv = new QuanLiNhanVien();
        System.out.print("Nhap vao nhan vien su li Hoa Don nay: \n");
        NhanVien employee = managerNv.SearchEmployee();

        ArrayList<String> productCode = new ArrayList<String>();
        ArrayList<String> unitPrice = new ArrayList<String>();
        ArrayList<String> quantityOfSale = new ArrayList<String>();
        ArrayList<Float> priceOfaProduct = new ArrayList<Float>();
        
        // tao list cac thong tin san pham
        for (SanPham product : listBuy) {
            productCode.add(product.getProductCode());
            unitPrice.add(product.getUnitPrice());
            quantityOfSale.add(product.getQuantity());
            priceOfaProduct.add(Integer.parseInt(product.getQuantity()) * Float.parseFloat(product.getUnitPrice()));
        }

        //ngay ban hang
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        String DateTime = currentDateTime.format(formatter);
       


        return new HoaDon(kh.getBuyerCode(), employee.getEmployeeCode(), productCode, quantityOfSale, unitPrice, priceOfaProduct, DateTime); 
    }
    //dung cho method addBill()
    public SanPham PurchasedProduct(){
        //nhap vao ma san pham de tim thong tin ko co thi thoi ko ban nua, co thi nhap vao so luong ban
        QuanLiSanPham managersp = new QuanLiSanPham();
        SanPham sp = null;
        boolean buyBack = false;
        boolean next = false;
        do {
            sp = managersp.SearchProductNoLoop();

            if (sp == null) {
                System.out.print("san pham het hang !\n ban co muon mua san pham khac khong?\n\n");
                Scanner sc = new Scanner(System.in);
                int x = 0;
                do {
                    System.out.print("chon 1 || 2 :\n");
                    System.out.print("  1: de mua san pham khac\n   2: khong mua gi nua\n");
                    x = sc.nextInt();
                } while (x < 1 || x > 2);
                if (x == 1) {
                    buyBack = true;
                }else{
                    buyBack = false;
                }
            }else{
                next = true;
                buyBack = false;
            }

        } while(buyBack);

        if(sp==null) System.exit(0); //khi khachang ko mua gi nua thoat va duoi khach
        int quantity = 0;
        if(next){
            Scanner sc = new Scanner(System.in);
            System.out.print("  so luong "+sp.getName()+" con lai trong kho la: "+sp.getQuantity()+"\n");

            
            do{
                System.out.print("nhap vao so luong "+sp.getName()+" can mua: ");
                quantity = sc.nextInt();
                if(quantity < 1 || quantity> Integer.parseInt(sp.getQuantity())){
                    System.out.print("so luong khong hop le!  nhap lai\n");
                }
            }while(quantity < 1 || quantity> Integer.parseInt(sp.getQuantity()));
          
            

            //cap nhap lai so luong san pham trong kho hang
            SanPham updateProduct = sp;
            updateProduct.setQuantity(Integer.toString(Integer.parseInt(sp.getQuantity())-quantity));
            managersp.EditProductInfo1(sp);   
        }
        return new SanPham(sp.getProductCode(), sp.getName(), Integer.toString(quantity) , sp.getUnitPrice());
    }


    //chuyen mot chuoi float sang tien te
    public String formatPrice(String input) {
        
        return null;
    }
}
