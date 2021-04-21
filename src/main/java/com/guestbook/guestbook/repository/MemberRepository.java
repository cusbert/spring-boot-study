package com.guestbook.guestbook.repository;

import com.guestbook.guestbook.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    // roleSet 을 left join 으로 가져오도록 EntityGraph 설정
    // FETCH: entity graph에 명시한 attribute는 EAGER로 패치, 나머지 attribute는 LAZY로 패치
    // LOAD: entity graph에 명시한 attribute는 EAGER로 패치, 나머지 attribute는 entity에 명시한 fetch type이나 디폴트 FetchType으로 패치
    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from Member m " +
            "where m.fromSocial = :social " +
            "and m.email = :email")
    Optional<Member> findByEmail(@Param("email") String email, @Param("social") boolean social);
}
