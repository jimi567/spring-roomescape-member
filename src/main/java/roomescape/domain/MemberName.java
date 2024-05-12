package roomescape.domain;

import io.micrometer.common.util.StringUtils;
import java.util.Objects;

public class MemberName {
    private static final int MAX_NAME_SIZE = 10;

    private final String value;

    public MemberName(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String value) {
        if (StringUtils.isBlank(value) || value.length() > MAX_NAME_SIZE) {
            throw new IllegalArgumentException(value + " 사용자 이름 1글자 이상 10글자 이하를 입력해주세요.");
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MemberName that = (MemberName) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}