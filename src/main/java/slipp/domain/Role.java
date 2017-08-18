package slipp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

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
