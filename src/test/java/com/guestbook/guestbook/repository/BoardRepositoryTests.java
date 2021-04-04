package com.guestbook.guestbook.repository;

import com.guestbook.guestbook.entity.Board;
import com.guestbook.guestbook.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertBoard() {
        IntStream.rangeClosed(1, 10).forEach(i -> {

            Member member = Member.builder()
                    .id("userID"+i)
                    .build();

            Board board  = Board.builder()
                    .title("bigbang theory season" + i)
                    .content("This is cool" + i)
                    .writer(member)
                    .build();

            boardRepository.save(board);
        });
    }

    @Test
    public void testReadBoard() {

        Optional<Board> result = boardRepository.findById(10L);

        Board board = result.get();
        System.out.println(board.toString());

    }
}
