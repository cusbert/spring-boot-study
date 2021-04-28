package com.study.springboot.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"movie", "member"})
@Table(name = "review")
public class Review extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewId", nullable = false)
    private Long reviewId;
    @Column(name = "grade", nullable = false)
    private int grade;
    @Column(name = "text", nullable = true)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mno", foreignKey = @ForeignKey(name = "FK_REVIEW_MOVIE"))
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", foreignKey = @ForeignKey(name = "FK_REVIEW_MEMBER"))
    private Member member;

    public void changeGrade(int grade) {
        this.grade = grade;
    }

    public void changeText(String text) {
        this.text = text;
    }
}
