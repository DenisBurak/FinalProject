package com.zemelya.service.bodyType;

import com.zemelya.domain.hibernate.HibernateBodyType;
import com.zemelya.domain.hibernate.HibernateRole;
import com.zemelya.repository.bodyType.BodyTypeSpringDataRepository;
import com.zemelya.repository.role.RoleSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BodyTypeServiceImpl implements BodyTypeService{

    private final BodyTypeSpringDataRepository repository;

    @Override
    public HibernateBodyType create(HibernateBodyType hibernateBodyType) {

        return saveBodyType(hibernateBodyType);
    }

    @Override
    public HibernateBodyType delete(Integer bodyTypeId) {

    HibernateBodyType hibernateBodyType = findById(bodyTypeId);

    hibernateBodyType.setIsAvailable(false);
        repository.save(hibernateBodyType);

    return findById(bodyTypeId);
    }

    @Transactional
    @Override
    public HibernateBodyType update(HibernateBodyType hibernateBodyType) {

        return saveBodyType(hibernateBodyType);
    }

    @Override
    public Page<HibernateBodyType> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<HibernateBodyType> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Object[]> findOnlyBodyTypes() {
        return repository.findOnlyBodyTypes();
    }

    @Override
    public HibernateBodyType findById(Integer roleId) {
        Optional<HibernateBodyType> result = repository.findById(roleId);

        if (result.isPresent()) {
            return result.get();
        } else {
            throw new EntityNotFoundException(
                    String.format("Body type with this id \"%s\" is not found", roleId));
        }
    }

    private HibernateBodyType saveBodyType(HibernateBodyType hibernateBodyType){
        return repository.save(hibernateBodyType);
    }
}
