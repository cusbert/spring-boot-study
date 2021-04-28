package com.study.springboot.converter;

import com.study.springboot.dto.BoardDTO;
import com.study.springboot.entity.Board;
import com.study.springboot.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class BoardConverter {

    public Board dtoToEntity(BoardDTO dto) {

        Member member = Member.builder()
                .id(dto.getWriterId())
                .build();

        return Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();

    }

    public BoardDTO entityToDTO(Board board, Member member, Long replyCount) {
        return BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerId(member.getId())
                .writerName(member.getName())
                .writerEmail(member.getEmail())
                .replyCount(replyCount.intValue())
                .build();
    }

    public BoardDTO entityToDTO(Board board) {

        Member member = Member.builder()
                .id(board.getWriter().getId())
                .build();

        return BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerId(member.getId())
                .build();
    }
}
