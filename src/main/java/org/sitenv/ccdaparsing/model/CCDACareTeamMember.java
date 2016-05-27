package org.sitenv.ccdaparsing.model;

import java.util.ArrayList;

public class CCDACareTeamMember extends CCDAXmlSnippet {

	private ArrayList<CCDAParticipant> members;
	
	public CCDACareTeamMember(){}

	public ArrayList<CCDAParticipant> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<CCDAParticipant> members) {
		this.members = members;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((members == null) ? 0 : members.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CCDACareTeamMember other = (CCDACareTeamMember) obj;
		if (members == null) {
			if (other.members != null)
				return false;
		} else if (!members.equals(other.members))
			return false;
		return true;
	}
}
