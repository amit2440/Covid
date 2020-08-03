package com.med.disease.tracking.app.dto;

import java.util.List;

public class ReportResponseDTO {

	public Integer count;
	
	public EPassDTO epassDTO;
	
	public List<EPassDTO> epassDTOList;
	
	
	public List<EPassDTO> getEpassDTOList() {
		return epassDTOList;
	}
	public void setEpassDTOList(List<EPassDTO> epassDTOList) {
		this.epassDTOList = epassDTOList;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public EPassDTO getEpassDTO() {
		return epassDTO;
	}
	public void setEpassDTO(EPassDTO epassDTO) {
		this.epassDTO = epassDTO;
	}
	
	
}
