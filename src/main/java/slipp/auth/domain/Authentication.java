package slipp.auth.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Authentication {
    @NonNull
    private String userId;

    @NonNull
    private String password;
}
