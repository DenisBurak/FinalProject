package com.zemelya.service.model;

import com.zemelya.domain.hibernate.HibernateModel;
import com.zemelya.repository.model.ModelSpringDataRepository;
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
public class ModelServiceImpl implements ModelService {

  private final ModelSpringDataRepository repository;

  @Override
  public HibernateModel create(HibernateModel hibernateModel) {

    return saveModel(hibernateModel);
  }

  @Override
  public HibernateModel delete(Integer brandId) {

    HibernateModel hibernateModel = findById(brandId);

    hibernateModel.setIsAvailable(false);
    repository.save(hibernateModel);

    return findById(brandId);
  }

  @Transactional
  @Override
  public HibernateModel update(HibernateModel hibernateModel) {

    return saveModel(hibernateModel);
  }

  @Override
  public Page<HibernateModel> findAll(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @Override
  public List<HibernateModel> findAll() {
    return repository.findAll();
  }

  @Override
  public HibernateModel findById(Integer modelId) {
    Optional<HibernateModel> result = repository.findById(modelId);

    if (result.isPresent()) {
      return result.get();
    } else {
      throw new EntityNotFoundException(
          String.format("Model with this id \"%s\" is not found", modelId));
    }
  }

  @Override
  public List<Object[]> findOnlyModels() {
    return repository.findOnlyModels();
  }

  private HibernateModel saveModel(HibernateModel hibernateModel) {
    return repository.save(hibernateModel);
  }
}
