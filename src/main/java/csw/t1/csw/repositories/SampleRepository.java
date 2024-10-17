package csw.t1.csw.repositories;

import csw.t1.csw.entities.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample, Long> {
}
