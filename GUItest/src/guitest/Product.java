package guitest;
/**
 *
 * @author Youssef Essam
 */
public class Product 
{
    private String name;
    private int qte;
    private String price;
    private byte[] Image;
    
    public Product(){}
    
    public Product(String Name, int Qte, String Price,byte[] image)
    {
        this.name = Name;
        this.qte = Qte;
        this.price = Price;
        this.Image = image;    
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String Name)
    {
        this.name = Name;
    }
    
    public int getQte()
    {
        return qte;
    }
    
    public void setQte(int Qte)
    {
        this.qte = Qte;
    }
    
    public String getPrice()
    {
        return price;
    }
    
    public void setPrice(String Price)
    {
        this.price = Price;
    }
    
    public byte[] getMyImage()
    {
        return Image;
    }
}