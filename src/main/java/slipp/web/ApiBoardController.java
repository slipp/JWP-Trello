package slipp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import slipp.board.Board;
import slipp.board.BoardRepository;
import slipp.domain.User;
import slipp.security.LoginUser;

@RestController
@RequestMapping("/api")
@Slf4j
public class ApiBoardController {
    @Autowired
    private BoardRepository boardRepository;
    
    @PostMapping("/boards")
    @ResponseStatus(HttpStatus.CREATED)
    public Board create(@LoginUser User loginUser, @RequestBody Board board) {
        log.debug("board : {}", board);
        board.loginUser(loginUser);
        return boardRepository.save(board);
    }
}
