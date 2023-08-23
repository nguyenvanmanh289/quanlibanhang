package doituong;

public class SanPham {
    private String productCode , name, quantity, unitPrice;

    public SanPham(String productCode, String name, String quantity, String unitPrice) {
        this.productCode = productCode;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    //====get/set====//

    public String getProductCode() {
        return this.productCode;
    }
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return this.quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnitPrice() {
        return this.unitPrice;
    }
    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    //====more method====//
}
