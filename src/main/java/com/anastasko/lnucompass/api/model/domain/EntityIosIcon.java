
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
public class EntityIosIcon
    extends AbstractContentEntity
{

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private UrlResource size2x;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private UrlResource size3x;

    public EntityIosIcon() {
        setSize2x(new UrlResource());
        setSize3x(new UrlResource());
    }

    public UrlResource getSize2x() {
        return size2x;
    }

    public void setSize2x(UrlResource size2x) {
        this.size2x = size2x;
    }

    public UrlResource getSize3x() {
        return size3x;
    }

    public void setSize3x(UrlResource size3x) {
        this.size3x = size3x;
    }

}
