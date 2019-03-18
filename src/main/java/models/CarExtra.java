package models;

public class CarExtra {
    private String name;
    private String status;
    private String price;

    public static Builder newEntity() {return new CarExtra().new Builder(); }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getPrice() {
        return price;
    }

    public class Builder {
        private Builder() {}
        public Builder withName(String name) { CarExtra.this.name = name; return this; }
        public Builder withStatus(String status) { CarExtra.this.status = status; return this; }
        public Builder withPrice(String price) { CarExtra.this.price = price; return this; }
        public CarExtra build() { return CarExtra.this; }
    }
}
