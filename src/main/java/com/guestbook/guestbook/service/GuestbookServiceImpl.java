package com.guestbook.guestbook.service;

import com.guestbook.guestbook.common.GuestbookNotFoundException;
import com.guestbook.guestbook.dto.GuestbookDTO;
import com.guestbook.guestbook.dto.PageRequestDTO;
import com.guestbook.guestbook.dto.PageResultDTO;
import com.guestbook.guestbook.entity.Guestbook;
import com.guestbook.guestbook.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor // 의존성 자동 주입
public class GuestbookServiceImpl implements GuestbookService {

    private final GuestbookRepository repository;

    @Override
    public GuestbookDTO register(GuestbookDTO dto) {

        Guestbook entity = dtoToEntity(dto);
        repository.save(entity);
        return entityToDTO(entity);
    }

    @Override
    public GuestbookDTO read(long gno) {
        Optional<Guestbook> result = repository.findById(gno);
        if(result.isPresent()) {
            return entityToDTO(result.get());
        } else {
            throw new GuestbookNotFoundException(gno);
        }
    }

    @Override
    public GuestbookDTO modify(GuestbookDTO guestbookDTO) {
       return repository.findById(guestbookDTO.getGno())
                .map(entity -> {
                    entity.changeTitle(guestbookDTO.getTitle());
                    entity.changeContent(guestbookDTO.getContent());
                    repository.save(entity);
                    return entityToDTO(entity);
                }).orElseGet(() -> {
                    return null; });
    }

    @Override
    public void delete(Long gno) {
        repository.deleteById(gno);
    }

    @Override
    public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());

        Page<Guestbook> result = repository.findAll(pageable);
        Function<Guestbook, GuestbookDTO> fn = (entity -> entityToDTO(entity));

        return new PageResultDTO<>(result, fn);
    }
}
