
package com.anastasko.lnucompass.api.model.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import com.anastasko.lnucompass.model.domain.AbstractContentEntity;
import com.anastasko.lnucompass.model.domain.UrlResource;

@Entity
@NamedEntityGraphs({
    @NamedEntityGraph(name = "mapItemsGraph", attributeNodes = {
        @NamedAttributeNode("mapItems")
    })
})
public class EntityMap
    extends AbstractContentEntity
{

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private UrlResource image;
    @Basic
    @Column(nullable = true)
    private Long floor;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private Set<EntityMapItem> mapItems;
    @ManyToOne
    private EntityCityItem owner;

    public EntityMap() {
        setImage(new UrlResource());
        setMapItems(new HashSet<EntityMapItem>());
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

    public Set<EntityMapItem> getMapItems() {
        return mapItems;
    }

    public void setMapItems(Set<EntityMapItem> mapItems) {
        this.mapItems = mapItems;
    }

    public EntityCityItem getOwner() {
        return owner;
    }

    public void setOwner(EntityCityItem owner) {
        this.owner = owner;
    }

}
