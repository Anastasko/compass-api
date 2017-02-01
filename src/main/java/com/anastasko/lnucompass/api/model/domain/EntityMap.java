
package com.anastasko.lnucompass.api.model.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedEntityGraphs;
import com.anastasko.lnucompass.model.domain.AbstractEntity;

@Entity
@NamedEntityGraphs({

})
public class EntityMap
    extends AbstractEntity
{

    @Basic
    @Column(nullable = true)
    private String imageUrl;
    @Basic
    @Column(nullable = true)
    private Long floor;
    @ManyToOne
    private EntityCityItem owner;

    public EntityMap() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
