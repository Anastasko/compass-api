package com.anastasko.lnucompass.model.domain;

import javax.persistence.*;

@MappedSuperclass
public class AbstractContentEntity extends AbstractEntity {

    @MapsId
    @JoinColumn(name="id")
    @OneToOne(cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
    private ItemProperties properties = new ItemProperties();

    public ItemProperties getProperties() {
        return properties;
    }

    public void setProperties(ItemProperties properties) {
        this.properties = properties;
    }
}
