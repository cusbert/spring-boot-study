package com.guestbook.guestbook.repository;

import com.guestbook.guestbook.entity.Guestbook;
import com.guestbook.guestbook.repository.GuestbookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import static org.junit.Assert.*;

@SpringBootTest
public class GuestbookRepositoryTests {

    @Autowired
    GuestbookRepository guestbookRepository;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Guestbook guestbook = Guestbook.builder()
                    .title("Title..." + i)
                    .content("content..." + i)
                    .writer("writer..."+i)
                    .build();

            System.out.println(guestbookRepository.save(guestbook));

        });
        //when
        List<Guestbook> postsList=guestbookRepository.findAll();

        //then
        Guestbook posts=postsList.get(0);

        System.out.println(posts);
    }

    @Test
    public void updateTest() {

        IntStream.rangeClosed(1, 100).forEach(i -> {
            Guestbook guestbook = Guestbook.builder()
                    .title("Title..." + i)
                    .content("content..." + i)
                    .writer("writer..."+i)
                    .build();

            System.out.println(guestbookRepository.save(guestbook));

        });

        Optional<Guestbook> result = guestbookRepository.findById(100l);

        if(result.isPresent()) {
            Guestbook guestbook = result.get();

            guestbook.changeTitle("changeTitle..");
            guestbook.changeContent("changeContent..");

            guestbookRepository.save(guestbook);
        }

        Optional<Guestbook> result2 = guestbookRepository.findById(100l);

        System.out.println(result2);
    }
}
