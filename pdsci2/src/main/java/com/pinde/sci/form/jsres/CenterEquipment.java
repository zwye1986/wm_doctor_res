package com.pinde.sci.form.jsres;

import java.io.Serializable;

public class CenterEquipment implements Serializable {
    /**
     * 基地信息-教学条件-临床技能模拟训练中心设备表单
     */
    private static final long serialVersionUID = -3524388431912961659L;

    private String equipmentName;

    private String equipmentModel;

    private String equipmentNumber;

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentModel() {
        return equipmentModel;
    }

    public void setEquipmentModel(String equipmentModel) {
        this.equipmentModel = equipmentModel;
    }

    public String getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(String equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }
}
