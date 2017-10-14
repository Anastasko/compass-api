package com.anastasko.lnucompass.model.domain;


import com.anastasko.lnucompass.api.model.domain.EntityCityItem;
import com.anastasko.lnucompass.model.enums.EntityState;
import com.anastasko.lnucompass.model.enums.EntityTypeName;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Item extends AbstractEntity {

    @ManyToOne
    private EntityCityItem cityItem;

    @Basic
    @Column(nullable = true)
    private Long lastTransaction;

    @Column
    private EntityState state = EntityState.ACTIVE;

    @Basic(fetch = FetchType.LAZY)
    private Date created;

    @Basic(fetch = FetchType.LAZY)
    private Date modified;

    @Column
    private EntityTypeName type;

    public Long getLastTransaction() {
        return lastTransaction;
    }

    public void setLastTransaction(Long lastTransaction) {
        this.lastTransaction = lastTransaction;
    }

    public EntityState getState() {
        return state;
    }

    public void setState(EntityState state) {
        this.state = state;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public EntityTypeName getType() {
        return type;
    }

    public void setType(EntityTypeName type) {
        this.type = type;
    }

    public EntityCityItem getCityItem() {
        return cityItem;
    }

    public void setCityItem(EntityCityItem cityItem) {
        this.cityItem = cityItem;
    }

}
