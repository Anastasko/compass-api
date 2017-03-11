
package com.anastasko.lnucompass.api.model.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import com.anastasko.lnucompass.model.domain.AbstractContentEntity;

@Entity
@NamedEntityGraphs({
    @NamedEntityGraph(name = "cityItemsGraph", attributeNodes = {
        @NamedAttributeNode("cityItems")
    }),
    @NamedEntityGraph(name = "itemKindsGraph", attributeNodes = {
        @NamedAttributeNode("itemKinds")
    }),
    @NamedEntityGraph(name = "iosIconsGraph", attributeNodes = {
        @NamedAttributeNode("iosIcons")
    }),
    @NamedEntityGraph(name = "androidIconsGraph", attributeNodes = {
        @NamedAttributeNode("androidIcons")
    })
})
public class EntityRoot
    extends AbstractContentEntity
{

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private Set<EntityCityItem> cityItems;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private Set<EntityItemKind> itemKinds;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private Set<EntityIosIcon> iosIcons;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private Set<EntityAndroidIcon> androidIcons;

    public EntityRoot() {
        setCityItems(new HashSet<EntityCityItem>());
        setItemKinds(new HashSet<EntityItemKind>());
        setIosIcons(new HashSet<EntityIosIcon>());
        setAndroidIcons(new HashSet<EntityAndroidIcon>());
    }

    public Set<EntityCityItem> getCityItems() {
        return cityItems;
    }

    public void setCityItems(Set<EntityCityItem> cityItems) {
        this.cityItems = cityItems;
    }

    public Set<EntityItemKind> getItemKinds() {
        return itemKinds;
    }

    public void setItemKinds(Set<EntityItemKind> itemKinds) {
        this.itemKinds = itemKinds;
    }

    public Set<EntityIosIcon> getIosIcons() {
        return iosIcons;
    }

    public void setIosIcons(Set<EntityIosIcon> iosIcons) {
        this.iosIcons = iosIcons;
    }

    public Set<EntityAndroidIcon> getAndroidIcons() {
        return androidIcons;
    }

    public void setAndroidIcons(Set<EntityAndroidIcon> androidIcons) {
        this.androidIcons = androidIcons;
    }

    @Override
    public Long getVersion() {
        Long version = getItem().getModified().getTime();
        return version;
    }

}
