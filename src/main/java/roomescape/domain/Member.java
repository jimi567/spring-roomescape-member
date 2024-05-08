package roomescape.domain;

import io.micrometer.common.util.StringUtils;

public class Member {

    private final Long id;
    private final String name;
    private final MemberEmail email;
    private final MemberPassword password;

    public Member(String name, MemberEmail email, MemberPassword password) {
        this(null, name, email, password);
    }

    public Member(Long id, String name, MemberEmail email, MemberPassword password) {
        validate(name, email, password);
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    private void validate(String name, MemberEmail email, MemberPassword password) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("사용자 이름은 비어 있을 수 없습니다.");
        }
        if (email == null || password == null) {
            throw new IllegalArgumentException("사용자는 이메일, 비밀 번호가 필수입니다.");
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public MemberEmail getEmail() {
        return email;
    }

    public MemberPassword getPassword() {
        return password;
    }
}
