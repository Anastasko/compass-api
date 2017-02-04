package com.anastasko.lnucompass.model.view;

import com.anastasko.lnucompass.model.domain.AbstractContentEntity;
import org.jsondoc.core.annotation.ApiObject;

import java.util.Date;

@ApiObject(name="AbstractEntity", show=false)
public class ModifiedEntityViewModel extends AbstractEntityViewModel {

    private Long modified;

    public ModifiedEntityViewModel() { }

    public ModifiedEntityViewModel(AbstractContentEntity entity){
        super(entity);
        Date date = entity.getProperties().getModified();
        if (date != null) {
            setModified(date.getTime());
        }
    }

    public Long getModified() {
        return modified;
    }

    public void setModified(Long modified) {
        this.modified = modified;
    }
}
