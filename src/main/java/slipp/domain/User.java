package slipp.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(length = 15, nullable = false, unique = true)
    private String userId;

    @NonNull
    @Column(length = 50, nullable = false)
    private String password;

    @NonNull
    @Column(length = 20, nullable = false)
    private String email;

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }
}
