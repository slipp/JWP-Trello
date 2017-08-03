package slipp.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Role {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String role;
}
