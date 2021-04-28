package com.study.springboot.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "password", length = 500,  nullable = false)
    private String password;

    @Column(name = "name", length = 25, nullable = false)
    private String name;

    @Column(name = "from_social", length = 1, nullable = false)
    private boolean fromSocial;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "FK_ROLE_MEMBER"))
    private Set<MemberRole> roleSet = new HashSet<>();

    public void addMemberRole(MemberRole memberRole) {
        roleSet.add(memberRole);
    }
}
