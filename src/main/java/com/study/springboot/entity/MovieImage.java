package com.study.springboot.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "movie")
@Table(name = "movie_image")
public class MovieImage extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ino", nullable = false)
    private Long ino;

    @Column(name = "uuid", nullable = false)
    private String uuid;

    @Column(name = "imgName", nullable = false)
    private String imgName;

    @Column(name = "path", nullable = true)
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mno", foreignKey = @ForeignKey(name = "FK_MOVIEIMAGE_MOVIE"))
    private Movie movie;
}
