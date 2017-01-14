package com.anastasko.lnucompass.model.view;

import com.anastasko.lnucompass.model.domain.HistoryEntry;
import com.anastasko.lnucompass.model.enums.ActionKind;
import com.anastasko.lnucompass.model.enums.EntityTypeName;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name = "HistoryEntry")
public class HistoryEntryViewModel extends AbstractEntityViewModel {

    @ApiObjectField
    private long order;

    @ApiObjectField
    private ActionKind action;

    @ApiObjectField
    private Long entity;

    @ApiObjectField
    private EntityTypeName type;

    public HistoryEntryViewModel(HistoryEntry entry){
        super(entry);
        setOrder(entry.getOrder());
        setAction(entry.getAction());
        setEntity(entry.getEntity());
        setType(entry.getType());
    }

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }

    public ActionKind getAction() {
        return action;
    }

    public void setAction(ActionKind action) {
        this.action = action;
    }

    public Long getEntity() {
        return entity;
    }

    public void setEntity(Long entity) {
        this.entity = entity;
    }

    public EntityTypeName getType() {
        return type;
    }

    public void setType(EntityTypeName type) {
        this.type = type;
    }
}
