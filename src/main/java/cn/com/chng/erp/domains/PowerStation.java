package cn.com.chng.erp.domains;

import cn.com.chng.erp.basic.BasicDomain;

import java.math.BigDecimal;

public class PowerStation extends BasicDomain {
    private String code;
    private String name;
    private String address;
    private Integer parentId;
    private boolean canInputData;
    private boolean isPhotovoltaic;
    private boolean isWindPower;
    private BigDecimal photovoltaicInstalledCapacity;
    private BigDecimal windPowerInstalledCapacity;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public boolean isCanInputData() {
        return canInputData;
    }

    public void setCanInputData(boolean canInputData) {
        this.canInputData = canInputData;
    }

    public boolean isPhotovoltaic() {
        return isPhotovoltaic;
    }

    public void setPhotovoltaic(boolean photovoltaic) {
        isPhotovoltaic = photovoltaic;
    }

    public boolean isWindPower() {
        return isWindPower;
    }

    public void setWindPower(boolean windPower) {
        isWindPower = windPower;
    }

    public BigDecimal getPhotovoltaicInstalledCapacity() {
        return photovoltaicInstalledCapacity;
    }

    public void setPhotovoltaicInstalledCapacity(BigDecimal photovoltaicInstalledCapacity) {
        this.photovoltaicInstalledCapacity = photovoltaicInstalledCapacity;
    }

    public BigDecimal getWindPowerInstalledCapacity() {
        return windPowerInstalledCapacity;
    }

    public void setWindPowerInstalledCapacity(BigDecimal windPowerInstalledCapacity) {
        this.windPowerInstalledCapacity = windPowerInstalledCapacity;
    }
}
