
package com.anastasko.lnucompass.api.model.domain;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToOne;
import com.anastasko.lnucompass.model.domain.AbstractContentEntity;
import com.anastasko.lnucompass.model.domain.UrlResource;

@Entity
@NamedEntityGraphs({

})
public class EntityFaculty
    extends AbstractContentEntity
{

    @Basic
    @Column(nullable = true)
    private String name;
    @Basic
    @Column(nullable = true)
    private String phone;
    @Basic
    @Column(nullable = true)
    private String email;
    @Basic
    @Column(nullable = true)
    private String website;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private UrlResource icon;
    @ManyToOne
    private EntityCityItem owner;

    public EntityFaculty() {
        setIcon(new UrlResource());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public UrlResource getIcon() {
        return icon;
    }

    public void setIcon(UrlResource icon) {
        this.icon = icon;
    }

    public EntityCityItem getOwner() {
        return owner;
    }

    public void setOwner(EntityCityItem owner) {
        this.owner = owner;
    }

    @Override
    public Long getVersion() {
        Long version = getItem().getModified().getTime();
        version = Math.max(version, this.getIcon().getVersion());
        return version;
    }

}
