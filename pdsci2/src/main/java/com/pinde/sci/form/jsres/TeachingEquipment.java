package com.pinde.sci.form.jsres;

import java.io.Serializable;

public class TeachingEquipment implements Serializable {
    /**
     * 基地信息-教学条件-教学设备表单
     */
    private static final long serialVersionUID = -3524388431912961659L;

    private String equipmentName;

    private String equipmentNumber;

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(String equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }
}
