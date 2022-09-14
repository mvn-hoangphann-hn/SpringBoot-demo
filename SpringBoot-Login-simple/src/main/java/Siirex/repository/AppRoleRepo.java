package Siirex.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import Siirex.entity.AppRole;
import Siirex.entity.UserRole;

@Repository
@Transactional
public class AppRoleRepo {

	@Autowired
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<String> getRoleNameWithUserIdOnDatabase(Long userId) {
		
		String hql = "SELECT e.appRole.roleName FROM " + UserRole.class.getName() + " AS e " 
		+ "WHERE e.appUser.userId = :id";
		
		Query query = this.entityManager.createQuery(hql, String.class);
		query.setParameter("id", userId);
		
		return query.getResultList();
	}
	
	public AppRole getRoleObjectByRoleName(String roleName) {
		
		try {
			String hql = "SELECT e FROM " + AppRole.class.getName() + " AS e " 
			+ "WHERE e.roleName = :name";
			
			Query query = this.entityManager.createQuery(hql, AppRole.class);
			query.setParameter("name", roleName);
			
			return (AppRole) query.getSingleResult();
			
		} catch (Exception e) {
			return null;
		}
	}
}
