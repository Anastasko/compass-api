package com.anastasko.lnucompass.model.domain;

import javax.persistence.*;

@MappedSuperclass
public class AbstractContentEntity extends AbstractEntity {

    @MapsId
    @JoinColumn(name="id")
    @OneToOne(cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
    private Item item = new Item();

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

   
}
