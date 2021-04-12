package com.guestbook.guestbook.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "member")
public class Member extends BaseEntity{

    @Id
    @Column(name = "id", length = 25, unique = true, nullable = false)
    private String id;

    @Column(name = "email", length = 25, nullable = false)
    private String email;

    @Column(name = "password", length = 25,  nullable = false)
    private String password;

    @Column(name = "name", length = 25, nullable = false)
    private String name;
}
