
package com.anastasko.lnucompass.api.model.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import com.anastasko.lnucompass.model.domain.AbstractContentEntity;

@Entity
@NamedEntityGraphs({
    @NamedEntityGraph(name = "mapsGraph", attributeNodes = {
        @NamedAttributeNode("maps")
    })
})
public class EntityCityItem
    extends AbstractContentEntity
{

    @ManyToOne
    private EntityRoot owner;
    @Basic
    @Column(nullable = true)
    private String name;
    @Basic
    @Column(nullable = true)
    private Double longitude;
    @Basic
    @Column(nullable = true)
    private Double latitude;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private Set<EntityMap> maps;
    @ManyToOne
    private EntityItemKind kind;

    public EntityCityItem() {
        setMaps(new HashSet<EntityMap>());
    }

    public EntityRoot getOwner() {
        return owner;
    }

    public void setOwner(EntityRoot owner) {
        this.owner = owner;
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

    public EntityItemKind getKind() {
        return kind;
    }

    public void setKind(EntityItemKind kind) {
        this.kind = kind;
    }

}
