package com.zx.bt.spider.controller;

import com.zx.bt.spider.store.InfoHashFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author:ZhengXing
 * datetime:2018-03-24 18:53
 * 过滤控制器
 * 主节点使用，用于给各子节点提供 infoHash过滤验证
 */
@RestController
@RequestMapping("/filter")
@ConditionalOnProperty(prefix = "zx-bt.main",name = "master",havingValue = "true")
public class FilterController {
    private final InfoHashFilter masterInfoHashFilter;

    public FilterController(InfoHashFilter masterInfoHashFilter) {
        this.masterInfoHashFilter = masterInfoHashFilter;
    }

    /**
     * put，调用该节点的 {@link InfoHashFilter}类
     */
    @GetMapping(InfoHashFilter.PUT_METHOD + "{infoHash}")
    public boolean put(@PathVariable String infoHash) {
        return masterInfoHashFilter.put(infoHash);
    }

}
