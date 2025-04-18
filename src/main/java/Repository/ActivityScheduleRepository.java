package Repository;

import Entity.ActivitySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityScheduleRepository extends JpaRepository<ActivitySchedule, Long> {
}
