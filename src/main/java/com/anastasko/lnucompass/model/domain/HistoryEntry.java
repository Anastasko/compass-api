package com.anastasko.lnucompass.model.domain;

import com.anastasko.lnucompass.model.enums.EntityTypeName;
import com.anastasko.lnucompass.model.enums.ActionKind;

import javax.persistence.*;

@Entity
public class HistoryEntry extends AbstractEntity {

    @Column(name = "ord")
    private long order;

    @Enumerated
    private ActionKind action;

    @Basic
    private Long entity;

    @Enumerated
    private EntityTypeName type;

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
