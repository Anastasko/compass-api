
package com.anastasko.lnucompass.api.model.view;

import com.anastasko.lnucompass.api.model.domain.EntityMapItem;
import com.anastasko.lnucompass.model.view.AbstractContentEntityViewModel;
import com.anastasko.lnucompass.model.view.AbstractEntityViewModel;

public class EntityMapItemViewModel
    extends AbstractContentEntityViewModel
{

    private AbstractEntityViewModel owner;
    private String name;
    private Double square;
    private String room;
    private AbstractEntityViewModel kind;
    private AbstractEntityViewModel faculty;

    public EntityMapItemViewModel() {
    }

    public EntityMapItemViewModel(EntityMapItem item) {
        super(item);
        setOwner(new AbstractEntityViewModel(item.getOwner()));
        setName(item.getName());
        setSquare(item.getSquare());
        setRoom(item.getRoom());
        setKind(new AbstractEntityViewModel(item.getKind()));
        setFaculty(new AbstractEntityViewModel(item.getFaculty()));
    }

    public AbstractEntityViewModel getOwner() {
        return owner;
    }

    public void setOwner(AbstractEntityViewModel owner) {
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

    public AbstractEntityViewModel getKind() {
        return kind;
    }

    public void setKind(AbstractEntityViewModel kind) {
        this.kind = kind;
    }

    public AbstractEntityViewModel getFaculty() {
        return faculty;
    }

    public void setFaculty(AbstractEntityViewModel faculty) {
        this.faculty = faculty;
    }

}
