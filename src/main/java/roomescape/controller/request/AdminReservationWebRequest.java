package roomescape.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AdminReservationWebRequest(
    @NotBlank(message = "예약 날짜는 필수입니다.") String date,
    @NotNull @Positive Long timeId,
    @NotNull @Positive Long themeId,
    @NotNull @Positive Long memberId
) {
}