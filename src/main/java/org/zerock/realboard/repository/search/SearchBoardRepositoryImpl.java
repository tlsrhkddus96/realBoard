package org.zerock.realboard.repository.search;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.realboard.entity.Board;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository {
    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param domainClass must not be {@literal null}.
     */
    public SearchBoardRepositoryImpl(Class<?> domainClass) {
        super(domainClass);
    }

    @Override
    public Board search1() {

        log.info("search1...");

        return null;
    }
}
