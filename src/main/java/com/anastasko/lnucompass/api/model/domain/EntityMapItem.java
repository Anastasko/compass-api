
package com.anastasko.lnucompass.api.model.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedEntityGraphs;
import com.anastasko.lnucompass.model.domain.AbstractContentEntity;

@Entity
@NamedEntityGraphs({

})
public class EntityMapItem
    extends AbstractContentEntity
{

    @ManyToOne
    private EntityMap owner;
    @Basic
    @Column(nullable = true)
    private String name;
    @Basic
    @Column(nullable = true)
    private Double x;
    @Basic
    @Column(nullable = true)
    private Double y;
    @Basic
    @Column(nullable = true)
    private Double path;
    @ManyToOne
    private EntityItemKind kind;

    public EntityMapItem() {
    }

    public EntityMap getOwner() {
        return owner;
    }

    public void setOwner(EntityMap owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getPath() {
        return path;
    }

    public void setPath(Double path) {
        this.path = path;
    }

    public EntityItemKind getKind() {
        return kind;
    }

    public void setKind(EntityItemKind kind) {
        this.kind = kind;
    }

    @Override
    public Long getVersion() {
        Long version = getItem().getModified().getTime();
        return version;
    }

}
