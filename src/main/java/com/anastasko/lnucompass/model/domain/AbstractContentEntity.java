package com.anastasko.lnucompass.model.domain;

import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractContentEntity extends AbstractEntity {

	@Basic(fetch=FetchType.LAZY)
	private Long timestamp;     // last change
	
	@ManyToOne(fetch=FetchType.LAZY)
	private UserAccount author;

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public UserAccount getAuthor() {
		return author;
	}

	public void setAuthor(UserAccount author) {
		this.author = author;
	}
	
}
