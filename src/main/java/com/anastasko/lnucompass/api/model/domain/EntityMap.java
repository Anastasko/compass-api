
package com.anastasko.lnucompass.api.model.domain;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToOne;
import com.anastasko.lnucompass.model.domain.AbstractContentEntity;
import com.anastasko.lnucompass.model.domain.UrlResource;

@Entity
@NamedEntityGraphs({

})
public class EntityMap
    extends AbstractContentEntity
{

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private UrlResource image;
    @Basic
    @Column(nullable = true)
    private Long floor;
    @ManyToOne
    private EntityCityItem owner;

    public EntityMap() {
        setImage(new UrlResource());
    }

    public UrlResource getImage() {
        return image;
    }

    public void setImage(UrlResource image) {
        this.image = image;
    }

    public Long getFloor() {
        return floor;
    }

    public void setFloor(Long floor) {
        this.floor = floor;
    }

    public EntityCityItem getOwner() {
        return owner;
    }

    public void setOwner(EntityCityItem owner) {
        this.owner = owner;
    }

}
