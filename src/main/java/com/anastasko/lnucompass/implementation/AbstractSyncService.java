package com.anastasko.lnucompass.implementation;

import com.anastasko.lnucompass.api.infrastructure.service.CityItemService;
import com.anastasko.lnucompass.infrastructure.*;
import com.anastasko.lnucompass.model.domain.AbstractContentEntity;
import com.anastasko.lnucompass.model.enums.EntityState;
import com.anastasko.lnucompass.model.view.EntityViewModel;
import com.anastasko.lnucompass.model.view.SyncModels;
import com.anastasko.lnucompass.sync.model.Range;
import com.anastasko.lnucompass.sync.model.SyncModel;
import com.anastasko.lnucompass.validation.exceptions.ResourceNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class AbstractSyncService implements SyncService {

    @Autowired
    private SyncModelsDecoder decoder;

    @Autowired
    private SyncModelsEncoder encoder;

    @Autowired
    private RootService rootService;

    @Autowired
    private CityItemService cityItemService;

    protected <
            T extends AbstractContentEntity,
            V extends EntityViewModel
            >
    void sync(SyncModels models, ContentEntityService<T> es, ViewService<T, V> vs, Consumer<List<V>> act, Consumer<List<Long>> del) {
        List<T> ts = es.findSync(models);

        act.accept(vs.viewModels(ts.stream()
                .filter(t -> t.getItem().getState() == EntityState.ACTIVE)
                .collect(Collectors.toList())));

        del.accept(ts.stream()
                .filter(t -> t.getItem().getState() == EntityState.DELETED)
                .map(t -> t.getId())
                .collect(Collectors.toList()));
    }

    @Override
    public String exchange(String hash, Set<Long> toWatch) {
        toWatch.forEach(id -> {
            if (cityItemService.findOne(id) == null) {
                throw new ResourceNotFoundException("no city item with id: " + id);
            }
        });
        SyncModels models = _decode(hash);
        long lastTransaction = rootService.lastTransactionNumber();
        models.forEach(model -> {
            model.getCityItems().retainAll(toWatch);
            model.getRange().setTo(lastTransaction);
        });
        toWatch.removeAll(models.get(0).getCityItems());
        if (models.size() == 1) {
            models.add(new SyncModel(0L, lastTransaction));
        }
        SyncModel model2 = models.get(1);
        model2.getCityItems().addAll(toWatch);
        if (models.get(0).empty()) {
            models.remove(0);
        } else if (models.get(1).empty()) {
            models.remove(1);
        }
        return encoder.encode(models);
    }

    @Override
    public String next(String hash) {
        SyncModels models = _decode(hash);
        long lastTransaction = rootService.lastTransactionNumber();
        if (models.get(0).getRange().getTo() == lastTransaction) {
            return hash;
        }
        Set<Long> toWatch = new HashSet<>();
        models.forEach(model -> toWatch.addAll(model.getCityItems()));

        SyncModel response = new SyncModel();
        response.setCityItems(toWatch);
        response.getRange().setFrom(models.get(0).getRange().getTo());
        response.getRange().setTo(lastTransaction);

        SyncModels results = new SyncModels();
        results.add(response);
        return encoder.encode(results);
    }

    private SyncModels _decode(String hash) {
        SyncModels models = new SyncModels();
        if (StringUtils.isEmpty(hash)) {
            models.add(new SyncModel(0L, 0L));
        } else {
            models = decoder.decode(hash);
        }
        return models;
    }

}
