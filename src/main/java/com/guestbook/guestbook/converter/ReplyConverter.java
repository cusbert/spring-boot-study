package com.guestbook.guestbook.converter;

import com.guestbook.guestbook.dto.ReplyDTO;
import com.guestbook.guestbook.entity.Board;
import com.guestbook.guestbook.entity.Reply;
import org.springframework.stereotype.Component;

@Component
public class ReplyConverter {

    public static Reply dtoToEntity(ReplyDTO replyDTO) {

        Board board = Board.builder().bno(replyDTO.getBno()).build();

        return Reply.builder()
                .rno(replyDTO.getRno())
                .text(replyDTO.getText())
                .replyer(replyDTO.getReplyer())
                .board(board)
                .build();
    }

    public static ReplyDTO entityToDTO(Reply reply) {
        return ReplyDTO.builder()
                .rno(reply.getRno())
                .text(reply.getText())
                .replyer(reply.getReplyer())
                .bno(reply.getBoard().getBno())
                .regDate(reply.getRegDate())
                .modDate(reply.getModDate())
                .build();
    }
}
