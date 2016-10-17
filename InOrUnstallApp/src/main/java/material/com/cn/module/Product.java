package material.com.cn.module;


public class Product {
    private String name;
    private int age;
    public Product(){

    }
    public Product(String name,int age){
        this.name = name;
        this.age = age;
    }
    public int getAge() {
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
}
