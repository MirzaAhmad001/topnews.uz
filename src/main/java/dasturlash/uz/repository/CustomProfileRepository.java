package dasturlash.uz.repository;

import dasturlash.uz.dto.FilterResultDTO;
import dasturlash.uz.dto.ProfileFilterDTO;
import dasturlash.uz.entities.ProfileEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomProfileRepository {

    @Autowired
    private EntityManager entityManager;


    public FilterResultDTO<ProfileEntity> filter(ProfileFilterDTO filterDTO, int page, int size) {
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

        if (filterDTO.getRoles() != null) { // ROLE_ADMIN
            conditionBuilder.append(" and pr.roles =:role ");
            params.put("role", filterDTO.getRoles());
        }

        if (filterDTO.getCreatedDateFrom() != null && filterDTO.getCreatedDateTo() != null) { // 01.01.2021 - 01.01.2024
            conditionBuilder.append(" and p.createdDate between :fromDate  and :toDate ");
            params.put("fromDate", filterDTO.getCreatedDateFrom());
            params.put("toDate", filterDTO.getCreatedDateTo());
        } else if (filterDTO.getCreatedDateFrom() != null) { // 01.01.2021
            conditionBuilder.append(" and p.createdDate >= :fromDate");
            params.put("fromDate", filterDTO.getCreatedDateFrom());
        } else if (filterDTO.getCreatedDateTo() != null) { // 01.01.2024
            conditionBuilder.append(" and p.createdDate <= :toDate ");
            params.put("toDate", filterDTO.getCreatedDateTo());
        }

        StringBuilder selectBuilder = new StringBuilder("Select p From ProfileEntity p ");
        StringBuilder countBuilder = new StringBuilder("Select count(*) From ProfileEntity p ");
        if (filterDTO.getRoles() != null) { // ROLE_ADMIN
            selectBuilder.append(" inner join p.roleList as pr ");
            countBuilder.append(" inner join p.roleList as pr ");
        }
        //
        selectBuilder.append(conditionBuilder);
        countBuilder.append(conditionBuilder);
        //
        Query selectQuery = entityManager.createQuery(selectBuilder.toString());
        Query countQuery = entityManager.createQuery(countBuilder.toString());

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            selectQuery.setParameter(entry.getKey(), entry.getValue());
            countQuery.setParameter(entry.getKey(), entry.getValue());
        }

        selectQuery.setMaxResults(size); // limit
        selectQuery.setFirstResult(page * size); // offset

        List<ProfileEntity> profileList = selectQuery.getResultList();
        Long totalCount = (Long) countQuery.getSingleResult();

//        return new PageImpl<>(profileList, PageRequest.of(page, size), totalCount);
        return new FilterResultDTO<>(profileList, totalCount);
    }
}
