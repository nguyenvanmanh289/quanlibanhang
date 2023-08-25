package doituong;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class HoaDon {
    private String buyerCode, employeeCode ,dayOfSale;
    private ArrayList<Float> priceOfaProduct;
    private ArrayList<String> productCode ; 
    private ArrayList<String> quantityOfSale ;
    private ArrayList<String> unitPrice ;
    
    
    public HoaDon(){};
    public HoaDon(String buyerCode, String employeeCode , ArrayList<String> productCode , ArrayList<String> quantityOfSale , ArrayList<String> unitPrice, ArrayList<Float> priceOfaProduct ,String dayOfSale){
        this.buyerCode=buyerCode;
        this.employeeCode=employeeCode;
        this.productCode= productCode;
        this.quantityOfSale = quantityOfSale;
        this.unitPrice=unitPrice;
        this.priceOfaProduct=priceOfaProduct;
        this.dayOfSale = dayOfSale;
    }


    public String getBuyerCode() {
        return this.buyerCode;
    }

    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    public String getEmployeeCode() {
        return this.employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public ArrayList<String> getProductCode() {
        return this.productCode;
    }

    public void setProductCode(ArrayList<String> productCode) {
        this.productCode = productCode;
    }

    public ArrayList<String> getQuantityOfSale() {
        return this.quantityOfSale;
    }

    public void setQuantityOfSale(ArrayList<String> quantityOfSale) {
        this.quantityOfSale = quantityOfSale;
    }

    public ArrayList<String> getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(ArrayList<String> unitPrice) {
        this.unitPrice = unitPrice;
    }

    public ArrayList<Float> getSumPrice() {
        return this.priceOfaProduct;
    }

    public void setSumPrice(ArrayList<Float> priceOfaProduct) {
        this.priceOfaProduct = priceOfaProduct;
    }

    public String getDayOfSale() {
        return this.dayOfSale;
    }

    public void setDayOfSale(String dayOfSale) {
        this.dayOfSale = dayOfSale;
    }
    //===============more method================//

    ///partHoaDonToString
    public String partHoaDonToStringToScreen(){
        String text = "\n\n    1"+"   ||   "+ProductCodeToName(productCode.get(0)) +"   ||   "+productCode.get(0)+"   ||   "+quantityOfSale.get(0)+"   ||   "+unitPrice.get(0)+"   ||   "+priceOfaProduct.get(0)+"   ||   "+SumPay()+"   ||   "+ this.dayOfSale+"\n";
        String text2 = "";
        for (int i = 1; i < this.productCode.size(); i++) {  
            text2+="    "+Integer.toString(i+1)+"   ||   "+ProductCodeToName(productCode.get(i)) +"   ||   "+productCode.get(i)+"   ||   "+quantityOfSale.get(i)+"   ||   "+unitPrice.get(i)+"   ||   "+priceOfaProduct.get(i)+"   ||"+"\n";
        }
        return text+text2;
    }

    //tinh tong cho 1 hoa don
    public float SumPay(){
        float sum=0;
        for (int i = 0; i < this.productCode.size(); i++) {
            sum+= this.priceOfaProduct.get(i);
        }
        return sum;
    }

    //chuyen hoa don sang database de luu 
    public void partHoaDonToStringToDataBase(){
        String listProductCode ="[";
        String listQuantityOfProduct ="[";
        String listUnitPrice ="[";
        String listPriceOfProduct ="[";
        for (int i = 0; i < this.productCode.size(); i++) {
            if(i>this.productCode.size()-2){
                listProductCode += productCode.get(i)+"]";
                listQuantityOfProduct += quantityOfSale.get(i)+"]";
                listUnitPrice += unitPrice.get(i)+"]";
                listPriceOfProduct += priceOfaProduct.get(i)+"]";
            }
            else{
                listProductCode += productCode.get(i)+",";
                listQuantityOfProduct += quantityOfSale.get(i)+",";
                listUnitPrice += unitPrice.get(i)+",";
                listPriceOfProduct += priceOfaProduct.get(i)+",";
            }
        }

        String data = this.buyerCode+"-"+this.employeeCode+"-"+listProductCode+"-"+listQuantityOfProduct+"-"+listUnitPrice+"-"+listPriceOfProduct+"-"+this.dayOfSale;
        System.out.print(data);
       
        try {
            FileWriter fw = new FileWriter("./doituong/database/hoadon.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
   
    //lay ten cua san pham dua vao ma san pham
    public String ProductCodeToName(String code){
        QuanLiSanPham manager = new QuanLiSanPham();
        String x = manager.FindNametByCode(code);
        return x;
    }


    //================================================PHAN BAO CAO =====================================//
    public ArrayList<HoaDon> ViewListBillInDay(){
        Scanner sc= new Scanner(System.in);
        System.out.print("De xem hoa don ban hang vui long nhap vao ngay/thang/nam\n ");
        int day = 0;
        int mounth = 0;
        int year = 2023;
        do{
            System.out.print("  nhap vao ngay ban: ");
            day = sc.nextInt();
            System.out.print("  nhap vao thang ban: ");
            mounth = sc.nextInt();
            System.out.print("  nhap vao nam ban: ");
            year =sc.nextInt();
             System.out.print("\n ");

            if(day>30 || day <1 || mounth>12 || mounth<1 || year>2023){
                System.out.print(" ban nhap sai thong tin gi do kiem tra lai!\n");
            }
        }while(day>30 || day <1 || mounth>12 || mounth<1 || year>2023);
        
        String time = Integer.toString(day)+"/"+Integer.toString(mounth)+"/"+Integer.toString(year);

        //tim hoa don trong ngay
        HoaDon biller = new HoaDon();
        ArrayList<HoaDon> listBill = biller.GetListHoaDon();
        ArrayList<HoaDon> listBillFindByTime = new ArrayList<HoaDon>();
        float sumPrice = 0;
        for (HoaDon bill : listBill) {
            if(bill.getDayOfSale().equals(time)){
                listBillFindByTime.add(bill);
                sumPrice+=bill.SumPay();
            }else{
                System.out.print("khong tim thay hoa don ngay: "+time+" hay thu lai!");

            }
        }
        if(sumPrice>0)
        System.out.print("Tong tien ban duoc ngay "+time+" la: "+sumPrice+"\n");
        return listBillFindByTime;
    }



    public ArrayList<HoaDon> ViewListBillInMounth(){
        Scanner sc= new Scanner(System.in);
        System.out.print("De xem hoa don ban hang vui long nhap vao thang/nam\n ");
        
        int mounth = 0;
        int year = 2023;
        do{
            System.out.print("  nhap vao thang ban: ");
            mounth = sc.nextInt();
            System.out.print("  nhap vao nam ban: ");
            year =sc.nextInt();
             System.out.print("\n ");

            if( mounth>12 || mounth<1 || year>2023){
                System.out.print(" ban nhap sai thong tin gi do kiem tra lai!\n");
            }
        }while( mounth>12 || mounth<1 || year>2023);
        
        String time = Integer.toString(mounth)+"/"+Integer.toString(year);

        //tim hoa don trong ngay
        HoaDon biller = new HoaDon();
        ArrayList<HoaDon> listBill = biller.GetListHoaDon();
        ArrayList<HoaDon> listBillFindByTime = new ArrayList<HoaDon>();
        float sumPrice = 0;
        for (HoaDon bill : listBill) {
            if(bill.getDayOfSale().contains(time)){
                listBillFindByTime.add(bill);
                sumPrice+=bill.SumPay();
            }else{
                System.out.print("khong tim thay hoa don thang: "+time+" hay thu lai!");

            }
        }
        if(sumPrice>0)
        System.out.print("Tong tien ban duoc thang "+time+" la: "+sumPrice+"\n");
        return listBillFindByTime;
    }


    //get list HoaDon ====================================================================
    public ArrayList<HoaDon> GetListHoaDon(){
        // doc du lieu tho tu file hoadon.txt 
        ArrayList<String> dataRaw = new ArrayList<String>();
        try {
            FileReader fr = new FileReader("./doituong/database/hoadon.txt");
            BufferedReader br = new BufferedReader(fr);
            String text = "";
            while((text = br.readLine()) != null){
                dataRaw.add(text);
            }
        } catch (Exception e) {
        }

        ArrayList<String[]> listArrPropertyRaw = new ArrayList<String[]>();
        for (String property : dataRaw){
            listArrPropertyRaw.add(property.split("-"));
        }

        ArrayList<HoaDon> listBill = new ArrayList<HoaDon>();
        for (String[] bill : listArrPropertyRaw) {
            listBill.add(new HoaDon(bill[0],bill[1], StringArrayToArraylist(bill[2]),
            StringArrayToArraylist(bill[3]),StringArrayToArraylist(bill[4]),StringArrayToArraylistFloat(bill[5]), bill[6])); 
        }
        return listBill;
    }
    //============================================================================================


    // "[a,b]" convert to [a,b]Arraylist ================
    public ArrayList<String> StringArrayToArraylist(String a){
      String[] arr = a.replace("[", "").replace("]","").split(","); // "[a,b]" => [a,b]
      ArrayList<String> list = new ArrayList<String>(); 
      for (String item : arr) {
            list.add(item);
      }
      return list;
    }
    public ArrayList<Float> StringArrayToArraylistFloat(String a){
      String[] arr = a.replace("[", "").replace("]","").split(","); // "[a,b]" => [a,b]
      ArrayList<Float> list = new ArrayList<Float>(); 
      for (String item : arr) {
            list.add(Float.parseFloat(item));
      }
      return list;
    }
    //==================================================
}
