package ese4.listener;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import ese4.model.RevisionInfo;

/**
 * 
 * @author ESE04
 * Gets the user name of the currenty logged in user and saves it in a revision entity.
 * 
 */
public class RevListener implements RevisionListener{

	@Override
	public void newRevision(Object revisionEntity) {
	    RevisionInfo rev = (RevisionInfo) revisionEntity;
	    rev.setUserName(getUserName());
	}

	private String getUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}
}
