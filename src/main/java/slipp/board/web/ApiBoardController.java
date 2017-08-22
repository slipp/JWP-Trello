package slipp.board.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import slipp.auth.domain.User;
import slipp.auth.service.LoginUser;
import slipp.board.domain.Board;
import slipp.board.service.BoardService;

@RestController
@RequestMapping("/api/boards")
@Slf4j
public class ApiBoardController {
    @Autowired
    private BoardService boardService;
    
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Board create(@LoginUser User loginUser, @RequestBody Board board) {
        log.debug("board : {}", board);
        return boardService.create(loginUser, board);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@LoginUser User loginUser, @PathVariable long id) {
        boardService.delete(loginUser, boardService.findById(id));
    }
}
