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

public class QuanLiSanPham {
    public QuanLiSanPham(){};

    public ArrayList<SanPham> GetListProduct(){
        try {
            FileReader fr = new FileReader("./doituong/database/sanpham.txt");
            BufferedReader bf = new BufferedReader(fr);

            //luu danh sach tung chuoi trong database
            ArrayList<String> list = new ArrayList<String>();
            String text ="";
            while ((text = bf.readLine()) != null) {
                list.add(text);
            }

            //luu danh sach cac mang chua tung thong tin cac khac hang
             ArrayList<String[]> listArrayProduct = new ArrayList<String[]>();
            for (String string : list) {
                listArrayProduct.add(string.split("-"));
            }

            //tao danh sach cac doi tuong khac hang va tra lai 
            ArrayList<SanPham> listProduct = new ArrayList<SanPham>();
            for (String[] product : listArrayProduct) {
                listProduct.add(new SanPham(product[0],product[1],product[2],product[3]));
            }
            return listProduct;

        } catch (IOException e) {
            System.out.print("co loi khi get danh sach nhan vien!\n");
            return null;
        }
    }

    //chuyen 1 doi tuong sang chuoi data de luu vao database
    public String ProductToString(SanPham a){
        String string = a.getProductCode()+"-"+a.getName()+"-"+a.getQuantity()+"-"+a.getUnitPrice();
        return string;
    }

    //tim san pham
    public SanPham SearchProduct() {
        QuanLiSanPham manager = new QuanLiSanPham();
        ArrayList<SanPham> products = new ArrayList<SanPham>();
        products = manager.GetListProduct();
        Scanner sc = new Scanner(System.in);
        SanPham x = null;
        do {
            System.out.print("nhap vao ma San Pham de tim kiem :\n");
            String a = sc.nextLine();

            boolean isFound = false;
                for(SanPham product : products) {
                    if(a.equals(product.getProductCode())){
                        System.out.print(
                                "Ket qua tim kiem theo: " + a + "\n\n" +
                                        "   San Pham: " + product.getName() + "\n"
                        );
                                        
                        x = product;
                        isFound = true;
                        break;
                    }
                }
                if (!isFound) {
                System.out.print("Khong tim thay San pham theo: " + a + "\n");
        }
        } while (x == null);  
        return x;
    }


    // method nhap de phuc vu dau vao cho method them nhan vien=========================================
    public SanPham InputProduct(){
        Scanner sc = new Scanner(System.in);
        System.out.print("nhap vao Ma San pham: ");
        String productCode = sc.nextLine();
        System.out.print("  ten san pham: ");
        String name = sc.nextLine();
        System.out.print("  so luong: ");
        String quantity = sc.nextLine();
        System.out.print("  don gia: ");
        String unitPrice = sc.nextLine(); // Đọc dữ liệu và chuyển đổi sang float
        System.out.print(quantity +" "+unitPrice);
        return new SanPham(productCode, name, quantity ,unitPrice);
    }

