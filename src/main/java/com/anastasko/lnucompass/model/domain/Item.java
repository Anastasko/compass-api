package com.anastasko.lnucompass.model.domain;


import com.anastasko.lnucompass.model.enums.EntityTypeName;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.Date;

@Entity
public class Item extends AbstractEntity {

    @Basic(fetch = FetchType.LAZY)
    private Date created;

    @Basic(fetch = FetchType.LAZY)
    private Date modified;

    @Column
    private EntityTypeName type;

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

}
