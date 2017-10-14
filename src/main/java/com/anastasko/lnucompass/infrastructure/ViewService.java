package com.anastasko.lnucompass.infrastructure;

import com.anastasko.lnucompass.model.domain.AbstractEntity;
import com.anastasko.lnucompass.model.view.AbstractEntityViewModel;
import com.anastasko.lnucompass.model.view.EntityViewModel;
import com.anastasko.lnucompass.model.view.SyncModels;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface ViewService<T extends AbstractEntity, V extends EntityViewModel> {

    List<V> findAll();

    List<V> findMany(Iterable<Long> ids);

    V findOne(Long id);

    Long create(V model);

    void update(V model);

    void delete(Long id);

    List<V> viewModels(Collection<T> list);

    void mergeFields(T e, V v);

}
