package guitest;
/**
 *
 * @author Youssef Essam
 */
public class Product 
{
    private int Id;
    private String name;
    private int Amount_available;
    private String price;
    private byte[] Image;
    
    
    public Product(){}
    
    public Product(int Id, String Name, int Amount_available, String Price, byte[] image)
    {
        this.Id   = Id;
        this.name = Name;
        this.Amount_available = Amount_available;
        this.price = Price;
        this.Image = image;    
    }
    
    public int getId()
    {
        return Id;
    }
    
    public void setId(int Id)
    {
        this.Id = Id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String Name)
    {
        this.name = Name;
    }
    
    public int getAmount_available()
    {
        return Amount_available;
    }
    
    public void setAmount_available(int Qte)
    {
        this.Amount_available = Qte;
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