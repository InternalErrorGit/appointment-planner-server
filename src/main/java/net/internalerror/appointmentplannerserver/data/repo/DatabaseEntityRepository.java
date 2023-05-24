package net.internalerror.appointmentplannerserver.data.repo;

import net.internalerror.appointmentplannerserver.data.DatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface DatabaseEntityRepository<T extends DatabaseEntity> extends JpaRepository<T, Long> {

}