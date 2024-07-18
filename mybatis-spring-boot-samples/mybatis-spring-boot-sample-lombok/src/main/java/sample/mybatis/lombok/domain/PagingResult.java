
package sample.mybatis.lombok.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PagingResult<T> {
	private final Integer page;
	private final Integer pageSize;
	private final Long totalRowCount;
	private final List<T> data;
}
