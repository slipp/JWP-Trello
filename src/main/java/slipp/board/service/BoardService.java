package slipp.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import slipp.auth.domain.User;
import slipp.board.domain.Board;
import slipp.board.domain.BoardRepository;

@Service
@Slf4j
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    
    public Board findById(long id) {
        return boardRepository.findOne(id);
    }
    
    public Board create(User loginUser, Board board) {
        board.loginUser(loginUser);
        return boardRepository.save(board);
    }

    @PreAuthorize("#board.email == authentication.name")
    public void delete(User loginUser, Board board) {
        log.debug("loginUser : {}", loginUser);
        boardRepository.delete(board);       
    }
}
