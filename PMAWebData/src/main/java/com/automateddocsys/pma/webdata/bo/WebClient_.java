package com.automateddocsys.pma.webdata.bo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-02-01T16:10:53.571-0500")
@StaticMetamodel(WebClient.class)
public class WebClient_ {
	public static volatile SingularAttribute<WebClient, Long> userId;
	public static volatile SingularAttribute<WebClient, String> username;
	public static volatile SingularAttribute<WebClient, String> password;
	public static volatile SingularAttribute<WebClient, Boolean> enabled;
	public static volatile SingularAttribute<WebClient, String> clientNumber;
	public static volatile SingularAttribute<WebClient, String> firstName;
	public static volatile SingularAttribute<WebClient, String> lastName;
	public static volatile SetAttribute<WebClient, ClientRole> authorities;
	public static volatile SetAttribute<WebClient, ClientAccount> accounts;
	public static volatile SetAttribute<WebClient, ClientAnswer> answers;
	public static volatile SingularAttribute<WebClient, Boolean> accountNonExpired;
	public static volatile SingularAttribute<WebClient, Boolean> accountNonLocked;
	public static volatile SingularAttribute<WebClient, Boolean> credentialsNonExpired;
	public static volatile SingularAttribute<WebClient, String> masterClientName;
	public static volatile SingularAttribute<WebClient, String> masterClientNumber;
}