    public void CheckAndAddProduct(SanPham a) throws IOException{
        QuanLiSanPham manager = new QuanLiSanPham();
        ArrayList<SanPham> products = new ArrayList<SanPham>();
        products = manager.GetListProduct();

        // quantity > 0 && unitPrice > 0
        boolean isValidQuantity = true;
        boolean isValidUnitPrice = true;
        
        //check valid quantity
        try {
            String quantity = a.getQuantity();
            if (Integer.parseInt(quantity) <= 0) {
                isValidQuantity = false;
            }
        } catch (Exception exception1) {
            isValidQuantity = false;
        } 
            
        //check valid unitPrice  
        try{
            String unitPrice = a.getUnitPrice();
            if (Float.parseFloat(unitPrice) <= 0) {
                isValidUnitPrice = false;
            }
        }catch(Exception e){
            isValidUnitPrice = false;
        }
        
        //check valid ma san pham
        boolean isFoundProducCode = false; //khi tim thay 1 san pham trong database trung ma san pham tra ve true
        for (SanPham product : products) {
            boolean isFound = false; // khi tim thay tra ve true de dung vong lap
            if(product.getProductCode().equals(a.getProductCode())){
                isFound = true; 
                isFoundProducCode = true; // dieu khien goi lai pt nhap vao lai
                
            };
            if(isFound){
                break;
            }
        }

        if(isFoundProducCode || !isValidUnitPrice || !isValidQuantity){
            System.out.print("\n\n");
            if(isFoundProducCode){
                System.out.print("  ma San pham ["+a.getProductCode()+"] da ton tai!\n");
            }
            if(!isValidUnitPrice){
                System.out.print(" don gia san pham khong the nho hon 0!\n");
            }
            if(!isValidQuantity){
                System.out.print("  so luong khong duoc it hon 0!\n");
            }

            System.out.print("Vui long nhap lai thong tin!\n");
            manager.CheckAndAddProduct(manager.InputProduct());
        }
        else{
            String string = manager.ProductToString(a);
            try {
                FileWriter fw = new FileWriter("./doituong/database/sanpham.txt",true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(string);
                bw.newLine();
                bw.close();
                System.out.print("\nda them thanh cong 1 San Pham\n"+string);
                
                
            } catch (IOException e) {
                System.out.print("co loi say ra trong qua trinh them San pham!");
                e.printStackTrace();
            }
        }
    }

    // sua mot san pham va kiem tra san pham moi nhap vao co hop le khong truoo khi sua
    public void CheckAndEditProduct(SanPham a){

        QuanLiSanPham manager = new QuanLiSanPham();
        System.out.print("BAT DAU SUA THONG TIN , LUU Y! sua thong tin nao ghi thong tin do , khong sua thi bo trong\n\n");
        SanPham x = manager.InputProduct();
        if(x.getProductCode().equals("")) x.setProductCode(a.getProductCode());
        if(x.getName().equals("")) x.setName(a.getName());
        if(x.getQuantity().equals("")) x.setQuantity(a.getQuantity());
        if(x.getUnitPrice().equals("")) x.setUnitPrice(a.getUnitPrice());

        ArrayList<SanPham> products = new ArrayList<SanPham>();
        products = manager.GetListProduct();
        
        // quantity > 0 && unitPrice > 0
        boolean isValidQuantity = true;
        boolean isValidUnitPrice = true;
        
        //check valid quantity
        try {
            String quantity = x.getQuantity();
            if (Integer.parseInt(quantity) <= 0) {
                isValidQuantity = false;
            }
        } catch (Exception exception1) {
            isValidQuantity = false;
        } 
            
        //check valid unitPrice  
        try{
            String unitPrice = x.getUnitPrice();
            if (Float.parseFloat(unitPrice) <= 0) {
                isValidUnitPrice = false;
            }
        }catch(Exception e){
            isValidUnitPrice = false;
        }
        
        //check valid ma san pham
        boolean isFoundProducCode = false; //khi tim thay 1 san pham trong database trung ma san pham tra ve true
        for (SanPham product : products) {
            boolean isFound = false; // khi tim thay tra ve true de dung vong lap
            if(product.getProductCode().equals(a.getProductCode())){
                isFound = true; 
                isFoundProducCode = true; // dieu khien goi lai pt nhap vao lai
                
            };
            if(isFound){
                break;
            }
        }

        if(isFoundProducCode || !isValidUnitPrice || !isValidQuantity){
            System.out.print("\n\n");
            if(isFoundProducCode){
                System.out.print("  ma San pham ["+a.getProductCode()+"] da ton tai!\n");
            }
            if(!isValidUnitPrice){
                System.out.print(" don gia san pham khong the nho hon 0!\n");
            }
            if(!isValidQuantity){
                System.out.print("  so luong khong duoc it hon 0!\n");
            }
            System.out.print("Vui long nhap lai thong tin!\n");
            manager.CheckAndEditProduct(manager.InputProduct());
        }
        else{
            String string = manager.ProductToString(x);
            
            try {
                FileWriter fw = new FileWriter("./doituong/database/sanpham.txt",true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(string);
                bw.newLine();
                bw.close();
                System.out.print("\nda sua thanh cong 1 San pham\n");
                System.out.print("  before: "+manager.ProductToString(a)+"\n");
                System.out.print("  after: "+manager.ProductToString(x)+"\n");
                                
            } catch (IOException e) {
                System.out.print("co loi say ra trong qua trinh sua San pham!");
                e.printStackTrace();
            }   
        }
    }

    public void EditProductInfo(){
        QuanLiSanPham manager = new QuanLiSanPham();
        System.out.print("===========================================\nDe sua thong tin San pham \n");
        ArrayList<SanPham> products = new ArrayList<SanPham>();
        products = manager.GetListProduct();

        //tim nhan vien can sua
        SanPham x = manager.SearchProduct();
       
        int index=0;
        for (SanPham product : products) {
            if(x.getProductCode().equals(product.getProductCode())){
               break;
            }
            index++;
        }
        products.remove(products.get(index));
            // xoa data base de chuan bi ghi lai du lieu
            try {
                FileWriter fw = new FileWriter("./doituong/database/sanpham.txt");
                BufferedWriter bf = new BufferedWriter(fw);
                bf.write("");
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        
        
        for (SanPham product : products) {
            try {
                FileWriter fw = new FileWriter("./doituong/database/sanpham.txt",true);
                BufferedWriter bf = new BufferedWriter(fw);
                bf.write(manager.ProductToString(product));
                bf.newLine();
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
         manager.CheckAndEditProduct(x);
    }

    //delete 
    public void DeleteProduct(){
        QuanLiSanPham manager = new QuanLiSanPham();
        ArrayList<SanPham> products = new ArrayList<SanPham>();
        products = manager.GetListProduct();
        SanPham x = manager.SearchProduct();
        
        int index = 0;
        for (SanPham product : products) {
            if(product.getProductCode().equals(x.getProductCode())){
                break;
            }
            index++;
        }
        //xoa khach hang tim dc
        products.remove(products.get(index));
        try {
            FileWriter fw = new FileWriter("./doituong/database/sanpham.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("");
            bw.close();
        } catch (IOException e) {
            System.out.print(" loi xay ra khi xoa danh sach!");
        }

        //ghi lai danh sach moi
        System.out.print("  Xoa thanh cong 1 san pham co ma san pham: "+x.getProductCode()+"\n");
        for (SanPham product : products) {
            try{
                FileWriter fw = new FileWriter("./doituong/database/sanpham.txt",true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(manager.ProductToString(product));
                bw.newLine();
                bw.close();
            }catch(IOException e){
                System.out.print(" loi xay ra khi xoa San pham!");
            }
        }

    }

}
