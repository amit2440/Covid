package com.med.disease.tracking.app.mapper;

public enum MappingTypeEnum {
	MAPTODOMAIN("mapToDomain"), MAPTORESPONSE("mapToResponse");

	String mapType;

	MappingTypeEnum(String mapType) {
		this.mapType = mapType;
	}
}
