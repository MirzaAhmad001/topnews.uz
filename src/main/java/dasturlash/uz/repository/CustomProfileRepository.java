package dasturlash.uz.repository;

import dasturlash.uz.dto.ProfileFilterDTO;
import dasturlash.uz.entities.ProfileEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomProfileRepository {

    @Autowired
    private EntityManager entityManager;


    public PageImpl<ProfileEntity> filter(ProfileFilterDTO filterDTO, int page, int size) {
        //    name,surname,phone,role,created_date_from,created_date_to

        // ROLE !!!
        StringBuilder conditionBuilder = new StringBuilder(" where 1=1 ");
        Map<String, Object> params = new HashMap<>();


        if (filterDTO.getName() != null) {
            conditionBuilder.append(" and name like :name ");
            params.put("name", "%" + filterDTO.getName() + "%");
        }

        if (filterDTO.getSurname() != null) {
            conditionBuilder.append(" and surname =: surname ");
            params.put("surname", filterDTO.getSurname());
        }

        if (filterDTO.getUserName() != null) {
            conditionBuilder.append(" and username like :username");
            params.put("username", "%" + filterDTO.getUserName() + "%");
        }

        if (filterDTO.getCreatedDateFrom() != null & filterDTO.getCreatedDateTo() != null) {
            conditionBuilder.append(" and createdDate > :createdDateFrom and createdDateTo < :createdDateTo ");
            params.put("createdDateFrom", "%" + filterDTO.getCreatedDateFrom() + "%");
            params.put("createdDateTo", "%" + filterDTO.getCreatedDateTo() + "%");
        }

        if (filterDTO.getRoles().size() > 0) {

        }



        //..
        StringBuilder selectBuilder = new StringBuilder("From ProfileEntity ");
        selectBuilder.append(conditionBuilder);
        selectBuilder.append(" order by createdDate desc ");
        // "From ProfileEntity   where 1=1 and id=:id

        StringBuilder countBuilder = new StringBuilder(" select count(*) From ProfileEntity ");
        countBuilder.append(conditionBuilder);
        // select count(*) From ProfileEntity where 1=1 and id=:id

        // get content
        Query selectQuery = entityManager.createQuery(selectBuilder.toString());
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            selectQuery.setParameter(entry.getKey(), entry.getValue());
        }
        selectQuery.setMaxResults(size); // limit
        selectQuery.setFirstResult(page * size); // offset
        List<ProfileEntity> entityList = selectQuery.getResultList(); // get content


        // get totalCount
        Query countQuery = entityManager.createQuery(countBuilder.toString());
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            countQuery.setParameter(entry.getKey(), entry.getValue());
        }

        Long totalCount = (Long) countQuery.getSingleResult();

        return new PageImpl<>(entityList, PageRequest.of(page, size), totalCount);
    }
}
