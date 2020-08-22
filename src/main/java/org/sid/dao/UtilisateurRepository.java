package org.sid.dao;

import java.util.List;

import org.sid.beans.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long>{

	public Utilisateur findByUsername(String username);
	public Utilisateur findByEmail(String email);
	public Utilisateur findByResetToken(String resetToken);

	@Query("SELECT u from Utilisateur u WHERE u.username like :username")
	public Utilisateur loadCurrentUser(@Param("username") String username);


	@Query("SELECT u from Utilisateur u WHERE u.username like :nom or u.nomUtilisateur like :nom or u.prenomUtilisateur like :nom")
	public List<Utilisateur> chercherUtilisateur(@Param("nom")String mc);

	@Modifying
	@Query("UPDATE Utilisateur u SET u.resetToken = :token WHERE u.email like :email")
	void updateresetToken(@Param("email")String email,@Param("token") String resetToekn);

	@Modifying
	@Query("UPDATE Utilisateur u SET u.password = :password WHERE u.resetToken like :token")
	void updatePassword(@Param("token")String token,@Param("password") String password);

}
