package com.guestbook.guestbook.repository;

import com.guestbook.guestbook.entity.Board;
import com.guestbook.guestbook.entity.Member;
import com.guestbook.guestbook.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ReplyRepositoryTests {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insertReply() {
        IntStream.rangeClosed(1, 10).forEach(i -> {

            long bno = (long) (Math.random() * 10 +1);

            Board board = Board.builder().bno(bno).build();

            Reply reply = Reply.builder()
                    .text("replay..." + i)
                    .board(board)
                    .replyer("userID" + i)
                    .build();

            replyRepository.save(reply);

        });
    }

    @Test
    public void testReadReply() {

        Optional<Reply> result = replyRepository.findById(2L);

        Reply reply = result.get();
        System.out.println(reply.toString());

    }

    @Test
    public void testReplyListByBoard() {
        List<Reply> replies = replyRepository.getRepliesByBoardOrderByRno(Board.builder().bno(51l).build());
        replies.forEach(reply -> System.out.println(reply));
    }
}
