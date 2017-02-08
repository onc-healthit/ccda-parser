package org.sitenv.ccdaparsing.model;

public class CCDAID extends CCDAXmlSnippet {
	
	private String parentElementName;
	private String root;
	private String extension;

	public String getParentElementName() {
		return parentElementName;
	}

	public void setParentElementName(String parentElementName) {
		this.parentElementName = parentElementName;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}
	
	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((extension == null) ? 0 : extension.hashCode());
		result = prime
				* result
				+ ((parentElementName == null) ? 0 : parentElementName
						.hashCode());
		result = prime * result + ((root == null) ? 0 : root.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CCDAID other = (CCDAID) obj;
		if (extension == null) {
			if (other.extension != null)
				return false;
		} else if (!extension.equals(other.extension))
			return false;
		if (parentElementName == null) {
			if (other.parentElementName != null)
				return false;
		} else if (!parentElementName.equals(other.parentElementName))
			return false;
		if (root == null) {
			if (other.root != null)
				return false;
		} else if (!root.equals(other.root))
			return false;
		return true;
	}
}
