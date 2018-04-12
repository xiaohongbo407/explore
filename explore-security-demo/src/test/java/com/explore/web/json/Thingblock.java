package com.explore.web.json;

import java.util.List;

/**
 * @program: explore
 * @description: 事物链
 * @author: XiaoHongBo
 * @create: 2018-03-28 17:34
 **/
public class Thingblock {

    private String blockId;
    private String blockName;

    private List<ThingblockDetail> details;

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public List<ThingblockDetail> getDetails() {
        return details;
    }

    public void setDetails(List<ThingblockDetail> details) {
        this.details = details;
    }
}
