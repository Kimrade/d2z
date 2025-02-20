package org.d2z.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CareerCalDTO {
	
	@Builder.Default
	private int fromNo = 0;
	
	@Builder.Default
	private int toNo = 0;
}
