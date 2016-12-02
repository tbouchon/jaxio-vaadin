/*
 * Source code generated by Celerio, a Jaxio product.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Follow us on twitter: @jaxiosoft
 * Need commercial support ? Contact us: info@jaxio.com
 * Template pack-vaadin:src/main/java/domain/Entity.java.e.vm
 */
package com.mycompany.myapp.domain;

import static javax.persistence.EnumType.STRING;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.mycompany.myapp.audit.AuditContextHolder;

/**
 * The User is a human that can connect to this web application
 */
@Entity
@Table(name = "`USER`")
public class User implements Identifiable<Integer>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(User.class.getName());

    // Raw attributes
    private Integer id;
    private String login;
    private String password;
    private String email;
    private Boolean isEnabled;
    private Civility civility;
    private String firstName;
    private String lastName;
    private LocalDateTime creationDate;
    private String creationAuthor;
    private LocalDateTime lastModificationDate;
    private String lastModificationAuthor;
    private Integer version;

    // Many to many
    private List<Role> roles = new ArrayList<Role>();

    @Override
    public String entityClassName() {
        return User.class.getSimpleName();
    }

    // -------------------------------
    // Role names support
    // -------------------------------

    /**
     * Returns the granted authorities for this user. You may override
     * this method to provide your own custom authorities.
     */
    @Transient
    public List<String> getRoleNames() {
        List<String> roleNames = new ArrayList<String>();

        for (Role role : getRoles()) {
            roleNames.add(role.getRoleName());
        }

        return roleNames;
    }
    // -- [id] ------------------------

    @Override
    @Column(name = "ID", precision = 10)
    @GeneratedValue
    @Id
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public User id(Integer id) {
        setId(id);
        return this;
    }

    @Override
    @Transient
    public boolean isIdSet() {
        return id != null;
    }
    // -- [login] ------------------------

    /**
     * The login used to login
     */
    @NotEmpty
    @Size(max = 100)
    @Column(name = "LOGIN", nullable = false, unique = true, length = 100)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public User login(String login) {
        setLogin(login);
        return this;
    }
    // -- [password] ------------------------

    @NotEmpty
    @Size(max = 100)
    @Column(name = "`PASSWORD`", nullable = false, length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User password(String password) {
        setPassword(password);
        return this;
    }
    // -- [email] ------------------------

    @Email
    @Size(max = 100)
    @Column(name = "EMAIL", length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User email(String email) {
        setEmail(email);
        return this;
    }
    // -- [isEnabled] ------------------------

    @NotNull
    @Column(name = "IS_ENABLED", nullable = false, length = 1)
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public User isEnabled(Boolean isEnabled) {
        setIsEnabled(isEnabled);
        return this;
    }
    // -- [civility] ------------------------

    @Column(name = "CIVILITY", length = 2)
    @Enumerated(STRING)
    public Civility getCivility() {
        return civility;
    }

    public void setCivility(Civility civility) {
        this.civility = civility;
    }

    public User civility(Civility civility) {
        setCivility(civility);
        return this;
    }
    // -- [firstName] ------------------------

    @Size(max = 100)
    @Column(name = "FIRST_NAME", length = 100)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public User firstName(String firstName) {
        setFirstName(firstName);
        return this;
    }
    // -- [lastName] ------------------------

    @Size(max = 100)
    @Column(name = "LAST_NAME", length = 100)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User lastName(String lastName) {
        setLastName(lastName);
        return this;
    }
    // -- [creationDate] ------------------------

    @Column(name = "CREATION_DATE", length = 23)
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public User creationDate(LocalDateTime creationDate) {
        setCreationDate(creationDate);
        return this;
    }
    // -- [creationAuthor] ------------------------

    @Column(name = "CREATION_AUTHOR", length = 200)
    public String getCreationAuthor() {
        return creationAuthor;
    }

    public void setCreationAuthor(String creationAuthor) {
        this.creationAuthor = creationAuthor;
    }

    public User creationAuthor(String creationAuthor) {
        setCreationAuthor(creationAuthor);
        return this;
    }
    // -- [lastModificationDate] ------------------------

    @Column(name = "LAST_MODIFICATION_DATE", length = 23)
    public LocalDateTime getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(LocalDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public User lastModificationDate(LocalDateTime lastModificationDate) {
        setLastModificationDate(lastModificationDate);
        return this;
    }
    // -- [lastModificationAuthor] ------------------------

    @Column(name = "LAST_MODIFICATION_AUTHOR", length = 200)
    public String getLastModificationAuthor() {
        return lastModificationAuthor;
    }

    public void setLastModificationAuthor(String lastModificationAuthor) {
        this.lastModificationAuthor = lastModificationAuthor;
    }

    public User lastModificationAuthor(String lastModificationAuthor) {
        setLastModificationAuthor(lastModificationAuthor);
        return this;
    }
    // -- [version] ------------------------

    @Column(name = "VERSION", precision = 10)
    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public User version(Integer version) {
        setVersion(version);
        return this;
    }

    // -----------------------------------------------------------------
    // Many to Many
    // -----------------------------------------------------------------

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
    // many-to-many: user ==> roles
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 

    /**
     * Returns the {@link #roles} list.
     */
    @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID") , inverseJoinColumns = @JoinColumn(name = "ROLE_ID") )
    @ManyToMany
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Set the {@link #roles} list.
     *
     * @param roles the list of Role
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * Helper method to add the passed {@link Role} to the {@link #roles} list.
     */
    public boolean addRole(Role role) {
        return getRoles().add(role);
    }

    /**
     * Helper method to remove the passed {@link Role} from the {@link #roles} list.
     */
    public boolean removeRole(Role role) {
        return getRoles().remove(role);
    }

    /**
     * Helper method to determine if the passed {@link Role} is present in the {@link #roles} list.
     */
    public boolean containsRole(Role role) {
        return getRoles() != null && getRoles().contains(role);
    }

    /**
     * Apply the default values.
     */
    public User withDefaults() {
        setIsEnabled(true);
        setCivility(Civility.MR);
        return this;
    }

    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof User && hashCode() == other.hashCode());
    }

    private volatile int previousHashCode = 0;

    @Override
    public int hashCode() {
        int hashCode = Objects.hashCode(getLogin());

        if (previousHashCode != 0 && previousHashCode != hashCode) {
            log.warning("DEVELOPER: hashCode has changed!." //
                    + "If you encounter this message you should take the time to carefuly review equals/hashCode for: " //
                    + getClass().getCanonicalName());
        }

        previousHashCode = hashCode;
        return hashCode;
    }

    /**
     * Construct a readable string representation for this User instance.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this) //
                .add("id", getId()) //
                .add("login", getLogin()) //
                .add("password", "XXXX") //
                .add("email", getEmail()) //
                .add("isEnabled", getIsEnabled()) //
                .add("civility", getCivility()) //
                .add("firstName", getFirstName()) //
                .add("lastName", getLastName()) //
                .add("creationDate", getCreationDate()) //
                .add("creationAuthor", getCreationAuthor()) //
                .add("lastModificationDate", getLastModificationDate()) //
                .add("lastModificationAuthor", getLastModificationAuthor()) //
                .add("version", getVersion()) //
                .toString();
    }

    @PrePersist
    protected void prePersist() {
        if (AuditContextHolder.audit()) {
            setCreationAuthor(AuditContextHolder.username());
            setCreationDate(LocalDateTime.now());

        }
    }

    @PreUpdate
    protected void preUpdate() {
        if (AuditContextHolder.audit()) {
            setLastModificationAuthor(AuditContextHolder.username());
            setLastModificationDate(LocalDateTime.now());
        }
    }
}