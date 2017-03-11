
package com.anastasko.lnucompass.api.model.view;

import com.anastasko.lnucompass.api.model.domain.EntityItemKind;
import com.anastasko.lnucompass.model.view.AbstractContentEntityViewModel;
import com.anastasko.lnucompass.model.view.AbstractEntityViewModel;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name = "Item Kind")
public class EntityItemKindViewModel
    extends AbstractContentEntityViewModel
{

    @ApiObjectField
    private String name;
    @ApiObjectField
    private AbstractEntityViewModel iosIcon;
    @ApiObjectField
    private AbstractEntityViewModel iosSelectedIcon;
    @ApiObjectField
    private AbstractEntityViewModel androidIcon;
    @ApiObjectField
    private AbstractEntityViewModel androidSelectedIcon;
    @ApiObjectField
    private AbstractEntityViewModel owner;

    public EntityItemKindViewModel() {
    }

    public EntityItemKindViewModel(EntityItemKind item) {
        super(item);
        setName(item.getName());
        setIosIcon(new AbstractEntityViewModel(item.getIosIcon()));
        setIosSelectedIcon(new AbstractEntityViewModel(item.getIosSelectedIcon()));
        setAndroidIcon(new AbstractEntityViewModel(item.getAndroidIcon()));
        setAndroidSelectedIcon(new AbstractEntityViewModel(item.getAndroidSelectedIcon()));
        setOwner(new AbstractEntityViewModel(item.getOwner()));
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

    public AbstractEntityViewModel getIosSelectedIcon() {
        return iosSelectedIcon;
    }

    public void setIosSelectedIcon(AbstractEntityViewModel iosSelectedIcon) {
        this.iosSelectedIcon = iosSelectedIcon;
    }

    public AbstractEntityViewModel getAndroidIcon() {
        return androidIcon;
    }

    public void setAndroidIcon(AbstractEntityViewModel androidIcon) {
        this.androidIcon = androidIcon;
    }

    public AbstractEntityViewModel getAndroidSelectedIcon() {
        return androidSelectedIcon;
    }

    public void setAndroidSelectedIcon(AbstractEntityViewModel androidSelectedIcon) {
        this.androidSelectedIcon = androidSelectedIcon;
    }

    public AbstractEntityViewModel getOwner() {
        return owner;
    }

    public void setOwner(AbstractEntityViewModel owner) {
        this.owner = owner;
    }

}
