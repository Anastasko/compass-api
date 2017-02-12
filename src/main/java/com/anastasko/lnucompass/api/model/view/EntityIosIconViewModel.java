
package com.anastasko.lnucompass.api.model.view;

import com.anastasko.lnucompass.api.model.domain.EntityIosIcon;
import com.anastasko.lnucompass.model.view.AbstractEntityViewModel;
import com.anastasko.lnucompass.model.view.UrlResourceViewModel;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name = "Ios Icon")
public class EntityIosIconViewModel
    extends AbstractEntityViewModel
{

    @ApiObjectField
    private UrlResourceViewModel size2x;
    @ApiObjectField
    private UrlResourceViewModel size3x;

    public EntityIosIconViewModel() {
        setSize2x(new UrlResourceViewModel());
        setSize3x(new UrlResourceViewModel());
    }

    public EntityIosIconViewModel(EntityIosIcon item) {
        super(item);
        setSize2x(new UrlResourceViewModel(item.getSize2x()));
        setSize3x(new UrlResourceViewModel(item.getSize3x()));
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

}
