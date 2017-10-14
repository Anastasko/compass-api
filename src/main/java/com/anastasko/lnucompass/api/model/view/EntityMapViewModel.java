
package com.anastasko.lnucompass.api.model.view;

import com.anastasko.lnucompass.api.model.domain.EntityMap;
import com.anastasko.lnucompass.model.view.AbstractEntityViewModel;
import com.anastasko.lnucompass.model.view.UrlResourceViewModel;

public class EntityMapViewModel
    extends AbstractEntityViewModel
{

    private UrlResourceViewModel image;
    private Long floor;
    private AbstractEntityViewModel owner;

    public EntityMapViewModel() {
        setImage(new UrlResourceViewModel());
    }

    public EntityMapViewModel(EntityMap item) {
        super(item);
        setImage(new UrlResourceViewModel(item.getImage()));
        setFloor(item.getFloor());
        setOwner(new AbstractEntityViewModel(item.getOwner()));
    }

    public UrlResourceViewModel getImage() {
        return image;
    }

    public void setImage(UrlResourceViewModel image) {
        this.image = image;
    }

    public Long getFloor() {
        return floor;
    }

    public void setFloor(Long floor) {
        this.floor = floor;
    }

    public AbstractEntityViewModel getOwner() {
        return owner;
    }

    public void setOwner(AbstractEntityViewModel owner) {
        this.owner = owner;
    }

}
