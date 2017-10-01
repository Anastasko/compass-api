
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
    private Double square;
    @Basic
    @Column(nullable = true)
    private String room;
    @ManyToOne
    private EntityItemKind kind;
    @ManyToOne
    private EntityFaculty faculty;

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

    public Double getSquare() {
        return square;
    }

    public void setSquare(Double square) {
        this.square = square;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public EntityItemKind getKind() {
        return kind;
    }

    public void setKind(EntityItemKind kind) {
        this.kind = kind;
    }

    public EntityFaculty getFaculty() {
        return faculty;
    }

    public void setFaculty(EntityFaculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public Long getVersion() {
        Long version = getItem().getModified().getTime();
        return version;
    }

}
