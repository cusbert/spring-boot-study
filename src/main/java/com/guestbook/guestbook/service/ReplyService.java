package com.guestbook.guestbook.service;

import com.guestbook.guestbook.dto.ReplyDTO;

import java.util.List;

public interface ReplyService {

    Long register(ReplyDTO replyDTO);

    List<ReplyDTO> getList(Long bno);

    void remove(Long rno);

    void modify(ReplyDTO replyDTO);
}
