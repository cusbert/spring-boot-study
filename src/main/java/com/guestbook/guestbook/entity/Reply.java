package com.guestbook.guestbook.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "board")
@Table(name = "reply")
public class Reply extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rno", nullable = false)
    private Long rno;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "replyer", nullable = false)
    private String replyer;

    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩 설정 -> 가급적 지연로딩을 써서 조인을 줄이자
    @JoinColumn(name = "bno", foreignKey = @ForeignKey(name = "FK_REPLY_BOARD"))
    private Board board;

}
