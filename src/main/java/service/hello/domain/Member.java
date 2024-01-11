package service.hello.domain;

import lombok.Data;

@Data
public class Member {
    private Long id;
    private String name;
    private Integer age;
    private String address;

    public Member() {
    }

    public Member(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
