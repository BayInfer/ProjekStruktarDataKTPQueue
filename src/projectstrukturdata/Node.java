package projectstrukturdata;

public class Node {
    // atribut data
    private int nomor;
    private String nama;
    private String Alamat;
    public Node next;
    // constructor
    public Node(int nomor,String nama,String alamat){
        this.nomor = nomor;
        this.nama=nama;
        this.Alamat=alamat;

    
    }
    public void displaynode(){
        System.out.println("Nomor : "+this.nomor);
        System.out.println("Nama  : "+this.nama);
        System.out.println("Alamat: "+this.Alamat);
    }
    public int getNomor(){
        return nomor;
    }
    public String getNama(){
        return nama;
    }
}
