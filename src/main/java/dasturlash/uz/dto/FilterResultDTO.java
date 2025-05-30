package dasturlash.uz.dto;

import dasturlash.uz.entities.ProfileEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Setter
@Getter
public class FilterResultDTO<E> {
    private List<E> content;
    private Long total;

    public FilterResultDTO(List<E> profileList, Long totalCount) {
        this.content = profileList;
        this.total = totalCount;
    }
}
