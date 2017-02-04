
package com.anastasko.lnucompass.api.model.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import com.anastasko.lnucompass.model.domain.AbstractContentEntity;
import com.anastasko.lnucompass.model.enums.ItemKind;

@Entity
@NamedEntityGraphs({
    @NamedEntityGraph(name = "mapsGraph", attributeNodes = {
        @NamedAttributeNode("maps")
    })
})
public class EntityCityItem
    extends AbstractContentEntity
{

    @Basic
    @Column(nullable = true)
    private String name;
    @Basic
    @Column(nullable = true)
    private Double longitude;
    @Basic
    @Column(nullable = true)
    private Double latitude;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EntityMap> maps;
    @Basic
    @Column(nullable = true)
    private ItemKind kind;

    public EntityCityItem() {
        setMaps(new HashSet<EntityMap>());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Set<EntityMap> getMaps() {
        return maps;
    }

    public void setMaps(Set<EntityMap> maps) {
        this.maps = maps;
    }

    public ItemKind getKind() {
        return kind;
    }

    public void setKind(ItemKind kind) {
        this.kind = kind;
    }

}
