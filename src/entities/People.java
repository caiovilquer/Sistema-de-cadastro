package entities;

import java.util.HashMap;
import java.util.Map;

public class People {
    private String name;
    private String email;
    private Integer age;
    private Double height;
    public static final int fixedQuestions = 4;
    private Map<Integer, String> extraAttributes = new HashMap<>();

    public People(String name, String email, Integer age, Double height) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.height = height;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }



    public void setExtraAttributes(Integer key, String value) {
        extraAttributes.put(key, value);
    }

    public String getExtraAttributes(Integer key) {
        return extraAttributes.get(key);
    }


    @Override
    public String toString() {
        return name + "\n" +
                email + "\n" +
                age + "\n" +
                height;
    }
}





