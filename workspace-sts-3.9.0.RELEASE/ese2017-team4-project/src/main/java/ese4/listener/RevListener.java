package ese4.listener;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import ese4.model.RevisionInfo;

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
