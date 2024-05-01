package roomescape.domain;

import java.time.LocalTime;
import java.util.List;

public interface ReservationTimeRepository {

    ReservationTime save(ReservationTime reservationTime);

    ReservationTime findById(Long id);

    int deleteById(Long id);

    List<ReservationTime> findAll();

    Long countByStartTime(LocalTime startTime);
}