package com.anastasko.lnucompass.infrastructure;

import com.anastasko.lnucompass.model.domain.AbstractEntity;
import com.anastasko.lnucompass.model.view.AbstractEntityViewModel;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface ViewService<T extends AbstractEntity, V extends AbstractEntityViewModel> {

    List<V> findAll();

    V findOne(Long id);

    Long create(V model);

    void update(V model);

    void delete(Long id);

    List<V> viewModels(Collection<T> list);

}
