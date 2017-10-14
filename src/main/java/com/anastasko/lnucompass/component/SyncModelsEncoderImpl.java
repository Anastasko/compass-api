package com.anastasko.lnucompass.component;

import com.anastasko.lnucompass.api.infrastructure.service.CityItemService;
import com.anastasko.lnucompass.api.model.domain.EntityCityItem;
import com.anastasko.lnucompass.implementation.AbstractSyncUtils;
import com.anastasko.lnucompass.infrastructure.SyncModelsEncoder;
import com.anastasko.lnucompass.model.view.SyncModels;
import com.anastasko.lnucompass.sync.converter.BitsBuilder;
import com.anastasko.lnucompass.validation.exceptions.ServiceException;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.anastasko.lnucompass.sync.compression.CompressionUtils.compress;

@Service
public class SyncModelsEncoderImpl extends AbstractSyncUtils implements SyncModelsEncoder {

    @Override
    public String encode(SyncModels models) {

        if (models.size() < 1 || models.size() > 2) throw new ServiceException("wrong models.size()");

        final BitsBuilder bitsBuilder = new BitsBuilder();

        bitsBuilder.put(models.size() == 2);

        models.forEach(model -> {
            bitsBuilder.put(model.getRange().getFrom());
            bitsBuilder.put(model.getRange().getTo());
            bitsBuilder.put(fromCityItems(model.getCityItems()));
        });

        List<Boolean> bits = bitsBuilder.getBits();

        bits = normalize(bits);

        return toString(bits);
    }

    private List<Boolean> fromCityItems(Set<Long> cityItems) {
        List<EntityCityItem> items = sortedCityItems();
        List<Boolean> result = new ArrayList<>();
        items.forEach(item -> result.add(cityItems.contains(item.getId())));
        return result;
    }

    private List<Boolean> normalize(List<Boolean> bits) {
        while (bits.size() % 8 != 0) {
            bits.add(false);
        }
        return bits;
    }

    private String toString(List<Boolean> bits) {
        byte [] bytes = makeBytes(bits);
        byte [] compressed = compress(bytes);
        return Base64.getEncoder().encodeToString(compressed);
    }

    private byte[] makeBytes(List<Boolean> bits) {
        byte [] bytes = new byte[bits.size()/8];
        int ind = 0;
        int pos = 0;
        for (Boolean bit : bits) {
            if (pos == 8) {
                pos = 0;
                ind++;
            }
            bytes[ind] <<= 1;
            if (bit) bytes[ind] |= 1;
            pos++;
        }
        return bytes;
    }

}
