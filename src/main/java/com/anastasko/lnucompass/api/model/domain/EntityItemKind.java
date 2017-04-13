
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
public class EntityItemKind
    extends AbstractContentEntity
{

    @Basic
    @Column(nullable = true)
    private String name;
    @ManyToOne
    private EntityIosIcon iosIcon;
    @ManyToOne
    private EntityIosIcon iosSelectedIcon;
    @ManyToOne
    private EntityAndroidIcon androidIcon;
    @ManyToOne
    private EntityAndroidIcon androidSelectedIcon;

    public EntityItemKind() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EntityIosIcon getIosIcon() {
        return iosIcon;
    }

    public void setIosIcon(EntityIosIcon iosIcon) {
        this.iosIcon = iosIcon;
    }

    public EntityIosIcon getIosSelectedIcon() {
        return iosSelectedIcon;
    }

    public void setIosSelectedIcon(EntityIosIcon iosSelectedIcon) {
        this.iosSelectedIcon = iosSelectedIcon;
    }

    public EntityAndroidIcon getAndroidIcon() {
        return androidIcon;
    }

    public void setAndroidIcon(EntityAndroidIcon androidIcon) {
        this.androidIcon = androidIcon;
    }

    public EntityAndroidIcon getAndroidSelectedIcon() {
        return androidSelectedIcon;
    }

    public void setAndroidSelectedIcon(EntityAndroidIcon androidSelectedIcon) {
        this.androidSelectedIcon = androidSelectedIcon;
    }

    @Override
    public Long getVersion() {
        Long version = getItem().getModified().getTime();
        return version;
    }

}
