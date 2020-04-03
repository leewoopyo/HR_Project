package com.spring.human_resources.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import com.spring.human_resources.domain.User;


//해당 클래스는 JpaSpecification 인터페이스를 따르는 헬퍼이다. 필터 조건()
public class User_Specs {
	
	public static Specification<User> search(Map<String, Object> filter) {
		return (root, query, builder) -> {

			List<Predicate> predicates = new ArrayList<>();
			filter.forEach((key, value) -> {
				
				String likevalue = "%" + value + "%";
				
				switch(key) {
				case "username":
					predicates.add(builder.like(root.get(key).as(String.class), likevalue));
					break;
				case "name":
					predicates.add(builder.like(root.get(key).as(String.class), likevalue));
					break;
				case "e_mail":
					predicates.add(builder.like(root.get(key).as(String.class), likevalue));
					break;
				case "tel":
					predicates.add(builder.like(root.get(key).as(String.class), likevalue));
					break;
				case "birth":
					predicates.add(builder.like(root.get(key).as(String.class), likevalue));
					break;
				case "S_type":
					predicates.add(builder.like(root.get(key).as(String.class), likevalue));
					break;
				case "created":
					predicates.add(builder.like(root.get(key).as(String.class), likevalue));
					break;
				}
			});
			return builder.and(predicates.toArray(new Predicate[0]));
		}; 
	}

}
