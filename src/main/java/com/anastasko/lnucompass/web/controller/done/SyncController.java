package com.anastasko.lnucompass.web.controller.done;

import com.anastasko.lnucompass.api.model.view.sync.SyncCountViewModel;
import com.anastasko.lnucompass.infrastructure.SyncModelsDecoder;
import com.anastasko.lnucompass.infrastructure.SyncService;
import com.anastasko.lnucompass.model.view.HashViewModel;
import com.anastasko.lnucompass.model.view.HashWithItemsViewModel;
import com.anastasko.lnucompass.model.view.SyncModels;
import com.anastasko.lnucompass.model.view.SyncViewModel;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Sync")
@RestController
public class SyncController {

    @Autowired
    private SyncService syncService;

    @Autowired
    private SyncModelsDecoder decoder;

    @GetMapping(value = "/count")
    public SyncCountViewModel count() {
        return syncService.count();
    }

    @PostMapping(value = "/sync")
    public SyncViewModel sync(@RequestBody HashViewModel hash) {
        SyncModels models = decoder.decode(hash.getHash());
        System.out.println(models);
        return syncService.sync(models);
    }

    @PostMapping(value = "/hash/next")
    public HashViewModel nextHash(@RequestBody HashViewModel view) {
        String hash = syncService.next(view.getHash());
        return new HashViewModel(hash);
    }

    @PostMapping(value = "/hash/exchange")
    public HashViewModel changeHash(@RequestBody HashWithItemsViewModel view) {
        String hash = syncService.exchange(view.getHash(), view.getCityItems());
        return new HashViewModel(hash);
    }

}
