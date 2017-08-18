package slipp.auth.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = 3707036525592329924L;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }
}
