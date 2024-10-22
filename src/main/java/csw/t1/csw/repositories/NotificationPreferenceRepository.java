package csw.t1.csw.repositories;

import csw.t1.csw.entities.NotificationPreference;
import csw.t1.csw.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationPreferenceRepository extends JpaRepository<NotificationPreference, Long> {

    NotificationPreference findByUser(User user);

}