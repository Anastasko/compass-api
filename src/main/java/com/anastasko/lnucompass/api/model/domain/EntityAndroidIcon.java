
package com.anastasko.lnucompass.api.model.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToOne;
import com.anastasko.lnucompass.model.domain.AbstractContentEntity;
import com.anastasko.lnucompass.model.domain.UrlResource;

@Entity
@NamedEntityGraphs({

})
public class EntityAndroidIcon
    extends AbstractContentEntity
{

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private UrlResource xxxhdpi;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private UrlResource xxhdpi;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private UrlResource xhdpi;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private UrlResource mdpi;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private UrlResource hdpi;

    public EntityAndroidIcon() {
        setXxxhdpi(new UrlResource());
        setXxhdpi(new UrlResource());
        setXhdpi(new UrlResource());
        setMdpi(new UrlResource());
        setHdpi(new UrlResource());
    }

    public UrlResource getXxxhdpi() {
        return xxxhdpi;
    }

    public void setXxxhdpi(UrlResource xxxhdpi) {
        this.xxxhdpi = xxxhdpi;
    }

    public UrlResource getXxhdpi() {
        return xxhdpi;
    }

    public void setXxhdpi(UrlResource xxhdpi) {
        this.xxhdpi = xxhdpi;
    }

    public UrlResource getXhdpi() {
        return xhdpi;
    }

    public void setXhdpi(UrlResource xhdpi) {
        this.xhdpi = xhdpi;
    }

    public UrlResource getMdpi() {
        return mdpi;
    }

    public void setMdpi(UrlResource mdpi) {
        this.mdpi = mdpi;
    }

    public UrlResource getHdpi() {
        return hdpi;
    }

    public void setHdpi(UrlResource hdpi) {
        this.hdpi = hdpi;
    }

}
