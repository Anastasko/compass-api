package com.anastasko.lnucompass.component;

import com.anastasko.lnucompass.api.infrastructure.service.CityItemService;
import com.anastasko.lnucompass.api.model.domain.EntityCityItem;
import com.anastasko.lnucompass.implementation.AbstractSyncUtils;
import com.anastasko.lnucompass.infrastructure.SyncModelsDecoder;
import com.anastasko.lnucompass.model.view.SyncModels;
import com.anastasko.lnucompass.sync.converter.BitsTokenizer;
import com.anastasko.lnucompass.sync.model.SyncModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.anastasko.lnucompass.sync.compression.CompressionUtils.decompress;

@Service
public class SyncModelsDecoderImpl extends AbstractSyncUtils implements SyncModelsDecoder {

    @Override
    public SyncModels decode(String s) {

        if (s == null) {
            throw new IllegalArgumentException("can't decode null");
        }

        List<Boolean> bits = fromString(s);

        SyncModels models = new SyncModels();

        BitsTokenizer tokenizer = new BitsTokenizer(bits);

        int cnt = tokenizer.nextBoolean() ? 2 : 1;

        while (cnt-- > 0) {
            SyncModel model = new SyncModel();
            model.getRange().setFrom(tokenizer.nextNumber());
            model.getRange().setTo(tokenizer.nextNumber());
            model.setCityItems(toCityItems(tokenizer.nextList()));
            models.add(model);
        }
        return models;
    }

    private Set<Long> toCityItems(List<Boolean> booleans) {
        List<EntityCityItem> items = sortedCityItems();
        Set<Long> result = new HashSet<>();
        for(int ind = 0; ind < booleans.size(); ++ind){
            if (booleans.get(ind)) {
                result.add(items.get(ind).getId());
            }
        }
        return result;
    }

    private List<Boolean> fromString(String s) {
        byte[] bytes = Base64.getDecoder().decode(s);
        byte[] decompressed = decompress(bytes);
        return makeBits(decompressed);
    }

    private List<Boolean> makeBits(byte[] bytes) {
        List<Boolean> bits = new ArrayList<>();
        for (int i = 0; i < bytes.length; ++i) {
            for (int j = 7; j >= 0; --j) {
                bits.add(((bytes[i] >> j) & 1) == 1);
            }
        }
        return bits;
    }

}
