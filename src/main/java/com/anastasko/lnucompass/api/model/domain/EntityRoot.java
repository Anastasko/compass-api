
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
    })
})
public class EntityRoot
    extends AbstractContentEntity
{

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private Set<EntityCityItem> cityItems;

    public EntityRoot() {
        setCityItems(new HashSet<EntityCityItem>());
    }

    public Set<EntityCityItem> getCityItems() {
        return cityItems;
    }

    public void setCityItems(Set<EntityCityItem> cityItems) {
        this.cityItems = cityItems;
    }

    @Override
    public Long getVersion() {
        Long version = getItem().getModified().getTime();
        return version;
    }

}
