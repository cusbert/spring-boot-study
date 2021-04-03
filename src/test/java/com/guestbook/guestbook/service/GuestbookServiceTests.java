package com.guestbook.guestbook.service;

import com.guestbook.guestbook.dto.GuestbookDTO;
import com.guestbook.guestbook.dto.PageRequestDTO;
import com.guestbook.guestbook.dto.PageResultDTO;
import com.guestbook.guestbook.entity.Guestbook;
import com.guestbook.guestbook.repository.GuestbookRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.stream.IntStream;

@SpringBootTest
public class GuestbookServiceTests {

    @Autowired
    private GuestbookService guestbookService;

    @Autowired
    private GuestbookRepository guestbookRepository;


    @Test
    public void testRegister() {
        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .title("sample title")
                .content("sample content")
                .writer("user1")
                .build();

        System.out.println(guestbookService.register(guestbookDTO));
    }

    @Test
    public void testList() {

        IntStream.rangeClosed(1, 100).forEach(i -> {
            Guestbook guestbook = Guestbook.builder()
                    .title("Title..." + i)
                    .content("content..." + i)
                    .writer("writer..."+i)
                    .build();

            System.out.println(guestbookRepository.save(guestbook));

        });

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();

        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = guestbookService.getList(pageRequestDTO);

        System.out.println("prev : " + resultDTO.isPrev());
        System.out.println("next : " + resultDTO.isNext());
        System.out.println("total : " + resultDTO.getTotalPage());


        System.out.println("####testList");
        for(GuestbookDTO guestbookDTO: resultDTO.getDtoList()) {
            System.out.println(guestbookDTO);
        }
    }

    @Test
    public void testSearch() {


        PageRequestDTO requestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .type("wc")
                .keyword("tony")
                .build();

        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = guestbookService.getList(requestDTO);

        System.out.println("prev : " + resultDTO.isPrev());
        System.out.println("next : " + resultDTO.isNext());
        System.out.println("total : " + resultDTO.getTotalPage());
        System.out.println("dto : " + resultDTO.getDtoList().size());

    }


}
