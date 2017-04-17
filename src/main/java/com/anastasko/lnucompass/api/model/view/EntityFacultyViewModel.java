
package com.anastasko.lnucompass.api.model.view;

import com.anastasko.lnucompass.api.model.domain.EntityFaculty;
import com.anastasko.lnucompass.model.view.AbstractContentEntityViewModel;
import com.anastasko.lnucompass.model.view.AbstractEntityViewModel;
import com.anastasko.lnucompass.model.view.UrlResourceViewModel;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name = "Faculty")
public class EntityFacultyViewModel
    extends AbstractContentEntityViewModel
{

    @ApiObjectField
    private String name;
    @ApiObjectField
    private String phone;
    @ApiObjectField
    private String email;
    @ApiObjectField
    private String website;
    @ApiObjectField
    private UrlResourceViewModel icon;
    @ApiObjectField
    private AbstractEntityViewModel owner;

    public EntityFacultyViewModel() {
        setIcon(new UrlResourceViewModel());
    }

    public EntityFacultyViewModel(EntityFaculty item) {
        super(item);
        setName(item.getName());
        setPhone(item.getPhone());
        setEmail(item.getEmail());
        setWebsite(item.getWebsite());
        setIcon(new UrlResourceViewModel(item.getIcon()));
        setOwner(new AbstractEntityViewModel(item.getOwner()));
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

    public UrlResourceViewModel getIcon() {
        return icon;
    }

    public void setIcon(UrlResourceViewModel icon) {
        this.icon = icon;
    }

    public AbstractEntityViewModel getOwner() {
        return owner;
    }

    public void setOwner(AbstractEntityViewModel owner) {
        this.owner = owner;
    }

}
