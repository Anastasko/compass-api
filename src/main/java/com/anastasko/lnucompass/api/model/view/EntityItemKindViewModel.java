
package com.anastasko.lnucompass.api.model.view;

import com.anastasko.lnucompass.api.model.domain.EntityItemKind;
import com.anastasko.lnucompass.model.view.AbstractEntityViewModel;

public class EntityItemKindViewModel
    extends AbstractEntityViewModel
{

    private String name;
    private AbstractEntityViewModel iosIcon;
    private AbstractEntityViewModel iosSelectedIcon;
    private AbstractEntityViewModel androidIcon;
    private AbstractEntityViewModel androidSelectedIcon;

    public EntityItemKindViewModel() {
    }

    public EntityItemKindViewModel(EntityItemKind item) {
        super(item);
        setName(item.getName());
        setIosIcon(new AbstractEntityViewModel(item.getIosIcon()));
        setIosSelectedIcon(new AbstractEntityViewModel(item.getIosSelectedIcon()));
        setAndroidIcon(new AbstractEntityViewModel(item.getAndroidIcon()));
        setAndroidSelectedIcon(new AbstractEntityViewModel(item.getAndroidSelectedIcon()));
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

}
