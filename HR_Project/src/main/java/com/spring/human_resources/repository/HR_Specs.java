package com.spring.human_resources.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import com.spring.human_resources.domain.HR;


//해당 클래스는 JpaSpecification 인터페이스를 따르는 헬퍼이다. 필터 조건()
public class HR_Specs {
	
	public static Specification<HR> search(Map<String, Object> filter) {
		return (root, query, builder) -> {

			List<Predicate> predicates = new ArrayList<>();
			filter.forEach((key, value) -> {
				switch(key) {
				case "id":
					predicates.add(builder.like(root.get(key).as(String.class), (String)value));
					break;
				case "name":
					predicates.add(builder.like(root.get(key).as(String.class), (String)value));
					break;
				case "e_mail":
					predicates.add(builder.like(root.get(key).as(String.class), (String)value));
					break;
				case "tel":
					predicates.add(builder.like(root.get(key).as(String.class), (String)value));
					break;
				case "birth":
					predicates.add(builder.like(root.get(key).as(String.class), (String)value));
					break;
				case "S_type":
					predicates.add(builder.like(root.get(key).as(String.class), (String)value));
					break;
				case "created":
					predicates.add(builder.like(root.get(key).as(String.class), (String)value));
					break;
				}
			});
			return builder.and(predicates.toArray(new Predicate[0]));
		}; 
	}

}
