package com.med.disease.tracking.app.mapper;

import java.util.Map;

public abstract class Mapper {
	protected abstract Object mapToObject(Object objectToMap, Map<String, String> extraField) throws Exception;

	protected abstract Object mapToResponse(Object objectToMap, Map<String, String> extraField) throws Exception;

	public final Object map(Object objectToMap, MappingTypeEnum mapType, Map<String, String> extraField)
			throws Exception {
		return MappingTypeEnum.MAPTODOMAIN.equals(mapType) 
				? mapToObject(objectToMap, extraField)
				: mapToResponse(objectToMap, extraField);
	}
}
