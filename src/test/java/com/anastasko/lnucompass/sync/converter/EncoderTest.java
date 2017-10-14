package com.anastasko.lnucompass.sync.converter;

import com.anastasko.lnucompass.infrastructure.SyncModelsDecoder;
import com.anastasko.lnucompass.infrastructure.SyncModelsEncoder;
import com.anastasko.lnucompass.model.view.SyncModels;
import com.anastasko.lnucompass.sync.model.SyncModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

import static org.junit.Assert.*;

public class EncoderTest {

    @Autowired
    private SyncModelsDecoder decoder;

    @Autowired
    private SyncModelsEncoder encoder;

    @Test
    public void encode1() throws Exception {
        SyncModel sm = new SyncModel();
        SyncModels list = new SyncModels();
        sm.getRange().setFrom(1234L);
        sm.getRange().setTo(4321L);
        List<Boolean> filters = new ArrayList<>();
        filters.add(true);
        filters.add(true);
        filters.add(true);
        filters.add(false);
        filters.add(false);
        filters.add(false);
        filters.add(true);
        filters.add(true);
        filters.add(true);
        list.add(sm);
        String HASH = encoder.encode(list);
        System.out.println(HASH);
        List<SyncModel> list2 = decoder.decode(HASH);
        assertEquals(list, list2);
    }

    @Test
    public void encode2() throws Exception {

        SyncModels list = new SyncModels();
        SyncModel sm1 = new SyncModel();
        sm1.getRange().setFrom(44234L);
        sm1.getRange().setTo(115521L);
        List<Boolean> filters = new ArrayList<>();
        filters.add(true);
        filters.add(false);
        filters.add(true);
        filters.add(true);
        filters.add(false);
        filters.add(true);
        filters.add(false);
        filters.add(true);
        filters.add(true);
        for(int i=0; i<290; ++i) filters.add(Math.random() < 0.41);

        SyncModel sm2 = new SyncModel();
        sm2.getRange().setFrom(4L);
        sm2.getRange().setTo(1121L);
        List<Boolean> filters2 = new ArrayList<>();
        filters2.add(true);
        filters2.add(false);
        filters2.add(false);
        filters2.add(true);
        filters2.add(true);
        filters2.add(true);

        list.add(sm1);
        list.add(sm2);

        String HASH = encoder.encode(list);
        System.out.println(HASH);

        List<SyncModel> list2 = decoder.decode(HASH);
        assertEquals(list, list2);
    }

}