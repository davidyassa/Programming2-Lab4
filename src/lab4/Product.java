/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

/**
 *
 * @author HP
 */
public class Product {
    private String productID; 
    private String productName;
    private String manufacturerName;
    private String supplierName;
    private int quantity;
    private float price;
    public Product(String productID, String productName, String manufacturerName, String supplierName, int quantity, float price)
    {
        this.productID = productID;
        this.productName =productName;
        this.manufacturerName=manufacturerName;
        this. supplierName= supplierName;
        this.quantity=quantity;
        this.price=price;
    }

   public String getSearchKey() {
        return productID;
    }


    public int getQuantity() {
        return quantity;
    }

   
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
public String lineRepresentation()
{
   String line = productID+","+productName+","+manufacturerName+","+supplierName+","+quantity+","+price;
   
   return line ;
}
public String getobject() {
    return productID + "," + productName + "," + manufacturerName + "," +
           supplierName + "," + quantity + "," + price;
}
}
