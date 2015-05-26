package com.automateddocsys.pma.webdata.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

@Entity
public class WebClient implements UserDetails, CredentialsContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5353360433914504455L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	@Column(length = 50, nullable = false, unique = true)
	@NotBlank
	private String username = "";

	@NotNull
	private String password;

	private boolean enabled = true;

	private String masterClientNumber;

	private String masterClientName;

	@OneToMany(orphanRemoval = true, cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name = "userId", nullable = false)
	private Set<ClientAnswer> answers = new HashSet<ClientAnswer>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "userId", referencedColumnName = "userId", updatable = false, nullable = false, insertable = false)
	Set<ClientRole> authorities = new HashSet<ClientRole>(0);

	// @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	// @JoinColumn(name="username",referencedColumnName="username",updatable=false,nullable=false,insertable=false)
	// Set<ClientAccount> accounts = new HashSet<ClientAccount>(0);

	// private final Set<GrantedAuthority> authorities;
	private boolean accountNonExpired = true;
	private boolean accountNonLocked = true;
	private boolean credentialsNonExpired = true;

	public WebClient() {
		super();
	}

	public WebClient(String pMastAccountNumber, String pMastClientName, String pUserName, String pPassword) {
		this.masterClientName = pMastClientName;
		this.masterClientNumber = pMastAccountNumber;
		this.username = pUserName;
		this.password = pPassword;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getClientNumber() {
		return masterClientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.masterClientNumber = clientNumber;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void addAuthority(String pAuthority) {
		ClientRole role = new ClientRole();
		role.setUserId(userId);
		role.setAuthority(pAuthority);
		// first see if this already exists - if so do not add
		if (!getAuthorities().contains(role)) {
			authorities.add(role);
		}
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	@Override
	public void eraseCredentials() {
		password = null;
	}

	private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
		Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
		// Ensure array iteration order is predictable (as per
		// UserDetails.getAuthorities() contract and SEC-717)
		SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<GrantedAuthority>(new AuthorityComparator());

		for (GrantedAuthority grantedAuthority : authorities) {
			Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
			sortedAuthorities.add(grantedAuthority);
		}

		return sortedAuthorities;
	}

	private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
		private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

		@Override
		public int compare(GrantedAuthority g1, GrantedAuthority g2) {
			// Neither should ever be null as each entry is checked before
			// adding it to the set.
			// If the authority is null, it is a custom authority and should
			// precede others.
			if (g2.getAuthority() == null) {
				return -1;
			}

			if (g1.getAuthority() == null) {
				return 1;
			}

			return g1.getAuthority().compareTo(g2.getAuthority());
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("AuthorityComparator []");
			return builder.toString();
		}
	}

	/**
	 * Returns {@code true} if the supplied object is a {@code User} instance
	 * with the same {@code username} value.
	 * <p>
	 * In other words, the objects are equal if they have the same username,
	 * representing the same principal.
	 */
	@Override
	public boolean equals(Object rhs) {
		if (rhs instanceof User) {
			return username.equals(((User) rhs).getUsername());
		}
		return false;
	}

	/**
	 * Returns the hashcode of the {@code username}.
	 */
	@Override
	public int hashCode() {
		return username.hashCode();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WebClient [userId=");
		builder.append(userId);
		builder.append(", username=");
		builder.append(username);
		builder.append(", masterClientNumber=");
		builder.append(masterClientNumber);
		builder.append(", masterClientName=");
		builder.append(masterClientName);
		builder.append(", answers=");
		builder.append(answers);
		builder.append("]");
		return builder.toString();
	}

	// public String getAccountsAsCSV() {
	// List<String> accts = new ArrayList<String>();
	// for (ClientAccount acct: getAccounts()) {
	// accts.add(acct.getAccountNumber());
	// }
	// return StringUtils.join(accts,",");
	// }
	//
	public void setAccountsAsCSV(String pList) {
		List<String> items = new ArrayList<String>(Arrays.asList(pList.split("\\s*,\\s*")));
		for (String string : items) {
			System.out.println("ACCT: " + string);
		}
	}

	/*
	 * public void addAccount(String acctNmbr) { ClientAccount acct = new
	 * ClientAccount(); acct.setUsername(this.username);
	 * acct.setAccountNumber(acctNmbr); acct.setAccountName("Client Account " +
	 * acctNmbr + " for " + this.username); if (!getAccounts().contains(acct)) {
	 * accounts.add(acct); } }
	 */

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setAuthorities(Set<ClientRole> authorities) {
		this.authorities = authorities;
	}

	public String getMasterClientNumber() {
		return masterClientNumber;
	}

	public void setMasterClientNumber(String masterClientNumber) {
		this.masterClientNumber = masterClientNumber;
	}

	public String getMasterClientName() {
		return masterClientName;
	}

	public void setMasterClientName(String masterClientName) {
		this.masterClientName = masterClientName;
	}

	public void addAnswer(PotentialQuestion pQuestion, String pAnswer) {
		// first see if this question already exists -- if so change the answer
		// else add a new one
		for (ClientAnswer clientAnswer : answers) {
			if (clientAnswer.getQuestion().equals(pQuestion)) {
				clientAnswer.setAnswer(pAnswer);
				return;
			}
		}
		getAnswers().add(new ClientAnswer(pQuestion, pAnswer));
		System.out.println(getAnswers().size());
	}

	public Set<ClientAnswer> getAnswers() {
		if (answers == null) {
			answers = new HashSet<ClientAnswer>();
		}
		return answers;
	}

	public void setAnswers(Set<ClientAnswer> answers) {
		this.answers = answers;
	}

	public String getAnswerFor(PotentialQuestion pQuestion) {
		String result = "";
		for (ClientAnswer clientAnswer : answers) {
			if (clientAnswer.getQuestion().equals(pQuestion)) {
				return clientAnswer.getAnswer();
			}
		}
		return result;
	}

	public String getAnswerFor(long pQuestionId) {
		for (ClientAnswer clientAnswer : answers) {
			if (clientAnswer.getQuestion().getQuestionId().equals(pQuestionId)) {
				return clientAnswer.getAnswer();
			}
		}
		return "";
	}

	public boolean answeredCorrectly(long pQuestionId, String pAnswer) {
		return getAnswerFor(pQuestionId).equalsIgnoreCase(pAnswer);
	}

	public ClientAnswer getClientAnswerFor(long pQuestionId) {
		for (ClientAnswer clientAnswer : answers) {
			if (clientAnswer.getQuestion().getQuestionId().equals(pQuestionId)) {
				return clientAnswer;
			}
		}
		return null;
	}
	
	public void removeQuestion(long pQuestionId) {
		ClientAnswer ca = getClientAnswerFor(pQuestionId);
		getAnswers().remove(ca);
	}

}
