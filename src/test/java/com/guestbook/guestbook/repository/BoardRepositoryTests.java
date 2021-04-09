package com.guestbook.guestbook.repository;

import com.guestbook.guestbook.entity.Board;
import com.guestbook.guestbook.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
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
                    .id("userID" + (i%11))
                    .build();

            Board board = Board.builder()
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

    @Transactional
    @Test
    public void testReadBoardForLazyLoading() {

        Optional<Board> result = boardRepository.findById(10L);

        Board board = result.get();
        System.out.println(board.toString());
        System.out.println(board.getWriter());
    }

    @Test
    public void testReadBoardWithWriter() {

        Object result = boardRepository.getBoardWithWriter(2L);

        Object[] arr = (Object[]) result;
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testReadBoardWithReply() {

        List<Object[]> result = boardRepository.getBoardWithReply(2L);

        for (Object[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }
    }

    @Test
    public void testReadBoardWithReplyCount() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);

        result.get().forEach(row -> {
            Object[] arr = (Object[]) row;
            System.out.println(Arrays.toString(arr));
        });
    }

    @Test
    public void testReadBoardByBno() {

        Object result = boardRepository.getBoardByBno(2L);
        Object[] arr = (Object[]) result;
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testSearchOne() {
        Board board = boardRepository.searchOne();
        System.out.println("===");
        System.out.println(board);
    }

    @Test
    public void testSearchPage() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("modDate").descending()
                .and(Sort.by("title").ascending()));
        Page<Object[]> result = boardRepository.searchPage("t", "big", pageable);

    }
}
