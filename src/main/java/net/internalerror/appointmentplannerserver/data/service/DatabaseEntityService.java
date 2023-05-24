package net.internalerror.appointmentplannerserver.data.service;

import lombok.AllArgsConstructor;
import net.internalerror.appointmentplannerserver.data.DatabaseEntity;
import net.internalerror.appointmentplannerserver.data.repo.DatabaseEntityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public abstract class DatabaseEntityService<Entity extends DatabaseEntity, Repository extends DatabaseEntityRepository<Entity>> {

    protected final Repository repository;

    public Entity save(Entity entity) {
        if (entity.isNew()) {
            entity.setCreateDate(LocalDateTime.now());
        }
        entity.setUpdateDate(LocalDateTime.now());

        return repository.save(entity);
    }

    public Entity get(long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Entity> getAll() {
        return repository.findAll();
    }

    public void delete(long id) {

        Entity entity = get(id);
        if (entity != null) {
            repository.delete(entity);
        }

    }


}
