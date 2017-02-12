
package com.anastasko.lnucompass.api.model.view;

import com.anastasko.lnucompass.api.model.domain.EntityAndroidIcon;
import com.anastasko.lnucompass.model.view.AbstractEntityViewModel;
import com.anastasko.lnucompass.model.view.UrlResourceViewModel;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name = "Android Icon")
public class EntityAndroidIconViewModel
    extends AbstractEntityViewModel
{

    @ApiObjectField
    private UrlResourceViewModel xxxhdpi;
    @ApiObjectField
    private UrlResourceViewModel xxhdpi;
    @ApiObjectField
    private UrlResourceViewModel xhdpi;
    @ApiObjectField
    private UrlResourceViewModel mdpi;
    @ApiObjectField
    private UrlResourceViewModel hdpi;

    public EntityAndroidIconViewModel() {
        setXxxhdpi(new UrlResourceViewModel());
        setXxhdpi(new UrlResourceViewModel());
        setXhdpi(new UrlResourceViewModel());
        setMdpi(new UrlResourceViewModel());
        setHdpi(new UrlResourceViewModel());
    }

    public EntityAndroidIconViewModel(EntityAndroidIcon item) {
        super(item);
        setXxxhdpi(new UrlResourceViewModel(item.getXxxhdpi()));
        setXxhdpi(new UrlResourceViewModel(item.getXxhdpi()));
        setXhdpi(new UrlResourceViewModel(item.getXhdpi()));
        setMdpi(new UrlResourceViewModel(item.getMdpi()));
        setHdpi(new UrlResourceViewModel(item.getHdpi()));
    }

    public UrlResourceViewModel getXxxhdpi() {
        return xxxhdpi;
    }

    public void setXxxhdpi(UrlResourceViewModel xxxhdpi) {
        this.xxxhdpi = xxxhdpi;
    }

    public UrlResourceViewModel getXxhdpi() {
        return xxhdpi;
    }

    public void setXxhdpi(UrlResourceViewModel xxhdpi) {
        this.xxhdpi = xxhdpi;
    }

    public UrlResourceViewModel getXhdpi() {
        return xhdpi;
    }

    public void setXhdpi(UrlResourceViewModel xhdpi) {
        this.xhdpi = xhdpi;
    }

    public UrlResourceViewModel getMdpi() {
        return mdpi;
    }

    public void setMdpi(UrlResourceViewModel mdpi) {
        this.mdpi = mdpi;
    }

    public UrlResourceViewModel getHdpi() {
        return hdpi;
    }

    public void setHdpi(UrlResourceViewModel hdpi) {
        this.hdpi = hdpi;
    }

}
