package com.guestbook.guestbook.service;

import com.guestbook.guestbook.common.GuestbookNotFoundException;
import com.guestbook.guestbook.dto.GuestbookDTO;
import com.guestbook.guestbook.dto.PageRequestDTO;
import com.guestbook.guestbook.dto.PageResultDTO;
import com.guestbook.guestbook.entity.Guestbook;
import com.guestbook.guestbook.entity.QGuestbook;
import com.guestbook.guestbook.repository.GuestbookRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
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

        Pageable pageable = requestDTO.getPageable(Sort.by("modDate").descending());
        // 검색조건 추가
        BooleanBuilder booleanBuilder = getSearch(requestDTO);
        // Querydsl 사용
        Page<Guestbook> result = repository.findAll(booleanBuilder, pageable);

        Function<Guestbook, GuestbookDTO> fn = (entity -> entityToDTO(entity));

        return new PageResultDTO<>(result, fn);
    }

    private BooleanBuilder getSearch(PageRequestDTO requestDTO) {

        String type = requestDTO.getType();

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QGuestbook qGuestbook = QGuestbook.guestbook;

        String keyword = requestDTO.getKeyword();
        BooleanExpression expression = qGuestbook.gno.gt(0L); // gno > 0


        booleanBuilder.and(expression);

        // 검색 조건이 없는 경우 -> gno > 0 으로만 생성
        if(type == null || type.trim().length() == 0) {
            return booleanBuilder;
        }

        // TODO: front 에서 에러 처리
        if(keyword == null || keyword.trim().length() == 0) {
            return booleanBuilder;
        }

        // 검색조건
        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if(type.contains(("t"))) {
            conditionBuilder.or(qGuestbook.title.contains(keyword));
        }
        if(type.contains(("c"))) {
            conditionBuilder.or(qGuestbook.content.contains(keyword));
        }
        if(type.contains(("w"))) {
            conditionBuilder.or(qGuestbook.writer.contains(keyword));
        }

        // 모든 조건 통합
        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }
}
