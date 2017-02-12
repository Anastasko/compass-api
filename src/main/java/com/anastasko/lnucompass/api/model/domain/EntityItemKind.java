
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
    private EntityAndroidIcon androidIcon;

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

    public EntityAndroidIcon getAndroidIcon() {
        return androidIcon;
    }

    public void setAndroidIcon(EntityAndroidIcon androidIcon) {
        this.androidIcon = androidIcon;
    }

}
