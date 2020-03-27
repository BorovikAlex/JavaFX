package client.entityClass;

public class Firm {
    private int id_firm;
    private String firm;
    private String city;
    private String street;
    private int house;

    public int getId_firm() {
        return id_firm;
    }

    public void setId_firm(int id_firm) {
        this.id_firm = id_firm;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }
}
