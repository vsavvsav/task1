public class People {
    private String _id;
    private long balance;
    private long age;
    private String name;
    private String email;
    private String phone;

    public People(String _id, long balance, long age, String name, String email, String phone) {
        this._id = _id;
        this.balance = balance;
        this.age = age;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    public People() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone){this.phone = phone;}

    @Override
    public String toString() {
        return "People{" +
                "_id='" + _id + '\'' +
                ", balance=" + balance +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
