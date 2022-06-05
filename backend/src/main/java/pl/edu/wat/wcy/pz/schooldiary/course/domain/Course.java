package pl.edu.wat.wcy.pz.schooldiary.course.domain;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record Course(
        UUID id,
        String name,
        String schoolYear,
        List<UUID> studentIds,
        Instant createdAt
) {
}
