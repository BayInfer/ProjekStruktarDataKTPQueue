package projectstrukturdata;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

import Koneksi.KoneksiDatabase;

public class Queue {

    private Node nodeAwal;
    private ArrayList<Node> queList;
    KoneksiDatabase data = new KoneksiDatabase();

    public Queue() {
        queList = new ArrayList<>();
        enqueue();
    }

    public void enqueue() {
        data.koneksiDatabase();
        try {
            ResultSet resultSet = data.eksekusiQuery("SELECT * FROM antrian"); // Ganti "nama_tabel" dengan nama tabel yang sesuai
            while (resultSet.next()) {
                int nomor = resultSet.getInt("nomor"); // Ganti "nomor" dengan nama kolom yang sesuai
                String nama = resultSet.getNString("Nama");
                String alamat = resultSet.getNString("Alamat");
                // Buat objek Node baru dengan nomor dari database
                Node newNode = new Node(nomor, nama, alamat); // Add the constructor argument with the nomor value
                // Tambahkan newNode ke dalam queList
                queList.add(newNode);
                // Jika nodeAwal masih kosong, set nodeAwal ke newNode
                if (nodeAwal == null) {
                    nodeAwal = newNode;
                }
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Node dequeue() {
        int lastindex = queList.size() - 1;
        Node removedNode = queList.get(0);
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot remove.");
            return null;
        } else {
            // Remove the data from the database
            data.eksekusiUpdate("DELETE FROM antrian WHERE nomor = " + removedNode.getNomor());
            data.eksekusiUpdate("ALTER TABLE antrian AUTO_INCREMENT = " + lastindex);
            return queList.remove(0);

        }
    }

    public Node peekFront() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot peek.");
            return null;
        }

        // Returning the first node in the queue without removing it
        return queList.get(0);
    }

    public boolean isEmpty() {
        return queList.isEmpty();
    }

    public int size() {
        return queList.size();
    }

    // Display method to show nodes in the order they are enqueued
    public void displayQueue() {
        for (Node node : queList) {
            node.displaynode();
        }
    }

    public void cetak() {
        enqueue();
        if (isEmpty()) {
            System.out.print(" Antrian kosong \n");
            return;
        }
        System.out.print("Urutan Antrian : ");
        Node kini = nodeAwal;
        while (kini != null) {
            System.out.print(kini.getNomor() + " <-----");
            kini = kini.next;
        }
        System.out.println();
    }

    public void displayAllNumbers() {
        enqueue();
        System.out.println("All numbers in the queue:");
        for (Node node : queList) {
            System.out.print(node.getNomor() + "<------");
        }
    }

    public int peekFrontNumber() {

        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot peek.");
            return -1;
        }

        // Returning the number of the first node in the queue without removing it
        return queList.get(0).getNomor();
    }
    public String peekFrontName(){
         if (isEmpty()) {
            System.out.println("Queue is empty. Cannot peek.");
            return null;
        }

        // Returning the number of the first node in the queue without removing it
        return queList.get(0).getNama();
    }
}
