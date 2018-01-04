package cn.com.chng.erp.services;

import cn.com.chng.erp.domains.PowerStation;
import cn.com.chng.erp.mappers.PowerStationMapper;
import cn.com.chng.erp.models.powerstation.ListModel;
import cn.com.chng.erp.utils.PagedSearchModel;
import cn.com.chng.erp.utils.SearchModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PowerStationService {
    @Autowired
    private PowerStationMapper powerStationMapper;

    @Transactional(readOnly = true)
    public Map<String, Object> list(ListModel listModel) {
        SearchModel searchModel = new SearchModel(true);
        long total = powerStationMapper.count(searchModel);
        List<PowerStation> powerStations = new ArrayList<PowerStation>();
        if (total > 0) {
            PagedSearchModel pagedSearchModel = new PagedSearchModel(true);
            pagedSearchModel.setPage(listModel.getPage());
            pagedSearchModel.setRows(listModel.getRows());
            powerStations = powerStationMapper.findAllPaged(pagedSearchModel);
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("total", total);
        data.put("rows", powerStations);
        return data;
    }
}
