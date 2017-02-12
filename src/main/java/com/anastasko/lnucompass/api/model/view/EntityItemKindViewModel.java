
package com.anastasko.lnucompass.api.model.view;

import com.anastasko.lnucompass.api.model.domain.EntityItemKind;
import com.anastasko.lnucompass.model.view.AbstractEntityViewModel;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name = "Item Kind")
public class EntityItemKindViewModel
    extends AbstractEntityViewModel
{

    @ApiObjectField
    private String name;
    @ApiObjectField
    private AbstractEntityViewModel iosIcon;
    @ApiObjectField
    private AbstractEntityViewModel androidIcon;

    public EntityItemKindViewModel() {
    }

    public EntityItemKindViewModel(EntityItemKind item) {
        super(item);
        setName(item.getName());
        setIosIcon(new AbstractEntityViewModel(item.getIosIcon()));
        setAndroidIcon(new AbstractEntityViewModel(item.getAndroidIcon()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AbstractEntityViewModel getIosIcon() {
        return iosIcon;
    }

    public void setIosIcon(AbstractEntityViewModel iosIcon) {
        this.iosIcon = iosIcon;
    }

    public AbstractEntityViewModel getAndroidIcon() {
        return androidIcon;
    }

    public void setAndroidIcon(AbstractEntityViewModel androidIcon) {
        this.androidIcon = androidIcon;
    }

}
