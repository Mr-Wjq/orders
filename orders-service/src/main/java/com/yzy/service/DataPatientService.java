package com.yzy.service;

import java.util.List;

import com.yzy.entity.DataPatient;
import com.yzy.utils.LayuiTable;
import com.yzy.utils.Result;

public interface DataPatientService {

	LayuiTable select(int page, int limit, String patientName, String phone);

	Result insert(DataPatient dataPatient);

	Result update(DataPatient dataPatient);

	Result delete(String ids);

	List<DataPatient> selectPatientByCurrUnitId(Long unitId);

}
