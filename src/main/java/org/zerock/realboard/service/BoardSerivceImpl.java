package org.zerock.realboard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.realboard.dto.BoardDTO;
import org.zerock.realboard.dto.PageRequestDTO;
import org.zerock.realboard.dto.PageResultDTO;
import org.zerock.realboard.entity.Board;
import org.zerock.realboard.entity.Member;
import org.zerock.realboard.repository.BoardRepository;
import org.zerock.realboard.repository.ReplyRepository;

import javax.transaction.Transactional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardSerivceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Override
    public Long register(BoardDTO dto) {

        log.info(dto);

        Board board = dtoToEntity(dto);

        boardRepository.save(board);

        return board.getBno();
    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        log.info(pageRequestDTO);

        Function<Object[] , BoardDTO> fn = (en -> entityToDTO((Board) en[0],(Member) en[1], (Long) en[2]));

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(
                pageRequestDTO.getPageable(Sort.by("bno").descending()));

        return new PageResultDTO<>(result,fn);
    }

    @Override
    public BoardDTO get(Long bno) {

        Object result = boardRepository.getBoardByBno(bno);

        Object[] arr = (Object[]) result;

        return entityToDTO((Board) arr[0],(Member) arr[1],(Long) arr[2]);
    }

    @Transactional
    @Override
    public void removeWithReplies(Long bno) {

        replyRepository.deleteByBno(bno);

        boardRepository.deleteById(bno);


    }

    @Transactional
    @Override
    public void modify(BoardDTO boardDTO) {

        Board board = boardRepository.getOne(boardDTO.getBno());

        board.changeContent(boardDTO.getContent());
        board.changeTitle(boardDTO.getTitle());

        boardRepository.save(board);

    }
}
