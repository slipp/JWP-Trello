package slipp.domain;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
    @NonNull
    private String userId;

    @NonNull
    private String password;

    @NonNull
    private String email;
}
