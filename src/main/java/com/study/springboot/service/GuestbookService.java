package com.study.springboot.service;

import com.study.springboot.dto.GuestbookDTO;
import com.study.springboot.dto.PageRequestDTO;
import com.study.springboot.dto.PageResultDTO;
import com.study.springboot.entity.Guestbook;

public interface GuestbookService {

    GuestbookDTO register(GuestbookDTO dto);

    PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO);

    GuestbookDTO read(long gno);

    GuestbookDTO modify(GuestbookDTO guestbookDTO);

    void delete(Long gno);

    default Guestbook dtoToEntity(GuestbookDTO dto) {

        return Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
    }

    default GuestbookDTO entityToDTO(Guestbook entity) {
        return GuestbookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
    }


}
