
package com.anastasko.lnucompass.api.model.view;

import com.anastasko.lnucompass.api.model.domain.EntityMapItem;
import com.anastasko.lnucompass.model.view.AbstractEntityViewModel;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name = "Map Item")
public class EntityMapItemViewModel
    extends AbstractEntityViewModel
{

    @ApiObjectField
    private AbstractEntityViewModel owner;
    @ApiObjectField
    private String name;
    @ApiObjectField
    private Double x;
    @ApiObjectField
    private Double y;
    @ApiObjectField
    private Double path;
    @ApiObjectField
    private AbstractEntityViewModel kind;

    public EntityMapItemViewModel() {
    }

    public EntityMapItemViewModel(EntityMapItem item) {
        super(item);
        setOwner(new AbstractEntityViewModel(item.getOwner()));
        setName(item.getName());
        setX(item.getX());
        setY(item.getY());
        setPath(item.getPath());
        setKind(new AbstractEntityViewModel(item.getKind()));
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

    public AbstractEntityViewModel getKind() {
        return kind;
    }

    public void setKind(AbstractEntityViewModel kind) {
        this.kind = kind;
    }

}
