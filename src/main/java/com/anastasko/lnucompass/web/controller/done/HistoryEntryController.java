package com.anastasko.lnucompass.web.controller.done;

import com.anastasko.lnucompass.infrastructure.HistoryEntryService;
import com.anastasko.lnucompass.model.domain.HistoryEntry;
import com.anastasko.lnucompass.model.view.HistoryEntryViewModel;
import com.anastasko.lnucompass.web.controller.AbstractController;
import org.jsondoc.core.annotation.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Api(name = "HistoryEntry", description = "HistoryEntry")
@RequestMapping(value = "/historyEntry")
@RestController
public class HistoryEntryController {

    private static final Logger logger = LoggerFactory.getLogger(HistoryEntryController.class);

    @Autowired
    private HistoryEntryService historyEntryService;

    @RequestMapping(method = RequestMethod.GET)
    public List<HistoryEntryViewModel> getHistory(@RequestParam(value = "order", required = false) Long order) {
        if (order == null){
            order = 0L;
        }
        return historyEntryService.findAfter(order).stream()
                .map(entry -> new HistoryEntryViewModel(entry))
                .collect(Collectors.toList());
    }


}
