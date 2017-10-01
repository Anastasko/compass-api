
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
    }),
    @NamedEntityGraph(name = "facultiesGraph", attributeNodes = {
        @NamedAttributeNode("faculties")
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
    private String placeId;
    @Basic
    @Column(nullable = true)
    private Double longitude;
    @Basic
    @Column(nullable = true)
    private Double latitude;
    @Basic
    @Column(nullable = true)
    private String address;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private Set<EntityMap> maps;
    @ManyToOne
    private EntityItemKind kind;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private Set<EntityFaculty> faculties;
    @ManyToOne
    private EntityRoot owner;

    public EntityCityItem() {
        setMaps(new HashSet<EntityMap>());
        setFaculties(new HashSet<EntityFaculty>());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Set<EntityFaculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(Set<EntityFaculty> faculties) {
        this.faculties = faculties;
    }

    public EntityRoot getOwner() {
        return owner;
    }

    public void setOwner(EntityRoot owner) {
        this.owner = owner;
    }

    @Override
    public Long getVersion() {
        Long version = getItem().getModified().getTime();
        return version;
    }

}
