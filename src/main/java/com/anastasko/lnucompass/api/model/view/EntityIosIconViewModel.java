
package com.anastasko.lnucompass.api.model.view;

import com.anastasko.lnucompass.api.model.domain.EntityIosIcon;
import com.anastasko.lnucompass.model.view.AbstractContentEntityViewModel;
import com.anastasko.lnucompass.model.view.AbstractEntityViewModel;
import com.anastasko.lnucompass.model.view.UrlResourceViewModel;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name = "Ios Icon")
public class EntityIosIconViewModel
    extends AbstractContentEntityViewModel
{

    @ApiObjectField
    private UrlResourceViewModel size2x;
    @ApiObjectField
    private UrlResourceViewModel size3x;
    @ApiObjectField
    private AbstractEntityViewModel owner;

    public EntityIosIconViewModel() {
        setSize2x(new UrlResourceViewModel());
        setSize3x(new UrlResourceViewModel());
    }

    public EntityIosIconViewModel(EntityIosIcon item) {
        super(item);
        setSize2x(new UrlResourceViewModel(item.getSize2x()));
        setSize3x(new UrlResourceViewModel(item.getSize3x()));
        setOwner(new AbstractEntityViewModel(item.getOwner()));
    }

    public UrlResourceViewModel getSize2x() {
        return size2x;
    }

    public void setSize2x(UrlResourceViewModel size2x) {
        this.size2x = size2x;
    }

    public UrlResourceViewModel getSize3x() {
        return size3x;
    }

    public void setSize3x(UrlResourceViewModel size3x) {
        this.size3x = size3x;
    }

    public AbstractEntityViewModel getOwner() {
        return owner;
    }

    public void setOwner(AbstractEntityViewModel owner) {
        this.owner = owner;
    }

}
