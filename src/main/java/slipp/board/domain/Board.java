package slipp.board.domain;

import javax.persistence.Entity;

import slipp.auth.domain.User;
import support.domain.AbstractUserEntity;

@Entity
public class Board extends AbstractUserEntity {
    private String name;
    
    Board() {}
    
    public Board(String name, User loginUser) {
        super.loginUser(loginUser);
        this.name = name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
